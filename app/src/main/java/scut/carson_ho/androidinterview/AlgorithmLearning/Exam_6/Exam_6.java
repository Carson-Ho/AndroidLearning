package scut.carson_ho.androidinterview.AlgorithmLearning.Exam_6;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Carson_Ho on 17/10/20.
 */

public class Exam_6 {

    /**
     * 结点结构
     */
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

    }

    /**
     * 思路1：栈结构
     * @param head = 头结点
     */
    private static ArrayList<Integer> printListFromTailToHead1(ListNode head){

        // 定义1链表结构用于存储结果
        ArrayList<Integer> arrayList = new ArrayList<>();

        // 1. 判断头结点是否为空
        if(head == null)
            return arrayList;

        ListNode cur = head;

        // 2. 声明用于存放 & 输出的栈
        Stack<ListNode> stack = new Stack<>();

        // 3. 遍历链表（所有结点），每经过1个结点，就将该结点放入到栈中并指向下1个结点
        while(cur!=null){
            stack.push(cur);
            cur = cur.next;
        }

        // 4. 遍历链表完毕后，从栈顶开始输出结点的值(放入到1个链表当中)
        while(!stack.isEmpty()){
            arrayList.add(stack.pop().val);

        }

        return arrayList;

    }


    /**
     * 思路2：递归
     * @param listNode = 头结点
     */

    // 定义1链表结构用于存储结果(注：为全局变量)
    ArrayList<Integer> arrayList = new ArrayList<>();

    // 解题算法
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {

        if(listNode != null){
            // 遍历链表：每访问1个结点，先递归输出它后面的结点，再输出该结点本身
            printListFromTailToHead2(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 功能测试
        ListNode head = new ListNode(67);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(24);
        ListNode node4 = new ListNode(58);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(printListFromTailToHead1((head)));
    }


}
