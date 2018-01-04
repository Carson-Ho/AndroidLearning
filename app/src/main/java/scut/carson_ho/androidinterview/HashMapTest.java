package scut.carson_ho.androidinterview;

/**
 * Created by Carson_Ho on 17/12/26.
 */

public class HashMapTest {

    public class ThreadLocalTest {

        // 测试代码
        public static void main(String[] args){
            // 新开2个线程用于设置 & 获取 ThreadLoacl的值
            MyRunnable runnable = new MyRunnable();
            new Thread(runnable, "线程1").start();
            new Thread(runnable, "线程2").start();
        }

        // 线程类
        public static class MyRunnable implements Runnable {

            // 创建ThreadLocal & 初始化
            private ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
                @Override
                protected String initialValue() {
                    return "初始化值";
                }
            };

            @Override
            public void run() {

                // 运行线程时，分别设置 & 获取 ThreadLoacl的值
                String name = Thread.currentThread().getName();
                threadLocal.set(name + "的threadLocal"); // 设置值 = 线程名
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "：" + threadLocal.get());
            }
        }
    }


// ThreadLocal的源码

    public class ThreadLocal<T> {

	...

        /**
         * 设置ThreadLocal的值：ThreadLocalMap的键Key = 线程
         **/
        public void set(T value) {

            // 1. 获得当前线程
            Thread t = Thread.currentThread();

            // 2. 获取该线程的ThreadLocalMap对象 ->>分析1
            ThreadLocalMap map = getMap(t);

            // 3. 若该线程的ThreadLocalMap对象已存在，则替换该Map里的值；否则创建1个ThreadLocalMap对象
            if (map != null)
                map.set(this, value);// 替换
            else
                createMap(t, value);// 创建->>分析2
        }

        /**
         * 获取ThreadLocal的值：ThreadLocalMap的值 = 该线程设置的存储在ThreadLocal变量的值
         **/
        public T get() {

            // 1. 获得当前线程
            Thread t = Thread.currentThread();

            // 2. 获取该线程的ThreadLocalMap对象
            ThreadLocalMap map = getMap(t);

            // 3. 若该线程的ThreadLocalMap对象已存在，则直接获取该Map里的值；否则创建1个ThreadLocalMap对象
            if (map != null) {
                ThreadLocalMap.Entry e = map.getEntry(this);
                if (e != null)
                    return (T)e.value; // 直接获取值
            }
            return setInitialValue(); // 初始化
        }

        /**
         * 初始化ThreadLocal的值
         **/
        private T setInitialValue() {

            T value = initialValue();

            // 1. 获得当前线程
            Thread t = Thread.currentThread();

            // 2. 获取该线程的ThreadLocalMap对象
            ThreadLocalMap map = getMap(t);

            // 3. 若该线程的ThreadLocalMap对象已存在，则直接替换该值；否则则创建
            if (map != null)
                map.set(this, value); // 替换
            else
                createMap(t, value); // 创建->>分析2
            return value;
        }


        /**
         * 分析1：获取当前线程的ThreadLocalMap对象
         **/
        ThreadLocalMap getMap(Thread t) {
            return t.threadLocals;
        }

        /**
         * 分析2：创建当前线程的ThreadLocalMap对象
         **/
        void createMap(Thread t, T firstValue) {
            // 新创建1个ThreadLocalMap对象 放入到 Thread类的threadLocals对象引用中
            t.threadLocals = new ThreadLocalMap(this, firstValue);
            // 即 threadLocals变量 属于 Thread类中 ->> 分析3
        }


    ...
    }

    /**
     * 分析3：Thread类 源码分析
     **/

    public class Thread implements Runnable {
       ...

        ThreadLocal.ThreadLocalMap threadLocals = null;
        // 即 Thread类持有threadLocals变量
        // threadLocals变量是线程类实例化后，每个对象拥有的独立的变量
        // threadLocals变量在 ThreadLocal对象中 通过set（） 或 get（）进行操作



       ...
    }







}
