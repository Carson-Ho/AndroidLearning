# 1. 泛型介绍 、原理 & 作用特点
![示意图](http://upload-images.jianshu.io/upload_images/944365-82a1204d44a19d92.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 2.  List<String>能否转为List<Object>

- 结论：不行
- 具体描述

![示意图](http://upload-images.jianshu.io/upload_images/944365-b001acf2eeafbecc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
// 代码1和代码2相同
// 代码1
List<String> strings = new LinkedList<String>( ); 
 List<Integer> ints = new LinkedList<Integer>( );

// 代码2
 List strings = new LinkedList( ); 
 List ints = new LinkedList( );

// 转换方式可以是如下：
List ss=strings; 
List<Object> objects=ss;
```

# 3. Java泛型与C++泛型区别
 `Java`中的泛型的实现原理是基于：**类型擦拭**

![示意图](http://upload-images.jianshu.io/upload_images/944365-7b3c9cc4706f566b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
