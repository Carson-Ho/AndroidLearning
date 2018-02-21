package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/25.
 */

public class Exam_10 {

    public static void main(String[] args) {

        System.out.println(Fibonacci(5));
        System.out.println(Fibonacci2(5));
    }


    /**
     * 递归实现
     */
    public static int Fibonacci(int n){
        if(n<=1)
            return n;
        return Fibonacci(n-1)+Fibonacci(n-2);
    }

    /**
     * 循环实现
     */
    public static int Fibonacci2(int n){
        if(n<=1)
            return n;
        
        int fibOne = 1;
        int fibTwo = 0;
        int sum = 0;

        for (int i = 2; i <= n; i++) {
            sum = fibOne + fibTwo;
            fibTwo = fibOne;
            fibOne = sum;
        }
        return sum;
    }
    
    /**
     * 青蛙跳台阶（基础）
     */
    public int jumpFloor(int number) {

        if(number <=2 )
            return number;

        int jumpone=2; // 离所求的number的距离为1步的情况，有多少种跳法
        int jumptwo=1; // 离所求的number的距离为2步的情况，有多少种跳法
        int sum=0;

        for( int i=3;i <= number; i++ ){

            sum = jumptwo + jumpone;
            jumptwo = jumpone;
            jumpone= sum;
        }

        return sum;
    }

    /**
     * 青蛙跳台阶（变式）
     */
    public int jumpFloor2(int num) {
        if( num <= 2)
            return num;

        int jumpone=2; // 前面一级台阶的总跳法数

        int sum=0;

        for(int i=3; i<=num ;i++){

            sum = 2*jumpone;
            jumpone = sum;

        }
        return sum;
    }


}
