package scut.carson_ho.androidinterview;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Carson_Ho on 17/12/26.
 */

public class HashMapTest {

//    public static void main(String[] args) {
//        // 1. 声明1个 HashMap的对象
//        Map<String, Integer> map = new HashMap<String, Integer>();
//
//        // 2. 向HashMap添加数据（成对 放入 键 - 值对）
//        map.put("Android", 1);
//        map.put("Java", 2);
//        map.put("iOS", 3);
//        map.put("数据挖掘", 4);
//        map.put("产品经理", 5);
//
//        // 3. 获取 HashMap 的某个数据
//        System.out.println("key = 产品经理时的值为：" + map.get("产品经理"));
//
//        // 4. 获取 HashMap 的全部数据：遍历HashMap
//        // 核心思想：
//        // 步骤1：获得key-value对（Entry） 或 key 或 value的Set集合
//        // 步骤2：遍历上述Set集合(使用for循环 、 迭代器（Iterator）均可)
//        // 方法共有3种：分别针对 key-value对（Entry） 或 key 或 value
//
//        // 方法1：获得key-value的Set集合 再遍历
//
//        System.out.println("方法1");
//        // 1. 获得key-value对（Entry）的Set集合
//        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
//
//        // 2. 遍历Set集合，从而获取key-value
//        // 2.1 通过for循环
//        for(Map.Entry<String, Integer> entry : entrySet){
//            System.out.print(entry.getKey());
//            System.out.println(entry.getValue());
//        }
//        System.out.println("----------");
//        // 2.2 通过迭代器：先获得key-value对（Entry）的Iterator，再循环遍历
//        Iterator iter1 = entrySet.iterator();
//        while (iter1.hasNext()) {
//            // 遍历时，需先获取entry，再分别获取key、value
//            Map.Entry entry = (Map.Entry) iter1.next();
//            System.out.print((String) entry.getKey());
//            System.out.println((Integer) entry.getValue());
//        }
//
//
//        // 方法2：获得key的Set集合 再遍历
//        System.out.println("方法2");
//
//        // 1. 获得key的Set集合
//        Set<String> keySet = map.keySet();
//
//        // 2. 遍历Set集合，从而获取key，再获取value
//        // 2.1 通过for循环
//        for(String key : keySet){
//            System.out.print(key);
//            System.out.println(map.get(key));
//        }
//
//        System.out.println("----------");
//
//        // 2.2 通过迭代器：先获得key的Iterator，再循环遍历
//        Iterator iter2 = keySet.iterator();
//        String key = null;
//        while (iter2.hasNext()) {
//            key = (String)iter2.next();
//            System.out.print(key);
//            System.out.println(map.get(key));
//        }
//
//
//        // 方法3：获得value的Set集合 再遍历
//        System.out.println("方法3");
//
//        // 1. 获得value的Set集合
//        Collection valueSet = map.values();
//
//        // 2. 遍历Set集合，从而获取value
//        // 2.1 获得values 的Iterator
//        Iterator iter3 = valueSet.iterator();
//        // 2.2 通过遍历，直接获取value
//        while (iter3.hasNext()) {
//            System.out.println(iter3.next());
//        }

    }


}
