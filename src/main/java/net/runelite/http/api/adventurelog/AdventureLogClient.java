package net.runelite.http.api.adventurelog;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import net.runelite.http.api.RuneLiteAPI;
import net.runelite.http.api.feed.FeedClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class AdventureLogClient {

        private static final Logger logger = LoggerFactory.getLogger(FeedClient.class);

        public List<AdventureLogItem> lookupFeed(String name) throws IOException
        {


            logger.debug("Built URI: {}", "https://Orbitz-rsps.com/runelite/adventurelog/log.php?name=" + name);

            Request request = new Request.Builder()
                    .url("https://Orbitz-rsps.com/runelite/adventurelog/log.php?name=" + name)
                    .build();

            try (Response response = RuneLiteAPI.CLIENT.newCall(request).execute())
            {
                if (!response.isSuccessful())
                {
                    logger.debug("Error looking up feed: {}", response.message());
                    return null;
                }

                InputStream in = response.body().byteStream();
                return RuneLiteAPI.GSON.fromJson(new InputStreamReader(in), new TypeToken<List<AdventureLogItem>>(){}.getType());
            }
            catch (JsonParseException ex)
            {
                throw new IOException(ex);
            }
        }
    }
