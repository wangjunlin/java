/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 思路: 区间合并，先把在开始和末端的极端情况排除，然后在遍历列表时判断是否在中间，即newInterval得最小大于当前的最大，
 * newInterval最大小于下一个的最小，由于是有序的，则可以判定处于中间，且未合并。
 * 其他情况为需合并情况，则原址操作，最后遍历完后把除了null之外的返回
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        if (newInterval.length == 0) {
            return intervals;
        }
        int[][] results;
        // No merge and insert pos at the begin
        if (intervals[0][0] > newInterval[1]) {
            results = new int[intervals.length + 1][2];
            results[0] = newInterval;
            System.arraycopy(intervals, 0, results, 1, intervals.length);
            return results;
        }
        // No merge and insert pos at the end
        if (intervals[intervals.length - 1][1] < newInterval[0]) {
            results = new int[intervals.length + 1][2];
            results[results.length - 1] = newInterval;
            System.arraycopy(intervals, 0, results, 0, intervals.length);
            return results;
        }
        int reduceSpace = -1;
        int insertPos = -1;
        for (int originIndex = 0; originIndex < intervals.length; originIndex++) {
            // No merge and insert pos in center
            if (originIndex < intervals.length - 1 &&
                    intervals[originIndex][1] < newInterval[0] && intervals[originIndex + 1][0] > newInterval[1]) {
                results = new int[intervals.length + 1][2];
                results[originIndex + 1] = newInterval;
                System.arraycopy(intervals, 0, results, 0, originIndex + 1);
                System.arraycopy(intervals, originIndex + 1, results, originIndex + 2, intervals.length - originIndex - 1);
                return results;
            }
            if (intervals[originIndex][1] < newInterval[0] || intervals[originIndex][0] > newInterval[1]) {
                continue;
            }
            if (insertPos == -1) {
                insertPos = originIndex;
                intervals[insertPos][0] = Math.min(newInterval[0], intervals[insertPos][0]);
                intervals[insertPos][1] = Math.max(newInterval[1], intervals[insertPos][1]);
            } else {
                intervals[insertPos][0] = Math.min(intervals[insertPos][0], intervals[originIndex][0]);
                intervals[insertPos][1] = Math.max(intervals[insertPos][1], intervals[originIndex][1]);
                intervals[originIndex] = null;
            }
            reduceSpace++;
        }
        results = new int[intervals.length - reduceSpace][2];
        int nowIndex = 0;
        for (int[] interval : intervals) {
            if (interval == null) {
                continue;
            }
            results[nowIndex] = interval;
            nowIndex++;
        }
        return results;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
//        int[][] intervals = {{9, 10}};
//        int[] newInterval = {14, 18};
//        int[][] intervals = {{3, 5}, {12, 15}};
//        int[] newInterval = {6, 6};
        System.out.println(Arrays.deepToString(new InsertInterval().insert(intervals, newInterval)));
    }
}
