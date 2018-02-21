package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/7.
 */

public class Exam_35 {

    /**
     * 结点结构
     */
    public static class RandomListNode {
        int label;
        RandomListNode next;
        RandomListNode random;

        public RandomListNode(int label) {
            this.label = label;
        }

    }

    /**
     * 解题思路3
     * 核心思想：分为3步
     * 1. 复制原始链表的每个节点，将复制节点（N`）放在对应的原始节点（N）后1个位置，即 N->N`
     * 2. 设置复制节点（N`）的m_pSibling指针,即 S -> S`
     * 3. 将该复制的长链表拆分成2个链表
     */
    public static RandomListNode clone3(RandomListNode head) {

        // 判断输入节点是否为空
        if(head == null) {
            System.out.println("输入的头节点为空");
            return null;
        }

        // 将3步分别封装成3个函数逐步实现

        // 1.复制原始链表的每个节点，将复制节点（N`）放在对应的原始节点（N）后1个位置
        cloneNodes(head);

        // 2.设置复制节点（N`）的m_pSibling指针
        connectRandomNodes(head);

        // 3.将该复制的长链表拆分成2个链表
        return reconnectNodes(head);
    }

    /**
     * 步骤1：复制原始链表的每个节点，将复制节点（N`）放在对应的原始节点（N）后1个位置
     * 核心思想：即添加节点到链表
     */
    public static void cloneNodes(RandomListNode head){
        RandomListNode cur = head;
        RandomListNode temp = null;
        while (cur!=null){
            // 创建复制结点 & 插入
            temp = new RandomListNode(cur.label);
            temp.next = cur.next;
            cur.next = temp;

            cur = cur.next.next;
        }
    }

    /**
     * 步骤2：设置复制节点（N`）的m_pSibling指针
     * 核心思想：该指针指向的S`节点 = 原始节点（N） m_pSibling指针 指向的节点S的m_pNext指针指向的节点，即 S` = S的下1个结点：S -> S`
     */
    public static void connectRandomNodes(RandomListNode head){
        RandomListNode cur = head;
        RandomListNode curNext = head.next;

        while (true){
            if(cur.random!=null)
                curNext.random = cur.random.next;

            cur = cur.next.next;
            if(cur == null)
                break;
            curNext = curNext.next.next;
        }
    }

    /**
     * 步骤3：将该复制的长链表拆分成2个链表：
     * 核心思想：
     *  a. 原始链表：将奇数位置的节点用 m_pNext指针 连接起来
     *  b. 复制链表 = 将数偶位置的节点用 m_pNext指针 连接起来
     */
    public static RandomListNode reconnectNodes(RandomListNode head){

        RandomListNode cur = head; // 原始链表的头节点
        RandomListNode newHead = head.next;// 复制链表的头节点
        RandomListNode newCur = newHead;

        while (true){
            // 创建原始链表：将奇数位置的节点用m_pNext指针连接起来
            cur.next = cur.next.next;
            cur = cur.next;
            if(cur==null){
                newCur.next = null;
                break;
            }

            // 复制链表 = 将数偶位置的节点用 m_pNext指针 连接起来
            newCur.next = newCur.next.next;
            newCur = newCur.next;
        }
        return newHead;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试
        RandomListNode head = new RandomListNode(1);
        RandomListNode c2 = new RandomListNode(2);
        RandomListNode c3 = new RandomListNode(3);
        RandomListNode c4 = new RandomListNode(4);
        RandomListNode c5 = new RandomListNode(5);
        head.next = c2;
        head.random = c3;
        head.next.next = c3;
        head.next.random = c5;
        head.next.next.next = c4;
        head.next.next.next.next = c5;
        head.next.next.next.random = c2;

        System.out.print("原始链表如下");
        // 输出链表
        RandomListNode head1 = head;
        while (head1 != null) {
            System.out.print(head1.label);
            head1 = head1.next;
        }

        System.out.println(" ");
        System.out.print("复制链表如下");
        RandomListNode head2 = clone3(head);
        // 输出链表
        while (head2 != null) {
            System.out.print(head2.label);
            head2 = head2.next;
        }

        // 特殊输入测试
        System.out.println(" ");
        System.out.println("复制链表 ："+'\t'+clone3(null));
    }


}
