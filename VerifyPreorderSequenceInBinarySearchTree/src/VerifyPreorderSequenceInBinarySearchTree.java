public class VerifyPreorderSequenceInBinarySearchTree {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        return helper(preorder, 0, preorder.length-1);
    }

    public boolean helper(int[] preorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = preorder[start];
        int rightChild = -1;

        for (int i = start+1; i <= end; ++i) {
            if (rightChild == -1 && preorder[i] > root) {
                //find the first number that is greater than root, this is the right child of root
                rightChild = i;
            }
            if (rightChild != -1 && preorder[i] < root) {
                //if anything after rightChild is less than root, that means this element is misplaced in BST
                return false;
            }
        }

        if (rightChild == -1) {
            //this means root doesn't have a right child, repeat same process on its left child
            return helper(preorder, start+1, end);
        }
        else {
            //repeat same process on both left child and right child
            return helper(preorder, start+1, rightChild-1) && helper(preorder, rightChild, end);
        }
    }
}
