package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/12/4.
 */

public class Exam_2 {

//    /**
//     * 枚举类型
//     */
//    public enum Singleton{
//
//        //定义1个枚举的元素，即为单例类的1个实例
//        INSTANCE;
//
//        // 隐藏了1个空的、私有的 构造方法
//        // private Singleton () {}
//
//    }
//
//    // 获取单例的方式：
//    Singleton singleton = Singleton.INSTANCE;
//
//    /**
//     * 静态内部类
//     */
//    class Singleton {
//
//        // 1. 创建静态内部类
//        private static class Singleton2 {
//            // 在静态内部类里创建单例
//            private static  Singleton ourInstance  = new Singleton()；
//        }
//
//        // 私有构造函数
//        private Singleton() {
//        }
//
//        // 延迟加载、按需创建
//        public static  Singleton newInstance() {
//            return Singleton2.ourInstance;
//        }
//
//    }

// 调用过程说明：
    // 1. 外部调用类的newInstance()
    // 2. 自动调用Singleton2.ourInstance
    // 2.1 此时单例类Singleton2得到初始化
    // 2.2 而该类在装载 & 被初始化时，会初始化它的静态域，从而创建单例；
    // 2.3 由于是静态域，因此只会JVM只会加载1遍，Java虚拟机保证了线程安全性
    // 3. 最终只创建1个单例

}
