package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/13.
 */

public class Exam_44 {

    /**
     * 解题算法
     */

    public static int digitAtIndex(int index){
        // 判断输入数据的合法性
        if(index<0) {
            System.out.println("输入的数据不合法");
            return -1;
        }

        /**
         * 1. 确定该数字 属于几位数
         * 若 n < (10 + 2*9*（10^i） ),那么该数字 属于 i 位数
         */
        // 个位数
        if(index<10)
            return index;
        // 2位以上
        int curIndex = 10;
        int length = 2; // 表示多少位数
        int boundNum = 10;

        while (curIndex + lengthSum(length)<index){
            curIndex+=lengthSum(length);
            boundNum*=10;
            length++;
        }

        /**
         * 2. 确定该数字 属于哪个数字
         * x = （10^i） + （n - (10 + 9* i *（10^i） ) ） / i
         */
        int addNum = (index-curIndex)/length;
        int curNum = boundNum + addNum;

        /**
         * 3. 确定该数字 在该数的位置
         * y = n - f (i-1) - （x -10^（i-1））* i
         */
        return Integer.toString(curNum).charAt(index-curIndex-addNum*length)-'0';
    }

    /**
     * 辅助算法：计算 2*9*（10^i）
     */
    public static int lengthSum(int length){
        int count = 9;
        for(int i=1;i<length;i++)
            count*=10;
        return count*length;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        // 功能测试
        System.out.println(digitAtIndex(15));

        // 性能测试：数字很大
        System.out.println(digitAtIndex(1001));

        // 特殊输入测试
        System.out.println(digitAtIndex(0));
    }

}
