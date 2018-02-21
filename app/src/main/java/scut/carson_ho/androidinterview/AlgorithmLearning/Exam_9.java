package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Carson_Ho on 17/10/24.
 */

public class Exam_9 {

    /**
     * 测试用例
     */

    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();

        // 插入元素
        queue.push(1);
        queue.push(2);
        queue.push(3);

        // 删除元素
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());


        MyStack<String> stack = new MyStack<>();
        // 入栈
        stack.push("a");
        stack.push("b");
        stack.push("c");

        // 出栈
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());




    }
}

/**
 * 用2个栈实现一个队列
 */

class MyQueue<T>{

    // 1. 建立2个堆栈
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 插入元素结点
     * 在栈1中进行
     */

    public void push(int node){
        // 直接将元素结点插入到栈1
        stack1.push(node);
    }

    /**
     * 删除元素结点
     * 在栈2中进行
     * @return
     */
    public int pop(){

        // 判断栈2是否为空（是否有结点）

        // a. 若栈2、栈1均为空
        // 则抛出异常
        if(stack2.isEmpty() && stack1.isEmpty()){

            try {
                throw new Exception("队列中没元素啦");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // b. 若栈2为空、栈1不为空
        // 则将栈1中的结点依次弹出 & 压入到栈2，最终直接弹出栈2元素
        if(stack2.isEmpty()){

            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());

            }
        }

        // c. 若栈2不为空，则直接弹出
        return stack2.pop();
    }

}

/**
 * 用两个队列实现一个栈
 */

class MyStack<E>{

    // 1. 定义2个队列
    Queue<E> queue1 = new LinkedList<>();
    Queue<E> queue2 = new LinkedList<>();


    // 2. 入栈
    // 使用队列1
    public void push(E n){
        // 直接插入到队列1
        queue1.add(n);
    }

    // 3. 出栈
    // 使用队列2
    public E pop(){

        // a. 若队列1 & 2均为空，则抛出异常
        if(queue1.isEmpty() && queue2.isEmpty())
            try {
                throw new Exception("栈中没元素啦");
            } catch (Exception e) {
                e.printStackTrace();
            }

        // b. 若队列1不为空
        // 将队列1的元素（除最后1个）依次出列 & 插入到队列2
        if(!queue1.isEmpty()){

            while(queue1.size()>1){
                queue2.add(queue1.remove());

            }
            // 返回队列1中的最后1个元素，即需删除的元素
            return queue1.remove();
        }

        // c. 若队列1为空时
        // 将队列2的元素（除最后1个）依次出列 & 插入到队列1
        while(queue2.size()>1){
            queue1.add(queue2.remove());
        }
        // 返回队列2中的最后1个元素，即需删除的元素
        return queue2.remove();

    }
}


