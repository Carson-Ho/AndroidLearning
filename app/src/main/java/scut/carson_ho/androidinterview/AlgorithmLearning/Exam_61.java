package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.Arrays;

/**
 * Created by Carson_Ho on 17/11/29.
 */

public class Exam_61 {

    /**
     * 解题算法
     */
    public static boolean isContinuous(int[] numbers) {

        // 判断输入数据的合法性
        if (numbers == null || numbers.length != 5) {
            return false;
        }

        // 1. 对元素进行排序
        Arrays.sort(numbers);

        int numberOfZero = 0; // 代表数组中0的个数
        int numberOfGap = 0;// 代表数组中数字间的空缺个数

        // 2. 统计数组中0的个数
        for (int i = 0; i < numbers.length && numbers[i] == 0; i++) {
            numberOfZero++;
        }

        // 3. 统计数组中空缺总数
        int small = numberOfZero;
        int big = small + 1;

        while (big < numbers.length) {
            // 若2个数相同，即存在对子，则不可能是顺子
            if (numbers[small] == numbers[big] && numbers[small] != 0 ) {
                return false;
            }

            // 计算空缺总数
            numberOfGap += (numbers[big] - numbers[small] - 1);
            small = big;
            big++;
        }

        // 比较 数组中空缺总数 与 0的个数
        // 若 空缺总数 > 0的个数，则该5个数字不连续
        // 若 空缺总数 < 0的个数，则该5个数字连续
        return numberOfGap <= numberOfZero;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 功能测试1：连续、不存在大、小王
        System.out.println("功能测试");
        int[] numbers1 = {1, 3, 2, 5, 4};
        System.out.println(isContinuous(numbers1));

        // 功能测试2：不连续、不存在大、小王
        int[] numbers2 = {1, 3, 2, 6, 4};
        System.out.println(isContinuous(numbers2));

        // 功能测试2：存在大、小王
        int[] numbers3 = {1, 0, 2, 6, 4};
        System.out.println(isContinuous(numbers3));

        // 功能测试3：存在对子
        int[] numbers4 = {1, 3, 2, 2, 4};
        System.out.println(isContinuous(numbers4));

        // 特殊输入测试：输入为空指针
        System.out.println("特殊输入测试");
        System.out.println(isContinuous(null));

    }
}
