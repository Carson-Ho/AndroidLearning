package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/13.
 */

public class Exam_41Test {

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 测试用例
        // 功能测试
        System.out.println("功能测试");
        Exam_41Solution test = new Exam_41Solution();
        // 通过循环插入，代表动态从数据流获取数据
        for(int i = 0; i < 10; ++i) {
            test.Insert(i);
            System.out.print(test.GetMedian() + "  ");
        }
        System.out.println();

        // 特殊输入测试：无数据输入
        System.out.println("特殊输入测试");
        Exam_41Solution test1 = new Exam_41Solution();
        System.out.print(test1.GetMedian() + "  ");

    }

}
