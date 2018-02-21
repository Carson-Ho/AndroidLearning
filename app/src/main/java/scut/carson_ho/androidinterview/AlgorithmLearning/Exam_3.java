package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/18.
 */

public class Exam_3 {

    /**
     * 解题算法：找出数组中重复数字（可修改数组）
     * @param arr = 输入数组
     * @return
     */

    private static boolean answer(int[] arr){

        // 1. 判断输入数据 是否合法
        // 即，判断数组下标是否越界 & 数字 = 0~n-1范围

        // 1.1 判断数组下标是否越界
        if(arr == null || arr.length<=0) {
            System.out.print("输入不合法 ");
            return false;
        }

        // 1.2 判断数字 是否在 （0 ~ n-1）范围
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 || arr[i] > arr.length-1) {
                System.out.print("输入不合法 ");
                return false;
            }
        }

        // 2. 遍历数组
        for (int i = 0; i < arr.length; i++) {

            // 3. 比较 下标= i位置的值与该下标本身
            while( arr[i]!=i ){
                // 若下标= i位置的值 = 下标= 前者值位置的值，即找到重复数字，输出
                if(arr[i]== arr[arr[i]]){
                    System.out.print("重复的数字是："+ arr[i] + "");

                    return true;
                    // 若只需找出任意1个重复数字，则采用return ，即找到重复数字时就直接结束函数
                    // 采用 break; ，即 找出全部重复的数字
                }

                else {
                    // 否则，交换位置
                    // 把后者放在属于它的位置
                    int temp = arr[i];
                    arr[i] = arr[arr[i]];
                    arr[temp] = temp;
                }
            }
        }

        System.out.print("不含重复数字 ");
        return false;
    }

    /**
     * 解题算法（已经过牛客网测试）
     * 找出数组中重复数字（可修改数组）
     * @param numbers = 输入数组
     * @param length = 数组长度
     * @param duplication = 返回的测试数组
     * @return
     */

    public boolean duplicate(int numbers[],int length,int [] duplication) {
        // 1. 判断输入数据 是否合法
        // 即，判断数组下标是否越界 & 数字 = 0~n-1范围

        // 1.1 判断数组下标是否越界
        if(numbers == null || length <=0) {
            return false;
        }

        // 1.2 判断数字 是否在 （0 ~ n-1）范围
        for (int i = 0; i < length; i++) {
            if (numbers[i] < 0 || numbers[i] > length-1) {
                return false;
            }
        }


        // 2. 遍历数组
        for (int i = 0; i < length; i++) {

            // 3. 比较 下标= i位置的值与该下标本身
            while( numbers[i]!=i ){
                // 若下标= i位置的值 = 下标= 前者值位置的值，即找到重复数字，输出
                if(numbers[i]== numbers[numbers[i]]){

                    // 赋值给结果数组
                    duplication[0] = numbers[i];

                    return true;
                    // 若只需找出任意1个重复数字，则采用return ，即找到重复数字时就直接结束函数
                    // 采用 break; ，即 找出全部重复的数字
                }

                else {
                    // 否则，交换位置
                    // 把后者放在属于它的位置
                    int temp = numbers[i];
                    numbers[i] = numbers[numbers[i]];
                    numbers[temp] = temp;
                }
            }
        }

        System.out.println("结束");
        return false;

    }


    /*******************************************/


    /**
     * 找出数组中重复数字（不可修改数组）
     * 方式：创建辅助数组
     * @param arr = 输入数组
     * @return
     */

     private static int answer_AssArr(int[] arr){
         // 1. 判断输入数据的合法性
         // 即，判断数组下标是否越界 & 数字 = 1~n 范围
         if(arr==null||arr.length-1<=0)
             return -1;
         for (int i = 0; i < arr.length; i++) {
             if(arr[i]<1||arr[i]>arr.length)
                 return -1;
         }
         // 2. 创建辅助数组
         int[] cp = new int[arr.length];

         for (int i = 0; i < arr.length; i++) {
             if(cp[arr[i]]==0)
             	// 将原数组中的arr[i]复制到新数组中下标为arr[i]的位置
                 cp[arr[i]] = arr[i];
             else {
                 System.out.println("重复的数字：" + arr[i]);
                 // return arr[i];
                 // 采用return，则是找出1个重复数字，则马上结束函数
             }
         }
         System.out.println("结束");
         return -1;
     }



    /*******************************************/

    /**
     * 找出数组中重复数字（不修改数组）
     * 方式：二分查找
     * @param arr = 输入数组
     * @return
     */
    private static int answer_NoChangArr(int[] arr){

        // 1. 判断输入数据 是否合法
        // 即，判断数组下标是否越界 & 数字 = 1~n 范围
        if(arr==null || arr.length==0)
            return -1;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]<1 || arr[i] > arr.length)
                return -1;
        }

        int start = 1;
        int end = arr.length-1;

        while(start <= end){

            // 2. 将范围数字分成2部分
            int mid = (end+start)/2;

            // 3. 统计区间内的数字出现的次数
            int count = countRange(arr,arr.length-1,start,mid);

            // 直到分到该部分只有1个数字 & 统计次数>1次，则找到重复数字，输出
            if(end == start){
                if(count>1) {
                    System.out.print("重复的数字：");
                    return start;
                }else
                    break;
            }

            // 若左1半统计的次数 > 该范围内的数字，则继续在该左1半采用二分法
            if(count>(mid-start+1))
                end = mid;
            else
                start = mid+1;
        }

        System.out.print("无重复数字");
        return -1;
    }

    /**
     * 辅助算法：统计区间范围内的数字出现的次数
     */
    private static int countRange(int[] arr, int n, int start, int end) {
        // 判断输入数据的合法性
        if(arr==null || n<=0 )
            return 0;

        int count = 0;

        // 统计区间范围内的数字出现的次数
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>=start&&arr[i]<=end)
                count++;
        }
        return count;
    }

    /*******************************************/

    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 功能测试1：数组长度 = 8，含1个重复数字
        int[] src = new int[]{2,1,5,4,3,2,6,7};
        System.out.println(answer_NoChangArr(src));

        // 功能测试2：数组长度 = 8，含多个重复数字
        int[] src1 = new int[]{2,2,5,4,3,2,6,7};
        System.out.println(answer_NoChangArr(src1));

        // 功能测试3：数组长度 = 8，不含重复数字
        int[] src2 = new int[]{0,1,2,3,4,5,6,7};
        System.out.println(answer_NoChangArr(src2));

        // 特殊输入测试：空指针
        System.out.println(answer_NoChangArr(null));


    }

}
