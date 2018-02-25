https://juejin.im/post/5a80dfe4f265da4e9b591002

# 1. 角色介绍
Looper、Handler、MessageQueue

![示意图](http://upload-images.jianshu.io/upload_images/944365-d08903087cb575d9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 2. 原理

  1. 应用程序主线程创建时，则自动为主线程创建1个构造器对象`（Looper）`、消息队列对象`（MessageQueue）` & 进入消息循环
  2. 手动创建`Handler`实例时 ，会绑定所在当前线程 & 对应的消息队列
  3. 自己构建 & 根据`Runnable`创建消息对象 从而定义消息处理方式
  4. 随着`Looper`对象的无限消息循环，不断从消息队列中取出`Handler`发送的消息 & 分发到对应`Handler`，最终回调`Handler.handleMessage()` 或 `Runnable.run（）`处理消息


![示意图](http://upload-images.jianshu.io/upload_images/944365-62eb790fbcdff4cd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 3. 发送消息的2种方式： Handler.post（）、Handler.sendMessage（）

- 同
工作流程类似，Handler.post（）内部 发送到消息队列的逻辑 = 方式1中sendMessage（Message msg）

- 异

![示意图](http://upload-images.jianshu.io/upload_images/944365-5a892e1818d29b9e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)