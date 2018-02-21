package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/10/24.
 */

public class Exam_8 {

    /**
     * 解题算法：获取下1个结点算法
     */

    private static TreeLinkNode getNext(TreeLinkNode pNode) {

        // 1. 判断输入的结点的合法性
        if (pNode == null)
            return null;

        TreeLinkNode cur = null;


        // 2. 判断输入结点是否有右子树
        if (pNode.right != null) {

            cur = pNode.right;

            // 若具备右子树，找出其右子树节点的 最 左边的子树节点
            while (cur.left != null) {
                // 下1个结点 = 其右子树 的最左子结点
                cur = cur.left;
            }
            return cur;
        }

        // 3. 若该节点没有右子树，则判断其是否 是其父节点的左子树
        if (pNode.next == null) {
            return null;
        }

        // 若是其父节点的左子树，下1个结点 = 其父结点
        if (pNode == pNode.next.left) {

            return pNode.next;
        }

        // 4. 若该节点无右子树 &  不是其父节点的左子树
        // 向上寻找其父节点，直到找到父节点是其父节点的左孩子为此，此时，下一个节点为最后那个父节点
        while (pNode.next != null) {
            if (pNode == pNode.next.left) {
                return pNode.next;
            }
            // 继续寻找
            pNode = pNode.next;
        }
        return null;

    }

    /**
     * 辅助类：建立二叉树节点类
     */

    static class TreeLinkNode {
        String val;
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;// 此处的next = 指向父节点
        public TreeLinkNode(String val) {
            this.val = val;
        }

    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        // 定义树结构

//                a
//              /  \
//             b    c
//             / \  / \
//            d  e f   g
//              / \
//             h   i

        TreeLinkNode root = new TreeLinkNode("a");
        TreeLinkNode r2 = new TreeLinkNode("b");
        TreeLinkNode r3 = new TreeLinkNode("c");
        TreeLinkNode r4 = new TreeLinkNode("d");
        TreeLinkNode r5 = new TreeLinkNode("e");
        TreeLinkNode r6 = new TreeLinkNode("f");
        TreeLinkNode r7 = new TreeLinkNode("g");
        TreeLinkNode r8 = new TreeLinkNode("h");
        TreeLinkNode r9 = new TreeLinkNode("i");

        root.left = r2;
        root.right = r3;

        r2.next = root;
        r2.left = r4;
        r2.right = r5;

        r3.next = root;
        r3.right = r6;
        r3.left = r7;

        r4.next = r2;
        r5.next = r2;
        r5.right = r9;
        r5.left = r8;

        r7.next = r3;
        r6.next = r3;

        r8.next = r5;
        r9.next = r5;

        // 测试该结点有右子树的情况（b）
        System.out.println(getNext(r2).val);

        // 测试该结点无 右子树 & 是其父结点的左子树（d）
        System.out.println(getNext(r4).val);

        // 测试该结点无 右子树 & 是其父结点的右子树（i）
        System.out.println(getNext(r9).val);

    }

}


