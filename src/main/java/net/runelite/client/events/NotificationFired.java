package net.runelite.client.events;

import java.awt.*;

public final class NotificationFired
{
    private final String message;
    private final TrayIcon.MessageType type;

    public NotificationFired(String message, TrayIcon.MessageType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public TrayIcon.MessageType getType() {
        return this.type;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NotificationFired)) return false;
        final NotificationFired other = (NotificationFired) o;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        return result;
    }

    public String toString() {
        return "NotificationFired(message=" + this.getMessage() + ", type=" + this.getType() + ")";
    }
}