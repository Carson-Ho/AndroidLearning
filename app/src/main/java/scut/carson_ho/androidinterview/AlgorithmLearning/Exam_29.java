package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.ArrayList;

/**
 * Created by Carson_Ho on 17/11/4.
 */

public class Exam_29 {

    /**
     * 解题算法：按照从外向里以顺时针的顺序依次打印每一个数字
     * 核心思路：通过1个循环打印矩阵，每次打印矩阵中的1个圈
     * @param matrix 输入的矩阵 (使用二维数组表示)
     */

    static ArrayList<Integer> result = new ArrayList<Integer> (); // 用于存放结果

    public  static ArrayList<Integer> printMatrixClockWisely(int[][] matrix) {

        // 1. 判断输入的矩阵的异常情况：为空、仅有1行、仅有1列
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            System.out.println("出现异常情况：输入的矩阵为空 / 仅有1行 / 仅有1列");
            return null;
        }

        // 记录第1个圈开始位置的行 & 横坐标
        int x = 0;
        // 记录一圈开始位置的列 & 纵坐标
        int y = 0;

        // 解决子问题1：循环结束条件：行数 ＞ 矩阵最后1圈左上角横坐标的2倍、列数 ＞ 矩阵最后1圈左上角纵坐标的2倍
        // 矩阵的列数 = matrix.length、列号最大 = (matrix.length-1)/2
        // 矩阵的行数 = matrix[0].length、行号最大 = (matrix[0].length-1)/2
        while (matrix.length > (x * 2)   &&  matrix[0].length > (y * 2) ) {

            // 解决子问题2：按规律 打印矩阵的每个圈（分为4步，但不是每步都必须）
            printMatrixInCircle(matrix, x, y);

            // 指向下一个要处理的圈的左上角位置
            x++;
            y++;
        }
        return result;

    }

    /**
     * 辅助算法：解决子问题2，即 按规律 打印矩阵的每个圈（分为4步，但不是每步都必须）
     */

    public  static void printMatrixInCircle(int[][] matrix, int x, int y) {

        int rows = matrix.length; // 数组的行数 = 矩阵行数
        int cols = matrix[0].length; // 数组的列数 = 矩阵列数

        int endX = cols - y - 1;// 终止列号
        int endY = rows - x - 1;// 终止行号


        // 第1步：从左 -> 右 打印1行
        // 前置条件：必需
        for (int i = y; i <= endX; i++) {
            System.out.print(matrix[x][i] + " ");
            result.add(matrix[x][i]);
        }

        // 第2步：2. 从上 -> 下 打印1列
        // 前置条件：终止行号 > 起始行号
        if (endY > x) {
            // 因为右边那一列的最上面那一个已经被输出了，所以行从起始行的第2行（x+1）开始，
            // 输出包括右边那列的最下面那个
            for (int i = x + 1; i <= endY; i++) {
                System.out.print(matrix[i][endX] + " ");
                result.add(matrix[i][endX]);
            }
        }

        // 第3步：从右 -> 左 打印1行
        // 前置条件：至少2行2列、即：终止行号 > 起始行号、终止列号 > 起始列号
        // cols-1-y：表示的是环最右那一列的列号
        if (endY > x && endX > y) {
            // 因为环的左下角的位置已经输出了，所以列号从倒数第2列（endX-1）开始
            for (int i = endX-1; i >= y; i--) {
                System.out.print(matrix[endY][i] + " ");
                result.add(matrix[endY][i]);
            }
        }

        // 第4步：从下 -> 上 打印1列
        // 前置条件：至少3行2列，即：终止行号 比 起始行号 大2、终止列号 > 起始列号
        // rows-x-1：终止行 = endY
        if (endX > y && endY > x + 1) {
            // 因为最左边那一列的第一个和最后一个已经被输出了
            // 所以从第2个开始、倒数第2个结束
            for (int i = endY - 1; i >= x + 1; i--) {
                System.out.print(matrix[i][y] + " ");
                result.add(matrix[i][y]);
            }
        }
    }


    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试：多行多列、1行1列
        int[][] data1={
                {1,2,3},
                {8,9,4},
                {7,6,5},
        };

        int[][] data2={
                {1,2,3,4},
                {10,11,12,5},
                {9,8,7,6},
        };

        int[][] data3={
                {1,2,3},
                {8},
                {7},
        };

        printMatrixClockWisely(data1);
        printMatrixClockWisely(data2);
        printMatrixClockWisely(data3);

        // 异常测试：仅有1行、仅有1列、空
        int[][] data4={
                {1,2,3},
        };
        int[][] data5={
                {1},
                {8},
                {7},
        };
        int[][] data6= null ;

        printMatrixClockWisely(data4);
        printMatrixClockWisely(data5);
        printMatrixClockWisely(data6);
    }

}
