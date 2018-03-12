

# 1. 定义
`Java`中的1个关键字

***

# 2. 作用
保证同一时刻最多只有1个线程执行 被`sychronized`修饰的方法 / 代码
>其他线程 必须等待当前线程执行完该方法 / 代码块后才能执行该方法 / 代码块

***
# 3. 应用场景

**保证线程安全，解决多线程中的并发同步问题（实现的是阻塞型并发）**，具体场景如下：

1. 修饰 实例方法 / 代码块时，（同步）保护的是同一个对象方法的调用 &  当前实例对象
2. 修饰 静态方法 / 代码块时，（同步）保护的是 静态方法的调用 & class 类对象

***
# 4. 原理
1. 依赖 `JVM` 实现同步
2. 底层通过一个监视器对象`（monitor）`完成， `wait（）`、`notify（）` 等方法也依赖于 monitor 对象
>监视器锁（monitor）的本质 依赖于 底层操作系统的互斥锁（Mutex Lock）实现

***

# 5. 具体使用
`sychronized` 用于 修饰 代码块、类的实例方法 & 静态方法

### 5.1 使用规则
![示意图](http://upload-images.jianshu.io/upload_images/944365-c4d981a833c3ff65.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 5.2 锁的类型 & 等级
- 由于`sychronized` 会修饰 代码块、类的实例方法 & 静态方法，故分为不同锁的类型
- 具体如下

![示意图](http://upload-images.jianshu.io/upload_images/944365-1cd5176979d4a9f6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 之间的区别

![示意图](http://upload-images.jianshu.io/upload_images/944365-73825030c7a118fd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 5.3 使用方式

```
/**
 * 对象锁
 */
    public class Test{ 
    // 对象锁：形式1(方法锁) 
    public synchronized void Method1(){ 
        System.out.println("我是对象锁也是方法锁"); 
        try{ 
            Thread.sleep(500); 
        } catch (InterruptedException e){ 
            e.printStackTrace(); 
        } 
 
    } 
 
    // 对象锁：形式2（代码块形式） 
    public void Method2(){ 
        synchronized (this){ 
            System.out.println("我是对象锁"); 
            try{ 
                Thread.sleep(500); 
            } catch (InterruptedException e){ 
                e.printStackTrace(); 
            } 
        } 
 
    } 
 ｝

/**
 * 方法锁（即对象锁中的形式1）
 */
    public synchronized void Method1(){ 
        System.out.println("我是对象锁也是方法锁"); 
        try{ 
            Thread.sleep(500); 
        } catch (InterruptedException e){ 
            e.printStackTrace(); 
        } 
 
    } 

/**
 * 类锁
 */
public class Test{ 
　　 // 类锁：形式1 ：锁静态方法
    public static synchronized void Method1(){ 
        System.out.println("我是类锁一号"); 
        try{ 
            Thread.sleep(500); 
        } catch (InterruptedException e){ 
            e.printStackTrace(); 
        } 
 
    } 
 
    // 类锁：形式2 ：锁静态代码块
    public void Method2(){ 
        synchronized (Test.class){ 
            System.out.println("我是类锁二号"); 
            try{ 
                Thread.sleep(500); 
            } catch (InterruptedException e){ 
                e.printStackTrace(); 
            } 
 
        } 
 
    } 
｝
```

### 5.4 特别注意
`synchronized`修饰方法时存在缺陷：若修饰1个大的方法，将会大大影响效率

- 示例
若使用`synchronized`关键字修饰 线程类的`run()`，由于`run()`在线程的整个生命期内一直在运行，因此将导致它对本类任何`synchronized`方法的调用都永远不会成功

- 解决方案
使用 `synchronized`关键字声明代码块

>该解决方案灵活性高：可针对任意代码块 & 任意指定上锁的对象

```
代码如下
  synchronized(syncObject) { 
    // 访问或修改被锁保护的共享状态 
    // 上述方法 必须 获得对象 syncObject（类实例或类）的锁
}
```
***
# 6. 特点
![示意图](http://upload-images.jianshu.io/upload_images/944365-afe57fc30465511a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

注：原子性、可见性、有序性的定义

![示意图](http://upload-images.jianshu.io/upload_images/944365-5285e9a40e83a041.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

***

# 7. 其他控制并发 / 线程同步方式
### 7.1 Lock、ReentrantLock
- 简介

![示意图](http://upload-images.jianshu.io/upload_images/944365-0579d1cac4d4c814.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 区别

![示意图](http://upload-images.jianshu.io/upload_images/944365-92abf2b6f92f6802.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 7.2 CAS
##### 7.2.1 定义
`Compare And Swap`，即 比较 并 交换，是一种解决并发操作的乐观锁
>`synchronized`锁住的代码块：同一时刻只能由一个线程访问，属于悲观锁

##### 7.2.2 原理

```
// CAS的操作参数
内存位置（A）
预期原值（B）
预期新值（C）

// 使用CAS解决并发的原理：
// 1. 首先比较A、B，若相等，则更新A中的值为C、返回True；若不相等，则返回false；
// 2. 通过死循环，以不断尝试尝试更新的方式实现并发

// 伪代码如下
public boolean compareAndSwap(long memoryA, int oldB, int newC){
    if(memoryA.get() == oldB){
        memoryA.set(newC);
        return true;
    }
    return false;
}


```


##### 7.2.3 优点
资源耗费少：相对于`synchronized`，省去了挂起线程、恢复线程的开销
>但，若迟迟得不到更新，死循环对`CPU`资源也是一种浪费

##### 7.2.4 具体实现方式
- 使用CAS有个“先检查后执行”的操作
- 而这种操作在Java中是典型的不安全的操作，所以 `CAS`在实际中是**由`C++`通过调用CPU指令实现的**
- 具体过程

```
// 1. CAS在Java中的体现为Unsafe类
// 2. Unsafe类会通过C++直接获取到属性的内存地址
// 3. 接下来CAS由C++的Atomic::cmpxchg系列方法实现
```



##### 7.2.5 典型应用：`AtomicInteger`
对 i++ 与 i--，通过`compareAndSet` & 一个死循环实现
>而`compareAndSet`函数内部 = 通过`jni`操作`CAS`指令。直到CAS操作成功跳出循环

```
   private volatile int value; 
    /** 
     * Gets the current value. 
     * 
     * @return the current value 
     */ 
    public final int get() { 
        return value; 
    } 
    /** 
     * Atomically increments by one the current value. 
     * 
     * @return the previous value 
     */ 
    public final int getAndIncrement() { 
        for (;;) { 
            int current = get(); 
            int next = current + 1; 
            if (compareAndSet(current, next)) 
                return current; 
        } 
    } 
 
    /** 
     * Atomically decrements by one the current value. 
     * 
     * @return the previous value 
     */ 
    public final int getAndDecrement() { 
        for (;;) { 
            int current = get(); 
            int next = current - 1; 
            if (compareAndSet(current, next)) 
                return current; 
        } 
    }
```

***
# 8. 总结
