package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main나무자르기2 {
	public static int n;
	public static int m;
	public static int[] namus;
	public static long max;
	public static long low,high,cut,ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		n=Integer.parseInt(s[0]);
		m=Integer.parseInt(s[1]);
		namus=new int[n];
		low=0;
		high=0;
		s=br.readLine().split(" ");
		max=0;
		for(int i=0;i<n;i++) {
			namus[i]=Integer.parseInt(s[i]);
			if(namus[i]>high) {
				high=namus[i];
			}
		}
		//이진탐색느낌으로 써보자
//		solve(0,max);
		cut=(low+high)/2;
		check(cut);
		System.out.println(ans);
		
 	}

	private static void check(long height) {
		System.out.println(height);
		if(low==cut||cut==high) {
			ans=cut;
			return;
		}
		long sum=0;
		for(int i=0;i<n;i++) {
			sum=namus[i]>height?(sum+(namus[i]-height)):sum;
		}
		if(sum>m) {
			low=cut;
			cut=(low+high)/2;
			check(cut);
		}else if(sum==m) {
			ans=cut;
			return;
		}else if(sum<m) {
//			ans=cut;
			high=cut;
			cut=(low+high)/2;
			check(cut);
		}
	}

	private static void solve(long start, long end) {
		if(start>end) {
			return;
		}
		long mid=(start+end)/2;
//		System.out.println(max);
		long sum=0;
		for(int i=0;i<n;i++) {
			if(namus[i]>mid) {
				sum+=namus[i]-mid;
			}
		}
		if(sum<m) { //부족하다 더 높이 낮추자
			solve(start,mid-1);
		}else { //충분하지만 최대한 높아야하기때문에 높여도 되는지 확인해보자
			max=mid;
			solve(mid+1,end);
		}
	}
}
