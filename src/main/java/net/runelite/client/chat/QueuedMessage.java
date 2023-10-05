/*
 * Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
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
package net.runelite.client.chat;

import net.runelite.api.ChatMessageType;

public class QueuedMessage
{
	private final ChatMessageType type;
	private final String value;
	private String name;
	private String sender;
	private String runeLiteFormattedMessage;

    QueuedMessage(ChatMessageType type, String value, String name, String sender, String runeLiteFormattedMessage) {
        this.type = type;
        this.value = value;
        this.name = name;
        this.sender = sender;
        this.runeLiteFormattedMessage = runeLiteFormattedMessage;
    }

    public static QueuedMessageBuilder builder() {
        return new QueuedMessageBuilder();
    }

    public ChatMessageType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public String getSender() {
        return this.sender;
    }

    public String getRuneLiteFormattedMessage() {
        return this.runeLiteFormattedMessage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRuneLiteFormattedMessage(String runeLiteFormattedMessage) {
        this.runeLiteFormattedMessage = runeLiteFormattedMessage;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof QueuedMessage)) return false;
        final QueuedMessage other = (QueuedMessage) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$sender = this.getSender();
        final Object other$sender = other.getSender();
        if (this$sender == null ? other$sender != null : !this$sender.equals(other$sender)) return false;
        final Object this$runeLiteFormattedMessage = this.getRuneLiteFormattedMessage();
        final Object other$runeLiteFormattedMessage = other.getRuneLiteFormattedMessage();
        if (this$runeLiteFormattedMessage == null ? other$runeLiteFormattedMessage != null : !this$runeLiteFormattedMessage.equals(other$runeLiteFormattedMessage))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof QueuedMessage;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $sender = this.getSender();
        result = result * PRIME + ($sender == null ? 43 : $sender.hashCode());
        final Object $runeLiteFormattedMessage = this.getRuneLiteFormattedMessage();
        result = result * PRIME + ($runeLiteFormattedMessage == null ? 43 : $runeLiteFormattedMessage.hashCode());
        return result;
    }

    public String toString() {
        return "QueuedMessage(type=" + this.getType() + ", value=" + this.getValue() + ", name=" + this.getName() + ", sender=" + this.getSender() + ", runeLiteFormattedMessage=" + this.getRuneLiteFormattedMessage() + ")";
    }

    public static class QueuedMessageBuilder {
        private ChatMessageType type;
        private String value;
        private String name;
        private String sender;
        private String runeLiteFormattedMessage;

        QueuedMessageBuilder() {
        }

        public QueuedMessageBuilder type(ChatMessageType type) {
            this.type = type;
            return this;
        }

        public QueuedMessageBuilder value(String value) {
            this.value = value;
            return this;
        }

        public QueuedMessageBuilder name(String name) {
            this.name = name;
            return this;
        }

        public QueuedMessageBuilder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public QueuedMessageBuilder runeLiteFormattedMessage(String runeLiteFormattedMessage) {
            this.runeLiteFormattedMessage = runeLiteFormattedMessage;
            return this;
        }

        public QueuedMessage build() {
            return new QueuedMessage(type, value, name, sender, runeLiteFormattedMessage);
        }

        public String toString() {
            return "QueuedMessage.QueuedMessageBuilder(type=" + this.type + ", value=" + this.value + ", name=" + this.name + ", sender=" + this.sender + ", runeLiteFormattedMessage=" + this.runeLiteFormattedMessage + ")";
        }
    }
}
