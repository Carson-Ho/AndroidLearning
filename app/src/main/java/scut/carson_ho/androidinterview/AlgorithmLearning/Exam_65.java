package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/30.
 */

public class Exam_65 {

    /**
     * 解题算法
     */
    public static int add(int x, int y) {
        int sum; // 记录二进制的每1位相加的和（不考虑进位）
        int carry;// 记录进位数的和

        do {
            // 1. 对2个数字的二进制的每1位相加
            // 实现方式 = 采用位运算的 异或 实现
            sum = x ^ y;

            // 2. 计算进位数
            // 实现方式 = 2个数先做位与运算，然后再向左移1位
            // 注：x & y 的某一位是1说明，它是它的前一位的进位，所以向左移动一位
            carry = (x & y) << 1;

            x = sum;
            y = carry;
        } while (y != 0);
        // 3. 将步骤2、3的结果相加，原理 同步骤2、3，直到不产生进位为止

        return x;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试：5+17 = 22
        System.out.println(add(5,17));

    }
}
