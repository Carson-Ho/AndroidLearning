package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.ArrayList;

/**
 * Created by Carson_Ho on 17/11/7.
 */

public class Exam_34 {

    /**
     * 结点结构
     */
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 解题算法
     *
     * @param root        树的根结点
     * @param exceptedSum 要求的路径和
     */
    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root,int exceptedSum){

        // 1. 创建1链表 & 链表集合

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); // 链表集合：存储所有符合条件的路径
        ArrayList<Integer> path = new ArrayList<Integer>(); // 链表：用于 存储路径节点，即存放根结点到当前处理结点的所经过的结点
        
        // 2. 判断输入数据的合法性：若二叉树的根结点为空，则结束判断
        if(root == null) {
            System.out.println("输入的二叉树节点为空");
            return result;
        }

        // 3. 调用辅助方法通过前序遍历二叉树 & 记录下路径
        findPath(root,path,result,exceptedSum,0);
        
        return result;
    }

    /**
     * 辅助方法
     *
     * @param curNode     当前要处理的结点（未加入到路径中）
     * @param path        路径节点，即存放根结点到当前处理结点的所经过的结点（未包括当前结点）
     * @param result     存储所有符合条件的路径
     * @param exceptedSum 要求的路径和
     * @param currentSum      当前记录的和（未加上当前结点的值）
     */

    public static void findPath(TreeNode curNode,ArrayList<Integer> path,ArrayList<ArrayList<Integer>> result,int exceptedSum,int currentSum){

        // 1. 记录下当前节点值到路径
        path.add(curNode.val);

        // 2. 计算路径节点值的和
        currentSum += curNode.val;

        // 3. 若当前节点 ≠ 叶子结点，则通过 递归 继续遍历其子节点
        if(curNode.left!=null)
            findPath(curNode.left,path,result,exceptedSum,currentSum);

        if(curNode.right!=null) {
            findPath(curNode.right, path, result, exceptedSum, currentSum);
        }

        // 4. 若当前节点 = 叶节点 & 路径节点值的和 = 期望值
        // 则认为该路径符合要求，将其添加到链表集合当中
        if(curNode.left == null && curNode.right == null && currentSum == exceptedSum) {
            System.out.println(path);
            result.add(new ArrayList<Integer>(path));
            System.out.println(result);
        }

        // 5. 若遍历的结点 = 叶结点 & 路径中节点值的和  ≠ 输入的整数
        // 自动回到其父结点 & 继续遍历其他子节点
        // 注：函数退出前，要在路径上删除当前结点 & 减去当前结点的值，以确保返回父结点时，路径 = 从根结点 -> 父结点的路径
        path.remove(path.size()-1) ;

    }


    /**
     * 测试用例
     */
    public static void main(String[] args) {

        // 功能测试
        //            10
        //          /   \
        //         5     12
        //       /  \
        //      4    7
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        System.out.println(findPath(root,22));
        System.out.println(findPath(root,32));

        // 特殊输入测试
        findPath(null,12);
    }

}
