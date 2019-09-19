package hash;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution베스트앨범 {
	public static void main(String[] args) {
		
	}
	
	public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        int count=0;
        int[] index=new int[genres.length];
        ArrayList<
        for(int i=0;i<genres.length;i++) {
        	index[i]=i;
        	if(hm.containsKey(genres[i])) {
        		hm.replace(genres[i], hm.get(genres[i])+plays[i]);
        	}else {
        		hm.put(genres[i], plays[i]);
        	}
        }
        
        return answer;
    }
}
