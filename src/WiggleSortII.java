public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int size = nums.length;
        int median = (size - 1) / 2;
        partitionByKthElement(nums, median);
        
        // move all medians to the center
        // this is for case [5,3,1,2,6,7,8,5,5]
        // after partition it becomes [5, 3, 1, 2, 5, 5, 8, 7, 6]
        // which will put the fisrt 5 and the last 5 at the end, which is not correct
        int start = 0, end = size - 1;
        while (start < end) {
            while (start < end && nums[start] != nums[median]) {
                start++;
            }
            while (start < end && nums[end] >= nums[median]) {
                end--;
            }
            if (start < end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
            }
        }
        
        int[] tmp = new int[size];
        /*
        int small = 0, large = median + 1;
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                tmp[i] = nums[small++];
            } else {
                tmp[i] = nums[large++];
            }
        }*/
        // use this approach instead of above comment one because
        // the case [4, 5, 5, 6]
        int small = median, large = size - 1;
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                tmp[i] = nums[small--];
            } else {
                tmp[i] = nums[large--];
            }
        }
        
        for (int i = 0; i < size; i++) {
            nums[i] = tmp[i];
        }
    }
    
    private void partitionByKthElement(int[] nums, int k) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int partition = partition(nums, start, end);
            if (partition == k) break;
            else if (partition > k) {
                end = partition - 1;
            } else {
                start = partition + 1;
            }
        }
    }
    
    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int originStart = start;
        while (start <= end) {
            while (start <= end && nums[start] <= pivot) {
                start++;
            }
            while (start <= end && nums[end] > pivot) {
                end--;
            }
            if (start < end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
            }
        }
        
        // swap originStart and end
        int tmp = nums[originStart];
        nums[originStart] = nums[end];
        nums[end] = tmp;
        
        return end;
    }
}