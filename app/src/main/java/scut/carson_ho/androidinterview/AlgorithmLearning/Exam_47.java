package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/15.
 */

public class Exam_47 {

    /**
     * 解题算法
     */
    public static int getMaxVaule(int[][] data){

        // 1. 检查输入数据的合法性
        if(data == null || data.length==0 || data[0].length==0)
            return 0;

        // 2. 使用1个辅助的2维数组（长度 = 棋盘 的列数n）
        // 由于第i行的值 仅与 第 i 行 & 第（i-1行）有关
        // 故 仅用数组中的2行用于缓存中间结果，，即data [2][] 即可完成状态的记录 & 更新
        int[][] dp = new int[2][data[0].length];
        
        int curRowIndex = 0; // 用于记录当前行
        int preRowIndex = 0; // 用于记录上1行

        // 通过 循环 实现递归
        for(int row=0;row<data.length;row++){

            curRowIndex = row & 1;
            preRowIndex = 1 - curRowIndex;

            for(int col=0 ; col<data[0].length ; col++){

                // 根据f (i , j ) = max [ f ( i-1, j )， f ( i ,  j -1 ) ]  + gift [ i , j ] 计算礼物的最大值
                if(col == 0)
                    dp[curRowIndex][col] = dp[preRowIndex][col]+data[row][col];

                else{
                    if(dp[preRowIndex][col] >= dp[curRowIndex][col-1])
                        dp[curRowIndex][col] = dp[preRowIndex][col]+data[row][col];
                    else
                        dp[curRowIndex][col] = dp[curRowIndex][col-1]+data[row][col];
                }
            }
        }

        return dp[(data.length-1)&1][data[0].length-1];
    }

    /**
     * 测试用例
     */

    public static void main(String[] args){
        // 功能测试
        int[][] data = {
                {1,10,3,8},
                {12,2,9,6},
                {5,7,4,11},
                {3,7,16,5}};
        System.out.println(getMaxVaule(data));

        // 只有1行
        int[][] data1 = {
                {1,10,3,8},
        };

        System.out.println(getMaxVaule(data1));

        // 只有1列
        int[][] data2 = {
                {1},
                {12},
                {5},
                {3},
        };
        System.out.println(getMaxVaule(data2));

        // 特殊输入测试
        System.out.println(getMaxVaule(null));
    }

}
