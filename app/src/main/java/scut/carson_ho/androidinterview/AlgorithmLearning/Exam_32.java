package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Carson_Ho on 17/11/6.
 */

public class Exam_32 {

    /**
     * 结点结构
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
     * 内容：层序遍历（不分行）
     * 方式：非递归（采用队列）
     */
    public static ArrayList<Integer> levelTravel(TreeNode root){

        Queue<TreeNode> q=new LinkedList<TreeNode>();// 创建1算法辅助队列
        ArrayList<Integer> list = new ArrayList<Integer>(); // 存储遍历后的结果

        // 步骤1：判断当前结点是否为空；若是，则返回空操作
        if(root == null) {
            System.out.println("输入的头节点为空");
            return list;
        }
        // 步骤2：入队当前结点
        q.add(root);

        // 步骤3：判断当前队列是否为空，若为空则跳出循环
        while(!q.isEmpty()){

            // 步骤4：出队队首元素
            root =  q.poll();

            // 步骤5：将出队元素放入到存储结果的链表中
            list.add(root.val);

            // 步骤6：若出队元素有左孩子，则入队其左孩子
            if(root.left!=null) q.add(root.left);

            // 步骤7：若出队元素有右孩子，则入队其右孩子
            if(root.right!=null) q.add(root.right);
        }
        return list;
    }


    /**
     * 内容：层序遍历（分行打印）
     * 方式：非递归（采用队列）
     */
    public static ArrayList<ArrayList<Integer> > levelTravel2(TreeNode root){

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); // 存储全部层的节点结果
        ArrayList<Integer> list = new ArrayList<Integer>(); // 存储每层节点结果

        Queue<TreeNode> q =new LinkedList<TreeNode>();// 创建1算法辅助队列


        // 步骤1：判断当前结点是否为空；若是，则返回空操作
        if(root == null) {
            System.out.println("输入的头节点为空");
            return result;
        }

        int toBePrinted =1; // 变量1 = 当前层还未打印的节点数
        int nextLevel = 0;  // 变量2 = 下1层的节点数

        // 步骤2：入队当前结点
        q.add(root);

        // 步骤3：判断当前队列是否为空，若为空则跳出循环
        while(!q.isEmpty()){

            // 步骤4：出队队首元素 & 输出
            // 注：每次出队时，当前层还未打印的节点数都减1
            root =  q.poll();
            --toBePrinted;

            // 步骤5：将出队元素放入到存储结果的链表中
            list.add(root.val);

            // 步骤6：若出队元素有左孩子，则入队其左孩子
            // 注：每次入队子树时，下1层的节点数+1
            if(root.left!=null) {
                q.add(root.left);
                ++nextLevel;
            }

            // 步骤7：若出队元素有右孩子，则入队其右孩子
            // 注：每次入队子树时，下1层的节点数+1
            if(root.right!=null) {
                q.add(root.right);
                ++nextLevel;
            }

            // 步骤6：当toBePrinted = 0时，代表当前层的节点数已打完
            // 则：换行（即将该层的节点结果存储起来）、将下层节点数赋给当前层节点数、下层节点数置0
            if (toBePrinted == 0 ){
                result.add(list);
                list = new ArrayList<Integer>();

                toBePrinted = nextLevel;
                nextLevel = 0 ;

            }

        }
        return result;
    }


    /**
     * 内容：之字形分行打印
     * 方式：采用2个栈
     */
    public static ArrayList<ArrayList<Integer>> levelTravel3(TreeNode root){

        // 1. 定义2个栈
        Stack<TreeNode> stack1 = new Stack<>();// 栈1：需打印的层数 = 奇数、入栈顺序 = 右子节点 -> 左子节点
        Stack<TreeNode> stack2 = new Stack<>();// 栈2：需打印的层数 = 偶数、入栈顺序 = 左子节点 -> 右子节点

        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();// 存储全部层的节点结果


        // 2. 判断当前结点是否为空
        if( root == null) {
            System.out.println("输入的头节点为空");
            return list;
        }

        TreeNode temp;

        // 栈1入栈根节点 = 第1层 = 奇数
        stack1.push(root);

        while(!stack1.isEmpty() || !stack2.isEmpty()){

            ArrayList<Integer> result1 = new ArrayList<Integer>(); // 存储奇数层的节点数（用于输出结果）

            if(!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {

                    // 出栈队首元素 & 输出
                    temp = stack1.pop();
                    // 将结果存储在链表中
                    result1.add(temp.val);

                    // 保存下1层节点
                    // 因为本层 = 奇数，那么下层 = 偶数，即保存到栈2，入栈顺序 = 左子节点 -> 右子节点
                    if (temp.left != null)
                        stack2.push(temp.left);
                    if (temp.right != null)
                        stack2.push(temp.right);
                }
                // 栈空时，说明当层节点已经遍历完毕，即输出该层所有节点
                list.add(result1);

            }
            else {
                ArrayList<Integer> result2 = new ArrayList<Integer>(); // 存储偶数层的节点数（用于输出结果）

                while (!stack2.isEmpty()) {

                    // 出栈队首元素 & 输出
                    temp = stack2.pop();
                    // 将结果存储在链表中
                    result2.add(temp.val);

                    // 保存下1层节点
                    // 因为本层 = 偶数，那么下层 = 奇数，即保存到栈1，入栈顺序 = 右子节点 -> 左子节点
                    if (temp.right != null)
                        stack1.push(temp.right);
                    if (temp.left != null)
                        stack1.push(temp.left);
                }

                // 栈空时，说明当层节点已经遍历完毕，即输出该层所有节点
                list.add(result2);

            }

        }
        return list;
    }


    /**
     * 测试用例
     */

    public static void main(String[] args){
        // 功能测试
        System.out.println("功能测试");
        //             1
        //          /    \
        //         2      3
        //       /  \    / \
        //      4    5 6   7
        //     / \  / \/ \ / \
        //   8    91011121314 15
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);

        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);

        root.right.left.left = new TreeNode(12);
        root.right.left.right = new TreeNode(13);

        root.right.right.left = new TreeNode(14);
        root.right.right.right = new TreeNode(15);

        System.out.println(levelTravel3(root));

        // 特殊输入测试：头节点为空、只有1个节点的二叉树
        System.out.println("特殊输入测试");
        System.out.println(levelTravel3(null));

        TreeNode root1 = new TreeNode(8);
        System.out.println(levelTravel3(root1));

//        // 功能测试
//        System.out.println("功能测试");
//        //            8
//        //          /   \
//        //         6     10
//        //       /  \   / \
//        //      5    7 9   11
//        TreeNode root = new TreeNode(8);
//        root.left = new TreeNode(6);
//        root.right = new TreeNode(10);
//        root.left.left = new TreeNode(5);
//        root.left.right = new TreeNode(7);
//        root.right.left = new TreeNode(9);
//        root.right.right = new TreeNode(11);
//        System.out.println(levelTravel2(root));
//
//        // 特殊输入测试：头节点为空、只有1个节点的二叉树
//        System.out.println("特殊输入测试");
//        System.out.println(levelTravel2(null));
//
//        TreeNode root1 = new TreeNode(8);
//        System.out.println(levelTravel2(root1));
    }

}
