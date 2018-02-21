package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/13.
 */

public class Exam_43 {

    /**
     * 解题算法：找规律
     */
    public static int NumberOf1Between1AndN_Solution(int n){

        // 1. 判断 输入数据的合法性
        if(n<=0) {
            System.out.println("输入的数据不合法");
            return 0;
        }

        int count = 0 ;// 记录出现1的次数

        // 2. 通过遍历 进行记录
        for(int i = 1;i<=n;i*=10){

            // 表示当前分析的是哪个数位(个、十、百...)，分割该数位
            int a = n/i; // 高位
            int b = n%i; // 低位

            // 2.1 当 i 对应的数为1时
            if(a%10==1){
                count = count+ (a+8)/10*i + (b+1);
            }else{
                // 2.2 当i位对应的数为 ≥2 或 为0时
                count = count + (a+8)/10*i;
            }
        }
        return count;

    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        // 功能测试
        System.out.println(NumberOf1Between1AndN_Solution(12));

        // 性能测试：数字很大
        System.out.println(NumberOf1Between1AndN_Solution(12222));

        // 特殊输入测试
        System.out.println(NumberOf1Between1AndN_Solution(0));
    }

}
