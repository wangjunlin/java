/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * 思路: 累计和，当前数组索引的值为从0到当前索引的值得总计和
 * 再遍历总计和
 * 使用哈希表存储，总体时间复杂度为O(n)
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        /*//  把数组中当前索引的和改为从开始到当前的累计值
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                result++;
            }
            for (int j = 0; j < i; j++) {
                if (nums[i] - nums[j] != k) {
                    continue;
                }
                result++;
            }
        }*/
        // HashMap策略
//         List<Integer> tempIndexes;
//         List<Integer> nowIndexes;
        // 将链表换成数字减少隐藏的时间开销
        Integer tempIndexes;
        Integer nowIndexes;
//         Map<Integer, List<Integer>> sumIndexMap = new HashMap<>(nums.length);
        Map<Integer, Integer> sumIndexMap = new HashMap<>(nums.length);
//         sumIndexMap.put(nums[0], new ArrayList<>(Collections.singletonList(0)));
        sumIndexMap.put(nums[0], 1);
        result += nums[0] == k ? 1 : 0;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
            if ((tempIndexes = sumIndexMap.get(nums[i] - k)) != null) {
//                 result += tempIndexes.size();
                result += tempIndexes;
            }
            if ((nowIndexes = sumIndexMap.get(nums[i])) == null) {
//                 sumIndexMap.put(nums[i], new ArrayList<>(Collections.singletonList(i)));
                sumIndexMap.put(nums[i], 1);
            } else {
//                 nowIndexes.add(i);
//                 sumIndexMap.put(nums[i],nowIndexes);
                sumIndexMap.put(nums[i], nowIndexes + 1);
            }
            result += nums[i] - k == 0 ? 1 : 0;
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {1,2,1,2,1};
//        int k = 3;
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int k = 0;
//        int[] nums = {1,2,3};
//        int k = 3;
//        int[] nums = {1};
//        int k = 0;
        System.out.println(new SubarraySumEqualsK().subarraySum(nums, k));
    }
}
