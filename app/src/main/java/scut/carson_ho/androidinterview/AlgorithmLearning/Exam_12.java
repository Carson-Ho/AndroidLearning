package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/25.
 */

public class Exam_12 {

    /**
     * 解题算法
     * 实现原理 = 回溯法，采用递归实现
     * @param matrix = 矩阵
     * param row = 矩阵行数
     * param cols = 矩阵列数
     * @param str = 需寻找的字符串路径
     * @return boolean = 矩阵中是否存在要寻找的字符串路径
     */

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {

        // 判断输入数据的合法性
        if(matrix == null || str == null || matrix.length == 0 || str.length == 0)
            return false;

        // 定义1个矩阵，用于标识路径是否已进入每个格子
        // 0表示未访问、1表示访问过
        int flag[] = new int[matrix.length];

        // 通过遍历比较矩阵中的值 & 字符串路径中的值是否相等
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 通过递归判断是否相等
                if (helper(matrix, rows, cols, i, j, str, 0, flag))
                    return true;
            }
        }
        return false;
    }

    /**
     * 辅助算法：判断 矩阵中是否存在于需寻找的字符串路径
     * 实现原理 = 递归
     * @param matrix = 矩阵
     * param row = 矩阵行数
     * param cols = 矩阵列数
     * param i = 当前行
     * param j = 当前列
     * param str = 需寻找的字符串路径
     * param k = 字符串路径下标
     * param flag = 路径标识矩阵
     * @return boolean = 矩阵中是否存在要寻找的字符串路径
     */
    private static boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {

        // 当前矩阵比较元素
        int index = i * cols + j;

        // 判断矩阵值是否与路径中的值相等
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
            return false;

        // 结束条件：即比较完毕
        if(k == str.length - 1)
            return true;

        flag[index] = 1;
        // 分上下左右四个方向位置通过递归继续判断矩阵中的值是否与字符串路径中下1个值相等
        if (helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
            return true;
        }

        flag[index] = 0;
        return false;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        char[] matrix = { 'a', 'b', 't', 'g',
                'c', 'f', 'c', 's',
                'j', 'd', 'e', 'h'};
        int rows = 3;
        int cols = 4;

        // 测试输出结果
        System.out.println(hasPath(matrix, rows, cols, new char[] {'b','f','c','e'})); //true
        System.out.println(hasPath(matrix, rows, cols, new char[] {'a','b','f','b'})); //false,访问过的位置不能再访问


    }

}
