import java.util.Arrays;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 示例:
 * 输入: 3
 * 输出:
 * [[ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]]
 * 思路: 状态转移，右 下 左 上循环
 */
public class SpiralMatrix2 {
    private enum Director {
        RIGHT, DOWN, LEFT, UP
    }
    public int[][] generateMatrix(int n) {
        int maxN = n * n;
        int[][] results = new int[n][n];
        if (n == 1){
            results[0][0] = 1;
            return results;
        }
        int row = 0, col = 0, nowNum = 1, round = 0;
        Director nowDirector = Director.RIGHT;
        while (nowNum <= maxN) {
            results[row][col] = nowNum;
            switch (nowDirector) {
                case RIGHT:
                    if (col + 1 >= n - round) {
                        nowDirector = Director.DOWN;
                        row++;
                        break;
                    }
                    col++;
                    break;
                case DOWN:
                    if (row + 1 >= n - round) {
                        nowDirector = Director.LEFT;
                        col--;
                        break;
                    }
                    row++;
                    break;
                case LEFT:
                    if (col - 1 < round) {
                        nowDirector = Director.UP;
                        row--;
                        break;
                    }
                    col--;
                    break;
                case UP:
                    if (row - 2 < round) {
                        nowDirector = Director.RIGHT;
                        round++;
                        col++;
                        break;
                    }
                    row--;
                    break;
            }
            nowNum++;
        }
        return results;
    }

    public static void main(String[] args) {
//        int n = 3;
//        int n = 4;
        int n = 5;
        System.out.println(Arrays.deepToString(new SpiralMatrix2().generateMatrix(n)));
    }
}
