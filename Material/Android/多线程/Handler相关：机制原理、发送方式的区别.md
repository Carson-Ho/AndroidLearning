https://www.jianshu.com/p/f0b23ee5a922



# 1. 作用
在多线程的应用场景中，将工作线程中需更新UI的操作信息 传递到 UI主线程，从而实现 工作线程对UI的更新处理，最终实现异步消息的处理

# 2. 意义
多个线程并发更新UI的同时 保证线程安全

# 3. 角色介绍
Looper、Handler、MessageQueue

![示意图](http://upload-images.jianshu.io/upload_images/944365-d08903087cb575d9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 4. 原理
![示意图](http://upload-images.jianshu.io/upload_images/944365-c86c852fa0a64d5b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

  1. 应用程序主线程创建时，则自动为主线程创建1个循环器对象`（Looper）`、消息队列对象`（MessageQueue）` ；同时 进入消息循环
  2. 手动创建`Handler`实例时 ，会绑定所在当前线程的Looper & 对应的消息队列
  3. 创建消息对象（自己构建 & 根据`Runnable`）， 从而定义消息处理方式；
  4. 发送消息到 绑定线程的消息队列中（2种方式： `Handler.post（）`、`Handler.sendMessage（）`）
  5. 随着`Looper`对象的无限消息循环，不断从消息队列中取出`Handler`发送的消息 & 分发到对应`Handler`，最终回调`Handler.handleMessage()` 或 `Runnable.run（）`处理消息


![示意图](http://upload-images.jianshu.io/upload_images/944365-494e0b26a2724087.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![示意图](http://upload-images.jianshu.io/upload_images/944365-ab8502405221b5c6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 5. 对比 发送消息的2种方式： Handler.post（）、Handler.sendMessage（）

![示意图](https://upload-images.jianshu.io/upload_images/944365-29fc8832f4a8b399.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)