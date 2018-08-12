/** Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example:
Input: [3,2,1,5,6,4] and k = 2
Output: 5
*/

// Method 1: heap
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap  = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }
}


// Method 2: quick sort
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int low = 0, high = nums.length -1;
        while (low <= high) {  
            int index = low - 1;
            // put larger than pivot before index, nums[high] is the pivot
            // index marks the pivot position
            for(int i = low; i < high; i++){
                if(nums[i] > nums[high]){
                    swap(nums, i, ++index);
                }
            }
            // put the pivot in place, so that larger than pivot on its left
            swap(nums, ++index, high);
            if (index == k - 1) {
                return nums[index];
            }
            if (index < k -1) {
                low = index + 1;
            } else {
                high = index - 1;
            }
        }
        return -1;  
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// Method 3:
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}