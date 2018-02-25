# 1. 为什么要进行技术框架的设计
- 模块化功能
使得程序模块化，即：**内部的高聚合**、**模块之间的低耦合**
- 提高开发效率
开发人员只需专注于某一点（视图显示、业务逻辑 / 数据处理）
- 提高测试效率
方便后续的测试 & 定位问题

**切记：不要为了设计而设计，否则反而会提高开发量**
![示意图](http://upload-images.jianshu.io/upload_images/944365-3dbfb477d9550b93.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


***
# 2. Android开发主流的技术框架
主要有`MVC`、`MVP`、`MVVM`三种模式

### 2.1 MVC模式
- 角色说明

![示意图](http://upload-images.jianshu.io/upload_images/944365-359865d7f8f8f25b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



- 模式说明
![示意图](http://upload-images.jianshu.io/upload_images/944365-43da33270047629a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



- 该模式存在的问题：**Activity责任不明、十分臃肿**
`Activity`由于其生命周期的功能，除了担任`View`层的部分职责（加载应用的布局、接受用户操作），还要承担`Controller`层的职责（业务逻辑的处理）
随着界面的增多 & 逻辑复杂度提高，`Activity`类的代码量不断增加，越加臃肿


### 2.2 MVP模式
- 出现的原因
为了解决上述`MVC`模式存在的问题，把分离`Activity`中的`View`层 和 `Controller`层的职责，从而对Activity代码量进行优化、瘦身，所以出现了`MVP`模式

- 角色说明

![示意图](http://upload-images.jianshu.io/upload_images/944365-bd0579550f7ed1a2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



- 模式说明

![示意图](http://upload-images.jianshu.io/upload_images/944365-fb8ea1c32f1eccde.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 优点：（对比MVC模式）
  
1. 耦合度更低：通过`Presenter`实现数据和视图之间的交互，完全隔离了View层与Mode层，二者互不干涉
>避免了`View`、`Model`的直接联系，又通过`Presenter`实现两者之间的沟通
  2. `Activity`代码变得更加简洁：简化了`Activity`的职责，仅负责UI相关操作，其余复杂的逻辑代码提取到了`Presenter`层中进行处理 

- 与 MVC的区别

![示意图](http://upload-images.jianshu.io/upload_images/944365-167531b41d865327.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 2.3 MVVM
为了更加分离M、V层，更加释放Activity的压力，于是出现了MVVM模式

- 定义
`VM`层：`ViewModel`，即 View的数据模型和Presenter的合体
>基本上与 `MVP` 模式完全一致，将逻辑处理层 `Presenter` 改名为 `ViewModel`


- 模式说明

![示意图](http://upload-images.jianshu.io/upload_images/944365-c253297e2af0c09e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 优点
使得视图层`（View）`& 控制层`（Controller）`之间的耦合程度进一步降低，关注点分离更为彻底，同时减轻了`Activity`的压力

>本文主要讲解MVC和MVP模式，不过多阐述MVVM模式.

### 2.5 总结
-  `MVC`模式的出现
为解决**程序模块化**问题，于是MVC模式出现了：将业务逻辑、数据处理与界面显示进行分离来组织代码，即分成M、V、C层；
- `MVP`模式的出现
但M、V层还是有相互交叉、**隔离度不够**，同时写到Activity上使得Activity代码**臃肿**，于是出现了MVP： 隔离了MVC中的 M 与 V 的直接联系，将M、V层更加隔离开来，并释放了Activity的压力；
- `MVVM`模式的出现
为了**更加分离M、V层**，更加释放Activity的压力，于是出现了MVVM： 使得V和M层之间的耦合程度进一步降低，分离更为彻底，同时更加减轻了Activity的压力。