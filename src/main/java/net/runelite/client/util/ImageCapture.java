/*
 * Copyright (c) 2018, Lotto <https://github.com/devLotto>
 * Copyright (c) 2019, Alexsuperfly <https://github.com/Alexsuperfly>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.util;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.WorldType;
import net.runelite.client.Notifier;
import okhttp3.*;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.EnumSet;

import static net.runelite.client.RuneLite.SCREENSHOT_DIR;

@Singleton
public class ImageCapture
{
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private static final HttpUrl IMGUR_IMAGE_UPLOAD_URL = HttpUrl.parse("https://api.imgur.com/3/image");
    private static final MediaType JSON = MediaType.parse("application/json");
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImageCapture.class);

    private final Client client;
    private final Notifier notifier;
    private final OkHttpClient okHttpClient;
    private final Gson gson;
    private final String imgurClientId;

    @Inject
    private ImageCapture(
            final Client client,
            final Notifier notifier,
            final OkHttpClient okHttpClient,
            final Gson gson
    )
    {
        this.client = client;
        this.notifier = notifier;
        this.okHttpClient = okHttpClient;
        this.gson = gson;
        this.imgurClientId = "30d71e5f6860809";
    }

    /**
     * Saves a screenshot of the client window to the screenshot folder as a PNG,
     * and optionally uploads it to an image-hosting service.
     *
     * @param screenshot BufferedImage to capture.
     * @param fileName Filename to use, without file extension.
     * @param subDir Directory within the player screenshots dir to store the captured screenshot to.
     * @param notify Send a notification to the system tray when the image is captured.
     * @param imageUploadStyle which method to use to upload the screenshot (Imgur or directly to clipboard).
     */
    public void takeScreenshot(BufferedImage screenshot, String fileName, @Nullable String subDir, boolean notify, ImageUploadStyle imageUploadStyle)
    {
        System.out.println("takeScreenshot");
        if (client.getGameState() == GameState.LOGIN_SCREEN)
        {
            // Prevent the screenshot from being captured
            System.out.println("Login screenshot prevented");
            return;
        }

        File playerFolder;
        if (client.getLocalPlayer() != null && client.getLocalPlayer().getName() != null)
        {

            String playerDir = client.getLocalPlayer().getName();

            if (!Strings.isNullOrEmpty(subDir))
            {
                playerDir += File.separator + subDir;
            }

            playerFolder = new File(SCREENSHOT_DIR, playerDir);
        }
        else
        {
            playerFolder = SCREENSHOT_DIR;
        }

        playerFolder.mkdirs();

        fileName += (fileName.isEmpty() ? "" : " ") + format(new Date());

        try
        {
            File screenshotFile = new File(playerFolder, fileName + ".png");

            // To make sure that screenshots don't get overwritten, check if file exists,
            // and if it does create file with same name and suffix.
            int i = 1;
            while (screenshotFile.exists())
            {
                screenshotFile = new File(playerFolder, fileName + String.format("(%d)", i++) + ".png");
            }

            ImageIO.write(screenshot, "PNG", screenshotFile);

            if (imageUploadStyle == ImageUploadStyle.IMGUR)
            {
                uploadScreenshot(screenshotFile, notify);
            }
            else if (imageUploadStyle == ImageUploadStyle.CLIPBOARD)
            {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                TransferableBufferedImage transferableBufferedImage = new TransferableBufferedImage(screenshot);
                clipboard.setContents(transferableBufferedImage, null);

                if (notify)
                {
                    notifier.notify("A screenshot was saved and inserted into your clipboard!", TrayIcon.MessageType.INFO);
                }
            }
            else if (notify)
            {
                notifier.notify("A screenshot was saved to " + screenshotFile, TrayIcon.MessageType.INFO);
            }
        }
        catch (IOException ex)
        {
            System.out.println("error writing screenshot");
            ex.printStackTrace();
        }
    }

    /**
     * Saves a screenshot of the client window to the screenshot folder as a PNG,
     * and optionally uploads it to an image-hosting service.
     *
     * @param screenshot BufferedImage to capture.
     * @param fileName Filename to use, without file extension.
     * @param notify Send a notification to the system tray when the image is captured.
     * @param imageUploadStyle which method to use to upload the screenshot (Imgur or directly to clipboard).
     */
    public void takeScreenshot(BufferedImage screenshot, String fileName, boolean notify, ImageUploadStyle imageUploadStyle)
    {
        takeScreenshot(screenshot, fileName, null, notify, imageUploadStyle);
    }

    /**
     * Uploads a screenshot to the Imgur image-hosting service,
     * and copies the image link to the clipboard.
     *
     * @param screenshotFile Image file to upload.
     * @throws IOException Thrown if the file cannot be read.
     */
    private void uploadScreenshot(File screenshotFile, boolean notify) throws IOException
    {
        String json = gson.toJson(new ImageUploadRequest(screenshotFile));

        Request request = new Request.Builder()
                .url(IMGUR_IMAGE_UPLOAD_URL)
                .addHeader("Authorization", "Client-ID " + imgurClientId)
                .post(RequestBody.create(JSON, json))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException ex)
            {
                log.warn("error uploading screenshot", ex);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try (InputStream in = response.body().byteStream())
                {
                    ImageUploadResponse imageUploadResponse =
                            gson.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), ImageUploadResponse.class);

                    if (imageUploadResponse.isSuccess())
                    {
                        String link = imageUploadResponse.getData().getLink();

                        StringSelection selection = new StringSelection(link);
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(selection, selection);

                        if (notify)
                        {
                            notifier.notify("A screenshot was uploaded and inserted into your clipboard!", TrayIcon.MessageType.INFO);
                        }
                    }
                }
            }
        });
    }

    private static String format(Date date)
    {
        synchronized (TIME_FORMAT)
        {
            return TIME_FORMAT.format(date);
        }
    }

    private static class ImageUploadResponse
    {
        private Data data;
        private boolean success;

        public ImageUploadResponse() {
        }

        public Data getData() {
            return this.data;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof ImageUploadResponse)) return false;
            final ImageUploadResponse other = (ImageUploadResponse) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$data = this.getData();
            final Object other$data = other.getData();
            if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
            if (this.isSuccess() != other.isSuccess()) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ImageUploadResponse;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $data = this.getData();
            result = result * PRIME + ($data == null ? 43 : $data.hashCode());
            result = result * PRIME + (this.isSuccess() ? 79 : 97);
            return result;
        }

        public String toString() {
            return "ImageCapture.ImageUploadResponse(data=" + this.getData() + ", success=" + this.isSuccess() + ")";
        }

        private static class Data
        {
            private String link;

            public Data() {
            }

            public String getLink() {
                return this.link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Data)) return false;
                final Data other = (Data) o;
                if (!other.canEqual((Object) this)) return false;
                final Object this$link = this.getLink();
                final Object other$link = other.getLink();
                if (this$link == null ? other$link != null : !this$link.equals(other$link)) return false;
                return true;
            }

            protected boolean canEqual(final Object other) {
                return other instanceof Data;
            }

            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                final Object $link = this.getLink();
                result = result * PRIME + ($link == null ? 43 : $link.hashCode());
                return result;
            }

            public String toString() {
                return "ImageCapture.ImageUploadResponse.Data(link=" + this.getLink() + ")";
            }
        }
    }

    private static class ImageUploadRequest
    {
        private final String image;
        private final String type;

        ImageUploadRequest(File imageFile) throws IOException
        {
            this.image = Base64.getEncoder().encodeToString(Files.readAllBytes(imageFile.toPath()));
            this.type = "base64";
        }

        public String getImage() {
            return this.image;
        }

        public String getType() {
            return this.type;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof ImageUploadRequest)) return false;
            final ImageUploadRequest other = (ImageUploadRequest) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$image = this.getImage();
            final Object other$image = other.getImage();
            if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
            final Object this$type = this.getType();
            final Object other$type = other.getType();
            if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ImageUploadRequest;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $image = this.getImage();
            result = result * PRIME + ($image == null ? 43 : $image.hashCode());
            final Object $type = this.getType();
            result = result * PRIME + ($type == null ? 43 : $type.hashCode());
            return result;
        }

        public String toString() {
            return "ImageCapture.ImageUploadRequest(image=" + this.getImage() + ", type=" + this.getType() + ")";
        }
    }
}