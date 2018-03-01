# 1. Service相关
https://www.jianshu.com/p/d963c55c3ab9
# 2. 生命周期
https://www.jianshu.com/p/8d0cde35eb10

2种启动方法的区别：生命周期调用过程不同、（功能上）无法操作Service、销毁时刻）

# 3. IntentService & 区别
https://www.jianshu.com/p/8a3c44a9173a


# 4. 如何保证Service不被杀死

2个方面考虑：降低进程被杀死的概率（提高进程优先级）、在进程被杀死后，进行拉活

![示意图](http://upload-images.jianshu.io/upload_images/944365-80ddb22ddb05763a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![示意图](http://upload-images.jianshu.io/upload_images/944365-314b51661a6945d1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)