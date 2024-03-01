package DataStructures.Queue;

import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item>{

    private Node first, last;

    public Item peek() {
        return first.item;
    }

    public void enqueue (Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public Item dequeue () {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty () {
        return first == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node i = first;
            @Override
            public boolean hasNext() {
                return i != null;
            }

            @Override
            public Item next() {
                Item item = i.item;
                i = i.next;
                return item;
            }
        };
    }

    private class Node {
        Item item;
        Node next;
    }
}
