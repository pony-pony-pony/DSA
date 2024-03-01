package DataStructures.Queue;

import java.util.Iterator;

public class ResizingArrayQueue <Item> implements Iterable<Item> {
    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 8;
    private Item[] q;
    private int head, tail, n;

    public ResizingArrayQueue() {
        q = (Item[])new Object[INIT_CAPACITY];
        head = tail = n = 0;
    }

    public Item peek() {
        return q[head];
    }

    public void enqueue (Item item) {
        if (n == q.length) resize(q.length*2);
        q[tail++] = item;
        if (tail == q.length) tail = 0;
        n++;
    }

    public Item dequeue () {
        Item item = q[head];
        q[head++] = null;
        n--;
        if (head == q.length) head = 0;
        if (n > 0 && n == q.length/4) resize(q.length/2);
        return item;
    }

    public boolean isEmpty () {
        return n == 0;
    }


    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < n; i++)
            copy[i] = q[(i+head) % q.length];
        q = copy;
        tail = 0;
        head = n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                return i < n;
            }

            @Override
            public Item next() {
                Item item = q[(i+head)%q.length];
                i++;
                return item;
            }
        };
    }
}
