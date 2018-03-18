![示意图](http://upload-images.jianshu.io/upload_images/944365-df840a0ddb6f1564.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 1. 二叉树类
- 上述讲解的是基础的二叉树
- 根据不同的需求场景，二叉树分为许多类型，主要有：

![示意图](http://upload-images.jianshu.io/upload_images/944365-fd7848a0a43c93a8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 下面，我将详细讲解各种二叉树的类型

### 1.1 线索二叉树
- 简介

![示意图](http://upload-images.jianshu.io/upload_images/944365-9b5abaa89fbeffd6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 示意图
![示意图](http://upload-images.jianshu.io/upload_images/944365-ef0076eb9a6f1697.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 特别注意
  - 问：如何区别该指针 = 指向左（右）孩子 or 前驱（后继）
  - 答：增设标志域：Ltag 和 Rtag

![示意图](http://upload-images.jianshu.io/upload_images/944365-8f15fcc15780f983.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 1.2 二叉排序树
>也称：二叉查找树、二叉搜索树

- 特点
![示意图](http://upload-images.jianshu.io/upload_images/944365-134c13235e65780c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 作用 & 应用场景

![示意图](http://upload-images.jianshu.io/upload_images/944365-a6da92a52867d3f8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 1.3 平衡二叉排序树（AVL树）
>属于 二叉搜索树的一种特殊类型

- 特点

![示意图](http://upload-images.jianshu.io/upload_images/944365-694c7327e8168f40.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 具体介绍

![示意图](http://upload-images.jianshu.io/upload_images/944365-57588e7eea1b073a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 1.4 红黑树
>属于 二叉搜索树的一种特殊类型

![示意图](http://upload-images.jianshu.io/upload_images/944365-fd3f325c087ad902.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 1.5 赫夫曼树
http://www.cnblogs.com/mcgrady/p/3329825.html

- 简介

![示意图](http://upload-images.jianshu.io/upload_images/944365-520cb2d728efa411.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 哈夫曼树算法
即，如何找出哈弗曼树。具体算法请看下图

![算法描述](http://upload-images.jianshu.io/upload_images/944365-596ce935ae0c6fb3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![示意图](http://upload-images.jianshu.io/upload_images/944365-f9049d27d3e9c50f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 哈夫曼编码

http://blog.csdn.net/lfeng_coding/article/details/47782141

![示意图](http://upload-images.jianshu.io/upload_images/944365-25897c3d7b1438bf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### 1.6 其他类型（特殊形态）
包括：斜树、满二叉树  & 完全二叉树

![示意图](http://upload-images.jianshu.io/upload_images/944365-15db3e2dc8363165.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 多路查找树、哈夫曼树属于 动态查找的算法

# 2. 多路查找树（含B+树）

![示意图](http://upload-images.jianshu.io/upload_images/944365-91cc2b930711007c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![示意图](http://upload-images.jianshu.io/upload_images/944365-73b570c3daa82b0a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)