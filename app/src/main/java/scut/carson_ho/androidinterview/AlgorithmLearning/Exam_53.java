package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/20.
 */

public class Exam_53 {

    /**
     * 解题算法
     * @ param array 排序数组
     * @ param k 需统计的数字
     */
    public static int getNumberOfK(int [] array , int k) {

        // 检查输入数据的合法性
        if(array == null || array.length == 0)
            return 0;

        // 1. 使用 二分法 查找数组中的 第1个 m
        int indexOfFirstK = getFirstK(array, 0, array.length - 1, k);

        // 2. 使用 二分法 查找数组中的 最后1个 m
        int indexOfLastK = getLastK(array, 0, array.length - 1, k);

        if(indexOfFirstK == -1)
            return 0;

        // 3. 返回需统计数字的次数 = 最后1个的下标 — 前1个下标
        return indexOfLastK - indexOfFirstK + 1;
    }

    /**
     * 辅助算法：使用 二分法 查找数组中的 第1个 m
     */
    private static int getFirstK(int[] array, int low, int high, int k) {

        if(low > high)
            return -1;

        // 1. 找出中间元素
        int mid = low + (high - low) / 2;
        // 此处需特别注意以下二者区别：
        // a. mid = （low + high) / 2
        // b. mid = low + (high - low ) / 2
        // 当low、high都是比较大的数时，完成同样的功能，(a)可能造成上溢，但(b)不会。

        // 通过 递归 方式找出元素
        // a.  若给定值 ＜ 中间记录，则 在中间记录的左半区（低半区） 继续查找
        if(k < array[mid]  )
            return getFirstK(array, low, mid - 1, k);

        // b.  若给定值 ＞ 中间记录，则 在中间记录的右半区 继续查找
        else if( k > array[mid] )
            return getFirstK(array, mid + 1, high, k);

        // c. 若给定值 = 中间记录，需判断该k是否是数组中的第一个k
        // 若中间记录 前1个数字 ≠ k，那么 中间记录 = 第1个k
        // 若中间记录 前1个数字 =  k，那么第1个k 在数组的左半段，下轮继续在数组的左半段查找
        else if(mid > 0 && array[mid - 1] == k)
            return getFirstK(array, low, mid - 1, k);
        else
            return mid;

    }

    /**
     * 辅助算法：使用 二分法 查找数组中的 最后1个 m
     */
    private static int getLastK(int[] array, int low, int high, int k) {

        // 1. 找出中间元素
        if(low > high)
            return -1;

        // 2. 找出中间元素
        int mid = low + (high - low) / 2;

        // a. 若给定值 ＜ 中间记录，则 在中间记录的左半区 继续查找
        if( k < array[mid]  )
            return getLastK(array, low, mid - 1, k);

        else if( k < array[mid]  )
            // b.  若给定值 ＞ 中间记录，则 在中间记录的右半区 继续查找
            return getLastK(array, mid + 1, high, k);

        // c. 若给定值 = 中间记录，需判断该k是否是数组中的最后一个k
        // 若中间记录 后1个数字 ≠ k，那么 中间记录 = 最后1个k
        // 若中间记录 后1个数字 =  k，那么第1个k 在数组的右半段，下轮继续在数组的右半段查找
        else if(mid < high && array[mid + 1] == k)
            return getLastK(array, mid + 1, high, k);
        else
            return mid;
    }


    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试：含查找的数字，出现1次 / 多次、无查找的数字
        int[] data1 = new int[]{1,2,3,4,5,6,7,8};
        int[] data2 = new int[]{1,2,3,3,3,3,5,6};
        System.out.println(getNumberOfK(data1,4));
        System.out.println(getNumberOfK(data2,3));

        // 特殊输入测试：null、输入的数组只有1个数字
        System.out.println(getNumberOfK(null,1));
        int[] data3 = new int[]{1};
        System.out.println(getNumberOfK(data3,1));
    }

}
