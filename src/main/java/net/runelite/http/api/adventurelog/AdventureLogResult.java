package net.runelite.http.api.adventurelog;

import java.util.List;

public class AdventureLogResult {
    private List<AdventureLogItem> items;

    public AdventureLogResult(List<AdventureLogItem> items) {
        this.items = items;
    }

    public List<AdventureLogItem> getItems() {
        return this.items;
    }

    public void setItems(List<AdventureLogItem> items) {
        this.items = items;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AdventureLogResult)) return false;
        final AdventureLogResult other = (AdventureLogResult) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$items = this.getItems();
        final Object other$items = other.getItems();
        if (this$items == null ? other$items != null : !this$items.equals(other$items)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AdventureLogResult;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $items = this.getItems();
        result = result * PRIME + ($items == null ? 43 : $items.hashCode());
        return result;
    }

    public String toString() {
        return "AdventureLogResult(items=" + this.getItems() + ")";
    }
}
