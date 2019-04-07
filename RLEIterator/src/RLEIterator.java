public class RLEIterator {
    int[] A;
    int index;

    public RLEIterator(int[] A) {
        this.A = A;
        index = 0;
    }

    public int next(int n) {
        //exhaust all the index to the closest to n
        while(index < A.length && A[index] < n) {
            n -= A[index];
            index += 2;
        }
        //if not all elements are exhausted
        if (index < A.length) {
            A[index] -= n;
            return A[index+1];
        }
        //if all elements are exhausted
        return -1;
    }
}
