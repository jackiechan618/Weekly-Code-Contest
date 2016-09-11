import java.util.Random;


public class Week4_Problem3 {
	private int[] nums;
	private Random rand;

    public Week4_Problem3(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
    	int result = -1;
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target){
                continue;
            } 
            
            if (rand.nextInt(++count) == 0){
                result = i;
            }
        }
        
        return result;
    }
	
    
    
	public static void main(String[] args){
		int[] nums = {1,2,3,3,3};
		
		Week4_Problem3 t = new Week4_Problem3(nums);
		System.out.println(t.pick(3));
		
	}
}
