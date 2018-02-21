package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Carson_Ho on 17/11/11.
 */

public class Exam_40 {

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 解题算法1测试用例
        // 功能测试1：数组中无相同数字
        int[] data = {4,5,1,6,2,7,3,8};
        System.out.println(GetLeastNumbers_Solution(data,4));

        // 功能测试2：数组中有相同数字
        int[] data1 = {4,5,1,6,3,7,3,8};
        System.out.println(GetLeastNumbers_Solution(data1,4));

        // 特殊输入测试：数组为空
        int[] data2 = null;
        System.out.println(GetLeastNumbers_Solution(data2,4));

    }

    /**
     * 解题算法1：使用Partition算法实现
     */
//    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
//
//        // 1. 判断输入数据的合法性
//        if(input == null || input.length == 0 || k <= 0 || k > input.length) {
//            System.out.println("输入的数据不合法");
//            return new ArrayList<>();
//        }
//
//
//        ArrayList<Integer> result = new ArrayList<>(); // 该链表用于存储结果
//        int start = 0;
//        int end = input.length - 1;
//
//        // 2. 通过 partition函数 选择第k个数字 来调整数组中数字的顺序
//        // 使得 比第k个数字小 的数字都排在它左边、比 第k个数字大 的数字都排在它右边
//        int p = partition(input, start, end);
//
//        while(p != k - 1) {
//            // 2.1 若选中数字的下标 > k-1，那么下面接着在它的左边部分的数组中查找
//            if(p > k - 1)
//                end = p - 1;
//            // 2.2 若选中数字的下标 ＜ k-1，那么下面接着在它的右边部分的数组中查找
//            if(p < k - 1)
//                start = p + 1;
//            p = partition(input, start, end);
//        }
//
//        // 3. 直接选取数组的前k个数
//        for(int i = 0; i < k; ++i)
//            result.add(input[i]);
//        return result;
//    }
//
//    /**
//     * 辅助算法：Partition算法
//     */
//    private static int partition(int[] array, int low, int high) {
//        if(low >= high) return low;
//        int val = array[0];
//        int i = low;
//        int j = high + 1;
//        while(true) {
//            while(array[++i] < val) if(i == high) break;
//            while(array[--j] > val) if(j == low) break;
//            if(i >= j) break;
//            swap(array, i, j);
//        }
//        swap(array, low, j);
//        return j;
//    }
//
//    /**
//     * 辅助算法：交换位置
//     */
//    private static void swap(int[] array, int indexA, int indexB) {
//        int t = array[indexA];
//        array[indexA] = array[indexB];
//        array[indexB] = t;
//    }



    /**
     * 解题算法2：使用红黑树作为数据容器
     */

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {

        // 1. 判断输入数据的合法性
        if(input == null || input.length == 0 || k <= 0 || k > input.length) {
            System.out.println("输入的数据不合法");
            return new ArrayList<>();
        }

        // 2. 创建1树结构作为数据容器
        TreeSet<Integer> set = new TreeSet<>();
        for(int i : input) {

            // 2.1 从n个整数中选取前k个数字放入到容器里
            if(set.size() != k) {
                set.add(i);

            } else if(i < set.last()  ) {
                // 2. 若下1个读取的数字 < 数据容器中的最大值，则替换
                set.pollLast();
                set.add(i);
            }
        }
        return new ArrayList<>(set);
    }

}
