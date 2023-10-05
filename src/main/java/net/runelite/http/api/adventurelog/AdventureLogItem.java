package net.runelite.http.api.adventurelog;

public class AdventureLogItem {
    private String username;
    private String title;
    private String details;
    private String image;
    private long timestamp;

    public AdventureLogItem() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AdventureLogItem)) return false;
        final AdventureLogItem other = (AdventureLogItem) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$details = this.getDetails();
        final Object other$details = other.getDetails();
        if (this$details == null ? other$details != null : !this$details.equals(other$details)) return false;
        final Object this$image = this.getImage();
        final Object other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
        if (this.getTimestamp() != other.getTimestamp()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AdventureLogItem;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $details = this.getDetails();
        result = result * PRIME + ($details == null ? 43 : $details.hashCode());
        final Object $image = this.getImage();
        result = result * PRIME + ($image == null ? 43 : $image.hashCode());
        final long $timestamp = this.getTimestamp();
        result = result * PRIME + (int) ($timestamp >>> 32 ^ $timestamp);
        return result;
    }

    public String toString() {
        return "AdventureLogItem(username=" + this.getUsername() + ", title=" + this.getTitle() + ", details=" + this.getDetails() + ", image=" + this.getImage() + ", timestamp=" + this.getTimestamp() + ")";
    }

    public String getUsername() {
        return this.username;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDetails() {
        return this.details;
    }

    public String getImage() {
        return this.image;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
