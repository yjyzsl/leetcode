package cn.yjyzsl.leetcode.digit;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author shil20
 * @Date 2020/1/29 11:47
 **/
public class TwoSum {

    public int[] twoSum1(int[] nums, int target) {
        int length = nums.length;
        int value;
        int[] result = new int[2];
        for(int i = 0; i < length; i++){
            for(int j = (i+1); j < length; j++){
                if(i == j){
                    continue;
                }
                value = nums[i] + nums[j];
                if(value == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public int[] twoSum2(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        // 快速排序
        quickSort(nums, 0, length - 1);
        int mid = target/2;
        // 查找第一个大于等于给定值的元素
        int midIndex = findFirstLteElement(nums, mid);
        System.out.println("midIndex:" + midIndex);
        if(midIndex == -1){
            return result;
        }
        // 找到中位数
        int value;
        for(int i = 0; i <= midIndex; i++){
            for(int j = midIndex; j < length; j++){
                if(i == j){
                    continue;
                }
                value = nums[i] + nums[j];
                if(value == target){

                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 快速排序
     * @param nums 数组
     * @param l 排序数组左边索引
     * @param r 排序数组右边索引
     */
    public void quickSort(int[] nums, int l, int r) {
        if(l >= r){
            return;
        }
        // 查找分区点，分区点左边的都不大于分区点，分区点右边的都不小于分区点
        int p = partition(nums, l, r);
        // 对左边数据排序
        quickSort(nums, l, p - 1);
        // 对右边边数据排序
        quickSort(nums, p + 1, r);
    }

    /**
     * 查找分区点
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int partition(int[] nums, int l, int r){
        if(l >= r){
            return l;
        }
        // 比较点
        int pivot = nums[r];
        int p = l;
        for(int j = p; j < r; j++){
            if(nums[j] < pivot){ // j小于pivot,将i,j位置的值交换,i位置往后移
                swap(nums, p, j);
                p++;
            }
        }
        // 交换
        swap(nums, p, r);
        return p;
    }

    /**
     * 查找第一个大于等于给定值的元素
     * @param nums
     * @param value
     * @return
     */
    public int findFirstLteElement(int[] nums,int value){
        if(null == nums || nums.length == 0){
            return -1;
        }
        int n = nums.length - 1;
        return findFirstLteElementInternally(nums, 0, n, value);
    }

    public int findFirstLteElementInternally(int[] nums, int l, int r, int value){
        if(l > r){
            return -1;
        }
        int mid = l + ((r - l)>>1);
        if(nums[mid] >= value){
            if(mid == 0 || nums[mid-1] < value){ // mid左边的值小于目标值，就表示mid为第一个大于等于目标值
                return mid;
            } else {
                r = mid - 1;
            }
        }else{
            // 目标值在右区间
            l = mid + 1;
        }
        return findFirstLteElementInternally(nums, l, r, value);
    }


    private void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public static void print(int[] nums){
        for(int i=0; i < nums.length - 1; i++){
            System.out.print(nums[i]);
            System.out.print(",");
        }
        System.out.print(nums[nums.length - 1]);
        System.out.println();
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap((int)(nums.length/0.8F + 1F));
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){// 遍历到第二个被加数时表示找了
                result[0] = map.get(temp);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }


    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        //int[] nums = {2, 7, 11, 3, 66, 45, 55};
        //int target = 48;
        int[] nums = new int[]{-3,4,3,90};
        int target = 0;
        int[] result = twoSum.twoSum(nums, target);
        print(result);

        nums = new int[]{2, 7, 11, 3, 66, 45, 55};
        target = 48;
        result = twoSum.twoSum(nums, target);
        print(result);

        nums = new int[]{3,3};
        target = 6;
        result = twoSum.twoSum(nums, target);
        print(result);
    }

}
