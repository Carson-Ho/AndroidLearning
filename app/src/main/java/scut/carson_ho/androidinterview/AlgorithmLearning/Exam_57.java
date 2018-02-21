package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.ArrayList;

/**
 * Created by Carson_Ho on 17/11/24.
 */

public class Exam_57 {

    /**
     * 解题算法
     */
    private static ArrayList<Integer> findTwoNumberWithSum(int[] array, int sum){

        ArrayList<Integer> result = new ArrayList<Integer>(); // 定义1个链表存储结果

        // 判断输入数据的合法性
        if (array == null || array.length < 2){
            System.out.println("输入的数据不合法");
            return result;
        }

        // 1. 定义2个指针
        // p1 = 指向数组的头数据元素
        // p2 = 指向数组的尾数据元素
        int start = 0;
        int end = array.length - 1;

        // 2. 计算2个指针指向元素的和（m）
        // 循环条件
        while (start < end){

            int curSum = array[start] + array[end];

            // 若m = 题目输入的s ，即2个指针指向的数据元素即为所求
            if (curSum == sum){
                result.add(array[start]);
                result.add(array[end]);
                return result;

                // 若m > 题目输入的s，指针p2前移1位
            } else if (curSum < sum) {
                start++;
                // 若m < 题目输入的s，指针p1后移1位
            }else {
                end--;
            }
        }
        return result;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) throws Exception {
        // 功能测试1：存在和为s的2个数
        int[] array1 = {1, 2, 4, 7, 11, 15};
        System.out.println(findTwoNumberWithSum(array1,15));

        // 功能测试2：不存在和为s的2个数
        int[] array2 = {1, 2, 3, 7, 11, 15};
        System.out.println(findTwoNumberWithSum(array2,15));

        // 特殊输入测试：数组为空
        System.out.println(findTwoNumberWithSum(null,15));

    }

}
