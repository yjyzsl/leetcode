package cn.yjyzsl.leetcode.digit;

import cn.yjyzsl.leetcode.base.ListNode;

/**
 * @Description 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author shil20
 * @Date 2020/1/29 15:59
 **/
public class AddTwoNumbers {

    /**
     * 两数相加
     * 1.创建一个Node节点作为头节点
     * 2.遍历两个链表，节点为null时，值设置为0，两个节点分发为p,q
     * 3.两个节点的值相加，进位用变量carry
     * 4.next节点的值为 (pVal + qVal + carry)%10 的余数 carry是(pVal + qVal + carry)/10 除数
     * 5.将next节点设置为当前节点
     * 6.遍历两个链表的下一个节点，注意临界点的判断
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode headNode = new ListNode(0);
        int carry = 0;
        ListNode p = l1, q = l2, currNode = headNode;
        while (null != p || null != q) {
            int pVal = p == null ? 0 : p.val;
            int qVal = q == null ? 0 : q.val;
            int sumVal = pVal + qVal + carry;
            carry = sumVal / 10;
            int curVal = sumVal % 10;
            currNode.next = new ListNode(curVal);
            currNode = currNode.next;

            // 遍历下一个节点
            if (null != p) p = p.next;
            if (null != q) q = q.next;
        }
        // 最高位进位
        if (carry > 0) {
            currNode.next = new ListNode(carry);
        }
        return headNode.next;
    }

    /**
     * 将两个链表求和的值合并到l1链表上
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode headNode = new ListNode(0);
        // 将求和的连接指向l1
        headNode.next = l1;
        ListNode p = l1, q = l2, currNode = headNode;

        while (null != p || null != q) {
            if (null == p) {
                p = new ListNode(0);
            }
            int pVal = p.val;
            int qVal = q == null ? 0 : q.val;
            int sumVal = pVal + qVal + carry;
            carry = sumVal / 10;
            int curVal = sumVal % 10;
            p.val = curVal;

            currNode.next = p;
            currNode = currNode.next;
            // 遍历下一个节点
            if (null != q) q = q.next;
            if (null != p) p = p.next;
        }
        // 最高位进位
        if (carry > 0) {
            currNode.next = new ListNode(carry);
        }
        return headNode.next;
    }

    public void print(ListNode listNode) {
        while (null != listNode) {
            System.out.print(listNode.val + ",");
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static ListNode getListNode(int[] a) {
        ListNode headNode = new ListNode(0);
        ListNode listNode = headNode;
        for (int i = 0; i < a.length; i++) {
            listNode.next = new ListNode(a[i]);
            listNode = listNode.next;
        }
        return headNode.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        int[] a1 = new int[]{0, 1};
        int[] a2 = new int[]{0, 1, 2};
        ListNode l1 = getListNode(a1);
        ListNode l2 = getListNode(a2);
        ListNode result = addTwoNumbers.addTwoNumbers(l1, l2);
        addTwoNumbers.print(result);

        a1 = new int[]{};
        a2 = new int[]{0, 1};
        l1 = getListNode(a1);
        l2 = getListNode(a2);
        result = addTwoNumbers.addTwoNumbers(l1, l2);
        addTwoNumbers.print(result);

        a1 = new int[]{9, 9};
        a2 = new int[]{1};
        l1 = getListNode(a1);
        l2 = getListNode(a2);
        result = addTwoNumbers.addTwoNumbers(l1, l2);
        addTwoNumbers.print(result);
    }

}

