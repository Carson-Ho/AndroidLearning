package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/16.
 */

public class Exam_50 {

    /**
     * 解题算法
     */
    public static char FirstNotRepeatingChar(String str) {

        // 1. 检查输入数据的合法性
        if(str == null || str.length() == 0)
            return '0';

        // 2. 本题 实现1简易的哈希表 = 1维数组：
        // a. 长度 = 256：所有字符都可用ASCII表示 = 0-255 = 256位
        // b. 数组下标 = 字符的ASCII码
        // c. 数组的值 = 每个字符出现的次数
        int[] ch = new int[256];


        // 3. 从头开始扫描字符串2次
        // 第1次：每扫描到1个字符，记录下字符出现的次数（即数组的值+1）
        for(int i = 0; i < str.length(); ++i) {
            ++ch[str.charAt(i)];
        }

        // 第2次：每扫描到1个字符，从数组中获取字符出现的次数，第1个只出现1次的字符即为所求
        for(int i = 0; i < str.length(); ++i) {
            if(ch[str.charAt(i)] == 1) {
                return str.charAt(i);
            }
        }
        return '0';
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 功能测试1：字符串存在只出现1次的字符
        System.out.println(FirstNotRepeatingChar("abaccdeff"));

        // 功能测试1：字符串中所有字符都知出现1次
        System.out.println(FirstNotRepeatingChar("abcdefg"));

        // 特殊输入：无效输入
        System.out.println(FirstNotRepeatingChar(null));
    }
}
