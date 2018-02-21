package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/30.
 */

public class Exam_66 {

    /**
     * 解题算法
     */
    public static int[] multiply(int[] data) {

        // 判断输入数据的合法性
        if (data == null || data.length < 2) {
            return null;
        }

        int[] result = new int[data.length];// 定义数组用于存放结果，该数组即为B数组
        result[0] = 1;// B[0]初始化为1

        // 步骤1：求得数组C，存于result数组中
        // 即，C[ i ] = A[ 0 ] * A[ 1 ] ... A[ i-1 ] = C[ i-1 ] * A[ i-1 ] = 自上而下计算
        for (int i = 1; i < data.length; i++) {
            result[i] = result[i -1] * data[i - 1];
        }

        // 步骤2：求得数组D，存于result数组中
        // 即，D[ i ] = A[ i+1 ] * ... A[ n-2 ] * A[ n-1 ] = D[ i+1 ] * A[ i+1 ] = 自下而上计算
        int tmp = 1;
        // 由于result[n-1]已计算，所以从data.length-2开始操作
        for (int i = data.length - 2; i >= 0; i--) {
            //计算数组D中的元素值 = D[ i+1 ] * A[ i+1 ]
            tmp *= data[i + 1];
            // 步骤3：最终计算B[i] = C[i]*D[i]
            result[i] *= tmp;
        }

        return result;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试：
        int[] data = new int[]{1,2,3,4,5};
        int[] result = multiply(data);
        for( int i=0;i<result.length;i++){
            System.out.print(result[i]);
            System.out.print("  ");
        }
    }
}
