package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/22.
 */

public class Exam_55_1 {

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
     * 解题算法
     * 原理 = 后续遍历每个节点时，每遍历到1个节点，其左右子树已经遍历  依次自底向上判断即可，每个节点只需要遍历一次
     */

    private static boolean isBalanced = true;  // 用于存储是否是平衡二叉树

    public static boolean isBalanced(TreeNode root) {

        // 1. 判断输入数据的合法性
        // 注：空树也算 平衡二叉树
        if (root == null)
            return true;

        // 2. 通过后序遍历进行遍历 & 判断
        getDepth(root);

        return isBalanced;
    }

    /**
     * 辅助算法
     */

    public static int getDepth(TreeNode root) {

        if (root == null)
            return 0;

        int left = getDepth(root.left); // 通过递归获取左子树的深度
        int right = getDepth(root.right); // 通过递归获取右子树的深度

        // 判断是否符合平衡二叉树的要求：即任意节点的左右子树深度之差不超过1
        // 边遍历、边判断
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        
        return right > left ? right + 1 : left + 1;

    }




    /**
     * 测试用例
     */
    public static void main(String[] args){

        // 功能测试：
        // a. 是平衡二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);
        System.out.println(isBalanced(root));

        // b. 不是平衡二叉树
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.left.right.left = new TreeNode(7);
        root2.right = new TreeNode(3);
        System.out.println(isBalanced(root2));

        // 特殊输入测试
        System.out.println(isBalanced(null));

    }

}

//    /**
//     * 解题算法
//     * 原理 = 后序遍历，记录每个节点的深度，从而可以通过一次遍历完成整棵树的判断
//     * 返回值 : true = 是平衡二叉树，false = 不是平衡二叉树
//     */
//    public static boolean isBalanced(TreeNode node){
//
//        // 1. 判断输入数据的合法性
//        // 注：空树也算 平衡二叉树
//        if(node == null)
//            return true;
//
//        // 2. 传入1个表示节点深度的整型变量
//        // 注：采用数组传递的原因：在Java中，基本数据类型无法使用引用传递
//        int[] depth = new int[1];
//        return isBalancedCore(node,depth);
//    }
//
//    /**
//     * 辅助算法
//     * 原理 = 对于某1子树，需给出它的是否是平衡性的判断 & 深度
//     * 返回值 = 此处将平衡性的判断作为返回值，深度通过长度为1的数组传递
//     * 注：采用数组传递的原因：在Java中，基本数据类型无法使用引用传递
//     */
//
//    public static boolean isBalancedCore(TreeNode node,int[] depth){
//        // 1. 判断输入数据的合法性
//        if(node == null){
//            depth[0] = 0;
//            return true;
//        }
//
//        // 2. 定义2数组变量，用于保存节点的左、右子树深度
//        // 注：采用数组传递的原因：在Java中，基本数据类型无法使用引用传递
//        int[] left = new int[1];
//        int[] right = new int[1];
//
//        // 3. 使用后序遍历遍历二叉树的每个节点
//        if(isBalancedCore(node.left,left) && isBalancedCore(node.right,right)){
//
//            // 每遍历1个节点，即记录下节点的深度
//            int diff = left[0] - right[0];
//
//            // 判断二叉树是不是平衡的
//            if(diff <=1 && diff >= -1){
//                depth[0] = 1 + (left[0] > right[0] ? left[0] : right[0]);
//                return true;
//            }
//        }
//        return false;
//    }
