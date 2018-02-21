package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/26.
 */

public class Exam_14 {

    /**
     * 解法1：动态规划
     */
    public static int maxCutting(int length){

        /**
         * 特殊情况考虑：绳子长度 <2、=2、=3时
         */
        // a. 当绳子长度<2时，无法剪，最大乘积 = 0
        if(length<2) return 0;
        
        // b. 当绳子长度 = 2时，只能剪成长度 = 1的两段，因此f（2）=1
        if(length==2) return 1;
        
        // c. 当绳子长度 = 3时，可剪成长度 = 1 / 2的两段 or 长度 =1 的三段，由于1x2>1x1x1，因此f（3）=2
        if(length==3) return 2;
        
        // 1. 定义1数组用于存储子问题的最优解
        // f[n] = 把长度为n的绳子剪成若干段后 各段长度乘积的最大值
        int[] f = new int[length+1];
        f[0]=0;
        f[1]=1;
        f[2]=2;
        f[3]=3;
        int max = 0;
        int temp = 0;
        
        // 2. 通过for循环，自下而上计算子问题的最优解
        for(int i=4;i<=length;i++){
            max = 0;
            // 3. 通过for循环，通过计算 & 比较的方式，求出子问题的最优解
            for(int j=1;j<=i/2;j++){

                // 先计算出 f（i）* f（n-i）所有值、再通过比较，从而求出最大值
                temp = f[j]*f[i-j];
                if(temp>max)
                    max = temp;
            }
            // 4. 将子问题的最优解存储在数组中
            f[i] = max;
        }
        // 5. 最终返回结果
        return f[length];
    }



    /**
     * 解法2：贪婪算法
     */
    public static int maxCuttingGreedy(int length) {

        /**
         * 特殊情况考虑：绳子长度 <2、=2、=3时
         */

        // 1. 在绳子长度<2、=2、3时，采用和动态规划同样的处理
        // a. 当绳子长度<2时，无法剪，最大乘积 = 0
        if(length<2) return 0;

        // b. 当绳子长度 = 2时，只能剪成长度 = 1的两段，因此f（2）=1
        if(length==2) return 1;

        // c. 当绳子长度 = 3时，可剪成长度 = 1 / 2的两段 or 长度 =1 的三段，由于1x2>1x1x1，因此f（3）=2
        if(length==3) return 2;


        // 2. 尽可能多地剪长度为3的绳子
        int timesOf3 = length/3;

        // 3. 当剩下的绳子长度 = 4时，不能再剪去长度 = 3的绳子段
        // 而是，剪成2-2
        if (length%3==1)
            timesOf3-=1;
        int timesOf2=(length-timesOf3*3)/2;

        // 计算最终的乘积最大值
        // Math.pow（a,b）的作用 = 返回a^b的值
        return (int)(Math.pow(3,timesOf3))*(int)(Math.pow(2,timesOf2));
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
//        // 功能测试：绳子长度 = 10
//        System.out.println("绳子长度 = 10 的最大值:"+maxCutting(10));
//
//        // 边界值测试：绳子长度 = 0、1、2、3、4
//        for(int i=0;i<5;i++){
//            System.out.println("长度 = "+i+"的最大值:"+maxCutting(i));
//        }



        // 功能测试：绳子长度 = 10
        System.out.println("绳子长度 = 10 的最大值:"+maxCuttingGreedy(10));

        // 边界值测试：绳子长度 = 0、1、2、3、4
        for(int i=0;i<5;i++){
            System.out.println("长度 = "+i+"的最大值:"+maxCuttingGreedy(i));
        }
    }


}
        

