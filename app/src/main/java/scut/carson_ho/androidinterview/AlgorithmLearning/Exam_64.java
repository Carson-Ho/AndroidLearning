package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/30.
 */

public class Exam_64 {

    /**
     * 解题算法
     */
    public static int Sum_Solution(int n)
    {
        int sum = n;
        // 递归的结束条件：n=0
        // 递归执行语句：累加
        boolean s = ((n > 0) && ((sum += Sum_Solution(n-1))>0));
        return sum;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试：1+2+....+5、1+2+....+10
        System.out.println(Sum_Solution(5));
        System.out.println(Sum_Solution(10));

        // 特殊输入测试：0，1
        System.out.println(Sum_Solution(0));
        System.out.println(Sum_Solution(1));
    }

}
