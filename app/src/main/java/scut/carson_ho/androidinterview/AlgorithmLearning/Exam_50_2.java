package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/16.
 */

public class Exam_50_2 {


    /**
     * 解题算法
     */
    public static class CharStatistics{

        // 1. 本题 实现1简易的哈希表 = 1维数组：
        // a. 长度 = 256：所有字符都可用ASCII表示 = 0-255 = 256位
        // b. 数组下标 = 字符的ASCII码
        // c. 数组的值 = 每个字符在字符流的位置，初始化为 -1
        // 数值表示：-1 = 未出现，≥ 0 = 出现的位置且仅出现一次，-2 = 出现两次 or 以上
        private int[] times; // 哈希表
        private int index;   // 字符在字符流的下标

        public CharStatistics(){
            index = 0;
            times = new int[256];
            for(int i=0;i<256;i++)
                times[i] = -1;
        }

        // 2. 读取字符时，存储 字符 在字符流中的位置
        // a. 当字符 第1次 从字符流读取时，在数组中对应的值 = 其在字符流的位置
        // b. 当字符 第（1+n）次 从字符流读取时，在数组中对应的值 从位置值 更新为：1特殊值，如负数 -2
        public void insert(char ch){

            if(times[ch] == -1)
                times[ch] = index;
            else
                times[ch] = -2;

            index++;
        }

        // 3. 获取 字符串中第1个只出现1次（不重复）的字符
        // 扫描数组，从中找出最小的、值≥0 对应的字符 即为所求
        public char find(){

            int minIndex = 256;

            char ret = '#'; // 若没有只出现一次的字符，显示#

            for(int i=0;i<256;i++){

                if( times[i]>=0 && times[i]<minIndex) {
                    minIndex = times[i];
                    ret = (char)i;
                }
            }
            return ret;
        }
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {

        String str = "google";
        CharStatistics charStatistics = new CharStatistics();

        // 通过循环 模拟 读取字符流的过程
        for (int i = 0; i < str.length(); i++) {
            charStatistics.insert(str.charAt(i));
            System.out.println("第"+ i + "次读取字符时，第1个不重复字符 = " + charStatistics.find());
        }
    }
}
