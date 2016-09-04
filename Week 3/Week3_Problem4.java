import java.util.HashSet;
import java.util.Set;


public class Week3_Problem4 {
	public int longestSubstring(String s, int k) {
        if(s == null || k <= 0){
            return 0;
        }
        
        int[] hash = new int[256];
        int len = s.length();
        int maxLen = 0;       
        int front = 0;       
        Set<Character> set = buildSet(s, k);
              
        for(int back = 0; back < len; back++){    
        	while(front < len && !set.contains(s.charAt(front))){
        		for(int i = 0; i < 256; i++){
        			hash[i] = 0;
        		}

        		front++;
        		back = front;
        	}
        	
        	while(front < len && !isValid(hash, k) && set.contains(s.charAt(front))){
        		hash[s.charAt(front)]++;
        		front++;
        	}
        	
        	if(isValid(hash, k)){
        		maxLen = Math.max(maxLen, front - back);
        		
        		while(front < len && set.contains(s.charAt(front))){
        			hash[s.charAt(front)]++;
        			front++;
        			
        			if(isValid(hash, k)){
        				maxLen = Math.max(maxLen, front - back);
        			}
        		}
        	}     		
        	
        	if(back < len){
        		hash[s.charAt(back)]--;
        	}
        }
        
        return maxLen;
    }
	
	public boolean isValid(int[] hash, int k){
		int count = 0;
		
		for(int i = 0; i < 256; i++){
			if(hash[i] > 0){
				count++;
			}
			
			if(hash[i] > 0 && hash[i] < k){
				return false;
			}
		}

		return count > 0;
	}
	
	public Set<Character> buildSet(String s, int k){
		int[] hash = new int[256];
		Set<Character> set = new HashSet<Character>();
		
		for(char c : s.toCharArray()){
			hash[c]++;
		}
		
		for(int i = 0; i < 256;i ++){
			if(hash[i] >= k){
				set.add((char) i);
			}
		}
		
		return set;
	}
	
	
	
	public static void main(String[] args){
		Week3_Problem4 t = new Week3_Problem4();
//		String s = "aaaaaaaaaaaaaaaabbbbbbbbbbbbaaaaaaabbbbbbbbbbbbcccccccccccdddddddddddddddddddeeeeeeeeeeeeeeefffffffffffffffgggggggggggggggggggghhhhhhhhhhhhhhhhiiiiiiiiiiiiiiiiiiiiiijjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkk";
		String s = "aaabb";
		int k = 3;
		System.out.println(t.longestSubstring(s, k));
	}
}
