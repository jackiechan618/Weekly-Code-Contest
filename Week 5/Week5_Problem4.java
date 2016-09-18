import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Week5_Problem4 {
	public boolean canCross(int[] stones) {
        if (stones.length < 1 || stones[0] != 0){
        	return false;
        }
        
        int len = stones.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int s : stones){
        	map.put(s, new HashSet<Integer>());
        }
        
        for (int stone : stones) {
            Set<Integer> jSet = map.get(stone);
            
            if (stone == 0) {
                jSet.add(0);
                
                if (map.containsKey(1)){
                	map.get(1).add(1);
                }
                continue;
            }
            
            for (int prevJump : jSet) {
                int curJump = prevJump - 1;
                int nextStone = stone + curJump;
                
                if (nextStone != stone && map.containsKey(nextStone)) {
                	map.get(nextStone).add(curJump);
                }
                
                curJump++; 
                nextStone = stone + curJump;
                
                if (nextStone != stone && map.containsKey(nextStone)) {
                	map.get(nextStone).add(curJump);
                }
                
                curJump++; 
                nextStone = stone + curJump;
                
                if (nextStone != stone && map.containsKey(nextStone)) {
                	map.get(nextStone).add(curJump);
                }
            }
        }
        
        return !map.get(stones[len - 1]).isEmpty();
	}
	
	
	
	public static void main(String[] args){
		Week5_Problem4 t = new Week5_Problem4();
		int[] stones = {0,1,3,5,6,8,12,17};
		int[] stones2 = {0,1,2,3,4,8,9,11};
		
		System.out.println(t.canCross(stones));
		System.out.println(t.canCross(stones2));
	}
}
