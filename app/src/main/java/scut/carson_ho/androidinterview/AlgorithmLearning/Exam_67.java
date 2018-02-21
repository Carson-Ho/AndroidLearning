package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/30.
 */

public class Exam_67 {

    // 设置一个全局变量，用于判断：当输出为0时，是字符串输入异常还是输入字符串为'0'
    // true = 输入异常、false = 输入字符串为'0'
    public static boolean flag;

    /**
     * 解题算法
     */

    public static int StrToInt(String str) {

        flag = false;

        // 非法输入1：空指针 & 空字符串 “ ”
        if (str == null || str.length() < 1 || str.trim().equals("")) {
            flag = true;
            System.out.println("非法输入1：空指针 & 空字符串 “ ”");
            return 0;
        }


        // 字符串开头 = “+”号  / “-”号的处理
        char first = str.charAt(0);
        if (first == '-') {
            // 将该开头-符号传入 到 转换字符串操作 中
            return parseString(str, 1, false);

        } else if (first == '+') {
            // 将该开头+符号传入到转换字符串中
            return parseString(str, 1, true);


        } else if (first <= '9' && first >= '0') {
            // 或开头没有+、-符号，那数字必须是 ‘0’~‘9’
            return parseString(str, 0, true);

        } else {
            // 非法输入2：第1位含 ‘0’~‘9’、‘+’、‘ - ’ 以外的字符
            flag = true;
            System.out.println("非法输入2：第1位含 ‘0’~‘9’、‘+’、‘ - ’ 以外的字符");
            return 0;
        }
    }

    /**
     * 辅助算法1：解析字符串
     *
     * @param str      数字串
     * @param index    开始解析的索引
     * @param positive 是正数还是负数
     * @return 返回结果
     */
    private static int parseString(String str, int index, boolean positive) {
        // 非法输入3：当字符串仅有 "+" 或 "-"号时，也算非法输入
        if (index >= str.length()) {
            flag = true;
            System.out.println("非法输入3：字符串仅有 \"+\" 或 \"-\"号");
            return 0;
        }

        int result;
        long tmp = 0;

        // 非法输入4：其余位含 ‘0’~‘9’以外的字符
        while (index < str.length() && isDigit(str.charAt(index))) {
            // 若字符串中除第1位外，其余位 属于 ‘0’~‘9’，则转换字符串为整数
            // 转换原理：每扫描字符串中的1个字符，将之前得到的结果 乘以10 再加上 当前扫描字符表示的数字
            tmp = tmp * 10 + str.charAt(index) - '0';

            // 非法输入5：溢出
            // 保证求得的值不溢出，即，整数不能上下溢出 最大值不可大于Integer.MAX_VALUE (2^31-1) ，最小值不可小于Integer.MIN_VALUE(-2^31)
            // 根据字符串的第1位正 / 负，判断求得的值是否溢出
            if ((positive && tmp >Integer.MAX_VALUE) || (!positive && tmp < Integer.MIN_VALUE)) {

                flag = true;
                System.out.println("溢出了");
                return 0;
            }

            index++;
        }

        // 其余位不含 ‘0’~‘9’以外的字符时，输出转换后的整数
        if (index == str.length()) {
            if (positive) {
                result = (int) tmp;
                return result;
            } else {
                result = (int) -tmp;
                return result;
            }
        } else {
            flag = true;
            System.out.println("其余位含 ‘0’~‘9’以外的字符");
            return 0;
        }


    }

    /**
     * 辅助算法2：判断字符是否是数字
     *
     * @param c 字符
     * @return true是，false否
     */
    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {
        // 功能测试
        System.out.println(StrToInt("+345")); // 正数
        System.out.println("");
        System.out.println(StrToInt("-345")); // 负数
        System.out.println("");
        System.out.println(StrToInt("0")); // 0
        System.out.println("");

        // 异常输入测试：空指针、空字符串、、其余位 含 ‘0’~‘9’以外的字符、字符串 仅有"+" 或 "-"号
        System.out.println(StrToInt(null)); // 空指针
        System.out.println(flag);
        System.out.println("");

        System.out.println(StrToInt("")); // 空字符串
        System.out.println(flag);
        System.out.println("");

        System.out.println(StrToInt("a345")); // 第1位含 ‘0’~‘9’、‘+’、‘ - ’ 以外的字符
        System.out.println(flag);
        System.out.println("");

        System.out.println(StrToInt("345a")); // 其余位 含 ‘0’~‘9’以外的字符
        System.out.println(flag);
        System.out.println("");

        System.out.println(StrToInt("+")); // 字符串 仅有"+" 或 "-"号
        System.out.println(flag);
        System.out.println("");
    }
}