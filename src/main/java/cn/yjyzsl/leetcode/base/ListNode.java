package cn.yjyzsl.leetcode.base;

/**
 * @Description singly-linked list
 * @Author shil20
 * @Date 2020/2/1 18:45
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode getListNode(int[] a) {
        if(null == a || a.length == 0){
            return null;
        }
        ListNode listNode = new ListNode(a[0]);
        ListNode headNode = listNode;
        for (int i = 1; i < a.length; i++) {
            listNode.next = new ListNode(a[i]);
            listNode = listNode.next;
        }
        return headNode;
    }

    public static void print(ListNode listNode) {
        if(null == listNode){
            System.out.print("listNode is empty.");
        }
        while (null != listNode) {
            System.out.print(listNode.val + ",");
            listNode = listNode.next;
        }
        System.out.println();
    }

}
