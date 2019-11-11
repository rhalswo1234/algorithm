package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class T_Node{
	long value;
	long lazy;
}


public class Main구간합구하기2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		int n=Integer.parseInt(s[0]);
		int m=Integer.parseInt(s[1]);
		m+=Integer.parseInt(s[2]);
		
		int[] inputs=new int[n];
		for(int i=0;i<n;i++) {
			inputs[i]=Integer.parseInt(br.readLine());
		}
		int height=0;
		int tmp=1;
		while(tmp<=n) {
			tmp<<=1;
			height++;
		}
//		System.out.println(height*n);
		T_Node[] tree=new T_Node[height*n];
		for(int i=0;i<tree.length;i++) {
			tree[i] = new T_Node();
		}
		init(tree,inputs,1,0,n-1);
		for(int i=0;i<m;i++) {
			s=br.readLine().split(" ");
			int a=Integer.parseInt(s[0]);
			int b=Integer.parseInt(s[1]);
			int c=Integer.parseInt(s[2]);
			switch(a) {
			case 1:
				int diff=Integer.parseInt(s[3]);
				update(tree,1,0,n-1,b-1,c-1,diff);
				break;
			case 2:
				System.out.println(sum(1,0,n-1,b-1,c-1));
				break;
			}
		}
	}

	private static void update(T_Node[] tree,int nodenum, int start, int end, int left, int right, int diff) {
		if(tree[nodenum].lazy!=0) {
			tree[nodenum].value+=(end-start+1)*tree[nodenum].lazy;
			if(start!=end) {
				tree[nodenum*2].lazy+=tree[nodenum].lazy;
				tree[nodenum*2+1].lazy+=tree[nodenum].lazy;
			}
			tree[nodenum].lazy=0;
		}
		
		if(left>=start && right<=end) {
			
		}
	}

	private static long init(T_Node[] tree,int[] inputs, int nodenum,int start,int end) {
		if(start==end) {
			return tree[nodenum].value=inputs[start];
		}
		return tree[nodenum].value=init(tree,inputs,nodenum*2,start,(start+end)/2)+init(tree,inputs,nodenum*2+1,(start+end)/2+1,end);
	}
}
