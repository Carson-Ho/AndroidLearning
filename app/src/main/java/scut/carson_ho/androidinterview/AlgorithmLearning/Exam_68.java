package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Carson_Ho on 17/12/1.
 */

public class Exam_68 {

    /**
     * 辅助类结构：树的节点结构
     */
    public static class TreeNode {
        int val; // 二叉树的结点数据
        List<TreeNode> children = new LinkedList<>(); // 保存路径

        public TreeNode(int data) {
            this.val = data;
        }
    }

    /**
     * 解题算法
     * @param root 树的根结点
     * @param p1 结点1
     * @param p2 结点2
     * @return 公共结点，没有返回null
     */
    public static TreeNode getLastCommonParent(TreeNode root, TreeNode p1, TreeNode p2) {
        // 判断输入节点的合法性
        if (root == null || p1 == null || p2 == null) {
            System.out.println("输入的节点存在空节点");
            return null;
        }

        // 1. 创建2个链表，用于保存 从根节点到输入2个节点的路径
        List<TreeNode> path1 = new LinkedList<>();
        List<TreeNode> path2 = new LinkedList<>();

        // 2. 求出从根节点到输入2个节点的路径 & 保存到链表中
        getNodePath(root, p1, path1);
        getNodePath(root, p2, path2);

        // 3. 寻找2个链表中的最后1个公共节点
        return getLastCommonNode(path1, path2);
    }

    /**
     * 辅助方法1：求出 从根节点到输入2个节点的路径
     *
     * @param root   根结点
     * @param target 目标结点
     * @param path   从根结点到目标结点的路径（保存位置）
     */
    public static void getNodePath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return;
        }

        // 添加当前结点
        path.add(root);

        List<TreeNode> children = root.children;

        // 处理子结点
        for (TreeNode node : children) {

            if (node == target) {
                path.add(node);
                return;
            } else {
                getNodePath(node, target, path);
            }
        }
        // 返回父节点前，在路径上删除当前节点
        // 因为此节点已经走不下去到达目标节点，所以需要继续找下1个路径
        path.remove(path.size() - 1);
    }


    /**
     * 辅助算法2：寻找2个链表中的最后1个公共节点
     * 原理：同时遍历 & 比较 2个链表的节点
     * @param p1 路径1
     * @param p2 路径2
     * @return 共同的结点，没有返回null
     */

    public static TreeNode getLastCommonNode(List<TreeNode> p1, List<TreeNode> p2) {
        Iterator<TreeNode> ite1 = p1.iterator();
        Iterator<TreeNode> ite2 = p2.iterator();
        TreeNode last = null;

        while (ite1.hasNext() && ite2.hasNext()) {
            TreeNode tmp = ite1.next();
            if (tmp == ite2.next()) {
                last = tmp;
            }
        }

        return last;

    }

    /**
     * 测试用例
     */
    public static void main(String[] args){
        // 功能测试1：形状普通的树
        System.out.print("功能测试1：形状普通的树:");

        //             1
        //           /   \
        //         2      3
        //        /  \
        //      4     5
        //     / \  / | \
        //    6   7 8 9 10
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);

        n1.children.add(n2);
        n1.children.add(n3);

        n2.children.add(n4);
        n2.children.add(n5);

        n4.children.add(n6);
        n4.children.add(n7);

        n5.children.add(n8);
        n5.children.add(n9);
        n5.children.add(n10);

        // 调用解题算法，获得2个链表中的公共节点
        TreeNode common = getLastCommonParent(n1, n6, n8);
        System.out.println(common.val);

        // 功能测试2：树退化成链状的树
        System.out.print("功能测试2：树退化成链状的树:");

        //               1
        //              /
        //             2
        //            /
        //           3
        //          /
        //         4
        //        /
        //       5

        TreeNode m1 = new TreeNode(1);
        TreeNode m2 = new TreeNode(2);
        TreeNode m3 = new TreeNode(3);
        TreeNode m4 = new TreeNode(4);
        TreeNode m5 = new TreeNode(5);
        TreeNode m6 = new TreeNode(6);

        m1.children.add(m2);
        m2.children.add(m3);
        m3.children.add(m4);
        m4.children.add(m5);
        // 调用解题算法，获得2个链表中的公共节点
        TreeNode common2 = getLastCommonParent(n1, n4, n5);
        System.out.println(common2.val);

        // 特殊输入测试：树的指针 = 空指针null
        System.out.print("特殊输入测试：树的指针 = 空指针null:");
        System.out.println(getLastCommonParent(null, n4, n5));

    }

}
