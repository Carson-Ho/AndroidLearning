package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Carson_Ho on 17/11/21.
 */

public class Exam_55 {

    /**
     * 设置结点结构
     */
    public static class TreeNode {
        int val; // 二叉树的结点数据
        TreeNode left; // 二叉树的左子树（左孩子）
        TreeNode right; // 二叉树的右子树（右孩子）

        public TreeNode(int data) {
            this.val = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 解题思路1
     * 原理 = 根据二叉树的形状判断
     * 实现方式 = 递归
     */
    public static int treeDepth(TreeNode root){

        // 检查输入数据的合法性
        if(root == null)
            return 0;

        // 采用递归获取左、右子树的深度
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);

        return left > right? (left+1) : (right+1);
    }

    /**
     * 解题思路2
     * 原理 = 层序遍历
     * 实现方式 = 队列
     */
    public static int treeDepth2(TreeNode root){

        // 判断输入数据的合法性
        if(root==null)
            return 0;

        Queue<TreeNode> q=new LinkedList<>();// 创建队列
        int depth = 0; // 用于记录深度

        // 步骤2：入队当前结点
        q.add(root);

        // 步骤3：判断当前队列是否为空，若为空则跳出循环
        while(!q.isEmpty()){

            int size = q.size();

            for(int i=0;i<size;i++){
                // 出队队首元素
                root = q.poll();

                // 若出队元素有左孩子，则入队其左孩子
                if(root.left!=null) q.add(root.left);
                // 若出队元素有右孩子，则入队其右孩子
                if(root.right!=null) q.add(root.right);
            }

            depth++;
        }
        return depth;

    }

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 功能测试
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);

        System.out.println(treeDepth(root));
        System.out.println(treeDepth2(root));

        // 特殊输入测试：头节点为空
        System.out.println(treeDepth(null));
        System.out.println(treeDepth2(null));
    }
}
