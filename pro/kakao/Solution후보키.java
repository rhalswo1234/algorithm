package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Solution후보키 {
	public static String[][] input;
	public static int answer;
	public static ArrayList<String> keylist;
	public static void main(String[] args) {
		String[][] input= {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
		String[][] test={{"100","ryan","music","2","1"},{"200","apeach","math","2","2"},{"300","tube","computer","3","3"},{"400","con","computer","4","4"},{"500","muzi","music","3","5"},{"600","apeach","music","2","2"}};
		solution(input);
		System.out.println(answer);
		for(int i=0;i<keylist.size();i++) {
			System.out.println(keylist.get(i));
		}
	}
	
    public static int solution(String[][] relation) {
    	keylist=new ArrayList<String>();
        answer = 0;
        input=relation;
        for(int size=1;size<=relation[0].length;size++) {
        	int[] selected_index=new int[size];
        	select(0,-1, selected_index, relation[0].length);
        }
//        for(int i=0;i<relation.length;i++) {
//        	for(int j=0;j<relation[i].length;j++) {
//        		System.out.print(relation[i][j]+" ");
//        	}
//        	System.out.println();
//        }
//        System.out.println(input.length);
        
        return answer;
    }

	private static void select(int index,int begin, int[] selected_index, int length) {
		if(index==selected_index.length) {
			//중복체크
			System.out.println(Arrays.toString(selected_index));
			if(keycheck(selected_index)) {
//				System.out.println("hi");
//				System.out.println(Arrays.toString(selected_index));
				answer++;
			}
			return;
		}
		
		for(int i=begin+1;i<length;i++) {
			selected_index[index]=i;
			select(index+1,i,selected_index,length);
		}
	}

	private static boolean keycheck(int[] selected_index) {
//		System.out.println(Arrays.toString(selected_index));
//		for(int i=0;i<selected_index.length;i++) {
//			if(check[selected_index[i]]) return false;
//		}
		HashSet<String> set=new HashSet<String>();
		for(int i=0;i<input.length;i++) {
			String tmp="";
			for(int j=0;j<selected_index.length;j++) {
				tmp+=input[i][selected_index[j]]+" ";
			}
//			System.out.println(tmp);
			set.add(tmp);
		}
//		System.out.println(set.size()+"dd");
//		System.out.println(input.length+"ee");
		if(set.size()==input.length) {
			String tmp="";
			for(int i=0;i<selected_index.length;i++) {
				tmp+=selected_index[i]+" ";
			}
//			System.out.println(tmp);
			for(int i=0;i<keylist.size();i++) {
//				System.out.println(keylist.get(i));
				String[] s=keylist.get(i).split(" ");
				boolean tf = false;//최소성 검사
				for(int j=0;j<s.length;j++) {
					if(!tmp.contains(s[j])) { //하나라도 겹치지 않으면 최소성 만족
						tf=true;
					}
				}
				if(!tf) { //겹친다 = 최소성 만족 x
					return false;
				}
			}
//			System.out.println(tmp);
//			System.out.println(tmp);
			keylist.add(tmp);
			return true;
		}else {
			return false;
		}
	}
}
