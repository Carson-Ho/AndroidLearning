>https://www.jianshu.com/p/a406b94f3188

# 1. 为什么要使用
可实现异步操作的功能需求外，基于事件流的链式调用 的使用方式 使得实现优雅、逻辑简洁 & 使用简单

# 2. 原理
基于 一种扩展的观察者模式，被观察者 （Observable） 通过 订阅（Subscribe） 按顺序发送事件 给观察者 （Observer）， 观察者（Observer） 按顺序接收事件 & 作出对应的响应动作

- 观察者模式

![示意图](http://upload-images.jianshu.io/upload_images/944365-13978bc10951c665.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)