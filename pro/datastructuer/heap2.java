package datastructuer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class hp2{
	int[] list;
	int size;
	hp2(int n){
		list=new int[n];
		size=0;
	}
	
	void push(int val) {
		list[++size]=val;
		
		int now=size;
		while(now!=1) {
			if(list[now>>1]<list[now]) { //큰순서대로
				swap(now,now>>1);
				now/=2;
			}else {
				break;
			}
		}
	}

	private void swap(int now, int i) {
		int tmp=list[now];
		list[now]=list[i];
		list[i]=tmp;
	}
}

public class heap2 {
	public static void main(String[] args) {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		
	}
}
