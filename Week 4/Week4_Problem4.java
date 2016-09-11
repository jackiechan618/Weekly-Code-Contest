import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Week4_Problem4 {
	private Map<String, Set<String>> graph = new HashMap<String, Set<String>>();
	private Map<String, Double> map = new HashMap<String, Double>();
	
	public double[] calcEquation(String[][] equations, double[] values, String[][] query) {
        if(equations == null || values == null || query == null || query.length == 0 || equations.length != values.length){
        	return new double[0];
        }
        
        int len = equations.length;
        double[] ans = new double[query.length];
        
        for(int i = 0; i < len; i++){
        	if(!graph.containsKey(equations[i][0])){
        		graph.put(equations[i][0], new HashSet<String>());
        	}
        	graph.get(equations[i][0]).add(equations[i][1]);
        	
        	if(!graph.containsKey(equations[i][1])){
        		graph.put(equations[i][1], new HashSet<String>());
        	}
        	graph.get(equations[i][1]).add(equations[i][0]);
        	
        	map.put(equations[i][0] + ":" +equations[i][1], values[i]);
        	map.put(equations[i][1] + ":" +equations[i][0], 1.0 / values[i]);
        }
        
        for(int i = 0; i < query.length; i++){
        	Set<String> visited = new HashSet<String>();
        	ans[i] = DFS(query[i][0], query[i][1], 1, visited);
        }
        
        return ans;
    }
	
	public double DFS(String start, String end, double sum, Set<String> visited){		
		if(!graph.containsKey(start)){
			return -1.0;
		} else if(start.equals(end)){
			return 1.0;
		} else if(map.containsKey(start + ":" + end)){
			return sum * map.get(start + ":" + end);
		} 
 		
		visited.add(start);
		double result = -1.0;		
		
		for(String next : graph.get(start)){			
			if(!visited.contains(next)){			
				if(map.containsKey(start + ":" + next)){
					result = DFS(next, end, sum * map.get(start + ":" + next), visited);
				} 
				
				if(result != -1.0){
					return result;
				}
			}
		}
		
		visited.remove(start);
		return result;
	}
	
	
		
	public static void main(String[] args){
		Week4_Problem4 t = new Week4_Problem4();
		
//		String[][] equations = {{"a", "b"}, {"b", "c"}};
//		double[] values = {2.0, 3.0};
//		String[][] query = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"} };
		
//		String[][] equations = {{"a", "e"}, {"b", "e"}};
//		double[] values = {4, 3};
//		String[][] query = {{"a", "b"}};
		
//		String[][] equations = {{"a", "aa"}};
//		double[] values = {9};
//		String[][] query = {{"aa", "a"}};
		
		String[][] equations = {{"x1", "x2"}, {"x2", "x3"}, {"x1", "x4"}, {"x2", "x5"}};
		double[] values = {3,0.5,3.4,5.6};
//		String[][] query = {{"x1","x5"}};
		String[][] query = {{"x2","x4"},{"x1","x5"},{"x1","x3"},{"x5","x5"},{"x5","x1"},{"x3","x4"},{"x4","x3"},{"x6","x6"},{"x0","x0"}};

		
		double[] ans = t.calcEquation(equations, values, query);
		
		for(double num : ans){
			System.out.print(num + ", ");
		}
		
	}
}
