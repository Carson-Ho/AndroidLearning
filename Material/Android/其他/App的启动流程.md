![示意图](https://upload-images.jianshu.io/upload_images/944365-a78148f5777ebf3f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点击桌面App图标：
1. Launcher进程通过BInder驱动向system_server进程发起启动Applicaiton进程的请求
2. system_server进程 向 Zygote进程发送创建进程的请求
3. Zygote进程fork出新的子进程，即App进程
4. App进程启动首个Activity的过程，具体如下：

![示意图](https://upload-images.jianshu.io/upload_images/944365-8746d7ac74220415.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

当请求启动Activity时：
1. Launcher进程通过Binder驱动向ActivityManagerService类发起startActivity请求；
2. ActivityManagerService类接收到请求后，向ActivityStack类发送启动Activity的请求；
3. ActivityStack类记录需启动的Activity的信息 & 调整Activity栈 将其置于栈顶、通过 Binder驱动 将Activity的启动信息传递到ApplicationThread线程中（即Binder线程）
4. ApplicationThread线程通过Handler将Activity的启动信息发送到主线程ActivityThread 
5. 主线程ActivityThread类接收到该信息 & 请求后，通过ClassLoader机制加载相应的Activity类，最终调用Activity的onCreate（），最后 启动完毕