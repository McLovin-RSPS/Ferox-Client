package com.ferox.collection;

public class Node implements net.runelite.api.Node {

    public long key;
    public Node prev;
    public Node next;

    public final void remove() {
        if (next != null) {
            next.prev = prev;
            prev.next = next;
            prev = null;
            next = null;
        }
    }

    public final boolean hasPrevious() {
        if(next == null)
            return false;

        return true;

    }

    @Override
    public net.runelite.api.Node getNext() {
        return next;
    }

    @Override
    public net.runelite.api.Node getPrevious() {
        return prev;
    }

    @Override
    public long getHash() {
        return key;
    }
}
