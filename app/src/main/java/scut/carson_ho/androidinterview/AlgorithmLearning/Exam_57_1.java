package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.ArrayList;

/**
 * Created by Carson_Ho on 17/11/24.
 */

public class Exam_57_1 {

    /**
     * 解题算法
     */
    private static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {

        ArrayList<ArrayList<Integer>> result =new ArrayList<ArrayList<Integer>>(); // 用于存储结果

        // 判断输入数据的合法性
        if (sum < 3){
            System.out.println("输入的数据不合法");
            return result;
        }

        // 1. 用2个指针分别指向当前序列的最小值 & 最大值
        // 指针1（small ）： 初始化指向1
        // 指针2（big ）： 初始化指向2
        int start = 1;
        int end = 2;
        int curSum = start + end;
        // 注：对于连续序列求和，考虑到每次操作前后的序列大部分数字相同，只是增加 / 减少1个数字
        // 故此处不采用循环求和，而是采用在前1个序列的基础上进行操作，从而减少不必要的运算，提高效率
        int mid = (sum + 1) / 2;

        // 2. 计算 指针1 ~ 指针2 之间数字的和（m）
        while (start < mid) {

            // 2.1 若m = 题目输入的s，即 指针1 ~ 指针2 之间数字 即为所求 = 1组解
            // 则：输出指针1 ~ 指针2 之间的数字序列、指针2 往后移1位、重复步骤2，继续求解
            if (curSum == sum) {

                // 求和
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                result.add(list);

            }

            // 2.2 若m > 题目输入的s
            // 则：指针p1后移1位、重复步骤2，直到指针1（small） > （s+1）/2  为止
            while (curSum > sum && start < mid) {
                curSum -= start;
                start++;

                if (curSum == sum) {
                    // 求和
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int i = start; i <= end; i++) {
                        list.add(i);
                    }
                    result.add(list);
                }
            }
            // 2.3 若m < 题目输入的s
            // 则，指针p2后移1位、指针1 ~ 指针2 之间数字多1个使得 m 变大 靠近 s，重复步骤2
            end++;
            curSum += end;
        }
        return result;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试
        System.out.println("功能测试");
        System.out.println(findContinuousSequence(15));

        // 边界值输入测试：连续序列最小和 = 3
        System.out.println("边界值测试");
        System.out.println(findContinuousSequence(3));
    }
}
