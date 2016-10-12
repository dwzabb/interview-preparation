import java.util.*;
public class ShortestDistanceFromAllBuildings {
    int m = 0, n = 0;
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int buildingCount = 0, emptyCount = 0;;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildingCount++;
                } else if (grid[i][j] == 0) {
                    emptyCount++;
                }
            }
        }
        
        if (emptyCount == 0) {
            return -1;
        }
        
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) { // empty land
                    List<Integer> distances = bfs(grid, i, j);
                    if (distances.size() != buildingCount) {
                        continue;
                    }
                    System.out.print("i "+i+", j "+j+", distance sum "+getSum(distances));
                    System.out.print(", [");
                    for (int val: distances) {
                    	System.out.print(val+ ", ");
                    }
                    System.out.println("]");
                    result = Math.min(result, getSum(distances));
                }
            }
        }
        
        return result == Integer.MAX_VALUE? -1 : result;
    }
    
    private int getSum(List<Integer> nums) {
        int sum = 0;
        for (int val: nums) {
            sum += val;
        }
        return sum;
    }
    
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    
    private List<Integer> bfs(int[][] grid, int x, int y) {
        List<Integer> distances = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        Queue<Position> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.offer(new Position(x, y));
        int distance = -1;
        
        while (!queue.isEmpty()) {
            distance++;
            int queueSize = queue.size();
            for (int k = 0; k < queueSize; k++) {
                Position cur = queue.poll();
                if (grid[cur.row][cur.col] == 1) {
                    distances.add(distance);
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int neighbor_row = cur.row + dx[i];
                    int neighbor_col = cur.col + dy[i];
                    if (neighbor_row < 0 || neighbor_row >= m || neighbor_col < 0 || neighbor_col >= n) {
                        continue;
                    }
                    // visited
                    if (visited[neighbor_row][neighbor_col]) {
                        continue;
                    }
                    // obstacle
                    if (grid[neighbor_row][neighbor_col] == 2) {
                        continue;
                    }
                    visited[neighbor_row][neighbor_col] = true;
                    queue.offer(new Position(neighbor_row, neighbor_col));
                }
            }
        }
        
        return distances;
    }
}

class Position {
    int row, col;
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}