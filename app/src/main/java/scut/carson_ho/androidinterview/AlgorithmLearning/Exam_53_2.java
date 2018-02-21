package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/20.
 */

public class Exam_53_2 {

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

            // 设中间元素的值 = m、下标 = i
            // a. 若m = i，该数字 即为所求
            if(data[mid] == mid)
                return mid;

            // b. 若m >  i，下一轮直接查找左边的数组（ 数组下标<i ）
            if(data[mid] > mid)
                right = mid -1;
                // c. 若m <  i，下一轮直接查找右边的数组（ 数组下标>i ）
            else
                left = mid +1;
        }
        // 无效输入时返回-1
        return -1;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试：数组中含数组 & 下标相等的数字、不含数组 & 下标相等的数字
        int[] data1 = new int[]{-1, 0, 1, 3, 10};
        int[] data2 = new int[]{-1, 0, 1, 2, 10};
        System.out.println("功能测试");
        System.out.println(getMissingNumber(data1));
        System.out.println(getMissingNumber(data2));

        // 边界值测试：数组中仅有1个数字、数组与下标相等的元素位于数组的开头 or 结尾
        int[] data3 = new int[]{3};
        int[] data4 = new int[]{0, 3, 4, 5, 10};
        int[] data5 = new int[]{-1, 0, 1, 2, 4};
        System.out.println("边界值测试");
        System.out.println(getMissingNumber(data3));
        System.out.println(getMissingNumber(data4));
        System.out.println(getMissingNumber(data5));

        // 特殊输入测试：数组为空
        System.out.println("特殊输入测试");
        System.out.println(getMissingNumber(null));

    }

}
