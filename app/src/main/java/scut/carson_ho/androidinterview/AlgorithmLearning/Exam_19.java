package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/31.
 */

public class Exam_19 {

    /**
     * 解题算法：模式匹配算法
     * @param str 目标字符串
     * @param pattern 模式字符串
     */

    public static boolean match(char[] str, char[] pattern) {
        // 1. 检查数据的合法性
        if (str == null || pattern == null) {
            return false;
        }

        int strIndex = 0; // 当前匹配的目标字符串的字符下标
        int patternIndex = 0; // 当前匹配的模式字符串的字符下标

        // 2. 进行模式匹配
        // 返回true  = 匹配成功
        // 返回false = 匹配失败
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    /**
     * 辅助算法：详细的模式匹配
     * @param str 目标字符串
     * @param strIndex 当前匹配的目标字符串的字符下标
     * @param pattern 模式字符串
     * @param patternIndex 当前匹配的模式字符串的字符下标
     */

    public static boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
    // 有效性检验：若目标字符串 & 模式字符串的下标同时到尾，则匹配成功
    if (strIndex == str.length && patternIndex == pattern.length) {
        return true;
    }
    // 若模式字符串的下标先到尾，匹配失败
    if (strIndex != str.length && patternIndex == pattern.length) {
        return false;
    }

    // 1. 若 当前匹配的模式字符串的下1个字符 = * & 目标字符串第当前字符跟模式字符串当前字符匹配
        // 分3种匹配模式；
        // 1. 视为匹配了0个字符，即 在模式字符串上直接后移2位（模式字符串中的"x*"被忽略）
        // 2. 视为匹配了1个字符，即 字符串后移1字符，模式后移2字符；
        // 3. 视为*匹配多为，即 字符串后移1字符，模式不变，即继续匹配字符下一位
    if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {

    if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
        return matchCore(str, strIndex, pattern, patternIndex + 2)
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)
                        || matchCore(str, strIndex + 1, pattern, patternIndex);
        } else {

            // 2. 若 当前匹配的模式字符串的下1个字符 = * & 目标字符串当前字符 跟 模式字符串当前字符不匹配
            // 则模式后移2个字符，继续匹配
            return matchCore(str, strIndex, pattern, patternIndex + 2);
        }
    }
    // 3. 若当前匹配的模式字符串的下1个字符不 = * 且 目标字符串当前字符 跟 模式字符串当前字符匹配
        // 则视为匹配成功，目标字符串 & 模式字符串都后移1位，继续匹配剩余的
        // 视为匹配不成功，直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
            return false;
    }

    /**
     * 测试模式匹配算法
     */
    public static void main(String[] args){
        // 功能测试用例：模式字符串含普通字符、'.'、'*'
        char[] a = {'a','a','a'};
        char[] b = {'a','.','a'};
        char[] c = {'a','b','*','a','c','*','a'};
        char[] d = {'a','a','.'};
        char[] e = {'a','a','*','a'};
        System.out.println(match(a,b));//true
        System.out.println(match(a,c));//true
        System.out.println(match(a,d));//true
        System.out.println(match(a,e));//true

        // 异常测试用例：为空 、空格字符串
        char[] f = {' '};
        System.out.println(match(null,null));//false
        System.out.println(match(f, f));//true
    }
}

// 旧的

//    public static boolean match(String str,String pattern){
//
//        // 1. 检查数据的合法性
//        if(str == null || pattern == null)
//            return false;
//
//        // 2. 进行模式匹配
//        // 返回true  = 匹配成功
//        // 返回false = 匹配失败
//        return matchCore(new StringBuilder(str),0,new StringBuilder(pattern),0);
//    }
//
//    /**
//     * 辅助算法：详细的模式匹配
//     * @param str 目标字符串
//     * @param strIndex 当前匹配的目标字符串的字符下标
//     * @param pattern 模式字符串
//     * @param patternIndex 当前匹配的模式字符串的字符下标
//     */
//
//    public static boolean matchCore(StringBuilder str,Integer strIndex,StringBuilder pattern, Integer patternIndex){
//
//        // 若下标 = 字符串长度，则代表匹配结束
//        if(strIndex == str.length() && patternIndex==pattern.length())
//            return true;
//        // 若仅有其中1个匹配结束，则代表不匹配
//        if(strIndex == str.length() || patternIndex == pattern.length())
//            return false;
//        // 若当前匹配的模式字符串的第2个字符不是* Or 只剩1个字符
//        if(patternIndex==pattern.length()-1|| pattern.charAt(patternIndex+1)!='*'){
//            if(pattern.charAt(patternIndex)=='.' || pattern.charAt(patternIndex)==str.charAt(strIndex))
//                return matchCore(str,strIndex+1,pattern,patternIndex+1);
//            else
//                return false;
//        }
//        // 否则，当前匹配的模式字符串的第2个字符不是 = "*"，则直接跳过
//        else{
//            if(pattern.charAt(patternIndex)==str.charAt(strIndex))
//                return matchCore(str,strIndex+1,pattern,patternIndex) ||matchCore(str,strIndex+1,pattern,patternIndex+2);
//            else
//                return matchCore(str,strIndex,pattern,patternIndex+2);
//        }
//    }






