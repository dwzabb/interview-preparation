
public class MergeSort {
	
	public static void mergeSort(int[] num) {
		int[] temp = new int[num.length];
		sort(num, 0, num.length - 1, temp);
	}
	
	private static void sort(int[] nums, int start, int end, int[] temp) {
		if (start >= end) {
			return;
		}
		
		int mid = start + (end - start) / 2;
		sort(nums, start, mid, temp);
		sort(nums, mid + 1, end, temp);
		merge(nums, start, mid, end, temp);
	}
	
	private static void merge(int[] nums, int start, int mid, int end, int[] temp) {
		int left = start, right = mid + 1;
		int index = start;
		while (left <= mid && right <= end) {
			if (nums[left] < nums[right]) {
				temp[index++] = nums[left++];
			} else {
				temp[index++] = nums[right++];
			}
		}
		
		while (left <= mid) {
			temp[index++] = nums[left++];
		}
		
		while (right <= end) {
			temp[index++] = nums[right++];
		}
		
		for (index = start; index <= end; index++) {
			nums[index] = temp[index];
		}
	}
}
