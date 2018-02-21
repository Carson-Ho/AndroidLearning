package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.Stack;

/**
 * Created by Carson_Ho on 17/11/4.
 */

public class Exam_30 {

    /**
     * 测试用例
     */
    public static void main(String[] args){
        StackWithMin stack = new StackWithMin();
        stack.push(3);
        stack.push(4);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
    }

}

/**
* 解题类：含min函数的栈结构（类）
*/
class StackWithMin {

// 1. 定义2个栈：数据栈、辅助栈
private Stack<Integer> stackData = new Stack<Integer>();
private Stack<Integer> stackMin = new Stack<Integer>();

// 2. 定义入栈规则
public void push(int data){
    // 对于数据栈：正常入栈
    stackData.push(data);

    // 对于辅助栈：
    // 若入栈元素 < 栈顶元素（最小元素），正常入栈；
    // 若入栈元素 > 栈顶元素（最小元素），再次入栈 栈顶元素
    if(stackMin.isEmpty())
        stackMin.push(data);

    else{
        int temp = stackMin.peek();
        if(temp < data)
            stackMin.push(temp);
        else
            stackMin.push(data);
    }
}

// 3. 定义出栈规则
public void pop(){
    // 数据栈 & 辅助栈都正常出栈
    // 注：要判断空栈的情况
    if(!(stackMin.isEmpty())) {
        stackMin.pop();
    }

    if(!(stackData.isEmpty())) {
        stackData.pop();
    }

}

// 4. 获得当前数据栈最小元素（不是出栈）
// 根据上述入栈规则后，辅助栈保证了在与数据栈同步出栈时，每次出栈元素均为当前数据栈中的最小元素
// 故，若需获得当前数据栈最小元素，直接出栈辅助栈元素即可
public int min(){
    // 注：要判断空栈的情况
    if(stackMin.isEmpty())
        return -1;

    int num = stackMin.pop();
    stackMin.push(num);
    return num;
}

// 5. 获取数据栈栈顶元素
// 注：不是出栈
public int top(){
    // 注：要判断空栈的情况
    if(stackData.isEmpty())
        return -1;

    int num = stackData.pop();
    stackData.push(num);
    return num;
}
}
