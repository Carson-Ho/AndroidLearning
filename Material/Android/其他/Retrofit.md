>https://www.jianshu.com/p/050c6db5af5a
>https://www.jianshu.com/p/a3e162261ab6


# 1. 使用原因
短心跳的网络请求（性能高）、使用简单（注解配置请求、封装性好），同时配合RxJava使用
![示意图](http://upload-images.jianshu.io/upload_images/944365-f48072d21b613aaf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 2. 原理
`Retrofit` 本质上是一个 `RESTful` 的` HTTP` 网络请求框架的封装，即通过 大量的设计模式 封装了 `OkHttp` ，使得简洁易用。具体过程如下：

1. `Retrofit` 将 `Http`请求 抽象 成 `Java`接口
2. 在接口里用 注解 描述和配置 网络请求参数
3. 用动态代理 的方式，动态将网络请求接口的注解 解析 成`HTTP`请求
4. 最后执行` HTTP`请求

最后贴一张非常详细的`Retrofit`源码分析图：

![Retrofit源码分析图](http://upload-images.jianshu.io/upload_images/944365-56df9f9ed647f7da.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)