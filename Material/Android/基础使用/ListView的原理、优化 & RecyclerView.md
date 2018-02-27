# 1. 原理
### 1.1 本质原理

- `ListView`仅作为容器（列表），用于装载 & 显示数据（即 列表项`Item`）
- 而容器内的具体数据（列表项`Item`）则是由 适配器（`Adapter`）提供
>适配器（Adapter）：作为`View` 和 数据之间的桥梁 & 中介，将数据映射到要展示的`View`中

- 当需显示数据时，`ListView`会向`Adapter`取出数据，从而加载显示，具体如下图

![示意图](http://upload-images.jianshu.io/upload_images/944365-9337bc62b916d9de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 结论
`ListView`负责以列表的形式显示`Adapter`提供的内容


### 1.2 缓存原理
>试想一个场景：若把所有数据集合的信息都加载到`ListView`上显示，若 `ListView`要为每个数据都创建一个视图，那么会占用非常多的内存

- 为了节省空间和时间，`ListView`不会为每一个数据创建一个视图，而是采用了**Recycler组件**，用于回收  & 复用 `View`

- 当屏幕需显示`x`个`Item`时，那么`ListView`会创建 `x+1`个视图；当第1个`Item`离开屏幕时，此`Item`的`View`被回收至缓存，入屏的`Item`的`View`会优先从该缓存中获取
>注：
>1. 只有`Item`完全离开屏幕后才可复用，这也是为什么`ListView`要创建比屏幕需显示视图多1个的原因：缓冲 显示视图
>2. 即：第1个`Item`离开屏幕是有过程的，会有1个 **第1个`Item`的下半部分 & 第8个`Item`上半部分同时在屏幕中显示**的状态，此时仍无法使用缓存的`View`，只能继续用新创建的视图`View`



### 1.3 绘制过程（从代码的角度）
开始绘制时

1. 通过调用getCount（）获取ListView的长度
2. 通过调用getView（），根据ListView的长度逐一绘制ListView的每一行
3. 获得数据时，则通过getItem()、getItemId()获得Adapter中的数据

# 2. 优化
![示意图](http://upload-images.jianshu.io/upload_images/944365-bdf795f809b752de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 3. 与RecyclerView的区别
Google推出代替ListView的滑动组件，其功能更加强大、支持定制样式更加丰富、扩展性高

![示意图](http://upload-images.jianshu.io/upload_images/944365-32181e7fc382bfc3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)