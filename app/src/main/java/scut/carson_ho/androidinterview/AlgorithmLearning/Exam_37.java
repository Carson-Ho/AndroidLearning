package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/8.
 */

public class Exam_37 {

    /**
     * 节点结构
     */
    public static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 序列化：二叉树转换成字符串
     * 核心思想：采用 递归 实现前序遍历
     */

    public static String Serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder();

        // 检查输入数据的合法性
        if(root == null){
            sb.append("#,"); // 遍历时当遇到空指针时，则采用 特殊字符（如$）代替
            return sb.toString();

        }

        // 根据 前序遍历的顺序 序列化二叉树
        sb.append(root.val + ",");// 1. 序列化根节点
        sb.append(Serialize(root.left));// 2.  序列化左子树
        sb.append(Serialize(root.right)); // 3.  序列化右子树
        return sb.toString();

        }

    /**
     * 反序列化
     * 核心思想：拆分分3步反序列化：反序列化根节点、左子树、右子树
     */

    static int index = -1;   // 当前反序列化节点的下标
    private static TreeNode Deserialize(String str) {

        index++; // 每次递归时记录下当前反序列化节点的下标

        String[] strr = str.split(",");
        TreeNode node = null;

        if(!strr[index].equals("#")){
            node = new TreeNode(Integer.valueOf(strr[index])); // 1. 反序列化根节点，即读取到的第1个数字
            node.left = Deserialize(str); // 2. 反序列化左子树
            node.right = Deserialize(str);// 3. 反序列化右子树
        }

        return node;

        }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        // 功能测试
        //            1
        //          /   \
        //         2     3
        //        /     / \
        //       4     5   6
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        // 序列化二叉树
        System.out.print("序列化后的二叉树：");
        System.out.println(Serialize(root));

        // 反序列化二叉树
        System.out.print("反序列化后的二叉树：");
        printTree(Deserialize(Serialize(root)));

    }

    /**
     * 仅用于测试时 打印二叉树（中序遍历）
     */

    private static void printTree(TreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.val + " ");
            printTree(root.right);
        }
    }

}


    /**
     * 序列化：二叉树转换成字符串
     * 核心思想：采用 递归 实现前序遍历
     */
//    public static void serialize(TreeNode root, List<Integer> result) {
//
//        // 1. 判断数据的合法性
//        if (root == null){
//            result.add(null);
//            return;
//        }
//        // 2. 通过递归实现前序遍历序列化二叉树
//        result.add(root.val);
//        serialize(root.left, result);
//        serialize(root.right, result);
//    }


    /**
     * 反序列化
     * 核心思想：拆分分3步反序列化：反序列化根节点、左子树、右子树
     */

//    int index = -1;   // 当前反序列化节点的下标
//    private static TreeNode deserialize(TreeNode root, List<Integer> result) {
//
//        if (result.get(0) == null) {
//            result.remove(0);
//            return null;
//        }
//        // 1. 反序列化根节点，即读取到的第1个数字
//        root = new TreeNode( result.remove(0));
//        // 2. 反序列化左子树
//        root.left = deserialize(root.left, result);
//        // 3. 反序列化右子树
//        root.right = deserialize(root.right, result);
//        return root;
//    }


