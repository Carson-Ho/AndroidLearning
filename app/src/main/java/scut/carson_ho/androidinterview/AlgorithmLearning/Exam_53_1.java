package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/20.
 */

public class Exam_53_1 {

    /**
     * 解题算法
     */
    public static int getMissingNumber(int[] data){

        // 检查输入数据的合法性
        if(data == null || data.length == 0)
            return -1;

        int left = 0;
        int right = data.length-1;
        int mid;

        // 采用二分法
        while (left<=right){

            // 求中间元素
            mid = left+((right-left)>>1);
            // 注：
            // a. 用位运算替换原来的除法：mid=left+(right-left)/2
            // b. 加减法 优先级 > 位运算


            // 1.若中间元素值 ≠ 下标

            if(data[mid]!=mid) {
                // a. 前1元素 = 其下标，中间元素 = 第1个值与下标不相等的元素，即中间元素下标 = 数组中不存在的数字
                if (mid ==0 || data[mid-1]== mid-1)
                    return mid;
                // b. 前1元素 ≠ 其下标，那么下一轮 只需在左半边查找
                right = mid-1;

            }
            // 2. 若中间元素值 = 下标,那么下一轮 只需在右半边查找
            else
                left = mid + 1;

        }

        if (left == data.length)

        return left;

        // 无效输入时返回-1，即数组不按要求排序 / 有数字不在0—~n-1的范围内
        return -1;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试：缺失的数字在开始、中间 & 结尾
        int[] data1 = new int[]{1,2,3,4,5,6};
        int[] data2 = new int[]{0,1,3,4,5};
        int[] data3 = new int[]{0,1,2,3,4,5};
        System.out.println(getMissingNumber(data1));
        System.out.println(getMissingNumber(data2));
        System.out.println(getMissingNumber(data3));

        // 边界值测试：数组中只有1个数字 = 0
        int[] data4 = new int[]{0};
        System.out.println(getMissingNumber(data4));

        // 特殊输入测试：数组为空
        System.out.println(getMissingNumber(null));
    }

}
