package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/24.
 */

public class Exam_58 {

    /**
     * 解题算法
     */
    public static String reverseSentence(String str){

        // 判断输入数据的合法性
        if (str == null || str.length() < 2){
            System.out.println("输入的数据不合法");
            return str;
        }

        // 转换成字符串数组便于处理
        char[] chars = str.toCharArray();

        // 1. 翻转句子中所有字符顺序
        reverse(chars,0,chars.length - 1);

        // 2. 翻转每个单词中的字符顺序
        // 通过扫描空格来确定每个单词的起始 & 终止位置
        int start = 0;
        int end = 0;

        while (start < chars.length) {
            // 2.1 起始位置 = 空格时，继续往下扫描
            if (chars[start] == ' ') {
                start++;
                end++;

                // 2.2 终止位置 = 空格时，即可以开始反转单词
            } else if (end == chars.length || chars[end] == ' ') {
                reverse(chars, start, end - 1);
                end++;
                start = end;
            } else {
                end++;
            }
        }

        return String.valueOf(chars);

    }

    /**
     * 辅助算法：翻转句子
     */
    private static char[] reverse(char[] chars, int start, int end){
        if (chars == null || chars.length < 2){
            return chars;
        }
        while (start < end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return chars;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args)  {

        // 功能测试：句子中多个单词
        String str1 = "I am a student.";
        System.out.println(reverseSentence(str1));

        // 特殊输入测试：字符串指针为空
        System.out.println(reverseSentence(null));
    }

}
