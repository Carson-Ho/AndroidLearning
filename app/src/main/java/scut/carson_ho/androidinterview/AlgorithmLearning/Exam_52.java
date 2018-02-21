package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/17.
 */


public class Exam_52 {

    /**
     * 链表节点结构
     */
    public static class ListNode {
        public int val;
        public ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 解题算法
     */
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 判断输入数据的合法性
        if(pHead1 == null || pHead2 == null)
            return null;
        int len1 = 0;
        int len2 = 0;
        ListNode cur1 = pHead1;
        ListNode cur2 = pHead2;

        // 1. 第1次遍历：遍历2个链表，获得2个链表的长度（即，m & n，设 m > n）
        for(ListNode cur = pHead1; cur != null; cur = cur.next)
            ++len1;
        for(ListNode cur = pHead2; cur != null; cur = cur.next)
            ++len2;

        // 2. 第2次遍历：在较长的链表先走（m-n）步，再同时在2个链表上遍历，找到的第1个相同节点即为2个链表的公共节点
         // 2.1 在较长的链表先走（m-n）步
        if(len1 > len2) {
            for(int i = 0; i < len1 - len2; ++i)
                cur1 = cur1.next;
        }
        else {
            for(int i = 0; i < len2 - len1; ++i)
                cur2 = cur2.next;
        }
         // 2.2 再同时在2个链表上遍历，找到的第1个相同节点即为2个链表的公共节点
        while(cur1 != null) {
            if(cur1 == cur2)
                return cur1;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return null;
    }

    /**
     * 测试用例
     */

    public static void main(String[] args){
        // 功能测试：二链表定义如下
        // 1->2->3->6->7
        //    4->5↗
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node6;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        ListNode commonNode = FindFirstCommonNode(node1,node4);
        System.out.println(commonNode.val);

        // 特殊输入测试：链表头节点 为空
        System.out.println(FindFirstCommonNode(null,null));
    }
}
