package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Carson_Ho on 17/11/29.
 */

public class Exam_62 {

    /**
     * 解题算法1：环形链表
     */
    public static int lastRemaining(int n, int m) {

        // 判断输入数据的合法性
        if (n < 1 || m < 1) {
            return -1;
        }
        
        // 为链表添加数据
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int index = 0; // 要删除元素的位置
        int start = 0; // 开始计数的位置

        while (list.size() > 1) {

            // 只要移动(m-1)次就可移动到下1个要删除的元素上
            for (int i = 1; i < m; i++) {
                index = (index + 1) % list.size();
            }

            list.remove(index);
        }

        return list.get(0);
    }

    /**
     * 解题算法2：数学分析法
     * 递归公式f（n,m） = [ f ( n-1,m ) + m ] % n
     * 可使用递归 & 循环实现，为了提高算法效率，采用 循环 实现
     */

    public static int lastRemaining2(int n, int m) {

        // 检查输入数据的合法性
        if (n < 1 || m < 1) {
            return -1;
        }

        // 根据规律进行计算：f（n,m） = [ f ( n-1,m ) + m ] % n
        int last = 0;
        for (int i = 2; i <=n ; i++) {
            last = (last + m)%i;
        }

        return last;
    }

    /**
     * 测试用例
     */

    public static void main(String[] args) {

        System.out.println(lastRemaining(5, 3));
        System.out.println(lastRemaining2(5, 3));
    }
}
