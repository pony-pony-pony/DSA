package DataStructures.Stack;

import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;

    public FixedCapacityStack(int capacity) {
        s = (Item[]) new Object[capacity];
    }

    public void push(Item item) {
        s[N++] = item;
    }

    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int i = N;
            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public Item next() {
                return s[--i];
            }
        };
    }
}
