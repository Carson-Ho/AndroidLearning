package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/10.
 */

public class Exam_39 {

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 解题算法1测试用例
//        // 功能测试1：数组中存在有1个数字出现的次数>数组长度的1半
//        int[] data = {1,2,3,2,2,2,5,4,2};
//        System.out.println(MoreThanHalfNum_Solution1(data));
//
//        // 功能测试2：数组中数字出现频率最高的次还未达到数组长度的1半
//        int[] data1 = {1,2,3,2,2,2,5,4,1};
//        System.out.println(MoreThanHalfNum_Solution1(data1));
//
//        // 特殊输入测试：数组为空
//        int[] data2 = null;
//        System.out.println(MoreThanHalfNum_Solution1(data2));

        // 解题算法2测试用例
        // 功能测试1：数组中存在有1个数字出现的次数>数组长度的1半
        int[] data = {1,2,3,2,2,2,5,4,2};
        System.out.println(MoreThanHalfNum_Solution2(data));

        // 功能测试2：数组中数字出现频率最高的次还未达到数组长度的1半
        int[] data1 = {1,2,3,2,2,2,5,4,1};
        System.out.println(MoreThanHalfNum_Solution2(data1));

        // 特殊输入测试：数组为空
        int[] data2 = null;
        System.out.println(MoreThanHalfNum_Solution2(data2));

        // 解题算法3测试用例
//        // 功能测试1：数组中存在有1个数字出现的次数>数组长度的1半
//        int[] data = {1,2,3,2,2,2,5,4,2};
//        System.out.println(MoreThanHalfNum_Solution(data));
//
//        // 功能测试2：数组中数字出现频率最高的次还未达到数组长度的1半
//        int[] data1 = {1,2,3,2,2,2,5,4,1};
//        System.out.println(MoreThanHalfNum_Solution(data1));
//
//        // 特殊输入测试：数组为空
//        int[] data2 = null;
//        System.out.println(MoreThanHalfNum_Solution(data2));
    }

    /**
     * 解题算法1：排序遍历
     * 核心思想：1. 排序数组（使用快排）、2. 遍历统计数字出现的次数
     */

