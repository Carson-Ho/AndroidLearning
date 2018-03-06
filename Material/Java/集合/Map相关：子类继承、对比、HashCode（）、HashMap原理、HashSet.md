# 1. `Map`接口的子类继承图
>注：`Map`接口 与 `Collection` 接口无关

![示意图](http://upload-images.jianshu.io/upload_images/944365-33093ba53e052dd5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



```
// 为了更好理解各类的关系，下面附上：各类的定义图
// HashMap 
public class HashMap<K,V> 
    extends AbstractMap<K,V> 
    implements Map<K,V>, Cloneable, Serializable{} 
 
// LinkedHashMap 
public class LinkedHashMap<K,V> 
    extends HashMap<K,V> 
    implements Map<K,V>{}

// TreeMap 
public class TreeMap<K,V> 
    extends AbstractMap<K,V> 
    implements NavigableMap<K,V>, Cloneable, java.io.Serializable{} 

// Hashtable 
public class Hashtable<K,V> 
    extends Dictionary<K,V> 
    implements Map<K,V>, Cloneable, java.io.Serializable {} 

// ConcurrentHashMap 
public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> 
        implements ConcurrentMap<K, V>, Serializable {} 
```

# 2. HashMap 与 LinkedHashMap、TreeMap的区别

![示意图](http://upload-images.jianshu.io/upload_images/944365-d5e9d83cb1dd662f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 3. HashMap 与 Hashtable 的区别
![示意图](http://upload-images.jianshu.io/upload_images/944365-84ac966cd49e8fff.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




# 4. HashMap 与 ConcurrentHashMap 的区别
![示意图](http://upload-images.jianshu.io/upload_images/944365-eab2bdec80d61e6a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#  5. HashCode作用
![示意图](http://upload-images.jianshu.io/upload_images/944365-14444e0456c60bbe.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 特别注意：重写 equals（）时，必须重写 hashCode （）
原因：原因是维护 hashCode 方法的常规协定的第一条
>HashCode（）的常规约定如下

![示意图](http://upload-images.jianshu.io/upload_images/944365-db4a7a0c3b5db069.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

保证上述规定，是为了哈希表类集合（如 HashSet、HashMap、Hashtable 等）保证 集合中的元素不可重复

若只重写`equals（）`，根据重写规则将2个对象`equals（）`返回true，但是`hashCode（）`无重写（依然保持默认实现），返回结果可能导致不同，从而违反上述的常规约定第1条，最终影响哈希表类集合的使用
>先根据hashCode（）计算位置，再遍历比较`key`是否相等，若相等则替换，否则插入



>重写 equals （）的原因：值比较
重写 hashCode （）的原因：存入数据到哈希表类集合（如 HashSet、HashMap、Hashtable 等）时 进行高效比较
>重写 compareTo （）的原因：对象大小比较


# 6.  HashMap的原理（put（）、get（））
- 1.7：http://blog.csdn.net/carson_ho/article/details/79373026
 
- 1.8 ：http://blog.csdn.net/carson_ho/article/details/79373134


### 6.1 put

根据key计算hashCode，从而找到对应数组的位置
遍历该位置的数组 & 链表，查找key是否已存在
若 key已存在，则直接更新value & 返回旧的value
若 key不存在，先判断空间是否足够（不足则扩容）；若足够，通过头插法，将新的键值对放入当前链表的第一个位置（即数组中）

### 6.2 get

首先根据key的hashCode找到对应数组的位置
然后遍历该位置的链表，查找key是否已经存在

# 7.  HashSet判断对象是否存在集合中

- HashSet 基于HashMap实现：只利用HashMap的Key，而value使用一个 static final的Object对象标识。
- 一次HashSet的存取 = HashMap的一次存取
>只不过HashSet只看重Key部分，不需要Value部分

集合中是不允许重复元素存在的，当HashSet需要添加新的对象obj时，需判断需添加的对象是否已在集合内

- 判断过程过程
调用obj.hashCode()，得到对应的hashcode值。
若集合中没有存储这个hashcode对应的对象，则直接添加。
若集合中已经存储了这个hashcode对应的对象，则调用equals判断是否对象相同。若不相同，则直接更新