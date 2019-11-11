package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Main네트워크연결_크루스칼{
	public static int[] p;
	public static int n;
	public static int m;
//	public static int[][] lines;
	public static int[][] temp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		n=Integer.parseInt(br.readLine());
		m=Integer.parseInt(br.readLine());
		
		p=new int[n];
		makeSet();
		int[][] lines=new int[m][3];
		temp=new int[m][3];
		
		for(int i=0;i<m;i++) {
			String[] s=br.readLine().split(" ");
			lines[i][0]=Integer.parseInt(s[0])-1;
			lines[i][1]=Integer.parseInt(s[1])-1;
			lines[i][2]=Integer.parseInt(s[2]);
		}
		MergeSort(lines,0,m-1);
//		QuickSort(0,m-1);
//		Arrays.sort(lines, new Comparator<int[]>() {
//
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				return o1[2]-o2[2];
//			}
//		});
//		for(int i=0;i<m;i++) {
//			System.out.println(Arrays.toString(lines[i]));
//		}
		int count=0;
		int answer=0;
		for(int i=0;i<m;i++) {
			int com1=lines[i][0];
			int com2=lines[i][1];
			if(findSet(com1)!=findSet(com2)) {
				uinonSet(com1,com2);
				count++;
				answer+=lines[i][2];
				if(count==n-1) {
					break;
				}
			}
		}
		System.out.println(answer);
	}
	private static void MergeSort(int[][] list,int start, int end) {
		if(start>=end) {
			return;
		}
		int mid=(start+end)/2;
		MergeSort(list,start,mid);
		MergeSort(list,mid+1,end);
		Merge(list,start,mid,end);
	}
	private static void Merge(int[][] list, int start, int mid, int end) {
		int i,j,k;
		i=start;
		k=start;
		j=mid+1;
		
		while(i<=mid && j<=end) {
			if(list[i][2]<=list[j][2]) {
				temp[k++]=list[i++];
			}else {
				temp[k++]=list[j++];
			}
		}
		if(i>mid) {
			for(int l=j;l<=end;l++) {
				temp[k++]=list[l];
			}
		}else {
			for(int l=i;l<=mid;l++) {
				temp[k++]=list[l];
			}
		}
		for(int l=start;l<=end;l++) {
			list[l]=temp[l];
		}
		
	}
	private static void uinonSet(int x, int y) {
		p[findSet(x)]=findSet(y);
	}
	private static int findSet(int x) {
		if(p[x]==x) {
			return x;
		}
		return p[x]=findSet(p[x]);
	}
//	private static void QuickSort(int start, int end) {
//		int pivot;
//		int i;
//		int j;
//		int[] tmp;
//		if(start<end) {
//			pivot=start;
//			i=start;
//			j=end;
//			while(i<j) {
//				while(lines[i][2]<=lines[pivot][2] && i<end) {
//					i++;
//				}
//				while(lines[j][2]>lines[pivot][2]) {
//					j--;
//				}
//				if(i<j) {
//					tmp=lines[i];
//					lines[i]=lines[j];
//					lines[j]=tmp;
//				}
//			}
//			tmp=lines[j];
//			lines[j]=lines[pivot];
//			lines[pivot]=tmp;
//			QuickSort(start,j-1);
//			QuickSort(j+1,end);
//		}
//	}
	private static void makeSet() {
		for(int i=0;i<n;i++) {
			p[i]=i;
		}
	}
}
