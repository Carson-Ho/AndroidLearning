![示意图](http://upload-images.jianshu.io/upload_images/944365-24ff7cd84219bce4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### JDK 1.7 
大部分的修改都是为方便程序员，简化书写，提高阅读性。

1. 支持将整数类型（`int， short，long，byte`）用二进制来表示，用0b或0B开头。
```
 byte b = 0b111;//7
 short s= (short) 0b0111111111111111;//32767
 int i=0B11111;
 long l=0b000011110001;
```

2. 可添加数字类型的下划线，提高阅读性，如：以四位一组

```
 long creditCardNumber = 1234_5678_9012_3456L;
```

>注：下划线添加的标准
只能放在数字与数字中间（可多个下划线相连）
不能放在整个数的开头和末尾，也不能放在其他符号与数字之间


3. `switch`语句支持`String`类型 
>1.7 以前，只支持`byte`，`short`，`char`，`int`` 

4. 泛型简化 
泛型实例的创建可以通过类型推断来简化 可以去掉后面new部分的泛型类型，只用<>就可以
```
  List<String> list = new ArrayList<>();
  Map<String, List<String>> map = new HashMap<>();
```

5. 异常处理多`catch`合并 
如果有两个异常我们处理的方式相同，不需要再写两个catch子句

```
public static void main(String[] args){
        try {
            test();
        } catch (IOException | SQLException e) {

        }
    }
    public static void test() throws IOException, SQLException {

    }
```


6. 增加释放资源的简便方式：`try-with-resource`语句

对比 以前繁琐的释放资源，增加了`try-with-resource`语句，将需释放的资源放入到try()括号里即可，如：

```
try( 
        ZipFile zf = new ZipFile(zipFileName); 
        BufferedWriter writer = Files.newBufferedWriter(outputFilePath, charset); 
    ){ 
 
}catch(Exception e){ 
}

// 前提：该类必须实现 `java.lang.AutoCloseable`接口；
// 执行完毕后：按照声明逆序关闭资源，即 `zf` 和   `writer` 一定会被关闭
```

### JDK 1.8
1. `lambda`表达式
对一种特殊接口匿名内部类的一种简化书写

>最大的新增的特性

2. 函数式接口（用于 配合 `lambda`表达式）
- 定义：是一个接口 、只有一个待实现的方法 
>与此对应，新引入了一个注解： @FunctionalInterface ；该注解只是起文档的作用，说明这个接口是函数式接口

- 作用
每个lambda表达式都对应一个类型 = 函数式接口类型
每一个该类型的lambda表达式都会被匹配到该接口的唯一的抽象方法
故可将lambda表达式当成：任意一个只包含1个抽象方法的接口类型

3. 接口
a. 允许为 接口 添加非抽象（即子类可以不用去实现）的方法：`default` 和 `static`
b. 定义方法实现

>在java8中，接口与抽象类最突出的区别 = 变量和方法的可访问性：
>1. 抽象类：变量允许非抽象和非final的属性，允许方法是public,private或protected的
>2. 接口：变量 = public、static、final；所有方法 = public

4. 方法与构造函数引用
允许使用 :: 关键字 来传递方法 / 构造函数的引用