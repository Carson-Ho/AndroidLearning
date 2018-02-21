package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/25.
 */

public class Exam_13 {

    /**
     * 解题算法
     * 实现原理 = 递归
     * @param threshold = 临界值K
     * @param rows = 矩阵行数
     * @param cols = 矩阵列数
     * @return boolean = 矩阵中是否存在要寻找的字符串路径
     */
        public static int movingCount(int threshold, int rows, int cols) {
        // 定义1个矩阵，用于标识路径是否已进入每个格子
        // 0表示未访问、1表示访问过
        int flag[][] = new int[rows][cols];
        //通过计算到达格子次数
        return helper(0, 0, rows, cols, flag, threshold);
}

    /**
     * 辅助算法：计算到达格子次数 实现
     * 实现原理 = 递归
     */
        private static int helper(int i, int j, int rows, int cols, int[][] flag, int threshold) {
            // 1. 需判断机器人是否可以进入当前坐标
            // 判断条件 = 横纵坐标数位之和是否>k，小于则可以进入
        if (i < 0 || i >= rows || j < 0 || j >= cols || numSum(i) + numSum(j) > threshold || flag[i][j] == 1) return 0;
            // 若可进入，则标记该位置已被访问
        flag[i][j] = 1;
            // 通过递归，继续向4个方向走
        return (1+helper(i - 1, j, rows, cols, flag, threshold)
            + helper(i + 1, j, rows, cols, flag, threshold)
            + helper(i, j - 1, rows, cols, flag, threshold)
            + helper(i, j + 1, rows, cols, flag, threshold)
            );
        }

    /**
     * 辅助算法：计算横、纵坐标数位用于相加的值
     */
    public  static int numSum(int number){
        int sum=0;
        while (number>0){
            sum += number%10;
            number/=10;
        }
        return sum;
    }

    /**
     * 测试用例
     */

    public static void main(String[] args){
        // 测试1：3行4列、k = 0
        System.out.println(movingCount(0,3,4)); // 1

        // 测试2：3行4列、k = 1
        System.out.println(movingCount(1,3,4)); // 3

        // 测试3：2行20列、k = 9
        System.out.println(movingCount(9,2,20)); // 36
    }
}
