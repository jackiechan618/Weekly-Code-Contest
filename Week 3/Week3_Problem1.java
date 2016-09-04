public class Week3_Problem1 {
	public static boolean isSubsequence(String s, String t) {
		if(s == null || t == null){
            if(s == null && t == null){
                return true;
            } else if(t == null){
                return false;
            } else {
                return true;
            }
        }

        int startIndex = 0;
        
        for(char c : s.toCharArray()){
            int curIndex = t.indexOf(c, startIndex);
                        
            if(curIndex < 0){
            	return false;
            } else {
            	startIndex = curIndex + 1;
            }
        }    
        
        return true;
	}
	
	
	
	public static void main(String[] args){
		String str1 = "axc";
		String str2 = "abbbbbcb";
		System.out.println(isSubsequence(str1, str2));
	}
}
