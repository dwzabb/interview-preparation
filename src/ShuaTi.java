import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

class ResultType {
	int val;
	ResultType(int val) {
		this.val = val;
	}
}

class Directory {
    String name;
    int length;
    
    public Directory(String name, int length) {
        this.name = name;
        this.length = length;
    }
}

public class ShuaTi {

	public static void main(String[] args) {
		int[] nums = {7, 2, 7, 2, 0};
		RangeSumQueryMutable rq = new RangeSumQueryMutable(nums);
		rq.update(4,6);
		rq.update(0,2);
		rq.update(0,9);
		rq.sumRange(4,4);
		//update(3,8),sumRange(0,4),update(4,1),sumRange(0,3),sumRange(0,4),update(0,4)
		/*
		int[][] matrix = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
				//sumRegion(2,1,4,3),update(3,2,2,sumRegion(2,1,4,3)
		NumMatrix nm = new NumMatrix(matrix);
		nm.sumRegion(2, 1, 4, 3);
		nm.update(3, 2, 2);
		nm.sumRegion(2, 1, 4, 3);*/
	}
	
	public static String minWindow(String s, String t) {
        if ( s == null || s.length() == 0 || s.length() < t.length())
            return "";
        int i = 0, j = 0, size = Integer.MAX_VALUE;
        String ans = "";
        
        for (i = 0; i < s.length(); i++) {
            while (j < s.length() && !s.substring(i, j).contains(t)) {
                j++;
            }
            if (s.substring(i, j).contains(t) && j-i < size) {
            	System.out.println("in if");
                size = j-i;
                ans = s.substring(i, j);
            }
            System.out.println("in for loop, i:"+i+", j: "+j);
            System.out.println("s.substring(i,j): "+s.substring(i,j)+", s.substring(i, j).contains(t): "+s.substring(i, j).contains(t));
        }
        return ans;
    }
	
	public static int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        
        String[] lines = input.split("\n");
        for (String line: lines) {
        	System.out.println(line);
        }
        Stack<Directory> stack = new Stack<>();
        
        int maxPathLength = 0;
        for (String line: lines) {
            // directory
            if (line.indexOf('.') == -1) {
                int firstTabPosition = line.indexOf("\t");
                int lastTabPosition = line.lastIndexOf("\t");
                int tabCount = firstTabPosition == -1? 0: (lastTabPosition - firstTabPosition) + 1;
                System.out.println("tabCount " +tabCount);
                while (stack.size() > tabCount) {
                    stack.pop();
                }
                int prevLength = stack.isEmpty()? 0 : stack.peek().length;
                String dirName = firstTabPosition == -1? line : line.substring(lastTabPosition + 1);
                stack.push(new Directory(dirName, prevLength + dirName.length()));
            } else { // file
                int firstTabPosition = line.indexOf('\t');
                int lastTabPosition = line.lastIndexOf('\t');
                int tabCount = firstTabPosition == -1? 0: (lastTabPosition - firstTabPosition) + 1;
                System.out.println("---------");
                System.out.println("file "+line);
                System.out.println("firstTabPosition "+firstTabPosition+", lastTabPosition "+lastTabPosition);
                System.out.println("tabCount " +tabCount);
                while (stack.size() > tabCount) {
                    stack.pop();
                }
                int prevLength = stack.isEmpty()? 0 : stack.peek().length;
                System.out.println("stack.peek().name "+stack.peek().name+", stack.peek().length"+stack.peek().length);
                String fileName = firstTabPosition == -1? line : line.substring(lastTabPosition + 1);
                System.out.println("fileName "+fileName);
                maxPathLength = Math.max(maxPathLength, prevLength + fileName.length());
            }
        }
        
        return maxPathLength;
    }

	public static void exercise1_1(String str){
		HashMap<Character, Boolean> map = new HashMap<Character, Boolean>();
		
		boolean strIsUnique = true;
		for(int ii = 0; ii < str.length(); ii++){
			char c = str.charAt(ii);
			if(map.containsKey(c)){
				strIsUnique = false;
			}else{
				map.put(c, true);
			}
		}
		
		if(strIsUnique){
			System.out.println("Unique");
		}else{
			System.out.println("Not unique:");
		}
	}
	
	public static void exercise1_2(String str1, String str2){
		if(str1.length() != str2.length()){
			System.out.println("Not permutation");
			return;
		}
		boolean isPermutation = true;
		int[] charCounter = new int[128];
		
		for(int ii = 0; ii < str1.length(); ii++){
			char c = str1.charAt(ii);
			int index = (int) c;
			charCounter[index]++;
		}
		
		for(int ii = 0; ii < str2.length(); ii++){
			char c = str2.charAt(ii);
			int index = (int) c;
			charCounter[index]--;
			if(charCounter[index] < 0){
				isPermutation = false;
				break;
			}
		}
		
		if(isPermutation){
			System.out.println("Is permutation");
		}else{
			System.out.println("Not permutation");
		}
	}
}
