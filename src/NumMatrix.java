public class NumMatrix {
    SegmentTreeNode root;
    int m, n;
    public NumMatrix(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        root = buildTree(matrix, 0, convert2DIndexTo1D(m - 1, n - 1));
    }
    
    private SegmentTreeNode buildTree(int[][] matrix, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            Element element = convert1DIndexTo2D(start);
            int val = matrix[element.row][element.col];
            return new SegmentTreeNode(start, end, val);
        }
        
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = buildTree(matrix, start, mid);
        SegmentTreeNode right = buildTree(matrix, mid + 1, end);
        
        int sum = 0;
        if (left != null) {
            sum += left.sum;
        }
        if (right != null) {
            sum += right.sum;
        }
        
        root.left = left;
        root.right = right;
        root.sum = sum;
        
        return root;
    }
    
    private int convert2DIndexTo1D(int x, int y) {
        return x * n + y;
    }
    
    private Element convert1DIndexTo2D(int index) {
        int row = index / n;
        int col = index % n;
        return new Element(row, col);
    }

    public void update(int row, int col, int val) {
        int index = convert2DIndexTo1D(row, col);
        if (index < 0 || index >= m * n) {
            return;
        }
        updateHelper(index, val, root);
    }
    
    private void updateHelper(int index, int val, SegmentTreeNode cur) {
        if (cur.start == index && cur.end == index) {
            cur.sum = val;
            return;
        }
        
        int mid = (cur.start + cur.end) / 2;
        if (index <= mid) {
            updateHelper(index, val, cur.left);
        } else {
            updateHelper(index, val, cur.right);
        }
        cur.sum = cur.left.sum + cur.right.sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int start = convert2DIndexTo1D(row1, col1);
        int end = convert2DIndexTo1D(row2, col2);
        if (start >= m * n || end < 0) {
            return Integer.MIN_VALUE;
        }
        start = Math.max(0, start);
        end = Math.min(m * n - 1, end);
        
        return sumHelper(start, end, root);
    }
    
    private int sumHelper(int start, int end, SegmentTreeNode cur) {
    	System.out.println("start "+start+", end "+end+", cur.start "+cur.start+", cur.end "+cur.end);
        if (cur.start == start && cur.end == end) {
            return cur.sum;
        }
        
        int mid = (cur.start + cur.end) / 2;
        if (end <= mid) {
            return sumHelper(start, end, cur.left);
        } else if (start > mid) {
            return sumHelper(start, end, cur.right);
        } else {
            return sumHelper(start, mid, cur.left) + sumHelper(mid + 1, end, cur.right);
        }
    }
    
    class Element {
        int row, col;
        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}


class SegmentTreeNode {
    int start, end, sum;
    SegmentTreeNode left, right;
    
    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    public SegmentTreeNode(int start, int end, int sum) {
        this.start = start;
        this.end = end;
        this.sum = sum;
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);