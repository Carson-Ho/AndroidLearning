# 1. 图的BFS、DFS算法

![示意图](http://upload-images.jianshu.io/upload_images/944365-30f33ecf7fc173d6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



# 2. 最小生成树prim算法
### 2.1 简介

![示意图](http://upload-images.jianshu.io/upload_images/944365-9c42cc205c683fe5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2.2 与 （Kruskal）克鲁斯卡尔算法 对比

同样是 用于生成最小生成树

![示意图](http://upload-images.jianshu.io/upload_images/944365-9260083dd53646ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 3. 最短路径 Dijkstra 算法

### 3.1 最短路径定义
- 对于非网图（无权值），最短路径 = 两顶点间经过的边数最少的路径
- 对于网图（有权值）：最短路径 = 两顶点间经过的边上权值和最少的路径
>第1个顶点 = 源点、第2个顶点 = 终点

### 3.2 寻找最短路径 算法
- 主要包括：迪杰斯特拉算法`（Dijkstra）`、弗洛伊德算法`（Floyd）`

- 具体介绍如下

![示意图](http://upload-images.jianshu.io/upload_images/944365-6fef2f991765a7e9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)