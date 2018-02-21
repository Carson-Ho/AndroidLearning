package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Carson_Ho on 17/11/24.
 */

public class Exam_59 {

    /**
     * 解题算法
     */
    public static ArrayList<Integer> findMaxInWindows(int[] num, int size){

        // 使用一个list存储每个滑动窗口的结果
        ArrayList<Integer> results = new ArrayList<>();

        // 判断输入数据的合法性
        if (num == null || size < 1 || num.length < size){
            System.out.println("输入数据不合法");
            return results;
        }

        // 创建1个双端队列，队列存储顺序：大 -> 小，即：
        // 队头 存储 较大的元素
        // 队尾 存储 较小的元素
        Deque<Integer> deque = new LinkedList<>();

        // 1. 当滑动窗口还没完全滑进数组时
        for (int i = 0; i < size; i++){
            // 若遍历的数m ≥ 队列队尾元素时
            // a. 不断出队 队尾元素、直到新的队尾元素 > m 或 队列为空
            // b. 将 m 入队 到队尾
            while (!deque.isEmpty() && num[i] >= num[deque.peekLast()]){
                deque.pollLast();
            }
            deque.addLast(i);
        }


        // 2. 当滑动窗口完全滑进数组时
        for (int i = size; i < num.length; i++){

            // 把队头元素做为结果
            results.add(num[deque.peekFirst()]);
            // a. 若遍历的数m ≥ 队列队尾元素时，不断出队 队尾元素、直到新的队尾元素 > m 或 队列为空
            while (!deque.isEmpty() && num[i] >= num[deque.peekLast()]){
                deque.pollLast();
            }
            // b. 必须 判断队头元素 是否存在于 滑动窗口中
            // 若1个数字的下标 与 当前处理数字的下标之差 ≥ 滑动窗口的大小
            // 即代表该数字不在滑动窗口内
            // 即可从队列中删除该数据元素
            while (!deque.isEmpty() && deque.peekFirst() <= i - size){
                deque.pollFirst();
            }
            // 3. 将 m 入队 到队尾
            deque.addLast(i);
        }
        // 把最后的滑动窗口元素队头元素输出为结果
        results.add(num[deque.peekFirst()]);
        return results;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) throws Exception {
        // 功能测试
        int[] array1 = {2, 3, 4, 2, 6, 2, 5, 1};
        int size1 = 3;
        System.out.println(findMaxInWindows(array1,size1));

        // 特殊输入测试
        System.out.println(findMaxInWindows(null,size1));

    }

}
