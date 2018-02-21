package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/20.
 */

public class Exam_5 {

    /**
     * 替换空格
     * @param str = 需替换的字符串
     * @return 返回替换后的新序列
     */

    private static String answer(StringBuffer str) {

        // 1. 检查输入的合法性
        if(str == null)
            return null;

        // 2. 求出当前字符串的长度
        int originLength = str.length();

        // 3. 通过遍历字符串，统计出字符串中的空格个数
        int numOfBlank = 0; // 记录空格数
        for (int i = 0; i < originLength; i++) {
            if( str.charAt(i) ==' ')
                numOfBlank++;
        }

        // 4. 计算替换后的字符串总长度，增加所需内存空间
        int newLength = originLength + numOfBlank*2; // 替换后的字符串长度
        str.setLength(newLength); // 增加所需内存空间

        // 5. 从尾 -> 头 复制 字符串 & 替换空格
        // 通过2个指针辅助：从后->前移动
        int indexOfOriginal = originLength-1; // P1指向旧字符串末尾
        int indexOfNew = newLength-1; // P2指向替换后的字符串末尾

        // 6. 当2指针不相等时，移动指针进行字符串复制 & 替换
        // 相等时就跳出循环
        while(indexOfOriginal >= 0 && indexOfOriginal != indexOfNew){

            // P1指向空格后：
            if(str.charAt(indexOfOriginal)==' '){

                // 在P2前插入“%20”，并将其向前移动3格
                str.setCharAt(indexOfNew--, '0');
                str.setCharAt(indexOfNew--, '2');
                str.setCharAt(indexOfNew--, '%');
                indexOfOriginal--; // P1向前移动1格

            }else {
                // 向前移动指针P1，逐步将它指向的字符复制到P2指针，直到P1指向空格为止
                // 先复制，再移动

                str.setCharAt(indexOfNew--, str.charAt(indexOfOriginal--));

            }
        }


        // 8. 返回结果
        return str.toString();

    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 功能测试1：字符串中含空格
        StringBuffer string1 =new StringBuffer("We are happy");
        System.out.println(answer(string1));

        // 功能测试2：字符串中不含空格
        StringBuffer string2 =new StringBuffer("Wearehappy");
        System.out.println(answer(string2));

        // 特殊输入测试：为空指针、为空字符串、只有1个空格字符
        System.out.println(answer(null));

        StringBuffer string3 =new StringBuffer("");
        System.out.println(answer(string3));

        StringBuffer string4 =new StringBuffer(" ");
        System.out.println(answer(string4));

    }

}
