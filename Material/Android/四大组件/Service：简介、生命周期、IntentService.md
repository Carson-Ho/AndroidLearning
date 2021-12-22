# 1. Service相关
https://carsonho.blog.csdn.net/article/details/53160049

# 2. 生命周期
https://carsonho.blog.csdn.net/article/details/53160137

2种启动方法的区别：生命周期调用过程不同、（功能上）无法操作Service、销毁时刻）

# 3. IntentService & 区别
https://carsonho.blog.csdn.net/article/details/90437126


# 4. 如何保证Service不被杀死

2个方面考虑：降低进程被杀死的概率（提高进程优先级）、在进程被杀死后，进行拉活

- Service保活

![示意图](https://upload-images.jianshu.io/upload_images/944365-b14633bc4f594bd8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 进程保活

![示意图](https://upload-images.jianshu.io/upload_images/944365-3568847da28a0f2a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
