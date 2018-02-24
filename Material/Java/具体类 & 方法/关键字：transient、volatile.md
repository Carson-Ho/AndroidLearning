# 1. transient
- 作用：防止被序列化

>即 被该关键字修饰 的字段，仅存于 调用者的内存，而不会写到磁盘里持久化

- 背景知识
序列化的定义：将待续列化的对象中的信息写入到磁盘或网络流中。

>1. 序列化中的引用会进行“深度复制“
>2. 不会重复创建： 若2个对象有共同的引用对象，且两个对象都写入同一个流，那么该引用对象不会重复创建，只会创建一次，并还原到虚拟机后引用的还是同一个对象
>3. 注：但 若写入的是不同的流中，那么2次创建的是完全不同还原对象


# 2. volatile
- 储备知识

![示意图](http://upload-images.jianshu.io/upload_images/944365-aae6879f89769c4a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 作用
保证 可见性 & 有序性，但不保证原子性

![示意图](http://upload-images.jianshu.io/upload_images/944365-ac37066fc9786f05.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 总结

由于`volatile`保证可见性和有序性，被volatile修饰的共享属性一般并发读/写没有问题，可看做是一种轻量级的synchronized的实现

- 背景知识
**线程操作堆中对象时，是通过副本变量而不是原子性的。**
  1. 每个线程 第一次 访问 堆中对象时，会将堆中对象加载到线程本地内存中，建立一个变量副本；
  2. 之后若线程需修改该对象时，则是直接修改副本变量值，而不再和对象在堆变量值有任何关系
  3. 修改后，自动把线程变量副本的值写到对象在堆变量中。这样堆中对象的值就产生了变化


https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650824896&idx=1&sn=a7d908267a69a58e4634f14b29505100&chksm=80b7b45eb7c03d48b07d9dc1f6fd643c1591d118f468ede4a86e9b85b81defb3dc7d2a033e2c&mpshare=1&scene=1&srcid=0124FvAFpdu8qzcWP12w68hi&key=0d08a39b58f4822cd4ff9cb0e86385cef2485aa4cfce5b1b4a58952fded45f2e5253777a4f1162e3a6934d8ba9763d43979602a1338b19e042c0d1d2feca62253a36691048bfe7e0fbdbd4737409d572&ascene=0&uin=MjE3MzUxMjQyMQ%3D%3D&devicetype=iMac+MacBookPro11%2C1+OSX+OSX+10.11.3+build(15D21)&version=11020201&lang=zh_CN&pass_ticket=CaTAC7WCW4Us%2BuKOktwUOihC75xqi%2B3R8GnoDaTJDm3DOVReOu8HVpMKt%2BW2hJcY
https://juejin.im/post/5a2b53b7f265da432a7b821c