//    public static int MoreThanHalfNum_Solution1(int [] array) {
//        // 1. 检查数据的合法性
//        if(array == null || array.length == 0) {
//            System.out.println("输入的数组为空");
//            return 0;
//        }
//        if(array.length == 1)
//            return array[0];
//
//        // 2. 使用快速排序 排序数组
//        quickSort(array);
//        int num = array.length / 2 + 1;
//        int number = array[0];
//        int count = 0;
//
//        // 3. 通过遍历，统计数字次数
//        for(int i : array) {
//            if(i == number) {
//                ++count;
//                if(count >= num)
//                    return i;
//            }
//            else {
//                number = array[i];
//                count = 1;
//            }
//        }
//        return 0;
//    }
//
//    private static void quickSort(int[] array) {
//        quickSort(array, 0, array.length - 1);
//    }
//
//    private static void quickSort(int[] array, int low, int high) {
//        if(low >= high)
//            return;
//        int p = partition(array, low, high);
//        quickSort(array, low, p - 1);
//        quickSort(array, p + 1, high);
//    }
//
//    private static int partition(int[] array, int low, int high) {
//        int val = array[low];
//        int i = low + 1;
//        int j = high;
//        while(true) {
//            while(i <= high && array[i] < val)
//                ++i;
//            while(j >= low && array[j] > val)
//                --j;
//            if(i > j)
//                break;
//            swap(array, i++, j--);
//        }
//        swap(array, low, j);
//        return j;
//    }
//
//    private static void swap(int[] array, int indexA, int indexB) {
//        int t = array[indexA];
//        array[indexA] = array[indexB];
//        array[indexB] = t;
//    }

    /**
     * 解题算法2：快速排序的分区函数（partition算法）
     * 核心思想： 若排序该数组，那么排序后 数组的中位数 = 出现次数超过数组长度1半的数字
     * 即，只需找出长度 = n的数组中，任意第（n/2）大的数字
     */
    public static int MoreThanHalfNum_Solution2(int [] array) {

        // 1. 检查数据的合法性
        if(array == null || array.length == 0) {
            System.out.println("输入的数组为空");
            return 0;
        }

        int midIndex = array.length / 2;
        int start = 0;
        int end = array.length - 1;

        // 2. 使用partition算法排序数组
        int p = partition(array, start, end);

        while(p != midIndex) {
            // 2.1 若选中数字的下标 > n /2，那么中位数位于其左边，下面接着在它的左边部分的数组中查找；
            if(p > midIndex)
                end = p - 1;
            // 2.2 若选中数字的下标 ＜ n /2，那么中位数位于其右边，下面接着在它的右边部分的数组中查找；
            else if(p < midIndex)
                start = p + 1;

            p = partition(array, start, end);
        }

        // 最终，考虑异常情况： 判断数组中数字出现频率最高的次数是否达到数组长度的1半
        if(checkMoreThanHalf(array, array[p]))
            return array[p];
        return 0;
    }

    /**
     * 辅助算法1：partition算法
     */
    private static int partition(int[] array, int low, int high) {
        int val = array[low];
        int i = low + 1;
        int j = high;
        while(true) {
            while(i <= high && array[i] < val)
                ++i;
            while(j >= low && array[j] > val)
                --j;
            if(i > j)
                break;
            swap(array, i++, j--);
        }
        swap(array, low, j);
        return j;
    }

    /**
     * 辅助算法2：交换位置
     */
    private static void swap(int[] array, int indexA, int indexB) {
        int t = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = t;
    }

    /**
     * 辅助算法3：考虑异常情况
     * 作用： 判断数组中数字出现频率最高的次数是否达到数组长度的1半
     */
    private static boolean checkMoreThanHalf(int[] array, int val) {
        int count = 0;
        for(int i : array) {
            if(i == val)
                ++count;
        }
        return count > array.length / 2;
    }
//
//
//
//    /**
//     * 解题算法3：根据数组规律
//     * 核心思想：根据数组特点，即若数组中有1个数字出现的次数>数组长度的1半，即说明它出现的次数>其他所有数字出现的次数和
//     */
//    public static int MoreThanHalfNum_Solution(int [] array) {
//
//        // 1. 检查输入数组的合法性
//        if(array == null || array.length == 0) {
//            System.out.println("输入的数组为空");
//            return 0;
//        }
//
//        // 2. 设置2个变量：均初始化为0
//        int val = 0; // 遍历的数组数字
//        int count = 0; // 该数字出现的次数
//
//        // 3. 遍历数组，遍历过程中根据以下规则更新2个变量
//        for(int i : array) {
//            System.out.println("val = " + val);
//            System.out.println("count = " + count);
//
//            // a. 若count减为0，更新value = 当前数字，并count设为1
//            if(count == 0) {
//                val = i;
//                count = 1;
//            }
//            // b. 若下1个数字 = value，那么count加1；
//            else if(val == i)
//                ++count;
//            // c. 若下1个数字 ≠ value，那么count减1；若count减为0，更新value = 当前数字，并count设为1
//            else
//                --count;
//        }
//
//        // 4. 最终，通过1辅助算法 判断数组中数字出现频率最高的次数是否达到数组长度的1半
//        if(checkMoreThanHalf(array, val)) {
//            return val;
//        }
//        else {
//            System.out.println("数组中数字出现频率最高的次还未达到数组长度的1半");
//            return 0;
//        }
//    }
//    /**
//     * 辅助算法
//     * 作用：判断数组中数字出现频率最高的次数是否达到了一半
//     */
//    private static boolean checkMoreThanHalf(int[] array, int val) {
//        int count = 0;
//        for(int i : array) {
//            if(i == val)
//                ++count;
//        }
//        return count > array.length / 2;
//    }
}
