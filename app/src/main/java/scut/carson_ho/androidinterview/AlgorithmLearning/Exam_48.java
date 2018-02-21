package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carson_Ho on 17/12/14.
 */

public class Exam_48 {

    /**
     * 解题思路1:直接法
     */

//    public static int lengthOfLongestSubstring(String s){
//
//        int n = s.length();
//        int ans = 0;
//        // 1. 遍历给定字符串s的所有可能的子字符串
//        // 通过枚举它们的开始和结束索引
//        // 设开始 & 结束索引 = i和j
//        // 那么我们有0 <= i < j <=n（这里的结束索引j按照惯例排除）
//        // 因此 使用2个循环嵌套枚举S的所有子串（i：从0到n-1、j：从i + 1到n）
//        for(int i = 0; i <n; i ++)
//            for(int j = i + 1; j <= n; j ++)
//
//                // 2. 调用allUnique（）检查子字符串是否有重复的字符
//                if(allUnique(s,i,j))ans = Math.max(ans,j-i);
//
//        return ans;
//    }
//
//    /**
//     * 辅助算法:检查子字符串是否有重复的字符
//     * 原理：
//     * 1. 使用1个辅助集合
//     * 2.遍历字符串中的所有字符 并将它们逐个放入辅助集合
//     * 3. 在放置1个字符前 检查该集合是否已经包含它。若包含 则说明含重复字符串 则返回false
//     */
//
//    public static boolean allUnique(String s,int start,int end){
//
//        Set <Character> set = new HashSet <>();
//
//        for(int i = start; i <end; i ++){
//            Character ch = s.charAt(i);
//            if(set.contains(ch))return false;
//            set.add(ch);
//        }
//        return true;
//    }
//
//    /**
//     * 测试用例
//     */
//    public static void main(String[] args){
//
//        // 功能测试
//        System.out.println(lengthOfLongestSubstring("arabcacfr"));
//
//    }

    /**
     * 解题思路2:滑动窗口
     */
//    public static int lengthOfLongestSubstring(String s) {
//
//        int n = s.length();
//        Set<Character> set = new HashSet<>(); // 使用HashSet实现滑动窗口
//
//        int ans = 0, i = 0, j = 0;
//
//        // 通过移动滑动窗口,找出最长不含重复字符的
//        while (i < n && j < n) {
//            // a. 若S[j]不在HashSet中,则将滑动窗口的索引j向右滑动,包含S[j],即添加该S[j]到HashSet中
//            if (!set.contains(s.charAt(j))){
//                set.add(s.charAt(j++));
//                ans = Math.max(ans, j - i);
//            }
//            // b. 若S[j]在HashSet中,则将滑动窗口的索引i向右滑动 & 删除
//            else {
//                set.remove(s.charAt(i++));
//            }
//        }
//        return ans;
//    }
//
//    /**
//     * 测试用例
//     */
//    public static void main(String[] args){
//
//        // 功能测试
//        System.out.println(lengthOfLongestSubstring("arabcacfr"));
//
//    }



    /**
     * 解题思路3：优化使用滑动窗口
     */
    public static int lengthOfLongestSubstring(String s) {

        int n = s.length(), ans = 0;

        // 1. 使用 HashMap 将字符存储在当前窗口[i,j]（初始化：j = i）
        Map<Character, Integer> map = new HashMap<>(); // 滑动窗口使用HashMap实现

        // 2. 往右移动滑动窗口
        for (int j = 0, i = 0; j < n; j++) {
            // a. 若 S[j] 在HashMap中（即为S [j']） 则将滑动窗口的索引i 直接移动到S[j]的后1个位置 即 i=j'+ 1
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            // b. 若 S[j] 不在HashMap中 则将滑动窗口的索引 j 向右滑动 包含S[j] 即添加该S[j]到HashMap中
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 功能测试
        System.out.println(lengthOfLongestSubstring("arabcacfr"));

    }

}
