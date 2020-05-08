/**
 * 62.不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 * <p>
 * 思路: 只有向右和向下，可以有递归和最优子结构
 * 回溯，超出时间限制
 * 动态规划 : 子结构 Grid[i][j] = Grid[i - 1][j] + Grid[i][j - 1]
 * 1.采用自底向上
 * 2.采用自顶向下
 */
public class UniquePaths {
    private int count = 0;

    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
//        backTracking(grid, 0, 0);
//        dpByBottomUpMemo(grid);
//        count = grid[0][0];
        count = dpByTopDownMemp(grid);
        return count;
    }

    private void dpByBottomUpMemo(int[][] grid) {
        for (int row = grid.length - 1; row > -1; row--) {
            for (int col = grid[0].length - 1; col > -1; col--) {
                if (row == grid.length - 1 || col == grid[0].length - 1) {
                    grid[row][col] = 1;
                    continue;
                }
                grid[row][col] = grid[row + 1][col] + grid[row][col + 1];
            }
        }
    }

    /**
     * With memorization
     *
     * @param grid
     */
    private int dpByTopDownMemp(int[][] grid) {
        return backTrackingWithMemo(grid, 0, 0);
    }

    private int backTrackingWithMemo(int[][] grid, int row, int col) {
        if (grid[row][col] != 0) {
            return grid[row][col];
        }
        if (row == grid.length - 1 || col == grid[0].length - 1) {
            grid[row][col] = 1;
            return 1;
        }
        // Record found path
        grid[row][col] = backTrackingWithMemo(grid, row + 1, col) + backTrackingWithMemo(grid, row, col + 1);
        return grid[row][col];
    }

    private void backTracking(int[][] grid, int row, int col) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            count++;
            return;
        }
        if (!isValid(grid, row, col)) {
            return;
        }
        backTracking(grid, row + 1, col);
        backTracking(grid, row, col + 1);
    }

    private boolean isValid(int[][] grid, int row, int col) {
        return row < grid.length && col < grid[0].length;
    }

    public static void main(String[] args) {
//        int m = 7, n = 3; // 28
//        int m = 3, n = 2; // 3
//        int m = 30, n = 50; // 750842072
        int m = 100, n = 80; // 363742368
//        int m = 1, n = 1; // 1
        System.out.println(new UniquePaths().uniquePaths(m, n));
    }
}
