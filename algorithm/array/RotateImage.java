/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 示例 1:
 * 给定 matrix =
 * [[1,2,3],
 * [4,5,6],
 * [7,8,9]],
 * 原地旋转输入矩阵，使其变为:
 * [[7,4,1],
 * [8,5,2],
 * [9,6,3]]
 * 示例 2:
 * 给定 matrix =
 * [[ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]],
 * 原地旋转输入矩阵，使其变为:
 * [[15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]]
 * 思路: 同旋转矩阵，先中心翻转180°后再以对角线对折
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
//        int[] tempRow;
        // 翻转
        for (int i = 0; i < matrix.length / 2; i++) {
            /*tempRow =  matrix[i];
            matrix[i] = matrix[matrix.length - 1 - i];
            matrix[matrix.length - 1 - i] = tempRow;*/
            // 不增加额外空间
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = matrix[i][j] ^ matrix[matrix.length - 1 - i][j];
                matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - 1 - i][j];
            }
        }
        // 对折
//        int temp;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                matrix[j][i] = matrix[j][i] ^ matrix[i][j];
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        new RotateImage().rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
