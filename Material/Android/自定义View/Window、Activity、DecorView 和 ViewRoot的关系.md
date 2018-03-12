# 1. 简介
![示意图](http://upload-images.jianshu.io/upload_images/944365-b9c41aa994e8ddf4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 2. 工作流程机制
![示意图](http://upload-images.jianshu.io/upload_images/944365-34992eb46bdf93e7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 每个 Activity 包含了一个 Window 对象 = PhoneWindow实现类对象
- PhoneWindow 将 DecorView 作为了一个应用窗口的根 View
- DecorView将屏幕划分为2个区域：TitleView、ContentView

我们平时在 Xml 文件中写的布局正好是展示在 ContentView 中的


# 3. 源码分析
![示意图](http://upload-images.jianshu.io/upload_images/944365-7628f5c6bdc57a0c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)