package scut.carson_ho.androidinterview.AlgorithmLearning;

import java.util.LinkedList;

/**
 * Created by Carson_Ho on 17/10/22.
 */

public class Exam_7 {

    /**
     * 解题算法
     */
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        // 判断输入数据的合法性
        if(pre ==null || pre ==null || pre.length==0 || pre.length != in.length)
            return null;

        // 执行递归辅助算法
        TreeNode root = reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;

     }

    /**
     * 递归辅助算法
     *
     * @param pre 前序序列
     * @param startPre 前序序列开始坐标
     * @param endPre 前序序列结束坐标
     *
     * @param in 中序序列
     * @param startIn 中序序列开始坐标
     * @param endIn 中序序列结束坐标
     * @return
     */

     private static TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre > endPre || startIn>endIn )
            return null;

            // 1. 重建二叉树的根节点 = 前序遍历序列的第1个结点
            TreeNode root=new TreeNode(pre[startPre]);

         // 2. 通过数组遍历，在中序遍历中找出根节点
      for(int i= startIn; i<=endIn ;i++)

          // 3. 根据在上1步找出的根节点，找出前序遍历 & 中序遍历中的左、右子树，并 作为递归输入，重复上述步骤进行递归
          // 最终找出重建二叉树的左、右子树
            if(in[i]==pre[startPre]){

                // a. 前序遍历的左子树 = 第1位（根节点）的下1位 ~ x ，其中，x = 根节点 + 中序遍历中左子树的数量、中序遍历左子树的数量 = 中序遍历根节点位置的前部分
                // b. 中序遍历的左子树 = 第1位~ 根节点前1位
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+ (i-startIn),  in,startIn,i-1);

                // a. 前序遍历的右子树 = 左子树最后1位的后1位 ~ 最后1位
                // b. 中序遍历的右子树 = 根节点前1位 ~ 最后1位
                root.right=reConstructBinaryTree(pre, (startPre+1) + i-startIn,endPre,in,i+1,endIn);
             }

         return root;
   }

    /**
     * 辅助类：二叉树节点结构类
     */

     static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }


    }

    /**
     * 测试用例
     */

//                1
//              /  \
//              2    3
//             / \  / \
//            4  5 7   6
    public static void main(String[] args) {
        //前序遍历{1,2,4,7,3,5,6,8} & 中序遍历序列{4,7,2,1,5,3,8,6}
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        // 重建二叉树后，使用宽度遍历进行输出测试
        levelTraversal(reConstructBinaryTree(pre,in));


    }

    /**
     * 测试辅助算法：分层遍历二叉树，使用一个队列，也就是宽度优先遍历
     * @param root
     */
    public static void levelTraversal(TreeNode root){
        if(root==null)
            return ;

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.remove();
            System.out.print(cur.val+" ");
            if(cur.left!=null)
                queue.add(cur.left);
            if(cur.right!=null)
                queue.add(cur.right);

        }
    }
}
