package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.Stack;

/**
 * Created by Carson_Ho on 17/11/20.
 */

public class Exam_54 {

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
     * 解题算法：中序遍历
     * 实现方式：非递归（栈实现）
     */
    public static TreeNode InOrder_stack(TreeNode root, int k) {

        // 检查输入节点的合法性
        if (root == null || k < 0)
            return null;

        // 创建1个栈用于实现中序遍历
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        int count = 0;

        // 步骤1：直到当前结点为空 & 栈空时，循环结束
        while (root != null || stack.size() > 0) {

            // 步骤2：判断当前结点是否为空
            // a. 若不为空，执行3、4
            // b. 若为空，执行5、6
            if (root != null) {

                // 步骤3：入栈当前结点
                stack.push(root);

                // 步骤4：置当前结点的左孩子为当前节点
                // 返回步骤1
                root = root.left;

            } else {

                // 步骤5：出栈栈顶结点
                root = stack.pop();

                // 步骤6：判断出栈顺序是否等于所要求顺序，若是则输出
                // 即，不需将整个中序序列求出来，只需求到所需位置即可
                count++;
                if (count == k)
                    return root;

                // 步骤7：置当前结点的右孩子为当前节点
                root = root.right;
                // 返回步骤1
            }
        }
        return null;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 构造二叉树结构：
        //          5
        //         / \
        //       3     7
        //      / \   / \
        //     2  4  6   8
        TreeNode root = new TreeNode (5);
        root.left = new TreeNode (3);
        root.left.left = new TreeNode (2);
        root.left.right = new TreeNode (4);
        root.right = new TreeNode (7);
        root.right.left = new TreeNode (6);
        root.right.right = new TreeNode (8);
        // 中序遍历序列为：{ 2,3,4,5,6,7,8 }
        System.out.println(InOrder_stack(root,3).val); // 求第3个
        System.out.println(InOrder_stack(root,6).val); // 求第6个

        // 特殊输入测试
        System.out.println(InOrder_stack(null,8)); //null
    }
}


