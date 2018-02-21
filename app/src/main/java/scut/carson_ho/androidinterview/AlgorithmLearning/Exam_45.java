package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Carson_Ho on 17/11/14.
 */

public class Exam_45 {

    /**
     * 解题算法
     */
    public static String PrintMinNumber(int [] numbers) {

        // 检查输入数据的合法性
        if (numbers == null|| numbers.length < 0){
            return "输入的数据不合法";
        }

        // 1. 将整型数组 转换成 字符串数组，从而解决大数问题
        String[] str = new String[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            str[i] = String.valueOf(numbers[i]);
        }

        // 2. 自定义比较规则 & 排序数组
        // 实现方式：通过Arrays.sort(String int[], Comparator)来定义
        // 原理：根据传入的 Comparator（可通过重写自定义）自定义排序规则，排序 数组元素
        // 注：
        // a. Comparator = 比较器 = 1个接口，通过实现这个接口重写compare（），可使用compareTo（）比较两个对象的大小
        // b. 因为String类内部实现了该方法，故可直接用compareTo进行比较
        // c. 返回正值 = 大于 、返回0 = 等于、返回负值 = 小于。这样就可自定义排序规则
        // d. 系统函数默认 = 递增排序
        // e. jdk7中，集合通过Collections.sort（）实现自定义比较器排序、数组通过Arrays.sort()实现自定义比较器排序
        Arrays.sort(str,new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                String c1 = s1 + s2;
                String c2 = s2 + s1;
                    return c1.compareTo(c2);
                 }
            });

        
        // 3. 通过遍历字符串数组，拼接数组元素，从而成为最终最小的1个数字
        StringBuilder sb = new StringBuilder(); // 用于存储拼接后的数字

        for(int i = 0; i < numbers.length; i++){
            sb.append(str[i]);
            }

        // 最终反馈
        return sb.toString();
         }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        int[] data = {3,32,321};

        // 功能测试
        System.out.println(PrintMinNumber(data));

        // 特殊输入测试
        System.out.println(PrintMinNumber(null));
    }
}
