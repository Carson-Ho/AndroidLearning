
http://blog.qianxuefeng.com/article/153

### 1. 简介

![示意图](http://upload-images.jianshu.io/upload_images/944365-f30c79c9e6d99d23.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2. 使用
#### 2.1 创建ThreadLocal变量
共有3种方式

```
// 1. 直接创建对象
private ThreadLocal myThreadLocal = new ThreadLocal()

// 2. 创建泛型对象
private ThreadLocal myThreadLocal = new ThreadLocal<String>();

// 3. 创建泛型对象 & 初始化值
// 指定泛型的好处：不需要每次对使用get()方法返回的值作强制类型转换
private ThreadLocal myThreadLocal = new ThreadLocal<String>() {
    @Override
    protected String initialValue() {
        return "This is the initial value";
    }
};

// 特别注意：
// 1. ThreadLocal实例 = 类中的private、static字段
// 2. 只需实例化对象一次 & 不需知道它是被哪个线程实例化
// 3. 每个线程都保持 对其线程局部变量副本 的隐式引用
// 4. 线程消失后，其线程局部实例的所有副本都会被垃圾回收（除非存在对这些副本的其他引用）
// 5. 虽然所有的线程都能访问到这个ThreadLocal实例，但是每个线程只能访问到自己通过调用ThreadLocal的set（）设置的值
 // 即 哪怕2个不同的线程在同一个`ThreadLocal`对象上设置了不同的值，他们仍然无法访问到对方的值
```

#### 2.2 访问ThreadLocal变量

```
// 1. 设置值：set()
// 需要传入一个Object类型的参数
myThreadLocal.set("初始值”);

// 2. 读取ThreadLocal变量中的值：get()
// 返回一个Object对象
String threadLocalValue = (String) myThreadLocal.get();
```

#### 2.3 具体使用
- 测试代码

```
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
```
- 测试结果

```
线程1：线程1的threadLocal
线程2：线程2的threadLocal

// 从上述结果看出，在2个线程分别设置ThreadLocal值 & 分别获取，结果并未互相干扰
```

### 2.4 实现原理
- 核心原理
`ThreadLocal`类中有1个`Map`（称：`ThreadLocalMap`）：用于存储每个线程 & 该线程设置的存储在`ThreadLocal`变量的值
>1. `ThreadLocalMap`的键`Key` = 当前`ThreadLocal`实例、值`value` = 该线程设置的存储在`ThreadLocal`变量的值
>2. 该`key`是 `ThreadLocal`对象的弱引用；当要抛弃掉`ThreadLocal`对象时，垃圾收集器会忽略该 `key`的引用而清理掉`ThreadLocal`对象 


- 关于如何设置 & 获取 `ThreadLocal`变量里的值，具体请看下面的源码分析
>请直接看代码注释

```

// ThreadLocal的源码

public class ThreadLocal<T> {

	...

  /** 
    * 设置ThreadLocal变量引用的值
    *  ThreadLocal变量引用 指向 ThreadLocalMap对象，即设置ThreadLocalMap的值 = 该线程设置的存储在ThreadLocal变量的值
    *  ThreadLocalMap的键Key = 当前ThreadLocal实例
    *  ThreadLocalMap的值 = 该线程设置的存储在ThreadLocal变量的值
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
    * 获取ThreadLocal变量里的值
    * 由于ThreadLocal变量引用 指向 ThreadLocalMap对象，即获取ThreadLocalMap对象的值 = 该线程设置的存储在ThreadLocal变量的值
    **/ 
    public T get() {

        // 1. 获得当前线程
        Thread t = Thread.currentThread();

        // 2. 获取该线程的ThreadLocalMap对象
        ThreadLocalMap map = getMap(t);

        // 3. 若该线程的ThreadLocalMap对象已存在，则直接获取该Map里的值；否则则通过初始化函数创建1个ThreadLocalMap对象
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
    * 分析1：获取当前线程的threadLocals变量引用
    **/ 
    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }

  /** 
    * 分析2：创建当前线程的ThreadLocalMap对象
    **/ 
    void createMap(Thread t, T firstValue) {
    // 新创建1个ThreadLocalMap对象 放入到 Thread类的threadLocals变量引用中：
        // a. ThreadLocalMap的键Key = 当前ThreadLocal实例
        // b. ThreadLocalMap的值 = 该线程设置的存储在ThreadLocal变量的值
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
       // 线程类实例化后，每个线程对象拥有独立的threadLocals变量变量
       // threadLocals变量在 ThreadLocal对象中 通过set（） 或 get（）进行操作

       ...
}


```

### 2.5 ThreadLocal如何做到线程安全
- 每个线程拥有自己独立的`ThreadLocals`变量（指向`ThreadLocalMap`对象 ）
- 每当线程 访问 `ThreadLocals`变量时，访问的都是各自线程自己的`ThreadLocalMap`变量（键 - 值）
- `ThreadLocalMap`变量的键 `key` = 当前ThreadLocal实例

上述3点 保证了线程间的数据访问隔离，即线程安全

- 测试代码

```
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
```
- 测试结果

```
线程1：线程1的threadLocal
线程2：线程2的threadLocal

// 从上述结果看出，在2个线程分别设置ThreadLocal值 & 分别获取，结果并未互相干扰
```

### 2.6 与同步机制的区别
![示意图](http://upload-images.jianshu.io/upload_images/944365-2a901aa6887eeac8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)