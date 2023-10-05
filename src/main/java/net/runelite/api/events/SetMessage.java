package net.runelite.api.events;

import net.runelite.api.ChatMessageType;
import net.runelite.api.MessageNode;

/**
 * An event where a {@link MessageNode} has had its message set.
 * <p>
 * Called whenever a message is received in the chat box.
 * <p>
 * Editing the {@link #messageNode} properties will reflect the change when
 * the message in the chat is rendered. The original values of the message
 * are held by the other fields of this event.
 */
public class SetMessage
{
	/**
	 * The updated message node.
	 */
	private MessageNode messageNode;
	/**
	 * The set message type.
	 */
	private ChatMessageType type;
	/**
	 * The name of the player that sent the message.
	 */
	private String name;
	/**
	 * The sender of the message (ie. clan name).
	 */
	private String sender;
	/**
	 * The new message value.
	 */
	private String value;

    public SetMessage() {
    }

    public MessageNode getMessageNode() {
        return this.messageNode;
    }

    public ChatMessageType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getSender() {
        return this.sender;
    }

    public String getValue() {
        return this.value;
    }

    public void setMessageNode(MessageNode messageNode) {
        this.messageNode = messageNode;
    }

    public void setType(ChatMessageType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SetMessage)) return false;
        final SetMessage other = (SetMessage) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$messageNode = this.getMessageNode();
        final Object other$messageNode = other.getMessageNode();
        if (this$messageNode == null ? other$messageNode != null : !this$messageNode.equals(other$messageNode))
            return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$sender = this.getSender();
        final Object other$sender = other.getSender();
        if (this$sender == null ? other$sender != null : !this$sender.equals(other$sender)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SetMessage;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $messageNode = this.getMessageNode();
        result = result * PRIME + ($messageNode == null ? 43 : $messageNode.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $sender = this.getSender();
        result = result * PRIME + ($sender == null ? 43 : $sender.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public String toString() {
        return "SetMessage(messageNode=" + this.getMessageNode() + ", type=" + this.getType() + ", name=" + this.getName() + ", sender=" + this.getSender() + ", value=" + this.getValue() + ")";
    }
}
