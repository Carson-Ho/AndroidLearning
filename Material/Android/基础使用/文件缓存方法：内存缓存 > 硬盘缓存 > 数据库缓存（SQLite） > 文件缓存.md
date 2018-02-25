# 1. 内存缓存
### 1.1 原理
![示意图](http://upload-images.jianshu.io/upload_images/944365-7cfbaadd831227af.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 1.2 示意图
![示意图](http://upload-images.jianshu.io/upload_images/944365-3471a714b98004b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 1.3 源码流程
![示意图](http://upload-images.jianshu.io/upload_images/944365-f25df45916b6178c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 2. 硬盘缓存
### 2.1 原理：LRU 算法
- 定义：`Least Recently Used`，即 **近期最少使用算法**
- 算法原理：当缓存满时，优先淘汰 近期最少使用的缓存对象
>采用 `LRU` 算法的缓存类型：内存缓存（`LrhCache`）  、  硬盘缓存（`DisLruCache`）

### 2.2 应用场景
磁盘文件缓存 适用于 内存缓存不可用时
>内存缓存不可用的情况：内存满了、`App`应用进程被杀死（内存缓存对象也被销毁）时

### 2.3 具体使用
操作时间长，故需在工作线程中进行

```
// 对磁盘缓存的操作（初始化、存储 & 读取）的时间 慢于 内存缓存 & 不能够保证，故需在工作线程中进行
// 内存缓存可在UI线程检测
// 1. 初始化
private DiskLruCache mDiskLruCache; = DiskLruCache.open(cacheDir, DISK_CACHE_SIZE); 

// 2. 添加到缓存
mMemoryCache.put(key, bitmap)；

// 3. 获取缓存
mDiskLruCache.get(key)；

```
### 2.4 特别注意：路径设置问题 & 设备配置参数改变时加载问题


### 2.4.1 路径设置问题
- `DiskLruCache`可自由设定缓存数据 的路径
- 通常情况下会选择存储在SD卡，即  /sdcard/Android/data/<应用的包名>/cache
>该路径被Android系统认定为应用程序的缓存路径

原因：
1. SD卡容量大 & 不影响手机内存
2. 当程序被卸载时，此处数据也会一起被清除，不会出现删除程序后手机仍有残留数据的问题

#### 2.4.2 设备配置参数改变时加载问题

![示意图](http://upload-images.jianshu.io/upload_images/944365-d647525c880094a7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 3. 数据库缓存（SQLite）
加载时根据url先从数据库中查询；若查询到 & 时间未过期，就根据路径读取本地文件，从而实现缓存的效果
>缓存路径：/data/data//databases/

# 4. 文件缓存
- 需缓存的数据存储在文件中
- 下次加载时判断文件是否存在 & 过期
>使用 `File.lastModified()`得到文件的最后修改时间，与当前时间判断
- 若存在 & 未过期 则 加载文件中的数据，否则请求服务器重新下载
>无网络环境下，默认读取文件缓存