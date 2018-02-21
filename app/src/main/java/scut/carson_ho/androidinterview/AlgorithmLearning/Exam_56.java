package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/23.
 */

public class Exam_56 {

    /**
     * 解题算法
     * @param array 题目数组
     * @param num1 存储第1个子数组的异或结果，即其中1个出现了1次的数字
     * @param num2 存储第2个子数组的异或结果，即其中1个出现了1次的数字
     */
    public static void findTwoOnceNumber(int[] array,int num1[] , int num2[]){

        // 判断输入数据的合法性
        if (array == null || array.length < 2){
            System.out.println("数组为空或者长度不足");
            return;
        }

        // 1. 将原数组分成2个子数组
        // 1.1 从头到尾 依次异或 数组中的每个数字
        int orResult = 0;
        for (int i : array){
            orResult ^= i;
        }
        // 1.2 在上述结果找出第1个= 1的位置，记为 第 n 位
        int indexOf1 = findFirstBitIs1(orResult);

        // 1.3 以二进制的第n位是否为1，将原数组的数字分为2个子数组

        for (int i : array){
            if (isBit1(i,indexOf1)){
                // 2. 分别从头到尾 依次异或 2个子数组中的每个数字
                num1[0] ^= i;
            }else {
                num2[0] ^= i;
            }
        }
        System.out.println("只出现一次的数字是：" + num1[0] + "和" + num2[0]);

    }

    /**
     * 辅助算法：寻找输入数字在二进制表示中，从右到左第1位是1的位置
     * @param i 输入的数
     * @return 第一个位是1的位置
     */
    private static int findFirstBitIs1(int i){
        int index = 0;
        while (((i & 1) == 0) && index < 32){
            i = i >> 1;
            index++;
        }
        return index;
    }

    /**
     * 辅助算法：判断该数字的第index位是否为1
     * @param number 输入的数
     * @param index 位置
     * @return 是否为1
     */
    private static boolean isBit1(int number, int index){
        number = number >> index;
        return (number & 1) == 1;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        int[] array1 = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        findTwoOnceNumber(array1,num1,num2);
    }
}
