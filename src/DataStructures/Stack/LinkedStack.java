package DataStructures.Stack;

import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item> {
    private Node first = null;

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
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
