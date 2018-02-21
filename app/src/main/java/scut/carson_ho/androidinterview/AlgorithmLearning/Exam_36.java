package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/8.
 */

public class Exam_36 {

    /**
     * 节点结构
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    /**
     * 解题算法
     * 原理：中序遍历
     *
     * @param root 二叉树的根结点
     * @return 双向链表的头结点
     */

    static TreeNode realHead = null;// 排序 / 拼接后（即根节点连接上左、右子树后），链表的第1个结点
    static TreeNode tail = null; // 每次排序 / 拼接后（即根节点连接上左、右子树后），链表的最后1个结点

    public static TreeNode Convert(TreeNode pRootOfTree) {

        if(pRootOfTree == null) {
            System.out.print(" 输入的根节点为空");
            return null;
        }

        // 执行辅助算法
        ConvertSub(pRootOfTree);
        return realHead;
        }

    /**
     * 辅助算法
     * 原理：中序遍历
     */
        private static void ConvertSub(TreeNode pRootOfTree) {

            if(pRootOfTree == null) {
                return;
            }

        // 1. 遍历左子树，即 转换左子树为链表
        ConvertSub(pRootOfTree.left);

            // 2. 遍历到根节点时，连接 根节点 与 当前转换的链表
            //  即，连接根节点 与 当前双向链表的尾节点 ：设置尾结点的后继 = 根节点、根节点的前驱元素 = 尾结点
            // 当前双向链表 = 已排序的左子树、当前双向链表的尾节点 = 左子树最大值
        if (tail == null) {
            tail = pRootOfTree;
            realHead = pRootOfTree;
        } else {
            tail.right = pRootOfTree; // a. 设置尾结点的后继 = 根节点
            pRootOfTree.left = tail;  // b. 设置根节点的前驱元素 = 尾结点
            tail = pRootOfTree;
        }
        // 3. 遍历右子树，即 转换右子树为链表
        ConvertSub(pRootOfTree.right);

        }





    /**
     * 与解题算法无关，仅用于测试时打印二叉树
     */

    private static void printTree(TreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.val + " ");
            printTree(root.right);
        }
    }

    /**
     * 与解题算法无关，仅用于测试时打印链表
     */
    private static void printList(TreeNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.right;
        }
    }

    /**
     * 测试用例
     */

    public static void main(String[] args) {
        // 功能测试
        //            10
        //          /   \
        //         6     14
        //        / \    / \
        //       4   8  12 16
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(14);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(16);


        System.out.print("原始的二叉树：");
        // 输出二叉树
        printTree(root);

        System.out.println(" ");
        System.out.print("转换后的链表：");
        // 输出链表
        printList(Convert(root));

        // 特殊输入测试
        System.out.println(" ");
        System.out.print("转换后的链表：" );
        printList(Convert(null));
    }
}


    /**
     * 解题算法（剑指的）
     *
     * @param root 二叉树的根结点
     * @return 双向链表的头结点
     */
//    public static TreeNode convert(TreeNode root) {
//
//        // 1. 双向链表的尾结点
//        // 使用一个长度为1的数组存储，类似C++中的二级指针
//        TreeNode[] lastNode = new TreeNode[1];
//
//        // 2. 链表转换过程如下函数
//        convertNode(root, lastNode);
//
//        // 3. 通过链表的尾节点的前驱指针，找到双向链表的头结点
//        TreeNode head = lastNode[0];
//        while (head != null && head.left != null) {
//            head = head.left;
//        }
//        return head;
//    }
//
//
//    /**
//     * 链表转换
//     *
//     * @param node     当前的根结点
//     * @param lastNode 当前双向链表的尾结点
//     */
//    public static void convertNode(TreeNode node, TreeNode[] lastNode) {
//        // 1. 检查输入的合法性
//        if (node == null) {
//            System.out.print(" 输入的根节点为空");
//            return;
//        }
//
//        // 1. 如果有左子树，就先转换左子树
//        if (node.left != null) {
//            convertNode(node.left, lastNode);
//        }
//
//        //  2. 连接 根节点 与 当前转换的链表
//        //  即，连接根节点 与 当前双向链表的尾节点 ：设置根节点的前驱元素 = 尾结点、尾结点的后继 = 根节点
//        // 当前双向链表 = 已排序的左子树、当前双向链表的尾节点 = 左子树最大值
//
//        node.left = lastNode[0]; // a. 设置根节点的前驱元素 = 尾结点
//
//        if (lastNode[0] != null) {
//            lastNode[0].right = node;
//        } // b. 设置尾结点的后继 = 根节点
//
//
//        // 3. 此时，当前链表的尾节点 = 根节点
//        // 进行当前链表尾节点更新
//        lastNode[0] = node;
//
//        // 4. 通过 转换右子树 & 连接右子树的最小值 与根节点
//        if (node.right != null) {
//            convertNode(node.right, lastNode);
//        }
//
//
//    }
