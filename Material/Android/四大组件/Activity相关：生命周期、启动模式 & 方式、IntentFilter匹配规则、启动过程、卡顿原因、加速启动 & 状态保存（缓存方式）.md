# 1. Activity与Fragment的生命周期
看简书文章

- Activity
![示意图](http://upload-images.jianshu.io/upload_images/944365-8f75c4ffd13eab1f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- Fragment
![示意图](http://upload-images.jianshu.io/upload_images/944365-9f4000d129ae5597.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 二者对比
![与Fragment生命周期对比](http://upload-images.jianshu.io/upload_images/944365-0f9670e55a52403c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 2. Activity的启动模式、IntentFilter匹配规则
https://www.jianshu.com/p/399e83d02e33

- 如下图

![示意图](http://upload-images.jianshu.io/upload_images/944365-b6244df4e6c21315.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 区别

![示意图](http://upload-images.jianshu.io/upload_images/944365-f8ff1efde0a29112.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 匹配规则

![示意图](http://upload-images.jianshu.io/upload_images/944365-b7156549d2e3d095.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 3. 启动Activity的方式
使用`Intent`的显式 & 隐式 启动`Activity`
- 显式（3种）

```
// 1. 使用构造函数 传入 Class对象
 Intent intent = new Intent(this, SecondActivity.class); 
 startActivity(intent);

// 2. 使用 setClassName（）传入 包名+类名 / 包Context+类名
 Intent intent = new Intent(); 
 // 方式1：包名+类名
 // 参数1 = 包名称
 // 参数2 = 要启动的类的全限定名称 
 intent.setClassName("com.hc.hctest", "com.hc.hctest.SecondActivity"); 

 // 方式2：包Context+类名
 // 参数1 = 包Context，可直接传入Activity
 // 参数2 = 要启动的类的全限定名称 
 intent.setClassName(this, "com.hc.hctest.SecondActivity"); 

 startActivity(intent);

// 3. 通过ComponentName（）传入 包名 & 类全名
 Intent intent = new Intent(); 
 // 参数1 = 包名称
 // 参数2 = 要启动的类的全限定名称 
 ComponentName cn = new ComponentName("com.hc.hctest", "com.hc.hctest.SecondActivity"); 
 intent.setComponent(cn); 
 startActivity(intent);


```

- 隐式

```
// 通过Category、Action设置
Intent intent = new Intent(); 
intent.addCategory(Intent.CATEGORY_DEFAULT); 
intent.addCategory("com.hc.second"); 
intent.setAction("com.hc.action"); 
startActivity(intent);
```

# 4. Activity启动过程
![示意图](https://upload-images.jianshu.io/upload_images/944365-8746d7ac74220415.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

当请求启动Activity时：


1. Launcher进程通过Binder驱动向ActivityManagerService类发起startActivity请求；
2. ActivityManagerService类接收到请求后，向ActivityStack类发送启动Activity的请求；
3. ActivityStack类记录需启动的Activity的信息 & 调整Activity栈 将其置于栈顶、通过 Binder驱动 将Activity的启动信息传递到ApplicationThread线程中（即Binder线程）
4. ApplicationThread线程通过Handler将Activity的启动信息发送到主线程ActivityThread 
5. 主线程ActivityThread类接收到该信息 & 请求后，通过ClassLoader加载相应的Activity类，最终调用Activity的onCreate（），最后 启动完毕

# 5. Activity卡顿原因

![示意图](http://upload-images.jianshu.io/upload_images/944365-d052fd1a02f5b4a2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![示意图](http://upload-images.jianshu.io/upload_images/944365-ee7646a45eac46e9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 6. 如何加速启动Activity
![示意图](http://upload-images.jianshu.io/upload_images/944365-19fa54eef58f1744.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 7. Activtiy的状态保存（Activity缓存方式）
- 问题描述

![示意图](http://upload-images.jianshu.io/upload_images/944365-8fe90db664407e3b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 具体说明

![示意图](http://upload-images.jianshu.io/upload_images/944365-305b102f39ac27ce.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)