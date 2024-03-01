package DataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int n;

    public Bag() {
        first = null;
        n = 0;
    }

    public void add(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public boolean isEmpty() { return n == 0; }

    public int size() { return n; }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> cur;

        public LinkedIterator(Node<Item> first) {
            cur = first;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = cur.item;
            cur = cur.next;
            return item;
        }
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
}
