package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/27.
 */

public class Exam_16 {

    /**
     * 常规解法，时间复杂度为O(n)
     * @param base = 底数
     * @param exponent = 幂
     * @return
     */
    public static double Power(double base, int exponent) {

        double result = 1.0;
        for(int i = 1;i <= exponent;i++){
            result *= base;
        }
        return result;
    }

    /**
     * 完整解法，时间复杂度为O(logN)
     * 完善点：问题的完整性 & 乘方效率
     * @param base = 底数
     * @param exponent = 幂
     * @return 结果
     */

    public static double PowerEst(double base, int exponent) {

        // 1. 特殊情况考虑
        // a. 指数 = 负数 & 底数 = 0时，通过 抛出异常 提示错误
        if(base == 0 && exponent <0)
            throw new RuntimeException("不合法输入：指数 = 负数 & 底数 = 0");
        //  return 0; // 也可通过返回0 提示错误

        // b. 指数 = 负数情况：对指数求绝对值
        int n = exponent ;
        if(exponent <0)
             n = Math.abs(exponent);

        // 2. 求幂次方
        double  result = powerWithUnsignedExponent(base,n);

        // 3. 当指数 = 负数时，将最后结果取倒数
        if(exponent < 0)
            result = 1/result;

        // 4. 返回最后结果
        return result;
    }


    /**
     * 辅助算法：求一个数的正整数次幂
     *
     * @param base     底数
     * @param exponent 幂
     * @return 结果
     */
    public static double powerWithUnsignedExponent(double base, long exponent) {

        // 1. 先求特殊情况：
        // a. 若指数 = 0，返回1
        if (exponent == 0) {
            return 1;
        }

        // b. 若指数 = 1，返回底数本身
        if (exponent == 1) {
            return base;
        }

        // 2. 使用公式 提高乘方效率（使用递归实现，即求一半的值）
        // 注：使用右移运算符 代替 除以2，以提高效率
        double result = powerWithUnsignedExponent(base, exponent >> 1);

        // 3. 求出最终的值
        result *= result;

        // 4. 判断最终值的奇偶:通过求余判断（用位与运算符 代替 求余运算符）
        // 若是奇数，就还要乘多1次底数
        if((exponent & 0x1)==1)
            result *= base;

        // 5. 返回结果
        return result;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 测试用例
        System.out.println(PowerEst(2, 4));
        System.out.println(PowerEst(2, -4));
        System.out.println(PowerEst(2, 0));
        System.out.println(PowerEst(-2, 3));
        System.out.println(PowerEst(0, 0));
        System.out.println(PowerEst(0, 3));
        System.out.println(PowerEst(0, -1));
    }

}
