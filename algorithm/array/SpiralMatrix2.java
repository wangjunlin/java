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
 * 思路 :状态转移， 同螺旋矩阵2，做好边界判定，读取过得设置为-1
 */
public class SpiralMatrix {
    private enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> results = new ArrayList<>(matrix.length * matrix[0].length);

        return results;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(new SpiralMatrix().spiralOrder(matrix));
    }
}
