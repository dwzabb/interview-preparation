import java.util.*;

public class WordSearchII {
    TrieNode root;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        buildTrieTree(words);
        
        List<String> result = new ArrayList<>();
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dfs(board, i, j, visited, new StringBuilder(), result);
            }
        }
        
        return result;
    }
    
    private void dfs(char[][] board,
                     int x, 
                     int y, 
                     boolean[][] visited,
                     StringBuilder sb,
                     List<String> result) {
    	
        if (containsWord(sb.toString())) {
            result.add(sb.toString());
        }
        
        visited[x][y] = true;
        sb.append(board[x][y]);
        
        System.out.println("dfs: x "+x+", y "+y+", sb "+sb.toString());
        if (!containsSubStr(sb.toString())) {
            visited[x][y] = false;
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int neighbor_x = x + dx[i];
            int neighbor_y = y + dy[i];
            if (neighbor_x < 0 || neighbor_x >= board.length) {
                continue;
            }
            if (neighbor_y < 0 || neighbor_y >= board[0].length) {
                continue;
            }
            if (visited[neighbor_x][neighbor_y]) {
                continue;
            }
            dfs(board, neighbor_x, neighbor_y, visited, sb, result);
        }
        visited[x][y] = false;
        sb.deleteCharAt(sb.length() - 1);
    }
    
    private boolean containsSubStr(String str) {
        TrieNode cur = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!cur.childs.containsKey(c)) {
                return false;
            }
            cur = cur.childs.get(c);
        }
        return true;
    }
    
    private boolean containsWord(String str) {
        TrieNode cur = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!cur.childs.containsKey(c)) {
                return false;
            }
            cur = cur.childs.get(c);
        }
        return cur.isWord;
    }
    
    private void buildTrieTree(String[] words) {
        for (String word: words) {
            addWord(word);
        }
    }
    
    private void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c= word.charAt(i);
            if (!cur.childs.containsKey(c)) {
                cur.childs.put(c, new TrieNode());
            }
            cur = cur.childs.get(c);
        }
        cur.word = word;
        cur.isWord = true;
    }
}

class TrieNode {
    HashMap<Character, TrieNode> childs;
    boolean isWord;
    String word;
    
    public TrieNode() {
        childs = new HashMap<>();
    }
    
    public TrieNode(String word) {
        this.word = word;
        this.isWord = true;
    }
}