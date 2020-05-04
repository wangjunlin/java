/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 示例 1:
 * 输入:
 * [[ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * 输入:
 * [[1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 思路 :状态转移， 同螺旋矩阵2，做好边界判定
 */
public class SpiralMatrix {
    private enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        int totalNum = matrix.length * matrix[0].length;
        List<Integer> results = new ArrayList<>(totalNum);

        int row = 0, col = 0, round = 0;
        Direction nowDirect = Direction.RIGHT;
        while (results.size() < totalNum) {
            results.add(matrix[row][col]);
            switch (nowDirect) {
                case RIGHT:
                    if (col + 1 >= matrix[0].length - round) {
                        nowDirect = Direction.DOWN;
                        row++;
                        break;
                    }
                    col++;
                    break;
                case DOWN:
                    if (row + 1 >= matrix.length - round) {
                        nowDirect = Direction.LEFT;
                        col--;
                        continue;
                    }
                    row++;
                    break;
                case LEFT:
                    if (col - 1 < round) {
                        nowDirect = Direction.UP;
                        row--;
                        continue;
                    }
                    col--;
                    break;
                case UP:
                    // round + 1的1为初始位置
                    if (row - 1 < round + 1) {
                        nowDirect = Direction.RIGHT;
                        col++;
                        round++;
                        continue;
                    }
                    row--;
                    break;
            }
        }
        return results;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}; // [1,2,3,4,8,12,11,10,9,5,6,7]
//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}; // [1,2,3,6,9,8,7,4,5]
        int[][] matrix = {}; // [1,2,3,6,9,8,7,4,5]
        System.out.println(new SpiralMatrix().spiralOrder(matrix));
    }
}
