package Algorithms;

public class BinarySearch {
    public static <T extends Comparable<T>> int search(T[] sorted,  T num, int l, int r) {
        while (r >= l) {
            int mid = l + (r-l)/2;
            if (num.compareTo(sorted[mid]) > 0) l = mid+1;
            else if (num.compareTo(sorted[mid]) < 0) r = mid-1;
            else return mid;
        }
        return -1;
    }
    public static <T extends Comparable<T>> int search(T[] sorted,  T num) {
        return search(sorted, num, 0, sorted.length-1);
    }


}
