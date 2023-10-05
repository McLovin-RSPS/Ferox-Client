/*
 * Copyright (c) 2019, TheStonedTurtle <https://github.com/TheStonedTurtle>
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
package net.runelite.client.ui.overlay.components.table;

import java.awt.*;

public class TableElement
{
	TableAlignment alignment;
	Color color;
	String content;

    TableElement(TableAlignment alignment, Color color, String content) {
        this.alignment = alignment;
        this.color = color;
        this.content = content;
    }

    public static TableElementBuilder builder() {
        return new TableElementBuilder();
    }

    public TableAlignment getAlignment() {
        return this.alignment;
    }

    public Color getColor() {
        return this.color;
    }

    public String getContent() {
        return this.content;
    }

    public void setAlignment(TableAlignment alignment) {
        this.alignment = alignment;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TableElement)) return false;
        final TableElement other = (TableElement) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$alignment = this.getAlignment();
        final Object other$alignment = other.getAlignment();
        if (this$alignment == null ? other$alignment != null : !this$alignment.equals(other$alignment)) return false;
        final Object this$color = this.getColor();
        final Object other$color = other.getColor();
        if (this$color == null ? other$color != null : !this$color.equals(other$color)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TableElement;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $alignment = this.getAlignment();
        result = result * PRIME + ($alignment == null ? 43 : $alignment.hashCode());
        final Object $color = this.getColor();
        result = result * PRIME + ($color == null ? 43 : $color.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    public String toString() {
        return "TableElement(alignment=" + this.getAlignment() + ", color=" + this.getColor() + ", content=" + this.getContent() + ")";
    }

    public static class TableElementBuilder {
        private TableAlignment alignment;
        private Color color;
        private String content;

        TableElementBuilder() {
        }

        public TableElementBuilder alignment(TableAlignment alignment) {
            this.alignment = alignment;
            return this;
        }

        public TableElementBuilder color(Color color) {
            this.color = color;
            return this;
        }

        public TableElementBuilder content(String content) {
            this.content = content;
            return this;
        }

        public TableElement build() {
            return new TableElement(alignment, color, content);
        }

        public String toString() {
            return "TableElement.TableElementBuilder(alignment=" + this.alignment + ", color=" + this.color + ", content=" + this.content + ")";
        }
    }
}
