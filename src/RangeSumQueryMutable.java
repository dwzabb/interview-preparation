public class RangeSumQueryMutable {
    int[] tree;
    int[] nums;
    public RangeSumQueryMutable(int[] nums) {
        this.nums = nums;
        tree = new int[nums.length + 1];
        buildTree(nums);
        
        printTree();
    }
    
    public void printTree() {
    	System.out.println("----- tree -----");
        for (int val: tree) {
        	System.out.print(val + ", ");
        }
        System.out.println();
    }
    
    private void buildTree(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int index = i + 1;
            while (index < tree.length) {
                tree[index] += val;
                index = getNext(index);
            }
        }
    }
    
    private int getNext(int index) {
        return index + (index & -index);
    }
    
    private int getParent(int index) {
        return index - (index & -index);
    }

    void update(int i, int val) {
        if (i < 0 || i >= nums.length) {
            return;
        }
        
        int diff = val - nums[i];
        int index = i + 1;
        while (index < tree.length) {
            tree[index] += diff;
            index = getNext(index);
        }
        System.out.println("After update i "+i+", val "+val);
        printTree();
    }

    public int sumRange(int i, int j) {
        
        int firstISum = 0;
        while (i > 0) {
            firstISum += tree[i];
            i = getParent(i);
        }
        
        int firstJSum = 0;
        while (j > 0) {
            firstJSum += tree[j];
            j = getParent(j);
        }
        
        System.out.println("firstISum "+firstISum+", firstJSum"+firstJSum);
        return firstJSum - firstISum;
    }
}