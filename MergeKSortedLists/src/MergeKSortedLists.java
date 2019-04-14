class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length-1);
    }

    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start >= end) {
            return lists[start];
        }
        int mid = start + (end-start)/2;
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid+1, end);
        return mergeTwoLists(left, right);
    }

    private ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while(a != null && b != null) {
            if (a.val < b.val) {
                cur.next = new ListNode(a.val);
                a = a.next;
            }
            else {
                cur.next = new ListNode(b.val);
                b = b.next;
            }
            cur = cur.next;
        }

        while(a != null) {
            cur.next = new ListNode(a.val);
            a = a.next;
            cur = cur.next;
        }

        while(b != null) {
            cur.next = new ListNode(b.val);
            b = b.next;
            cur = cur.next;
        }
        return dummy.next;
    }
}
