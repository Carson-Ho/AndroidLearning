package scut.carson_ho.androidinterview;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Carson_Ho on 17/12/26.
 */

public class HashMapTest {

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
