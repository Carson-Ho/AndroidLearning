package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/12/4.
 */

public class Exam_1 {
    private int tintl;
    private int tint2;
    private int tint3;
    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 功能测试1：赋值
        MyString s1 = new MyString("a");
        MyString s2 = new MyString("b");
        System.out.println(s1.assign(s2));

        // 功能测试2：连续赋值
        MyString s3 = new MyString("c");
        MyString s4 = new MyString("d");
        MyString s5 = new MyString("e");
        System.out.println("s3:" + s3.assign(s4).assign(s5));
        System.out.println("s4:" + s4);
        System.out.println("s5:" + s5);
    }




    /**
     * 解题算法
     */
    public static class MyString{

        private String data;

        // 构造函数
        public MyString(String data) {
            this.data = data;
        }

        // 赋值运算符函数
        // 考察1：需将函数的返回类型 声明为该类型的引用
        // 考察2：需将传入参数的类型 声明为常量引用
        public MyString assign(final MyString another){

            // 考察3：需判断传入参数 & 当前实例是否为1个实例
            // 若是，则不需进行赋值操作，直接返回，提高函数效率
            if(this == another || this.data.equals(another.data))
                return this;
            else{
                // 若不是，则进行赋值操作
                this.data = another.data;
                return this;
            }
        }

        @Override
        public String toString() {
            return "MyString{" +
                    "data='" + data + '\'' +
                    '}';
        }
    }


}
