package scut.carson_ho.androidinterview.AlgorithmLearning;

/**
 * Created by Carson_Ho on 17/11/2.
 */

public class Exam_26 {

        /**
         * 设置结点结构
         */
        public static class TreeNode {
            double val;
            TreeNode left;
            TreeNode right;

            public TreeNode(double data) {
                this.val = data;
                this.left = null;
                this.right = null;
            }
        }



    /**
     * 解题算法
     * 主要步骤：
     * 1. 在树A中找到与树B的根节点值相同的节点R
     * 2. 判断 树A中以R为根节点的子树 是否 包含和树B一样的结构
     */
    public static boolean HasSubtree(TreeNode root1, TreeNode root2){

        // 输入的树A、树B空指针的判断
        if(root1 == null || root2 == null)
            return false;

        // 1.  在树A中找到与树B的根节点值相同的节点R
        // 通过新定义的判断函数，判断2个小数（doubl）是否相等
        if(equal(root1.val,root2.val)){
            // 若相等，则继续判断 树A中以R为根节点的子树 是否 包含和树B一样的结构
            if(tree1HasTree2FromRoot(root1,root2))
                return true;
        }

        // 通过递归继续寻找 树A中与树B的根节点值相同的节点R
        return HasSubtree(root1.left,root2) || HasSubtree(root1.right,root2);
    }

    /**
     * 辅助算法：判断 树A中以R为根节点的子树 是否 包含和树B一样的结构
     */

    public static boolean tree1HasTree2FromRoot(TreeNode root1, TreeNode root2){
        // 注意输入的子树节点的空指针判断
        if(root2 == null)
            return true;

        if(root1 == null)
            return false;

        // 若子树节点相等，则继续往下判断
        if(equal(root1.val,root2.val) && tree1HasTree2FromRoot(root1.left,root2.left) && tree1HasTree2FromRoot(root1.right,root2.right))
            // 若根节点的左、右子树均相等，则说明 B是A的子结构

            return true;
        else
            return false;

    }

    /**
     * 辅助算法：判断 double值相等函数
     * 原理：通过判断二者之间的差的绝对值是否在一个很小的范围（-0.00000001~0.00000001），若 是，则认为二者相等。反之亦然
     */
    public static boolean equal(double root1, double root2){
        if((root1 - root2 > -0.0000001) && (root1 - root2 < 0.0000001))
            return true;
        else
            return false;

    }

    /**
     * 测试用例
     */

    public static void main(String[] args){
        // 树结构定义
        // 树A
        TreeNode root1 = new TreeNode (8);
        root1.left = new TreeNode(8);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(9);
        root1.left.right = new TreeNode(2);
        root1.left.right.left = new TreeNode(4);
        root1.left.right.right = new TreeNode(7);

        // 树B
        TreeNode root2 = new TreeNode(8);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(2);

        // 树C
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(4);
        root3.right = new TreeNode(3);

        // 功能测试：树B是树A的子结构、树C是树A的子结构
        System.out.println(HasSubtree(root1,root2));
        System.out.println(HasSubtree(root1,root3));

        // 异常测试：树A、树B 任一为空 / 均为空时
        System.out.println(HasSubtree(null,root3));
        System.out.println(HasSubtree(root1,null));
        System.out.println(HasSubtree(null,null));
    }


}
