public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int lo = matrix[0][0];
        int hi = matrix[n-1][n-1];

        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int count = getLessEqual(matrix, mid);

            if (count < k) {
                lo = mid+1;
            }
            else {
                hi = mid-1;
            }
        }
        return lo;
    }

    private int getLessEqual(int[][] matrix, int val) {
        int n = matrix.length;
        int i = n-1;
        int j = 0;
        int res = 0;

        while(i >= 0 && j < n) {
            if (matrix[i][j] > val) {
                i--;
            }
            else {
                res += i+1;
                j++;
            }
        }
        return res;
    }
}
