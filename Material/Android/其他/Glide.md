# 1. 简介
在主流的图片加载框架UIL、picasso、Glide、Fresco；其中Glide性能最好。这得益于其高效的图片缓存策略

![示意图](http://upload-images.jianshu.io/upload_images/944365-005e933ce9106de5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

通过，其还有多样化的媒体格式加载：如GIF、Video，对于商城首页需展示丰富样式、信息的页面需求来说，也是必不可少的。

# 2. 加载原理
- 使用分为3步：`Glide.with(this).load(url).into(imageView);`
- 加载原理也分为3步：.with（）、.load（）、.into（）

![示意图](http://upload-images.jianshu.io/upload_images/944365-ba0a2d4178643155.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#3. 缓存策略

- 缓存的图片资源

![示意图](http://upload-images.jianshu.io/upload_images/944365-14ad7a8e8de7c4ec.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 缓存机制设计
![示意图](http://upload-images.jianshu.io/upload_images/944365-c1a63398b7c352ee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![示意图](http://upload-images.jianshu.io/upload_images/944365-536ef22a7989ac4a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![示意图](http://upload-images.jianshu.io/upload_images/944365-02e1bbb94a52e978.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 4. 缓存问题
http://blog.csdn.net/carson_ho/article/details/79256773