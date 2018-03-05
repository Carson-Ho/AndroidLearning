![示意图](http://upload-images.jianshu.io/upload_images/944365-ec21807bb16946d2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

注：`finally`中最好：
1. 不要包含`return`，否则程序会提前退出
2. 且 返回值≠ `try` 或 `catch`中保存的返回值