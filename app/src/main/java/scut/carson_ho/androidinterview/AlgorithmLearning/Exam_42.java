package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/13.
 */

public class Exam_42 {

    /**
     * 解题算法：动态规划
     */
    public static int findGreatestSumOfSumArrays(int[] array){
        // 1. 判断输入数据的合法性
        if(array==null || array.length==0){
            System.out.println("输入的数组为空");
            return 0;
        }

        // 2. 定义所需变量
        int dp = array[0]; // dp[i]：以array[i]为结尾元素的连续数组的最大和(初始化为第1个元素)
        int maxdp = dp; // maxdp：当前dp的最大值

        // 3. 通过遍历数组进行动态规划
        for(int i=1;i < array.length;i++){

            // 3.1 当以 第（ i - 1） 个数字结尾的子数组中所有数字的和 > 0时，最大值 = 等于 二者的和
            if( dp>0 )
                dp += array[i];
            else
                // 3.2 当以 第（ i - 1） 个数字结尾的子数组中所有数字的和 < 0时，最大值 = 其本身
                dp = array[i];
            // 3.3 当前dp的最大值
            if(dp > maxdp)
                maxdp = dp;
        }
        return maxdp;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 功能测试：数组中无相同数字
        int[] array = {1,-2,3,10,-4,7,2,-5};
        System.out.println(findGreatestSumOfSumArrays(array));

        // 特殊输入测试：数组为空
        int[] array2 = null;
        System.out.println(findGreatestSumOfSumArrays(array2));


    }

}
