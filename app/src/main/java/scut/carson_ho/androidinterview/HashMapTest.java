package scut.carson_ho.androidinterview;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Carson_Ho on 17/12/26.
 */

public class HashMapTest {

    final int hash(Object k) {
        int h = hashSeed;
        if (0 != h && k instanceof String) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        h ^= k.hashCode();
        // 预处理hash值，避免较差的离散hash序列，导致table没有充分利用
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }


    /**
     * put 函数源码分析
     */
    public V put(K key, V value) {

        // 1. 判断2个异常情况
        // 异常情况1：
        // 若 哈希表无初始化(数组table为空)
        // 则用构造时的阈值(初始容量)扩展table
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);
        }

        // 异常情况2：
        // 若 key == null，将该value加到table[0]的位置（本质：key为Null时，hash值 = 0）
        // 该位置永远只有1个value，新传进来的value会覆盖旧的value
        if (key == null)
            return putForNullKey(value);

        // 2. 根据键值计算hash值
        int hash = hash(key);

        // 3. 搜索该hash值中对应数组table中的下标索引
        int i = indexFor(hash, table.length);

        // 4. 循环遍历Entry数组，若该key对应的键值对已经存在，则用新的value取代旧的value
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue; //并返回旧的value
            }
        }

        modCount++;

        // 5. 若在table[i]中没找到对应的key，那么就直接在该位置的链表中添加此Entry
        addEntry(hash, key, value, i);
        return null;
    }











    /**
     * put 函数源码分析
     */
    public V put(K key, V value) {

        // 1. 计算key的哈希值（hash）值 -> 分析1
        // 2. 再调用putVal -> 分析2
        return putVal(hash(key), key, value, false, true);

    }

    /**
     * 分析1：hash（）函数
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        // 1. 当key = null时，也有hash值 = 0，所以HashMap的key 可为null
         // 注：对比HashTable，HashTable对key直接hashCode（），若key为null时，会抛出异常，所以HashTable的key不可为null
        // 2. 当key ≠ null时，则计算出 key的hashCode()（记为h），然后 按位异或(^)  h无条件右移16位后的二进制
         // 注：此处计算规则设计的原因 = 让hash值分布的更加均匀，避免出现冲突
        // 3. 最终得到该key的hash值 = 键值对存储在数组中的位置 = 数组下标
    }

    /**
     * 分析2：putVal（）函数
     * @param hash：key的hash值
     * @param key：待存储的key值
     * @param value：待存储的value值，从此处可知：HashMap底层存储的是key-value键值对，而不只是存储了value
     * @param onlyIfAbsent：是否需要替换相同的value值；若为true，表示不替换已经存在的value
     * @param evict：若为false，表示数组 = 新增
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // tab为空则创建
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;

        // 计算index，并对null做处理
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            // 节点存在
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
                // 该链为树
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
                // 该链为链表
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            // 写入
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        // 超过load factor*current capacity，resize
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

    public static void main(String[] args){
        // 1. 声明1个 HashMap的对象
        Map<String,Integer> map = new HashMap<String,Integer>();

        // 2. 向HashMap添加数据（成对 放入 键 - 值对）
        map.put("Android", 1);
        map.put("Java", 2);
        map.put("iOS", 3);
        map.put("数据挖掘", 4);
        map.put("产品经理", 5);

        // 3.获得Map中所有key组成的set集合
        Set<String> keySet = map.keySet();

        // 4.通过遍历，获取对应键上的值
        for (String key : keySet) {
            //根据key获得指定的value
            System.out.println(key + map.get(key));
        }

    }



}
