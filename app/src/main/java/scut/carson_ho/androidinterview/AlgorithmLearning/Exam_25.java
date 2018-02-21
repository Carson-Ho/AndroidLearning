package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/2.
 */

public class Exam_25 {
    
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
     * 解题算法：合并链表
     * 解题思路：比较指针结点大小
     * 注：鲁棒性
     */
    public static ListNode Merge(ListNode head1,ListNode head2){

        // 鲁棒性判断：
        // a. 二链表中，随意1个链表的头节点为空，合并结果 = 另外1个链表
        // b. 二链表的头节点同时为空，合并结果 = 1空链表
        if(head1 == null) {
            return head2;
        }

        if(head2 == null) {
            return head1;
        }

        // 1. 定义2指针，初始分别指向2链表的头节点
        // 第3个指针指向合并的新链表
        ListNode index1 = head1;
        ListNode index2 = head2;
        ListNode indexMerge = null;

        // 2. 比较2指针上的节点值，将小的值合并到新链表的尾节点后
        if(index1.val<index2.val) {
            indexMerge = index1;
            // 通过递归 继续合并链表中的剩余节点
            indexMerge.next = Merge(index1.next,index2);

        }
        else {
            indexMerge = index2;
            indexMerge.next = Merge(index1,index2.next);
        }

        return indexMerge;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        // 功能测试：输入链表有多个节点
        // 链表1 = 1->3->5->7
        ListNode head1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        ListNode node7 = new ListNode(7);
        head1.next = node3;
        node3.next = node5;
        node5.next = node7;

        // 链表2 = 2->4->6->8
        ListNode head2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node6 = new ListNode(6);
        ListNode node8 = new ListNode(8);

        head2.next = node4;
        node4.next = node6;
        node6.next = node8;

        ListNode newHead1 = Merge(head1,head2);
        // 输出链表
        while (newHead1 != null) {
            System.out.print(newHead1.val);
            newHead1 = newHead1.next;
        }

        // 异常情况测试1：二链表中，随意1个链表的头节点为空，合并结果 = 另外1个链表
        System.out.println("异常情况测试1：二链表中，随意1个链表的头节点为空，合并结果 = 另外1个链表");
        ListNode newHead2 = Merge(head1,null);
        while (newHead2 != null) {
            System.out.print(newHead2.val);
            newHead2 = newHead2.next;
        }

        System.out.println("异常情况测试1：二链表中，随意1个链表的头节点为空，合并结果 = 另外1个链表");
        ListNode newHead3 =  Merge(null,head2);
        while (newHead3 != null) {
            System.out.print(newHead3.val);
            newHead3 = newHead3.next;
        }

        // 异常情况测试2：二链表的头节点同时为空，合并结果 = 1空链表
        System.out.println("异常情况测试2：二链表的头节点同时为空，合并结果 = 1空链表");
        Merge(null,null);

    }

}
