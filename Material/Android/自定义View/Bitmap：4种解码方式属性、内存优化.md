# 1. 四种属性

不同的图片解码方式 对应的 内存占用大小 相差很大，具体如下
![示意图](http://upload-images.jianshu.io/upload_images/944365-d79f856e0559076b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 优化方案
根据需求 选择合适的解码方式
>1. 使用参数：`BitmapFactory.inPreferredConfig` 设置
>2. 默认使用解码方式：`ARGB_8888`


### 2. 内存优化方式
![示意图](http://upload-images.jianshu.io/upload_images/944365-1ac379cec2a945af.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

注：因存储位置不同，所以图片资源释放方式不同
- 优化方案
a. 在 `Android2.3.3（API 10）`前，调用 `Bitmap.recycle()`方法
b. 在 `Android2.3.3（API 10）`后，采用软引用`（SoftReference）`

- 具体描述
在 `Android2.3.3（API 10）`前、后，Bitmap对象 & 其像素数据 的存储位置不同，从而导致释放图片资源的方式不同，具体如下图

![示意图](http://upload-images.jianshu.io/upload_images/944365-8b6b26416a085516.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>注：若调用了`Bitmap.recycle()后`，再绘制`Bitmap`，则会出现`Canvas: trying to use a recycled bitmap`错误