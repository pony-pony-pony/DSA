package Algorithms;

import java.util.Arrays;
import java.util.Collections;



public class Sort {
    public static void main(String[] args) {
        System.out.println(13/5);



        Integer [] a = new Integer [] { 6, 5, 4, 1, 2, 3};
        heap(a);
        System.out.println(Arrays.toString(a));
        System.out.println(BinarySearch.search(a, 0, 4, 1));

    }

    // O(N^2) - always
    // elements from the left bound to i are sorted; on the i-iteration going from i to the
    // right bound to find an index of minimum number; that element is swapped with i-element
    // Briefly: we SELECT i-minimum element and put it in i-position
    public static <T extends Comparable<T>> void selection(T[] a, int l, int r) {
        for(int i = l; i < r; i++) {
            int min = i;
            for (int j = i+1; j <= r; j++)
                if (a[j].compareTo(a[min]) < 0) min = j;
            swap(a, i, min);
        }
    }


    // O(NlogN);  uses memory equal to the sorting array;
    // Let's say merging is a process of merging two sorted arrays.
    // In merge sort, first, we divide a given array in pairs of sub arrays of size 1 and
    // merge sub arrays in every pair. At this point we have sorted sub arrays of size 2.
    // Then we do the same thing but the size of sub arrays is 2. So, we increase the size by two each time.
    // The result of each iteration is a given array divided by sorted sub arrays of size = 2^i, where i is a
    // number of an iteration.
    // 1) Bottom-up Merge Sort:
    // In this approach we go from left bound to right with step=sizeOfSubArray*2 and merging
    // two sub arrays: from i to sizeOfSubArray and from sizeOfSubArray to i+step-1;
    // 2) Recursive Merge Sort:
    // Here we're recursively calling merge method: merge on first have, merge on second have,
    // then merging halves together
    public static void merge(Comparable[] a, int l, int r) {
        Comparable[] aux = new Comparable[r-l+1];
        merge(a, aux, l, r);
    }
    private static void merge(Comparable[] a, Comparable[] aux,  int l, int r) {
        // 1
        for (int sz = 1; sz < (r-l+1); sz *= 2) {
            for (int i = l; i <= r-sz; i += sz+sz) {
                merging(a, aux, i, i+sz-1, Math.min(i+sz+sz-1, r));
            }
        }
        // 2
//        if (r <= l) return;
//        int mid = l + (r-l)/2;
//        merge(a, aux, l, mid);
//        merge(a, aux, mid+1, r);
//        merging(a, aux, l, mid, r);
    }
    private static void merging(Comparable[] a, Comparable[] aux, int l, int mid, int r) {
        for(int i = l; i <= r; i++) {
            aux[i] = a[i];
        }

        int i = l, j = mid+1;
        for(int k = l; k <= r ; k++) {
            if (i > mid) a[k] = aux[j++]; // first subarray is already inserted
            else if (j > r) a[k] = aux[i++]; // second subarray is already inserted
            else if (aux[i].compareTo(aux[j]) > 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }


    // O(NlogN) - O(N^2/2). shuffle is needed to avoid a sorted array which is the worst case;
    // each call of quickSort() places the l-element in place the following way:
    // - element p is in the position when on the left there is no elements grater than p
    // and on the right there is no elements less than p;
    // - p = a[i]; it actually can be any element, p = a[i] is just the easiest way;
    // - first we increase i (i = l in the beginning) till we find element that is greater than p;
    // - then we decrease j (j = r-1 in th beginning) till we find element that is lower than p;
    // - these two elements are swapped;
    // - then we continue this process;
    // - when j = i-1 (j and i are crossed) all the elements are already swapped, so that p must be swapped
    // with the j-element and p is now in its position;
    // - then we call quickSort() for sub arrays of elements on the left and right from p.
    public static <T extends Comparable<T>> void quick(T[] a, int l, int r) {
        Collections.shuffle(Arrays.asList(a));
        quickSort(a, l, r);
    }
    private static <T extends Comparable<T>> void quickSort(T[] a, int l, int r) {
        if (r <= l) return;
        // partition
        int i = l, j = r+1;
        T p = a[i];
        while(true) {
            while (p.compareTo(a[++i]) > 0) // a[i] < p
                if (i == r) break;
            while (p.compareTo(a[--j]) < 0) // p < a[j]
                if (j == l) break;

            if (i >= j) break;
            swap(a, i, j);
        }
        // placing p in its position
        swap(a, l, j);

        // recursive calls for element on the left and right of partition element
        quickSort(a, l, j-1);
        quickSort(a, j+1, r);
    }

    // O(n^2)
    // 1 - is more popular; but 2nd one does not have extra calculation (r-i - inner loop in 1st);
    // 2: On the first i-iteration, all the elements starting from the last one and finishing at first
    // are sequentially compared with the j-1-element, j is an index of the current element
    // ( 5 with 4, 4 with 3, 3 with 2 ), on the second iteration, the first element is already in its place and
    // we do the same thing as in the first iteration. So, on the i-iteration there are i elements sorted
    // at the beginning of the array.
    // 1: same thing as 2, but sorted elements are at the end of array.
    // Briefly: minimum(2 implementation)/maximum(1 implementation) element
    // BUBBLES UP from the other end through array to the tail of sorted part of array.
    public static <T extends Comparable<T>> void bubble(T[] a, int l, int r) {
        int flag = 0; // swaps count

        // 1
//        for (int i = l; i <= r; i++) {
//            for (int j = l; j < r-i; j++) {
//                flag += ifSwap(a, j, j+1) ? 1 : 0;
//            }
//            if (flag == 0) break;
//        }

        // 2
        for (int i = l; i <= r; i++) {
            for(int j = r; j > i; j--) {
                flag += ifSwap(a, j-1, j) ? 1 : 0;
            }
            if (flag == 0) break;
        }
    }

    // insertion sorting algorithms

    // O(n^2)
    // going through array (on each iteration all the elements before i are sorted)
    // and placing i-element in its position by
    // swapping sequentially with all the elements before it (i-1, i-2 ...) that are bigger than i-element
    // Briefly: you take new element on each iteration and INSERT it in the sorted part
    public static <T extends Comparable<T>> void insertion(T[] a, int l, int r) {
        for (int i = l+1; i <= r; i++) {
            for (int j = i; j > l; j--) {
                if(!ifSwap(a, j-1, j)) break;
            }
        }
    }

    // O(n^2)
    // basically the same algorithm as insertion sort;
    // we do the same insertion sort but instead of going i-1, i-2... in inner loop
    // we go with step defined by h;
    // outer loop that defines gap sequence can be changed and
    // defines complexity of algorithm - this one uses Shell's sequence
    // Briefly: insertion sort performed on elements with step that gradually decreases to 1
    // (when step is 1 it's and insertions sort)
    public static <T extends Comparable<T>> void shellOriginal(T[] a, int l, int r) {
        for(int h = (r-l+1)/2; h > 0; h /= 2) {
            for (int i = l+h; i <= r; i++) {
            for (int j = i; j >= l+h; j -= h)  {
                    if (!ifSwap(a, j-h, j)) break;
                }
            }
        }
    }

    // O(n^(3/2)) ?
    // shell sort using Knuth's gap sequence
    public static <T extends Comparable<T>> void shellKnuth(T[] a, int l, int r) {
        int h = 1;
        while (h <= (r-l) / 3) // ? r-l+1
            h = h * 3 + 1;

        for (; h > 0; h = (h-1) / 3) {
            for (int i = l + h; i <= r; i++) {
                for (int j = i; j >= l + h; j -= h) {
                    if(!ifSwap(a, j-h, j)) break;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void heap(T[] heap) {
        int n = heap.length;
        for(int k = n/2; k >= 1; k--)
            sink(heap, k, n);
        while(n > 1) {
            swapForHeap(heap, 1, n--);
            sink(heap, 1, n);
        }
    }

    private static <T extends Comparable<T>> void sink(T[] heap, int k, int n) {
        while(2*k <= n) {
            int j = 2*k;
            if (j < n && isGreater(heap, j+1, j)) j++;
            if(!isGreater(heap, j, k)) break;
            swapForHeap(heap, k, j);
            k = j;
        }
    }
    private static <T extends Comparable<T>> boolean isGreater(T[] heap, int a, int b) {
        return heap[a-1].compareTo(heap[b-1]) > 0;
    }
    private static <T> void swapForHeap(T[] heap, int a, int b) {
        a--; b--;
        T temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    // helper methods
    private static <T> void swap(T[] array, int a, int b) {
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    private static <T extends Comparable<T>> boolean ifSwap(T[] array, int a, int b) {
        if (array[a].compareTo(array[b]) > 0) { // a > b
            swap(array, a, b);
            return true;
        }
        return false;
    }
}


