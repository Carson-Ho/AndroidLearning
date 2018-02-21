package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/25.
 */

public class Exam_11 {

    /**
     * 解题算法
     * 原理：二分查找，时间复杂度为O(logn)。
     * 注意对特殊情况的处理：特殊情况1 （旋转数组= 有序）、特殊情况2（两指针指向元素 与 中间元素 相等）
     * @param arr
     * @return
     */
    public static int minNumberInRotateArray(int[] arr){

        // 1. 设置2个指针的初始值
        int index1 = 0; // 第1个指针设置为：旋转数组的第1个元素 = 前子数组的第1个元素
        int index2 = arr.length-1; // 第2个指针设置为：旋转数组的最后1个元素 = 后子数组的最后1个元素

        // 2. 初始化中间元素值
        // 为了兼顾 特殊情况1（旋转数组= 有序），故将中间元素初始化为第1个元素
        int mid = 0;
        // 一旦发现数组第1个数字 < 最后1个数字，即说明旋转数组 = 有序
        // 则直接跳出循环，直接输出第1个数字
        while( arr[index1] >= arr[index2] ){
            // 若2个指针距离 = 1，即相邻时，则代表
              // a. 第1个指针指向的元素 = 前子数组的最后1个元素
              // b. 第2个指针指向的元素 = 后子数组的第1个元素,即，数组中最小的元素，此时直接输出第2个指针元素，并跳出循环
            if( index2 - index1 == 1 ){
                mid = index2;
                break;
            }

            // 若2个指针距离 ≠ 1，即开始解题算法
            // 3. 找出中间元素
            mid = index1 + (index2-index1) / 2;

            // 为了兼顾特殊情况2（两指针指向元素 与 中间元素 相等），则只能顺序查找
            if( arr[index1]==arr[index2] && arr[mid]==arr[index1] ){
                return inInOrder(arr,index1,index2);
            }

            // 4. 开始比较中间元素 与 指针元素 的大小
            if( arr[mid]>=arr[index1] ){
                index1 = mid;
            }else if( arr[mid]<=arr[index2] ){
                index2 = mid;
            }
        }

        // 最终返回中间元素，此时中间元素 = 第2个指针指向的元素
        return arr[mid];
    }

    /**
     * 辅助算法：顺序查找算法
     */
    public static int inInOrder(int[] arr, int index1, int index2) {
        int min = arr[index1];
        for(int i = index1+1;i<=index2;i++){
            if(min>arr[i])
                min = arr[i];
        }
        return min;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 输入旋转后的数组
        int[] src = new int[]{3,4,5,1,2};
        // 输出结果
        System.out.println(minNumberInRotateArray(src));
    }
}
