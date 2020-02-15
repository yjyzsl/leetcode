package cn.yjyzsl.leetcode.digit;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author shil20
 * @Date 2020/1/31 19:16
 **/
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 思路-窗口滑动
     * 遍历字符串，当遇到相同字符时滑动窗口就从已存在字符开始
     * 如：abcabcbb 当遍历到 abca时，滑动窗口就为bca
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            // 字符串中存在相同的字符
            if (map.containsKey(s.charAt(j))) {
                // 获取滑动窗口起始位置
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //取较大值作为最长长度
            maxLen = Math.max(maxLen, j - i + 1);
            //保存字符j与j的索引
            map.put(s.charAt(j), j + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {

        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        String s = "abba";
        int len = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s);
        System.out.println(s + ":" + len);

        s = "bbbbb";
        len = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s);
        System.out.println(s + ":" + len);

        s = "pwwkew";
        len = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s);
        System.out.println(s + ":" + len);

        s = "abcde";
        len = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s);
        System.out.println(s + ":" + len);

        s = "a";
        len = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s);
        System.out.println(s + ":" + len);

        s = "abcabcbb";
        len = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s);
        System.out.println(s + ":" + len);

        s = "tmmzuxt";
        len = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s);
        System.out.println(s + ":" + len);


    }

}
