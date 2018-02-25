# 1. 总体介绍
- `View`的绘制流程开始于：`ViewRootImpl`对象的`performTraversals()`

- **`View`的绘制流程从顶级`View（DecorView）`的`ViewGroup`开始，一层一层从`ViewGroup`至子`View`遍历测绘**
>即：自上而下遍历、由父视图到子视图、每一个 `ViewGroup` 负责测绘它所有的子视图，而最底层的 View 会负责测绘自身


- 绘制的流程 = `measure`过程、`layout`过程、`draw`过程，具体如下

![示意图](http://upload-images.jianshu.io/upload_images/944365-c1adb9dd2d22c056.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![示意图](http://upload-images.jianshu.io/upload_images/944365-9344bc3c61f5cbaf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

下面，我将详细讲解`View`绘制的三大流程：`measure`过程、`layout`过程、`draw`过程

# 2. 三大流程讲解
##### 流程1：Measure 过程
- 作用
测量`View`的宽 / 高
>1. 在某些情况下，需要多次测量`（measure）`才能确定`View`最终的宽/高；
>2. 该情况下，`measure`过程后得到的宽 / 高可能不准确；
>3. 此处建议：在`layout`过程中`onLayout()`去获取最终的宽 / 高

- 具体流程

![示意图](http://upload-images.jianshu.io/upload_images/944365-4654ff32550dc58c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



![示意图](http://upload-images.jianshu.io/upload_images/944365-1250b5f61c90147f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 详细讲解
请看文章：[自定义View Measure过程 - 最易懂的自定义View原理系列（2）](https://www.jianshu.com/p/1dab927b2f36)

***
##### 流程2：Layout过程
- 作用
计算视图`（View）`的位置
>即计算`View`的四个顶点位置：`Left`、`Top`、`Right` 和 `Bottom`

- 具体流程
![示意图](http://upload-images.jianshu.io/upload_images/944365-bb11305f1e40a8fb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![示意图](http://upload-images.jianshu.io/upload_images/944365-6baebb31c56040dc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 详细讲解

请看文章：[自定义View Layout过程 - 最易懂的自定义View原理系列（3）](https://www.jianshu.com/p/158736a2549d)

***
##### 流程3：Draw过程
- 作用
绘制`View`视图

- 具体流程

![示意图](http://upload-images.jianshu.io/upload_images/944365-53962940989bb451.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![示意图](http://upload-images.jianshu.io/upload_images/944365-c9d3cd1d746be319.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 详细讲解
请看文章：[（4）自定义View Draw过程- 最易懂的自定义View原理系列](https://www.jianshu.com/p/95afeb7c8335)