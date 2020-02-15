package cn.yjyzsl.leetcode.sort;


import cn.yjyzsl.leetcode.base.ListNode;

/**
 * @Description 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author shil20
 * @Date 2020/2/1 18:27
 **/
public class SortList {

    /**
     * 合并 k 个排序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }
        ListNode headNode = lists[0];
        ListNode endNode = getEndNode(lists[0]);
        for (int i = 1; i < lists.length; i++) {
            ListNode tempListNode = lists[i];
            if(null == tempListNode){
                continue;
            }
            if(null != endNode){
                endNode.next = tempListNode;
            }else{
                // 当endNode为空时,headNode指向另一个链表的头节点
                headNode = tempListNode;
            }
            endNode = getEndNode(tempListNode);
        }
        return sortList(headNode);
    }

    /**
     * 链表进行排序
     * 使用的归并排序，先分治后合并
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return splitListNode(head);
    }

    private ListNode getEndNode(ListNode listNode){
        ListNode curNode = listNode, endNode = curNode;
        while (null != curNode) {
            endNode = curNode;
            curNode = curNode.next;
        }
        return endNode;
    }

    /**
     * 切割链表
     *
     * @param listNode
     * @return
     */
    public ListNode splitListNode(ListNode listNode) {
        if (null == listNode || null == listNode.next) {
            return listNode;
        }

        ListNode slowNode = listNode, fastNode = listNode, pNode = listNode;
        // 使用快慢指针对链表进行切割
        while (null != fastNode && null != fastNode.next) {
            pNode = slowNode;
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        ListNode lNode = listNode;
        // 断开
        ListNode rNode = pNode.next;
        pNode.next = null;

        splitListNode(lNode);
        splitListNode(rNode);
        // 合并链表
        return mergeListNode(lNode, rNode);
    }

    /**
     * 将rightListNode链表中的节点合并到leftListNode中，保证合并后有序
     *
     * @param leftListNode
     * @param rightListNode
     * @return
     */
    public ListNode mergeListNode(ListNode leftListNode, ListNode rightListNode) {
        ListNode lNode = leftListNode, rNode = rightListNode, tempNode = leftListNode;

        while (null != lNode && null != rNode) {
            if (lNode.val <= rNode.val) {
                tempNode = lNode;
                lNode = lNode.next;
            } else {
                // 交换位置
                swap(lNode, rNode);

                // 断开rNode
                tempNode = rNode;
                rNode = rNode.next;
                tempNode.next = null;

                // 将rNode合并到lNode
                tempNode.next = lNode.next;
                lNode.next = tempNode;
            }
        }

        // 将剩下的合并
        while (null != rNode) {
            tempNode.next = rNode;
            tempNode = tempNode.next;
            rNode = rNode.next;
        }

        return leftListNode;
    }

    public void swap(ListNode lNode, ListNode rNode) {
        int temp = lNode.val;
        lNode.val = rNode.val;
        rNode.val = temp;
    }

    public static void main(String[] args) {
        SortList sortList = new SortList();
        int[] a = new int[]{4, 1, 3, 2, 1, 3, 3};
        ListNode listNode = ListNode.getListNode(a);
        ListNode.print(listNode);

        listNode = sortList.sortList(listNode);
        ListNode.print(listNode);

        a = new int[]{5, 4, 3, 2, 1, 0};
        listNode = ListNode.getListNode(a);
        ListNode.print(listNode);

        listNode = sortList.sortList(listNode);
        ListNode.print(listNode);

        int[] b1 = new int[]{1, 2, 4};
        ListNode listNode1 = ListNode.getListNode(b1);

        int[] b2 = new int[]{2, 1, 3};
        ListNode listNode2 = ListNode.getListNode(b2);

        int[] b3 = new int[]{4, 2, 5};
        ListNode listNode3 = ListNode.getListNode(b3);

        ListNode[] listNodes = {listNode1, listNode2, listNode3};
        listNode = sortList.mergeKLists(listNodes);
        ListNode.print(listNode);


//        listNodes = {listNode3};
//        listNode = sortList.mergeKLists(listNodes);
//        ListNode.print(listNode);
    }

}

