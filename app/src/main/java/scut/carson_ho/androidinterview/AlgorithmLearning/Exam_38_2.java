package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Carson_Ho on 17/11/9.
 */

public class Exam_38_2 {

    /**
     * 解题算法
     */
    public static List<String> stringCombination(String str) {
        // 1. 判断数据的合法性
        if(str == null || str.trim().length() == 0) {
            System.out.println("输入的字符串为空");
            return new ArrayList<>();
        }

        char chars[] = str.toCharArray();// 将字符串转换成数组便于处理
        StringBuilder sb = new StringBuilder();
        int index = 0;

        // 2. 创建1链表用于存储排列
        List<String> result = new LinkedList<>();

        // 3. 通过 循环 求出字符串的组合
        for(int i = 1; i <= str.length(); ++i) {
            stringCombination(chars, i,result,sb,index);
        }

        return result;
    }

    /**
     * 辅助算法
     */
    private static void stringCombination(char[] chars, int length,List<String> result,StringBuilder sb,int index) {
        if(length == 0) {
            result.add(sb.toString());
            return;
        }
        if(chars.length - index < length) {
            return;
        }
        // 选择第一个，从剩下的中选择length-1个
        sb.append(chars[index]);
        ++index;
        stringCombination(chars, length - 1,result,sb,index);
        sb.deleteCharAt(sb.length() - 1);

        // 不选择第一个，从剩下的中选择length个
        stringCombination(chars, length,result,sb,index);
        --index;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 功能测试：无重复字符 字符串
        System.out.println("功能测试1：无重复字符 字符串");
        System.out.println(stringCombination("abc"));

        // 特殊输入测试：输入字符为空
        System.out.println("特殊输入测试：输入字符为空");
        System.out.println(stringCombination(null));

    }
}
