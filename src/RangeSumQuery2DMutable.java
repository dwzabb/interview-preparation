public class RangeSumQuery2DMutable {
    int[][] tree;
    int[][] matrix;
    int m, n;
    public RangeSumQuery2DMutable(int[][] matrix) {
        m = matrix.length;
        if (m == 0) {
            return;
        }
        n = matrix[0].length;
        tree = new int[m + 1][n + 1];
        this.matrix = new int[m][n];
        
        buildTree(matrix);
    }
    
    private void buildTree(int[][] matrix) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }
    
    public void printTree() {
    	System.out.println("----- Tree -----");
    	for (int i = 1; i <= m; i++) {
    		for (int j = 1; j <= n; j++) {
    			System.out.print(tree[i][j] + ", ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    }

    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        
        for (int i = row + 1; i <= m; i = i + (i & -i)) {
            for (int j = col + 1; j <= n; j = j + (j & -j)) {
                tree[i][j] += diff;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row2 + 1, col2 + 1) + sum(row1, col1) - sum(row1, col2 + 1) - sum(row2 + 1, col1);
    }
    
    private int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i = i - (i & -i)) {
            for (int j = col; j > 0; j = j - (j & -j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);