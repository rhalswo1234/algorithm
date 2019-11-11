package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class xy{
	int x;
	int y;
}

public class Solution_closet {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			xy[] list=new xy[n];
			xy[] sort=new xy[n];
			for(int i=0;i<n;i++) {
				String[] s=br.readLine().split(" ");
				list[i]=new xy();
				sort[i]=new xy();
				list[i].x=Integer.parseInt(s[0]);
				list[i].y=Integer.parseInt(s[1]);
			}
			mergeSort(list,sort,0,n-1);
			
//			for(int i=0;i<n;i++) {
//				System.out.println(list[i].x+" "+list[i].y);
//			}
			
			long answer=solve(list,0,n-1);
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static void mergeSort(xy[] list, xy[] sort, int start, int end) {
		if(start>=end) return;
		int mid=(start+end)>>1;
		mergeSort(list,sort,start,mid);
		mergeSort(list,sort,mid+1,end);
		merge(list,sort,start,mid,end);
	}

	private static void merge(xy[] list, xy[] sort, int start, int mid, int end) {
		int i=start;
		int j=mid+1;
		int k=start;
		
		while(i<=mid && j<=end) {
			if(list[i].x==list[j].x) {
				if(list[i].y<=list[j].y) {
					sort[k].x=list[i].x;
					sort[k++].y=list[i++].y;
				}else {
					sort[k].x=list[j].x;
					sort[k++].y=list[j++].y;
				}
			}else if(list[i].x<list[j].x) {
				sort[k].x=list[i].x;
				sort[k++].y=list[i++].y;
			}else {
				sort[k].x=list[j].x;
				sort[k++].y=list[j++].y;
			}
		}
		
		if(i<=mid) {
			for(int t=i;t<=mid;t++) {
				sort[k].x=list[t].x;
				sort[k++].y=list[t].y;
			}
		}else {
			for(int t=j;t<=end;t++) {
				sort[k].x=list[t].x;
				sort[k++].y=list[t].y;
			}
		}
		
		for(int t=start;t<=end;t++) {
			list[t].x=sort[t].x;
			list[t].y=sort[t].y;
		}
	}

	private static long solve(xy[] list, int start, int end) {
		if(start+1==end) {
			return calc(list,start,end);
		}
		int mid=(start+end)>>1;
		long left=solve(list,start,mid);
		long right=solve(list,mid+1,end);
//		System.out.println("left"+left +" start"+start+" end"+end);
//		System.out.println("right"+right);
		long d=left<right ? left : right;
		System.out.println("d"+d);
		int l=0;
		boolean lcheck;
		for(int i=mid-1;i>=start;i--) {
			if(calc(list,mid,i)<d) {
				l=i;
				lcheck=true;
			}
		}
		int r=1;
		boolean rcheck;
		for(int i=mid+1;i<=end;i++) {
			if(calc(list,i,mid)<d) {
				r=i;
				rcheck=true;
			}
		}
		System.out.println(l+" "+r);
		long middle=solve(list,l,r);
		if(d>middle) {
			d=middle;
		}
		
		return d;
	}

	private static long calc(xy[] list,int start, int end) {
		// TODO Auto-generated method stub
		return (list[start].x-list[end].x)*(list[start].x-list[end].x)+(list[start].y-list[end].y)*(list[start].y-list[end].y);
	}
}
