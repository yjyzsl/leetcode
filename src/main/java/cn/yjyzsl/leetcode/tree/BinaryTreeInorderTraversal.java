package cn.yjyzsl.leetcode.tree;

import cn.yjyzsl.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 二叉树中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author shil20
 * @Date 2020/2/1 16:48
 **/
public class BinaryTreeInorderTraversal {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode add(TreeNode parent, int val) {
        if (null == root) {
            root = new TreeNode(val);
            parent = root;
        }
        TreeNode treeNode = new TreeNode(val);
        if (val < parent.val) {
            parent.left = treeNode;
        } else {
            parent.right = treeNode;
        }
        return treeNode;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> midPrintList = new ArrayList<Integer>();
        if(null == root){
            return midPrintList;
        }
        midPrint(root, midPrintList);
        return midPrintList;
    }

    /**
     * 中序遍历
     *
     * @param treeNode
     * @param midPrintList
     */
    public void midPrint(TreeNode treeNode, List<Integer> midPrintList) {
        if (null == treeNode) {
            return;
        }
        midPrint(treeNode.left, midPrintList);
        midPrintList.add(treeNode.val);
        midPrint(treeNode.right, midPrintList);
    }

    public static void main(String[] args) {
        BinaryTreeInorderTraversal binaryTreeInorderTraversal = new BinaryTreeInorderTraversal();
        /**
         *    4
         *  2  6
         * 1 3 5 7
         */
        binaryTreeInorderTraversal.add(null, 4);
        TreeNode root = binaryTreeInorderTraversal.getRoot();
        TreeNode node2 = binaryTreeInorderTraversal.add(root, 2);
        TreeNode node6 = binaryTreeInorderTraversal.add(root, 6);

        TreeNode node1 = binaryTreeInorderTraversal.add(node2, 1);
        TreeNode node3 = binaryTreeInorderTraversal.add(node2, 3);

        TreeNode node5 = binaryTreeInorderTraversal.add(node6, 5);
        TreeNode node7 = binaryTreeInorderTraversal.add(node6, 7);

        List<Integer> midPrintList = binaryTreeInorderTraversal.inorderTraversal(root);
        System.out.println(midPrintList);
    }

}

