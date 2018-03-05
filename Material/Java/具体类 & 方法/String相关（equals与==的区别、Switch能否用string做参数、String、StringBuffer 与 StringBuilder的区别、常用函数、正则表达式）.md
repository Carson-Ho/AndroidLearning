# 1. equals与==的区别

![示意图](http://upload-images.jianshu.io/upload_images/944365-eff8e7467c425887.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

附：

```
  /**
   * 附1：Object的equals（）原函数实现
   * 作用 = 比较的是对象的内存地址（内部实现实际 是 “==”，故作用同 “==”作用）
   */
  public boolean equals (Object obj）{
    return （this == obj）；
     }

 /**
   * 附2：复写了Object equals（）原函数的String 类中的equals
   * 作用：比较两个字符串的内容是否相同
   */
public boolean equals(Object obj){
  // 若2者指向同一个地址，那么它们的内容肯定相同
  // 使用 “==” 比较
  if (this == obj){
    return true;
   }

  // 若不指向同一地址，则判断规则为：
      // 1. 类型是否相同（ 即，传入对象是否是String类型，采用 instanceof 比较）
      // 2. 内容是否相同 = 字符串序列是否相同（String类 内部存储 采用char[]实现）
      if (anObject instanceof String) {

                  String anotherString = (String)anObject;
                   int n = value.length; // 注：比较次数 = 第1个String对象的长度n，而不是传入参数中的String对象长度
                   if (n == anotherString.value.length) {
                   char v1[] = value;
                   char v2[] = anotherString.value;

                   // 遍历过程中只要有1个字符不同，就返回false
                   int i = 0;
                    while (n-- != 0) {
                      if (v1[i] != v2[i])
                          return false;
                        i++;
                      }
                   return true;
                  }
              }
           return false;
        }
```

# 2. Switch能否用string做参数？
- 在`Java7` 前，不支持；在Java 7后，支持
>`Java7` 前支持的类型：枚举、`byte`、`short`、`char`、`int` & 对应的封装类


# 3. String、StringBuffer 与 StringBuilder的区别
3者 同样用于储存 & 操作字符串，区别如下：

![示意图](http://upload-images.jianshu.io/upload_images/944365-0dc3197cc9ae63d5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 4. String 常用函数
![示意图](http://upload-images.jianshu.io/upload_images/944365-92f4917dae9295fe.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 5. 正则表达式
- 作用：从字符串中 匹配出满足 需求的字符串部分
- 具体使用：`Pattern`类

```
// 示例：取出字符串中所有的字母（a-z），并显式出来：
public static void main(String[] args) { 

    String dataStr = "M12v,L23f,d34"; 
    Pattern pattern = Pattern.compile("[a-zA-Z]"); 
    Matcher matcher = pattern.matcher(dataStr); 

    // 遍历匹配正则表达式的字符串 
    while (matcher.find()) { 
        // s为匹配的字符串 
        String s = matcher.group(); 
        System.out.println(s);  
    } 
}

// 运行结果
 M v L f d
```