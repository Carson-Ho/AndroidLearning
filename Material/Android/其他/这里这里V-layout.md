# 1. 简介

![示意图](http://upload-images.jianshu.io/upload_images/944365-b38a6f6665f42d6c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 2. 原理
- 通过自定义一个 `VirtualLayoutManager`（继承自 `LayoutManager`），用于管理一系列`LayoutHelper`
- 将具体的布局能力交给`LayoutHelper`来完成
- 从而 快速实现组合布局 的需求。

>每个 LayoutHelper负责 页面某一个范围内的布局

# 3. 工作流程
分为 2步：初始化 & 布局流程
### 1. 初始化：
• 设置VirtualLayoutAdapter
• 将源数据绑定到VirtualLayoutAdapter
• 根据数据生成LayoutHelper列表
• 对每个LayoutHelper位置做索引（即将LayoutHelper绑定到LayoutHelperFinder）
>此处暴露给使用者的主要是VirtualLayoutAdapter：它接收数据列表和LayoutHelper列表，内部在传递给RecyclerView和VirtualLayoutManager进行后续的工作。

### 2. 布局流程
  - 当完成初始化工作后，每当用户刚打开页面第一次渲染布局 或 用户滑动页面时，都会进行一次布局流程
  - 布局流程的本质是：
1. 自定义 VirtualLayoutManager从RecyclerView持续获取页面状态 &
 获取到下一个要填充的组件的位置信息
>主要是否存在未填充满组件的区域，即（空白处），同时计算该区域还有多大空间

2. VirtualLayoutManager通过LayoutHelperFinder找到对应的LayoutHelper

3. LayoutHelper会从让RecyclerView返回一个组件，从而实现对应的布局逻辑，从而快速实现组合布局 的需求
>1. 获取组件 = 缓存，逻辑：RecyclerView会尝试从回收池里获取一个被缓存的组件：
• 如果存在缓存组件，就直接返回给LayoutHelper使用；
• 如果不存在，则要调用VirtualLayoutAdapter去生成一个新的 组件实例，一步一步返回给LayoutHelper。
（该逻辑是RecyclerView的固有逻辑，即组件复用）

>2. 布局之前先对组件进行一次宽、高的测量计算
>• 宽度是LayoutHelper通过布局信息、样式等条件计算得到的；
• 而高度不由LayoutHelper决定，而是通过测量组件的高度来获取


4. 布局完成后，VirtualLayoutManager将布局完成消息（消耗了多少空白区域）反馈给RecyclerView
>RecyclerView进行状态更新：
• 如果空白区域都被填充满了，那么就结束一次布局了；
• 如果空白区域还未被填满，就触发下一个位置的布局，重复上述流程
•至此，整个布局流程讲解完毕