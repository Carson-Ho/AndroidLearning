package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/7.
 */

public class Exam_33 {

    /**
     * 解题算法
     * @param sequence 需判断的某二叉搜索树的后序遍历的结果
     * @return true：该数组是某二叉搜索树的后序遍历的结果。false：不是
     */
    public static boolean verifySequenceOfBST(int[] sequence) {

        // 1. 判断输入的数据合法性：输入的数组不能为空，并且有数据
        if (sequence == null || sequence.length <= 0) {
            return false;
        }

        // 2. 调用辅助方法进行检验
        return verifySequenceOfBST(sequence, 0, sequence.length - 1);
    }

    /**
     * 辅助算法（递归使用）
     * @param sequence 某二叉搜索树的后序遍历的结果
     * @param start    处理的开始位置
     * @param end      处理的结束位置
     * @return true：该数组是某二叉搜索树的后序遍历的结果。false：不是
     */
    public static boolean verifySequenceOfBST(int[] sequence, int start, int end) {

        // 若要处理的数据只有1个 or 已无数据要处理（start>end）就返回true
        if (start >= end) {
            return true;
        }

        // 1. 寻找二叉搜索树的左子树节点
        // 即，从左向右找第1个 ≤ 根结点的元素的位置
        // 根节点 = 数组最后1个元素 = sequence[end]
        int index = start;
        while (index < end - 1 && sequence[index] < sequence[end]) {
            index++;
            // 跳出循环后，[start, index-1]的元素都是小于根结点的（sequence[end]）
            // 即，[start, index-1]范围的节点 = 根结点的左子树
        }

        // 2. 寻找二叉搜索树的右子树节点
         // 即，从左向右找第1个 ≥ 根结点的元素的位置
         // 由于上面已寻找到左子树节点，故只需从从最后1个≤根结点的元素开始，找第1个≥根结点的元素

        int right = index;// right用于记录第一个 ≥ 根结点的元素的位置
        while (index < end - 1 && sequence[index] > sequence[end]) {
            index++;
            // 跳出循环后，[index, end-1]的元素都是大于根结点的（sequence[end]）
            // [index, end-1]范围的节点 = 根结点的左子树
        }

        // 3. 判断上述2部分是否符合后序遍历序列的规则
        // 若第2部分满足规则，那么一定有index=end-1，
        // 若不满足，即说明根结点的右子树[index, end-1]中有≤根结点的元素，即不符合二叉搜索树的定义，返回false
        if (index != end - 1) {
            return false;
        }

        // 若执行到此处，则说明当前序列符合后序遍历规则
        // 即，[start, index-1] = 根结点左子树的位置、[index, end-1] = 根结点右子树的位置
        // 通过递归方式，继续判断2部分内部是否仍然满足后序遍历规则
        index = right;
        return verifySequenceOfBST(sequence, start, index - 1) && verifySequenceOfBST(sequence, index, end - 1);
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 功能测试
        int[] data = {5,7,6,9,11,10,8};
        int[] data1 = {7,4,6,5};
        System.out.println(verifySequenceOfBST(data));
        System.out.println(verifySequenceOfBST(data1));

        // 特殊输入测试
        System.out.println(verifySequenceOfBST(null));
    }
}
