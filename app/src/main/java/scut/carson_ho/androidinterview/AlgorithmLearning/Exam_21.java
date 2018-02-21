package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/31.
 */

public class Exam_21 {

    /**
     * 优化解题算法
     * 解题思路：通过2指针判断 & 交换位置
     */
    public static void reorder(int[] array){

        // 1. 判断输入数据的合法性
        if(array==null||array.length<2)
            return;

        // 2. 初始化2指针
        // p1 = 初始化指向数组第1个元素，
        // p2 = 初始化指向数组最后1个元素
        int left = 0;
        int right = array.length-1;

        // 3. 跳出循环条件 = p2在p1的前1个位置
        while(left<right){

            // a. 将p1指针不断往后移，直到遇到的数 = 偶数
            // 调用辅助算法（功能判断函数)进行判断奇、偶
            while (left<right && !isEven(array[left]) )
                left++;

            // b. 将p2指针不断往前移，直到遇到的数 = 奇数
            // 调用辅助算法（功能判断函数)进行判断奇、偶
            while (left<right && isEven(array[right]))
                right--;

            if(left<right){
                // c. 当p1、p2分别指向偶数、奇数时，交换2指针指向的数字位置
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
    }

    /**
     * 辅助算法：功能判断函数
     * 将判断数字 = 奇 / 偶 的代码独立出来，实现最大解耦
     * 注：采用位运算 代替 除2运算，提高运算效率
     */

    public static boolean isEven(int n){
        return (n&1)==0;
    }


    /**
     * 变式题的解题算法
     * 解题思路：
     * 1. 新建1个等长数组，长度 = 输入数组，用于放置原数组的奇数数组
     * 2. 新建2个指针：p1指针从新建数组下标为0 开始，p2指针从新建数组下标为 奇数个数的末尾开始(通过统计奇数个数时进行设置)
     * 3. 通过遍历原数组，将奇数填入到新建数组的前半部分、偶数填入到新建数组的后半部分
     * 时间复杂度为O（n），空间复杂度为O（n）
     * 4. 通过遍历，将转换后的数组赋值到原有数组
     */

    public static void reOrderArray(int [] array) {
        if(array.length==0||array.length==1)
            return;

        // 1. 新建1个数组,长度 = 输入数组，用于放置原数组的奇数数组
        int[] newArray=new int[array.length];

        // 2. 新建2个指针：
        // p1：指针从新建数组下标为0 开始
        // p2：指针从新建数组下标为 奇数个数的末尾开始(通过统计奇数个数时进行设置)
        int p1 = 0;
        int p2 = 0;

        // 设置P2指针统计奇数的个数
        for(int i=0 ; i<array.length ; i++){

            if(!isEven(array[i]))
                p2++;

        }

        // 3. 通过遍历原数组，将奇数填入到新建数组的前半部分、偶数填入到新建数组的后半部分
        for(int i=0; i<array.length; i++){

            if(!isEven(array[i]))
                newArray[p1++] = array[i];
            else newArray[p2++]=array[i];

        }

        // 4. 通过遍历，将转换后的数组赋值到原有数组
        for(int i=0;i<array.length;i++){
            array[i] = newArray[i];
        }
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){

        int[] array1 = {1,2,3,4,5};
        reorder(array1);
        for(int i=0;i<array1.length;i++) {
            System.out.print(array1[i]);
        }

        System.out.println();
        int[] array2 = {1,2,3,4,5};
        reOrderArray(array2);
        for(int i=0;i<array2.length;i++) {
            System.out.print(array2[i]);
        }
    }

}


