package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/23.
 */

public class Exam_56_1 {

    /**
     * 解题算法
     */
    public static int findOnceNumber(int[] intArray) throws Exception {

        // 判断输入数据的合法性
        if (intArray == null || intArray.length < 1) {
            System.out.println("数组为空或者长度不足");
            return 0;
        }

        // 将和的结果存储到1个大小 = 32的数组中
        int[] bitSum = new int[32];

        // 1. 将数组所有数字的二进制表示的每1位都加起来
        // 将和的结果都放到数组中
        for (int i : intArray) {

            int bitMask = 1;

            for (int j = 31; j >= 0; --j){
                int bit = i & bitMask;
                if (bit != 0){
                    bitSum[j] += 1;
                }
                bitMask = bitMask << 1;
            }
        }

        //2. 对每1位的和 都对3取余
        int result = 0;
        for (int i = 0; i < 32; i++){
            result = result << 1;
            // 若能被3整除（余数 = 0），则说明包含该位数的数字出现了3次，即 只出现1次的数字二进制表示中对应那位 = 0
            // 若不能被3整除（余数 = 1），则说明包含该位数的数字出现了1次，即 只出现1次的数字二进制表示中对应那位 = 1
            result += bitSum[i] % 3;
            }
        return result;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) throws Exception {
        int[] array1 = {2,4,2,5,2,5,5};
        System.out.println("数组中唯一出现一次数字是：" + findOnceNumber(array1));

    }
}
