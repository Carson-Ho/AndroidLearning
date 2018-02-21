package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/26.
 */

public class Exam_15 {

    /**
     * 解法1：右移判断
     */
    public static int numberOfOne1(int n){
        int count=0;
        while(n!=0){
            // 不断右移相与
            if((n&1)!=0)
                count++;
            n>>>=1;
        }
        return count;
    }

    /**
     * 解法2：用1个辅助数与其 相与 判断
     */
    public static int numberOfOne2(int n){
        int count=0;
        int flag=1;
        while(flag!=0){
            if((n&flag)!=0)
                count++;
            // 辅助数右移
            flag<<=1;
        }
        return count;
    }

    /**
     * 解法3：通过观察总结的规律求解
     * 规律如下：
     * 1. 把1个整数减去1后，再和原来的整数作 与运算
     * 2. 上述得到的结果 = 把原整数的二进制表示的最右边的1变成0
     * 3. 进行了多少次上述操作，则代表原整数的二进制表示有多少个1
     */
    public static int numberOfOne3(int n){
        int count=0;

        while(n!=0){
            n = n&(n-1);
            count++;
        }
        return count;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 对3种解法进行测试
        System.out.println(numberOfOne1(3));
        System.out.println(numberOfOne1(-3));

        System.out.println(numberOfOne2(3));
        System.out.println(numberOfOne2(-3));

        System.out.println(numberOfOne3(3));
        System.out.println(numberOfOne3(-3));
    }

}


