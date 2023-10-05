/*
 * Copyright (c) 2018, Lotto <https://github.com/devLotto>
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
package net.runelite.http.api.feed;

public class FeedItem
{
	private final FeedItemType type;
	private String avatar;
	private final String title;
	private final String content;
	private final String url;
	private final long timestamp;

    public FeedItem(FeedItemType type, String title, String content, String url, long timestamp) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.url = url;
        this.timestamp = timestamp;
    }

    public FeedItem(FeedItemType type, String avatar, String title, String content, String url, long timestamp) {
        this.type = type;
        this.avatar = avatar;
        this.title = title;
        this.content = content;
        this.url = url;
        this.timestamp = timestamp;
    }

    public FeedItemType getType() {
        return this.type;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getUrl() {
        return this.url;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FeedItem)) return false;
        final FeedItem other = (FeedItem) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$avatar = this.getAvatar();
        final Object other$avatar = other.getAvatar();
        if (this$avatar == null ? other$avatar != null : !this$avatar.equals(other$avatar)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        if (this.getTimestamp() != other.getTimestamp()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FeedItem;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $avatar = this.getAvatar();
        result = result * PRIME + ($avatar == null ? 43 : $avatar.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final long $timestamp = this.getTimestamp();
        result = result * PRIME + (int) ($timestamp >>> 32 ^ $timestamp);
        return result;
    }

    public String toString() {
        return "FeedItem(type=" + this.getType() + ", avatar=" + this.getAvatar() + ", title=" + this.getTitle() + ", content=" + this.getContent() + ", url=" + this.getUrl() + ", timestamp=" + this.getTimestamp() + ")";
    }
}
