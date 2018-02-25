https://www.jianshu.com/p/269873a16937

# 1. 需求本质
一键退出 `App` 其实是 两个需求：
1. 一键结束当前`App`所有的`Activity` 
2. 一键结束当前`App`进程

**即 需要2个步骤 才可 完成 一键退出 `App` 需求**。下面，我将根据这两个步骤进行功能实现讲解。

# 2. 功能实现

![示意图](http://upload-images.jianshu.io/upload_images/944365-2db15a6ac03d23b1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)