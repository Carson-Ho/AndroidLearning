package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/27.
 */

public class Exam_17 {

    /**
     * 解题算法：用数组表达大数
     * @param num 输入的n位数
     */

    public static void print1ToMaxOfNDigits(int num) {

        // 1. 判断输入数据的合法性
        if (num <= 0)
            return;

        // 2. 设置1数组用于表达数字（大小 = n位）
        int[] number = new int[num];

        // 3. 初始化数组
        for (int i = 0; i < num; i++)
            number[i]= 0;


        // 4. 在数组上模拟加法，每次加1都输出结果
        // 关于在数组上模拟加法 & 打印输出数字 请看具体函数注释
        while (!increment(number)) {

            printNumber(number);
            // 每次输出1个数后，用空格隔开
            System.out.println();
        }
    }

    /**
     * 辅助算法：在数组上模拟加法
     * @param number 输入的数组
     */

    public static boolean increment(int[] number){

        // 1. 检查输入数组长度的合法性
        if(number.length<1)
            throw new RuntimeException("invalid lenth of array");

        // 最高位产生进位标志，true时表示数组中的数为最大的n位整数
        boolean isOverFlow=false;

        // 进位位
        int carry=0;

        // 无产生进位时，直接+1，循环只运行1次；
        // 每产生一次进位，循环多运行一次
        for(int i=number.length-1;i>=0;i--){

            // 用于存储最终表示数字的每一位数
            int sum=number[i]+carry;

            // 数组的最高位+1，即 表示数字的最低位+1
            if(i == number.length-1)
                sum++;

            // 发生进位时
            if(sum>=10){
                // a. 若最高位产生进位，则代表已经增加到了数组中的数为最大的n位整数
                if(i==0)
                    isOverFlow=true;
                    // b. 若只是普通位产生进位，将当前位数设置为0，sum设置为0
                else{
                    carry=1;
                    number[i]=0;
                    sum=0;
                }
                // 若无发生进位
            }else{
                // +1后的结果保存到数组中，并退出循环
                number[i]=sum;
                break;
            }
        }
        return isOverFlow;
    }

    /**
     * 辅助算法：打印输出数字
     * @param number 输入的字符串
     */
    public static void printNumber(int[] number){

        boolean isBeginning=true;

        for(int i=0; i<number.length; i++){
            // 只有在碰到第一个非0的字符后才开始打印，直到字符串结尾
            if(isBeginning && number[i]!=0)
                isBeginning=false;
            if(!isBeginning)
                System.out.print(number[i]);
        }
    }

    /**
     * 测试算法
     */
    public static void main(String[] args) {
        print1ToMaxOfNDigits(3);
    }
}


