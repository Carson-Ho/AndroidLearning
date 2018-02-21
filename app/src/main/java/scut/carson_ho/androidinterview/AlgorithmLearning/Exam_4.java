package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/19.
 */

public class Exam_4 {

    /**
     * 在二维数组中查找
     * @param array = 二维数组
     * @param target = 需查找的整数
     * @return 返回false代表该数组无该整数；返回true代表该数组有该整数
     */

    private static boolean answer(int[][] array,int target){
        // 1. 判断输入数据 是否合法
        if(array==null) {
            System.out.print("输入数据不合法");
            return false;
        }

        // 2. 设置查找范围初始右上角整数的二维坐标 = 最右上角元素
        int row = 0; // 第1行
        int col = array[0].length-1; // 最后列

        int numRow = array.length; // 二维数组的行数

        // 3. 比较查找范围右上角整数 与 需查找的整数
        while(row < numRow && col>=0){

            // 若 需查找的整数 < 右上角整数 ，则在当前查找范围中删除当前列
            if(target < array[row][col])
                col--;

                // 若 需查找的整数 > 右上角整数 ，则在当前查找范围中删除当前行
            else if (target > array[row][col]) {
                row++;

            }else {
                // 若 需查找的整数 = 右上角整数表示二维数组存在该整数
                System.out.print("数组中存在整数" + target);
                return true;
            }
        }
        System.out.print("数组中不存在整数" + target);
        return false;
    }

    /**
     * 执行解题算法
     */
    public static void main(String[] args) {

        // 功能测试
        int[][] src = new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        System.out.println(answer(src,7));

        // 特殊输入测试
        System.out.println(answer(null,7));

    }

}


