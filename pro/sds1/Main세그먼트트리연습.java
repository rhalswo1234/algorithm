package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main세그먼트트리연습 {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		int n=Integer.parseInt(s[0]);
		int m=Integer.parseInt(s[1]);
		int k=Integer.parseInt(s[2]);
		
		int[] input=new int[n];
		for(int i=0;i<n;i++) {
			input[i]=Integer.parseInt(br.readLine());
		}
		
		int t=1;
		int height=0;
		while(t<=n) {
			t<<=1;
			height++;
		}
		long[] segTree=new long[n*height+1];
		init(segTree,input,1,0,n-1);
		for(int i=0;i<m+k;i++) {
			s=br.readLine().split(" ");
			int a=Integer.parseInt(s[0]);
			int b=Integer.parseInt(s[1]);
			int c=Integer.parseInt(s[2]);
			switch(a) {
			case 1:
				int d=Integer.parseInt(s[3]);
				update(segTree,input,1,0,n-1,b-1,c-1,d);
				break;
			case 2:
				System.out.println(sum(segTree,input,1,0,n-1,b-1,c-1));
				break;
			}
		}
	}

	private static long sum(long[] segTree, int[] input, int nodenum, int start, int end, int left, int right) {
		if(left>end || right<start) {
			return 0;
		}
		if(left<=start && right>=end) {
			return segTree[nodenum];
		}
		return sum(segTree,input,nodenum*2,start,(start+end)/2,left,right)+sum(segTree,input,nodenum*2+1,(start+end)/2+1,end,left,right);
	}

	private static void update(long[] segTree, int[] input, int nodenum, int start, int end,int left,int right, int d) {
		if(left>end || right<start ) {
			return;
		}
		segTree[nodenum]+=d;
		if(start!=end) {
			update(segTree,input,nodenum*2,start,(start+end)/2,left,right,d);
			update(segTree,input,nodenum*2+1,(start+end)/2+1,end,left,right,d);
		}
	}

	private static long init(long[] segTree, int[] input,int nodenum, int start, int end) {
		if(start==end) {
			return segTree[nodenum]=input[start];
		}
		int mid=(start+end)>>1;
		return segTree[nodenum]=init(segTree,input,nodenum*2,start,mid)+init(segTree,input,nodenum*2+1,mid+1,end);
	}
}
