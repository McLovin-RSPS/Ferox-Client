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
import java.util.Collections;
import java.util.List;

public class TableRow
{
	Color rowColor;
	TableAlignment rowAlignment;
	List<TableElement> elements = Collections.emptyList();

    TableRow(Color rowColor, TableAlignment rowAlignment, List<TableElement> elements) {
        this.rowColor = rowColor;
        this.rowAlignment = rowAlignment;
        this.elements = elements;
    }

    private static List<TableElement> $default$elements() {
        return Collections.emptyList();
    }

    public static TableRowBuilder builder() {
        return new TableRowBuilder();
    }

    public Color getRowColor() {
        return this.rowColor;
    }

    public TableAlignment getRowAlignment() {
        return this.rowAlignment;
    }

    public List<TableElement> getElements() {
        return this.elements;
    }

    public void setRowColor(Color rowColor) {
        this.rowColor = rowColor;
    }

    public void setRowAlignment(TableAlignment rowAlignment) {
        this.rowAlignment = rowAlignment;
    }

    public void setElements(List<TableElement> elements) {
        this.elements = elements;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TableRow)) return false;
        final TableRow other = (TableRow) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$rowColor = this.getRowColor();
        final Object other$rowColor = other.getRowColor();
        if (this$rowColor == null ? other$rowColor != null : !this$rowColor.equals(other$rowColor)) return false;
        final Object this$rowAlignment = this.getRowAlignment();
        final Object other$rowAlignment = other.getRowAlignment();
        if (this$rowAlignment == null ? other$rowAlignment != null : !this$rowAlignment.equals(other$rowAlignment))
            return false;
        final Object this$elements = this.getElements();
        final Object other$elements = other.getElements();
        if (this$elements == null ? other$elements != null : !this$elements.equals(other$elements)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TableRow;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $rowColor = this.getRowColor();
        result = result * PRIME + ($rowColor == null ? 43 : $rowColor.hashCode());
        final Object $rowAlignment = this.getRowAlignment();
        result = result * PRIME + ($rowAlignment == null ? 43 : $rowAlignment.hashCode());
        final Object $elements = this.getElements();
        result = result * PRIME + ($elements == null ? 43 : $elements.hashCode());
        return result;
    }

    public String toString() {
        return "TableRow(rowColor=" + this.getRowColor() + ", rowAlignment=" + this.getRowAlignment() + ", elements=" + this.getElements() + ")";
    }

    public static class TableRowBuilder {
        private Color rowColor;
        private TableAlignment rowAlignment;
        private List<TableElement> elements$value;
        private boolean elements$set;

        TableRowBuilder() {
        }

        public TableRowBuilder rowColor(Color rowColor) {
            this.rowColor = rowColor;
            return this;
        }

        public TableRowBuilder rowAlignment(TableAlignment rowAlignment) {
            this.rowAlignment = rowAlignment;
            return this;
        }

        public TableRowBuilder elements(List<TableElement> elements) {
            this.elements$value = elements;
            this.elements$set = true;
            return this;
        }

        public TableRow build() {
            List<TableElement> elements$value = this.elements$value;
            if (!this.elements$set) {
                elements$value = TableRow.$default$elements();
            }
            return new TableRow(rowColor, rowAlignment, elements$value);
        }

        public String toString() {
            return "TableRow.TableRowBuilder(rowColor=" + this.rowColor + ", rowAlignment=" + this.rowAlignment + ", elements$value=" + this.elements$value + ")";
        }
    }
}
