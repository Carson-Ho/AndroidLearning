package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by Carson_Ho on 17/11/13.
 */

/**
 * 解题算法：使用堆
 */
public class Exam_41Solution {
    
    // 通过 TreeSet 类实现最大堆、最小堆
    TreeSet<Integer> maxH = new TreeSet<>(Collections.reverseOrder());
    TreeSet<Integer> minH = new TreeSet<>();

    /**
     * 插入数据到堆里
     * 注：需保证数据平均分到2个堆里 & （最大堆）左部分数据 < （最小堆）右部分的数据
     */
    public void Insert(Integer num) {
        // 需保证数据平均分到2个堆里 & （最大堆）左部分数据 < （最小堆）右部分的数据
        // 当偶数时，将数据插入最大堆 & 将最大堆最大的数据插入到最小堆中
        // 当奇数时，将数据插入最小堆 & 将最小堆最小的数据插入到最大堆中
        if(((maxH.size() + minH.size()) & 1) == 0) {
            maxH.add(num);
            minH.add(maxH.pollFirst());
        }
        else {
            minH.add(num);
            maxH.add(minH.pollFirst());
        }
    }

    /**
     * 获取中位数
     * 根据 左部分最大数据（最大堆的堆顶数据） & 右部分最小数据（最小堆的堆顶数据） ，从而获得中位数
     * 数据总数 = 奇数时取后者、偶数时取2者的平均
     *
     */
    public Double GetMedian() {
        if(maxH.size() == 0 && minH.size() == 0)
            return new Double(0.0);

        // 偶数时，取平均
        if(((maxH.size() + minH.size()) & 1) == 0) {
            return (double)(minH.first() + maxH.first()) / 2;
        }
        else {
            // 奇数时，取最小堆顶元素
            return (double)(minH.first());
        }
    }

}
