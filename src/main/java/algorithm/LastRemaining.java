package algorithm;

public class LastRemaining {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) return -1;
        if (n == 1) return 0;
        ListNode tail = new ListNode(n - 1);
        ListNode head = tail;
        while (n - 1 > 0) {
            n--;
            ListNode node = new ListNode(n - 1);
            node.next = head;
            head = node;
        }
        tail.next = head;
        ListNode pre = tail;
        while (true) {
            if (head.next == head) return head.val;
            int num = m - 1;
            while (num > 0) {
                pre = head;
                head = head.next;
                num--;
            }
            pre.next = head.next;
            head = head.next;
        }
    }

    public static void main(String[] args) {
        LastRemaining_Solution(5, 3);
    }
}
