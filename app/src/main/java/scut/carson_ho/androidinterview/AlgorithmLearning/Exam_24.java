package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/1.
 */

public class Exam_24 {

    /**
     * 设置结点结构
     */

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 反转链表
     * 解题思路：反转链表中节点的指针方向
     */

    public static ListNode reverseList(ListNode head) {

        // 1. 异常情况判断
        // a. 若链表头节点为空 返回空
        if (head == null) {
            System.out.println("链表头节点为空");
            return null;
        }

        // b. 若链表只有1个节点的情况，就返回头结点
        if (head.next == null) {
            System.out.println(head.val);
            return head;
        }

        // 2. 定义3个指针：当前节点、当前节点的前1节点、当前节点的后1节点
        ListNode pre = null;
        ListNode cur = head;
        ListNode post = head.next;

        // 3. 通过3个指针配合，反转链表节点的指针方向
        while (true) {

            // 翻转当前节点
            cur.next = pre;// 将 当前结点 的下1个指针设置为 前1节点

            // 继续翻转下1个节点
            pre = cur; // 将 前1节点 设置为当前结点
            cur = post;// 将 当前节点 设置为后1节点

            // 判断已反转到最后节点：后1节点是否为空
            if (post != null)
                post = post.next;// 将 后后1节点 设置为后1节点
            else {
                // 返回最后1个节点
                System.out.println(pre.val);
                return pre;
            }
        }
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        // 功能测试：输入链表有多个节点
        // 链表 = 1->2->3->4->5->6
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        reverseList(head);

        // 异常情况测试1：输入链表有1个节点
        // 链表 = 1
        ListNode head2 = new ListNode(1);
        reverseList(head2);

        // 异常情况测试2：输入链表为空
        ListNode head3 = null;
        reverseList(head3);

    }
}


