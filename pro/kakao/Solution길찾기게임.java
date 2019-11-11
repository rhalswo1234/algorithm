package kakao;

import java.util.ArrayList;
import java.util.Comparator;

class Node{
	int value;
	int height;
	Node left;
	Node right;
	Node(int n,int h){
		value=n;
		height=h;
	}
}

public class Solution길찾기게임 {
	public static void main(String[] args) {
		int[][] input= {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		solution(input);
	}
	
	public static int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        ArrayList<int[]> list=new ArrayList<int[]>();
        for(int i=0;i<nodeinfo.length;i++) {
        	list.add(new int[] {nodeinfo[i][0],nodeinfo[i][1],i+1});
        }
        list.sort(new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				int result=o2[1]-o1[1];
				if(result==0) {
					return o1[0]-o2[0];
				}
				return result;
			}
		});
        for(int i=0;i<nodeinfo.length;i++) {
        	System.out.println(list.get(i)[0]+" "+list.get(i)[1]+" "+list.get(i)[2]);
        }
        int tmp=1;
        int h=0;
        while(tmp<nodeinfo.length) {
        	tmp<<=1;
        	h++;
        }
        Node[] Tree=new Node[nodeinfo.length*h+1];
        for(int i=0;i<list.size();i++) {
        	addNode()
        }
        return answer;
	}
}
