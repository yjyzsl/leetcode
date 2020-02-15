package cn.yjyzsl.leetcode.heap;

/**
 * @Description 有序矩阵中第K小的元素
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 * <p>
 * 示例:
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * 返回 13。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author shil20
 * @Date 2020/2/2 20:28
 **/
public class KthSmallest {

    /**
     * 构建一个k个元素的大顶堆，堆顶就为第k小元素
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int[] arr = new int[k + 1];
        int index = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int val = matrix[i][j];
                // 初始化数组
                if(index <= k){
                    arr[index] = val;
                } else if(index == k + 1){
                    // 初始化堆
                    buildHeap(arr, k);
                    // 插入数据
                    insert(arr, val);
                } else {
                    // 插入数据
                    insert(arr, val);
                }
                index++;
            }
        }
        if(index == k + 1){
            // 初始化堆
            buildHeap(arr, k);
        }
        return arr[1];
    }

    /**
     * 初始化堆
     *
     * @param arr
     * @param n
     * @return
     */
    public void buildHeap(int[] arr, int n) {
        if (null == arr || arr.length == 0) {
            return;
        }
        for (int i = n / 2; i > 0; --i) {
            heapify(arr, n, i);
        }
    }

    /**
     * 堆化
     *
     * @param arr
     * @param n   堆化结束位置
     * @param i   堆化开始位置
     */
    public void heapify(int[] arr, int n, int i) {
        int k = i;
        while (true) {
            if ((2 * i) <= n && arr[i] < arr[2 * i]) {
                k = 2 * i;
            }
            if ((2 * i + 1) <= n && arr[k] < arr[2 * i + 1]) {
                k = 2 * i + 1;
            }
            // 说明i已经是最大值了，不需要堆化了
            if (k == i) {
                break;
            }
            swap(arr, i, k);
            i = k;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 新增元素如果大于堆顶元素则不做处理
     * 小于堆顶元素则将堆顶元素与最后一个元素进行交换然后堆化
     * @param arr
     * @param val
     * @return 返回当前堆顶元素
     */
    public int insert(int[] arr, int val) {
        // 取堆顶元素
        int top = arr[1];

        if(top <= val){
            return top;
        }

        int count = arr.length - 1;
        // 替换堆顶元素
        arr[1] = val;
        // 堆化
        heapify(arr, count, 1);

        return arr[1];
    }

    public void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,  4,  9},
                {10, 11, 3},
                {12, 5, 2}
        };
        KthSmallest kthSmallest = new KthSmallest();
        int res = kthSmallest.kthSmallest(matrix, 5);
        System.out.println(res);

        res = kthSmallest.kthSmallest(matrix, 6);
        System.out.println(res);

        matrix = new int[][]{
                {1,  2},
                {1,  3}
        };
        res = kthSmallest.kthSmallest(matrix, 4);
        System.out.println(res);

    }

}
