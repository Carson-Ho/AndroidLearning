package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Carson_Ho on 17/11/25.
 */

public class Exam_59_1 {

    /**
     * 测试用例
     */

    public static void main(String[] args) throws Exception {
        // 构造1队列 = { 1,5,4,6,1,2 }
        QueueWithMax queueWithMax1 = new QueueWithMax();
        queueWithMax1.pushBack(1);
        System.out.println("queueWithMax1最大值：" + queueWithMax1.getMax());
        queueWithMax1.pushBack(5);
        System.out.println("queueWithMax1最大值：" + queueWithMax1.getMax());
        queueWithMax1.pushBack(4);
        System.out.println("queueWithMax1最大值：" + queueWithMax1.getMax());
        queueWithMax1.pushBack(6);
        System.out.println("queueWithMax1最大值：" + queueWithMax1.getMax());
        queueWithMax1.pushBack(1);
        System.out.println("queueWithMax1最大值：" + queueWithMax1.getMax());
        queueWithMax1.pushBack(2);
        System.out.println("queueWithMax1最大值：" + queueWithMax1.getMax());
        queueWithMax1.popFront();
        queueWithMax1.popFront();
        queueWithMax1.popFront();
        System.out.println("queueWithMax1最大值：" + queueWithMax1.getMax());
        queueWithMax1.popFront();
        System.out.println("queueWithMax1最大值：" + queueWithMax1.getMax());
        queueWithMax1.popFront();
        System.out.println("queueWithMax1最大值：" + queueWithMax1.getMax());

    }

    /**
     * 解题类
     */
    static class QueueWithMax {

        private int currentIndex = 0;

        // 创建2个辅助队列
        private Deque<InterData> dateDeque = new LinkedList<>(); // 队列1：存放原始数据的原始队列
        private Deque<InterData> maxDeque = new LinkedList<>(); // 队列2：双端开口的队列，存储 可能成为 队列最大值 的数值

        /**
         * 入队，将数字加到队尾
         * @param number 入队数字
         */
        public void pushBack(int number) {


            // 队列2
            // a. 若入队的数m ≥ 队列队尾元素时：不断出队 队尾元素、直到新的队尾元素 > m 或 队列为空、将 m 入队 到队尾
            // b. 若入队的数m < 队列队尾元素时：则直接将m入队到队尾
            while (!maxDeque.isEmpty() && number >= maxDeque.peekLast().number) {
                maxDeque.pollLast();
            }

            // 注：将入队数m入队到队尾时，此处需用到1个辅助类，因题目要求 = 队列，不是数组，所以需创建1个有数值下标（index）的Data类
            InterData newData = new InterData(number, currentIndex);
            maxDeque.addLast(newData);

            // 队列1正常入队
            dateDeque.addLast(newData);

            ++ currentIndex;
        }

        /**
         * 出队，移出队头数字
         *
         * @return 出队数字
         * @throws EmptyQueueException 队列是空的时候会抛出这个异常
         */
        public int popFront() throws EmptyQueueException {

            if (maxDeque.isEmpty()) {
                throw new EmptyQueueException();
            }
            // 队列2
            // 若在队列1（原始队列）需出队的是最大元素，则队列2也需出队 队头元素（最大元素）
            if (maxDeque.getFirst().index == dateDeque.getFirst().index) {
                maxDeque.removeFirst();
            }
            // 队列1正常出队
            return dateDeque.removeFirst().number;
        }

        /**
         * 获取队列里面元素的最大值
         * 原理：根据队列2的特点，直接获取 存放队列2的队头元素 即可
         * @return 最大值
         * @throws EmptyQueueException 队列是空的时候会抛出这个异常
         */
        public int getMax() throws EmptyQueueException {
            if (maxDeque.isEmpty()) {
                throw new EmptyQueueException();
            }
            return maxDeque.peekFirst().number;
        }

        /**
         * 辅助类1：数据结构类
         * 因题目要求 = 队列，不是数组，所以需创建1个有数值下标（index）的Data类
         */
        private class InterData {
            public InterData(int number, int index) {
                this.number = number;
                this.index = index;
            }

            int number;
            int index;
        }
        /**
         * 辅助类2：抛出异常类
         * 因题目要求 = 队列，不是数组，所以需创建1个有数值下标（index）的Data类
         */
        private class EmptyQueueException extends Exception {
            public EmptyQueueException() {
                super("Queue is empty!");
            }
        }
    }
}
