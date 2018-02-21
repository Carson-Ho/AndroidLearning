package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/1.
 */

public class Exam_23 {

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
     * 解题算法
     * 分为3个步骤：1.判断链表中是否有环；2.获得环中节点；3.找到环的入口节点
     */
    public static ListNode meetingNode(ListNode pHead) {

        // 判断输入头节点的合法性
        if(pHead == null) {
            System.out.println("头节点为空");
            return null;
        }

        /**
         * 步骤1：判断链表中是否有环
         */

        // 定义2个指针，同时从链表头节点出发
        ListNode fast = pHead;
        ListNode slow = pHead;

        // p1移动1步/次，p2移动2步/次
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 若2者相遇，则代表有环，跳出循环
            if (fast == slow) {
                System.out.println("链表中有环");
                break;
            }

        }

        // 若p2走到了链表的尾端依然未遇到p1，则代表链表中无环，返回空
        if (fast == null || fast.next == null) {
            System.out.println("链表中无环");
            return null;
        }

        /**
         * 步骤2：获得环中节点
         * a. 从上述2指针相遇的节点出发（该节点在环内）
         * b. 一直往前移动 & 计数
         * c. 当指针再次回到该相遇节点时，计数器即为环的数量
         */

        int nodesLoop =1 ; // 用于记录环节点的数量
        while(fast.next!= slow){
            fast = fast.next;
            ++ nodesLoop;
        }


        /**
         * 步骤3：找到环的入口节点
         * a. 定义2个指针p1、p2
         * b. 同时从链表头节点出发：p1先移动n步，p2不动（n= 环的节点数量）
         * c. 2指针同时往前移动，当2指针相遇时，相遇节点 = 环的入口节点
         * 此时，p2到达环的入口节点、p1也已经围绕环走了1圈到达环的入口节点
         */
        // 将2个指针p1、p2重新回到链表头节点出发
        fast = pHead;
        slow = pHead;

        // p1先移动n步（n= 环的节点数量，此处n=4）,p2不动
        for (int i = 0;i < nodesLoop;++i){
            fast = fast.next;
        }

        // 2指针同时往前移动
        // 当2指针相遇时，相遇节点 = 环的入口节点
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 创建链表：
        // 1->2->3->4->5->6
        //       ^        |
        //       |        |
        //       +--------+
        ListNode pHead = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        pHead.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;

        // 功能测试：倒数第1个节点（尾结点）、倒数第6个节点（头节点）、中间节点
        System.out.println(meetingNode(pHead).val);

        // 异常测试：链表中没环
        // 创建链表：
        // 1->2->3->4->5->6
//        ListNode pHead = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(6);
//        pHead.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//
//        System.out.println(meetingNode(pHead).val);


        // 异常测试：头节点为空
//        System.out.println(meetingNode(null).val);


    }
}
