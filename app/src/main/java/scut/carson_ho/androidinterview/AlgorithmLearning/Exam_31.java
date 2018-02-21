package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.Stack;

/**
 * Created by Carson_Ho on 17/11/5.
 */

public class Exam_31 {

    /**
     * 解题算法
     */
    public static boolean isPopOrder(int[] pushSeq,int[] popSeq){

        // 判断输入数据的合法性：传入参数是否为null，压入序列与弹出序列长度是否一致
        if(pushSeq == null || popSeq == null|| pushSeq.length != popSeq.length)
            return false;

        // 设置2指针分别指向压入序列、弹出序列
        int pushSeqIndex=0,popSeqIndex=0;

        // 创建1个栈 用于辅助判断
        Stack<Integer> stack = new Stack<>();

        // 若指向弹出序列的指针已到序列结尾，则代表被测序列 = 弹出序列
        // 即，循环跳出
        while (popSeqIndex < popSeq.length){

            // 若栈顶元素 ≠ 弹出序列指针指向元素时，入栈 压栈序列指针指向的元素，指针后移1位
            if(stack.isEmpty()||stack.peek()!=popSeq[popSeqIndex]) {
                if(pushSeqIndex < pushSeq.length ) {
                    stack.push(pushSeq[pushSeqIndex]);
                    pushSeqIndex++;

                }else
                    // 若指向压栈序列的指针p1已到序列结尾，则代表被测序列 ≠ 弹出序列
                    return false;
            }
            // 否则，出栈 栈顶元素，弹出序列指针向后移1位
            else{
                stack.pop();
                popSeqIndex++;
            }
        }
        return true;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 压入序列
        int[] push = {1,2,3,4,5};

        // 判断弹出序列
        int[] pop1 = {4,5,3,2,1};
        int[] pop2 = {4,3,5,1,2};
        System.out.println(isPopOrder(push,pop1));
        System.out.println(isPopOrder(push,pop2));
        // 特殊输入测试
        System.out.println(isPopOrder(null,null));
    }


}
