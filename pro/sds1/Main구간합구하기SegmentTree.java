package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main구간합구하기SegmentTree {
	public static int n;
	public static int m;
	public static int[] nums;
	public static long[] segTree;
	public static int treesize;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		n=Integer.parseInt(s[0]);
		m=Integer.parseInt(s[1]);
		m+=Integer.parseInt(s[2]);
		
		nums=new int[n];
		treesize=1;
		int height=0;
		while(treesize<n) {
			treesize<<=1;
			height++;
		}
		segTree=new long[height*n];
		for(int i=0;i<n;i++) {
			nums[i]=Integer.parseInt(br.readLine());
		}
		init(nums,1,0,n-1);
		
		for(int i=0;i<m;i++) {
			s=br.readLine().split(" ");
			int a=Integer.parseInt(s[0]);
			int b=Integer.parseInt(s[1]);
			int c=Integer.parseInt(s[2]);
			switch(a) {
			case 1:
				int diff=c-nums[b-1];
				nums[b-1]=c;
				update(diff,b-1,1,0,n-1);
				break;
			case 2:
				System.out.println(sum(1,0,n-1,b-1,c-1));
				break;
			}
		}
	}
	private static long sum(int nodenum, int start, int end, int left, int right) {
		if(start>right || end<left) {
			return 0;
		}
		if(left<=start && right>=end) {
			return segTree[nodenum];
		}
		return sum(nodenum*2,start,(start+end)/2,left,right)+sum(nodenum*2+1,(start+end)/2+1,end,left,right);
	}
	private static void update(int diff,int index, int nodenum, int start, int end) {
		if(index<start || index > end) {
			return;
		}
		segTree[nodenum]+=diff;
		if(start!=end) {
			update(diff,index,nodenum*2,start,(start+end)/2);
			update(diff,index,nodenum*2+1,(start+end)/2+1,end);
		}
	}
	private static long init(int[] inputs,int nodenum, int start, int end) {
		if(start==end) {
			return segTree[nodenum]=inputs[start];
		}else {
			return segTree[nodenum]=init(inputs,nodenum*2,start,(start+end)/2)+init(inputs,nodenum*2+1,(start+end)/2+1,end);
		}
	}
	
	
}
