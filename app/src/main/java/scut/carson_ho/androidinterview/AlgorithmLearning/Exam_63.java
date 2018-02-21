package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/29.
 */

public class Exam_63 {

    /**
     * 解题算法
     */

    public static int maxDiff(int[] data){

        // 判断输入数据的合法性
        if(data==null||data.length<2)
            return 0;

        int min = data[0]; // 存储遍历数组过程中，遍历过数字的最小值min
        int maxDiff = data[1] - min; // diff（i） = 当卖出价为 数组中第i个数字时 可能获得的最大利润

        if(data[1]<min)
            min = data[1];

        // 遍历数组
        for( int i=2; i<data.length; i++ ){

            // 遍历过程中，记录遍历过数字的最小值min、将该数字 与 之前记录的（i-1）个数字中的最小值min 作减法
            // 即 可求出股票的最高利润
            if(data[i] - min > maxDiff)
                maxDiff = data[i]-min;

            if( data[i]<min )
                min = data[i];
        }
        return maxDiff;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试：无序、单调递增、单调递减
        int[] data1 = new int[]{9,11,8,5,7,12,16,14};
        int[] data2 = new int[]{5,8,7,9,11,12,14,16};
        int[] data3 = new int[]{9,8,7,6,5,4,3,1};
        System.out.println(maxDiff(data1));
        System.out.println(maxDiff(data2));
        System.out.println(maxDiff(data3));

        // 特殊输入测试
        System.out.println(maxDiff(null));
    }

}
