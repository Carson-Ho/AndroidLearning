# IO 和 NIO
http://www.iteye.com/topic/834447
区别：http://www.hollischuang.com/archives/184
https://github.com/francistao/LearningNotes/blob/master/Part2/JavaConcurrent/NIO.md
http://huachao1001.github.io/article.html?8cb2Q00Q
http://www.bijishequ.com/detail/403272?p=


### 1. IO 简介
https://www.jianshu.com/p/80f8a74d4662

![示意图](http://upload-images.jianshu.io/upload_images/944365-3a31f3fd3df13c18.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 2. NIO简介
![示意图](http://upload-images.jianshu.io/upload_images/944365-63aa40ee9ed89322.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 3.二者区别 

![示意图](http://upload-images.jianshu.io/upload_images/944365-a155ef4609137f75.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### 4. 核心组件
核心组件 包括： 通道（Channel）、缓冲区（Buffer）、选择器（Selectors）

![示意图](http://upload-images.jianshu.io/upload_images/944365-93cd55b2ed7cd37c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### 5. 基于通道 & 缓冲数据的 使用步骤
```
    // 1. 获取数据源 和 目标传输地的输入输出流（此处以数据源 = 文件为例）
    FileInputStream fin = new FileInputStream(infile);
    FileOutputStream fout = new FileOutputStream(outfile);

    // 2. 获取数据源的输入输出通道
    FileChannel fcin = fin.getChannel();
    FileChannel fcout = fout.getChannel();

    // 3. 创建 缓冲区 对象：Buffer（共有2种方法）
     // 方法1：使用allocate()静态方法
     ByteBuffer buff = ByteBuffer.allocate(256);
     // 上述方法创建1个容量为256字节的ByteBuffer
     // 注：若发现创建的缓冲区容量太小，则重新创建一个大小合适的缓冲区

    // 方法2：通过包装一个已有的数组来创建
     // 注：通过包装的方法创建的缓冲区保留了被包装数组内保存的数据
     ByteBuffer buff = ByteBuffer.wrap(byteArray);

     // 额外：若需将1个字符串存入ByteBuffer，则如下
     String sendString="你好,服务器. ";
     ByteBuffer sendBuff = ByteBuffer.wrap(sendString.getBytes("UTF-16"));

    // 4. 从通道读取数据 & 写入到缓冲区
    // 注：若 以读取到该通道数据的末尾，则返回-1
    fcin.read(buff);

    // 5. 传出数据准备：将缓存区的写模式 转换->> 读模式
    buff.flip();

    // 6. 从 Buffer 中读取数据 & 传出数据到通道
    fcout.write(buff);

    // 7. 重置缓冲区
    // 目的：重用现在的缓冲区,即 不必为了每次读写都创建新的缓冲区，在再次读取之前要重置缓冲区
    // 注：不会改变缓冲区的数据，只是重置缓冲区的主要索引值
    buff.clear();
```
### 6. 具体实例：实现文件复制功能
实现方式：通道`FileChannel`、 缓冲区`ByteBuffer`

```
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {

    public static void main(String[] args) throws IOException {
        // 设置输入源 & 输出地 = 文件
        String infile = "C:\\copy.sql";
        String outfile = "C:\\copy.txt";

        // 1. 获取数据源 和 目标传输地的输入输出流（此处以数据源 = 文件为例）
        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        // 2. 获取数据源的输入输出通道
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        // 3. 创建缓冲区对象
        ByteBuffer buff = ByteBuffer.allocate(1024);
        
        while (true) {

            // 4. 从通道读取数据 & 写入到缓冲区
            // 注：若 以读取到该通道数据的末尾，则返回-1  
            int r = fcin.read(buff);
            if (r == -1) {
                break;
            }
            // 5. 传出数据准备：调用flip()方法  
            buff.flip();
            
            // 6. 从 Buffer 中读取数据 & 传出数据到通道
            fcout.write(buff);
            
            // 7. 重置缓冲区
            buff.clear();
            
          }
        }

}
```
### Selecter

```
// 1. 创建Selector对象   
Selector sel = Selector.open();

// 2. 向Selector对象绑定通道   
 // a. 创建可选择通道，并配置为非阻塞模式   
 ServerSocketChannel server = ServerSocketChannel.open();   
 server.configureBlocking(false);   
 
 // b. 绑定通道到指定端口   
 ServerSocket socket = server.socket();   
 InetSocketAddress address = new InetSocketAddress(port);   
 socket.bind(address);   
 
 // c. 向Selector中注册感兴趣的事件   
 server.register(sel, SelectionKey.OP_ACCEPT);    
 return sel;

// 3. 处理事件
try {    
    while(true) { 
        // 该调用会阻塞，直到至少有一个事件就绪、准备发生 
        selector.select(); 
        // 一旦上述方法返回，线程就可以处理这些事件
        Set<SelectionKey> keys = selector.selectedKeys(); 
        Iterator<SelectionKey> iter = keys.iterator(); 
        while (iter.hasNext()) { 
            SelectionKey key = (SelectionKey) iter.next(); 
            iter.remove(); 
            process(key); 
        }    
    }    
} catch (IOException e) {    
    e.printStackTrace();   
}
```
