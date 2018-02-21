package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/14.
 */

public class Exam_46 {

    /**
     * 解题算法
     */
    public static int getTranslationCount(int number){

        // 1. 检查输入数据的合法性
        if(number < 0)
            return 0;

        if(number == 1)
            return 1;

        // 2. 递归计算翻译数
        // 为了便于转换，故将数字转成字符串
        return getTranslationCount(Integer.toString(number));
    }
    /**
     * 辅助算法
     * 作用 = 递归计算翻译数
     * 原理 = 动态规划、从右到左计算：f(i-2) = f(i-1)+g(i-2,i-1)*f(i)、
     */

    public static int getTranslationCount(String number) {

        // f（n） = 0、f（n-1）=1

        int f1 = 0,f2 = 1,g = 0;
        int temp;

        // 从右到左计算
        for( int i = number.length()-2; i>=0; i-- ){

            // 当第 i 位 和 第（i+1）位 2个数字拼接起来的数字在10~25的范围内时，g (i，i+1)  =1
            // 注：通过""拼接起来
            if(Integer.parseInt( number.charAt(i)+""+number.charAt(i+1) )<26)
                g = 1;
            else
                g = 0;

            temp = f2;

            // 计算f (i-2) = f (i-1)+ g (i-2，i-1) x  f (i)
            f2 = f2+g*f1;
            f1 = temp;
        }

        return f2;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 功能测试
        System.out.println(getTranslationCount(12258));

        // 特殊输入测试：负数、0
        System.out.println(getTranslationCount(-10));
        System.out.println(getTranslationCount(0));
    }

}
