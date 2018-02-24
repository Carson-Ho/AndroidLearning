# 1. 包结构
![示意图](http://upload-images.jianshu.io/upload_images/944365-16c9eaa2007a1fd5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 2. 与Collections的区别
- `Collection`：一个接口，是所有集合（如`Set`、`List`）容器的顶层父接口
- `Collections`：一个工具类，提供了一系列的静态方法来辅助容器操作

>1. 如对容器的搜索、排序、线程安全化等等
>2. 该类不能被实例化


# 3. Collections与 Arrays 的区别
![示意图](http://upload-images.jianshu.io/upload_images/944365-ffdeaf01bcec1d5e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**附：Collections的使用**

```
// 主要功能有：搜索元素、获取最大最小值、排序集合、对象线程安全化、将1个List所有元素复制到另1个 等
// 1. 获取最大最小值
static T max(Collection<? extends T> coll, Comparator<? super T> comp); 
static T min(Collection<? extends T> coll, Comparator<? super T> comp); 

// 2. 排序 
static void sort(List<T> list, Comparator<? super T> c); 

// 3. 将线程不安全的Collection转为线程安全的Collection 
static Collection<T> synchronizedCollection(Collection<T> c); 
static List<T> synchronizedList(List<T> list); 
static Map<K,V> synchronizedMap(Map<K,V> m); 
static Set<T> synchronizedSet(Set<T> s);

// 交换指定位置的两个元素 
static void swap(List<?> list, int i, int j) 

// 主要操作对象 = 集合类 = `Collection`接口的实现类
List list = new ArrayList(); 
list.add(XXX); 
//··· 
list.add(XXX); 
Collectoions.sort(list);
```
