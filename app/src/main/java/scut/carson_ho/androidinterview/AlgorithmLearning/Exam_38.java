package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Carson_Ho on 17/11/8.
 */

public class Exam_38 {
    
    /**
     * 解题算法
     * @param strs  传入的字符串
     */
    public static ArrayList<String> permutation(String strs) {

        // 1. 创建1链表用于存储排列
        ArrayList<String> ret = new ArrayList<>();

        // 2. 判断数据的合法性
        if (strs == null || strs.length() == 0) {
            System.out.println("输入的头节点为空");
            return ret;
        }

        // 3. 将字符串转换成数组便于处理
        char[] chars = strs.toCharArray();

        // 4. 求出字符串的排列
        permutationCore(chars, ret, 0);

        // 5. 调用Collections工具类的sort（）对结果进行排序
        Collections.sort(ret);
        return ret;
    }

    /**
     * 辅助算法1：求字符串的排列
     */
    public static void permutationCore(char[] strs, ArrayList<String> ret, int k) {
        // 下标 = k的字符 = 当前字符串的第1个字符

        // 若下标 = 最后1个字符，则输出所有结果
        if (k == strs.length) {
            ret.add(String.valueOf(strs));
            return;
        }

        // 下标=i的字符 = 当前字符串第1个字符的后面所有字符（i的范围 = [k,length) ）
        // 下标=k的字符 依次 与下标为i的字符 交换
        // 若相同，则不交换，直到最后一个元素为止
        for (int i = k; i < strs.length; i++) {

            // 注：此处需考虑字符重复的情况：若第1个字符与后面字符存在重复情况，则不交换（因为交换后的排列相同）
            if (i == k || strs[k] != strs[i]) {
                swap(strs, i, k);
                permutationCore(strs, ret, k + 1);
                swap(strs, i, k);

            }
        }
    }

    /**
     * 辅助算法2：交换字符位置
     */
    public static void swap(char[] strs, int x, int y) {
        char temp = strs[x];
        strs[x] = strs[y];
        strs[y] = temp;

    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 功能测试1：无重复字符 字符串
        System.out.println("功能测试1：无重复字符 字符串");
        System.out.println(permutation("abc"));


        // 功能测试2：重复字符 字符串
        System.out.println("功能测试2：重复字符 字符串");
        System.out.println(permutation("aac"));

        // 特殊输入测试：输入字符为空
        System.out.println("特殊输入测试：输入字符为空");
        System.out.println(permutation(null));

    }

}


    /**
     * 解题算法
     * @param strs  传入的字符串
     */
//    public static List<char[]> permutation(String strs) {
//
//        // 1. 判断数据的合法性
//        if (strs == null || strs.length() == 0) {
//            System.out.println("输入的头节点为空");
//            return null;
//        }
//
//        char[] chars = strs.toCharArray();// 将字符串转换成数组便于处理
//
//        // 2. 创建1链表用于存储排列
//        List<char[]> ret = new LinkedList<>();
//        // 3. 求出字符串的排列
//        permutationCore(chars, ret, 0);
//        return ret;
//    }
//
//    /**
//     * 求字符串的排列
//     */
//    public static void permutationCore(char[] strs, List<char[]> ret, int k) {
//        // 下标 = k的字符 = 当前字符串的第1个字符
//        if (k == strs.length)
//            ret.add(Arrays.copyOf(strs, strs.length));
//
//        // 下标 = i的字符 = 当前字符串第1个字符的后面所有字符（i的范围 = [k,length) ）
//        // 下标 = k的字符依次与下标为i的字符交换
//        // 若相同，则不交换，直到最后一个元素为止
//        for (int i = k; i < strs.length; i++) {
//            if (i == k || strs[k] != strs[i]) {
//                swap(strs, k, i);
//                permutationCore(strs, ret, k + 1);
//                swap(strs, k, i);
//            }
//        }
//    }
//
//    /**
//     * 交换字符位置
//     */
//    public static void swap(char[] strs, int x, int y) {
//        char temp = strs[x];
//        strs[x] = strs[y];
//        strs[y] = temp;
//    }
