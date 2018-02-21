package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/15.
 */

public class Exam_49 {

    /**
     * 解题算法
     */
    public static int getUglyNumber(int num){
        // 检查数据的合法性
        if(num <= 0 )
            return 0;

        // 因1-6都是丑数，所以当丑数数量<7个时直接返回数量
        if (num < 7)
            return num;

        // 1. 创建1数组
        // 作用：放置已经排序好的丑数
        // 长度：丑数的数量。如，为了得到第1500个丑数，则需要长度 = 1500的数组来记录已经计算出来的丑数
        int[] uglyNumber = new int[num];

        uglyNumber[0] = 1; // 第1个丑数 = 1

        // 2. 定义1个指针指向第1个丑数1，3个指针 分别指向 前1个丑数乘2、乘3、乘5后的丑数
        int uglyIndex=0, multiply2=0, multiply3=0, multiply5=0;

        while (uglyIndex+1<num){
            // 3. 将3个指针计算出来的最小的丑数放在数组的下1个位置，并将该指针向后移动1个位置
            uglyNumber[++uglyIndex] = min(uglyNumber[multiply2]*2,uglyNumber[multiply3]*3,uglyNumber[multiply5]*5);

            // 将最小值的丑数的指针往后移1位
            // 注：在计算时，会存在重复值，即2*3=6，3*2=6，故需使用if，而不用if-else
            if(uglyNumber[multiply2]*2 == uglyNumber[uglyIndex])
                multiply2++;

            if(uglyNumber[multiply3]*3 == uglyNumber[uglyIndex])
                multiply3++;

            if(uglyNumber[multiply5]*5 == uglyNumber[uglyIndex])
                multiply5++;
        }

        return uglyNumber[num-1];
    }

    /**
     * 辅助算法：计算三者中的最小值
     */

    public static int min(int x,int y,int z){
        int temp = x<y?x:y;
        return temp<z?temp:z;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 功能测试：2、3、4
        System.out.println(getUglyNumber(2));

        // 性能测试：很大的数
        System.out.println(getUglyNumber(1500));

        // 特殊输入：边界值1、无效输入
        System.out.println(getUglyNumber(1));
        System.out.println(getUglyNumber(0));
    }


}
