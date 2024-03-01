package DataStructures.PriorityQueue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

// Heap is an array representation of complete binary tree(binary tree that is perfectly balanced except bottom level)
// root has index 1; parent can have only two children since it's a binary tree representation;
// if k is index of a parent, than children are k * 2 and k * 2 + 1. if k is a child, than parent is k / 2.
// insert - O(logN)
// poll - O(logN)
public class HeapPriorityQueue <Item> implements Iterable<Item> {

    public static void main(String[] args) {
        Random rand = new Random();
        HeapPriorityQueue<Integer> priorityQueue = new HeapPriorityQueue<>((o1, o2) -> -Integer.compare(o1, o2));
        for(int i = 0; i < 100; i++) {
            priorityQueue.insert(rand.nextInt(2, 100));
        }
        StringBuilder sb = new StringBuilder();
        for(int i : priorityQueue)
            sb.append(i).append(' ');
        System.out.println(sb);
    }

    private Item[] pq;
    private int n;
    private Comparator<Item> comparator;

    public HeapPriorityQueue() {
        pq = (Item[]) new Object[1];
        n = 0;
    }

    public HeapPriorityQueue(Comparator<Item> comparator) {
        this.comparator = comparator;
        pq = (Item[]) new Object[1];
        n = 0;
    }



    public void insert(Item item) {
        if (n == pq.length-1) resize(pq.length*2);
        pq[++n] = item; // insert element
        swim(n); // and put it into the right position
    }

    public Item poll() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Item ret = pq[1];
        swap(1, n--);
        sink(1);
        pq[n+1] = null;
        if (!isEmpty() && n == (pq.length-1)/4) resize(pq.length/2);
        return ret;
    }

    public Item max() {
        return pq[1];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[])new Object[capacity];
        for(int i = 1; i <= n; i++)
            copy[i] = pq[i];
        pq = copy;
    }


    private void sink(int p) { // puts parent smaller than both of his children into the right position
        while (2*p <= n) { // while there is at least one child
            int c = 2*p;
            if (c < n && isGreater(c+1, c)) c++; // picking up greater child
            if (!isGreater(c, p)) break; // parent is already in place
            swap(p, c);
            p = c;
        }
    }

    private void swim(int k) { // puts child larger than its parent into the right position
        while (k > 1 && isGreater(k, k/2)) { // while k is not root and bigger than its parent
            swap(k/2, k);
            k /= 2;
        }
    }

    private boolean isGreater(int a, int b) {
        if (comparator != null)
            return comparator.compare(pq[a], pq[b]) > 0;
        else
            return ((Comparable<Item>)pq[a]).compareTo(pq[b]) > 0;
    }

    private void swap(int a, int b) {
        Item temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private HeapPriorityQueue<Item> copy;
            {
                if (comparator != null) copy = new HeapPriorityQueue<>(comparator);
                else copy = new HeapPriorityQueue<>();
                for(int i = 1; i <= n; i++)
                    copy.insert(pq[i]);
            }

            @Override
            public boolean hasNext() {
                return !copy.isEmpty();
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                return copy.poll();
            }
        };
    }
}
