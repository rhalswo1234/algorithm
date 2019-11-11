package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_Inversion_Counting {
	public static long answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			int[] input = new int[n];
			String[] s=br.readLine().split(" ");
			for(int i=0;i<n;i++) {
				input[i]=Integer.parseInt(s[i]);
			}
			int[] sort= new int[n];
			answer=0;
			mergeSort(input,sort,0,n-1);
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static void mergeSort(int[] input, int[] sort, int start, int end) {
		if(start>=end) return;
		int mid=(start+end)>>1;
		mergeSort(input,sort,start,mid);
		mergeSort(input,sort,mid+1,end);
		merge(input,sort,start,mid,end);
	}

	private static void merge(int[] input, int[] sort,int start, int mid, int end) {
		int i=start;
		int j=mid+1;
		int k=start;
		
		while(i<=mid && j<=end) {
			if(input[i]<=input[j]) {
				sort[k++]=input[i++];
			}else {
				sort[k++]=input[j++];
				answer+=(mid-i+1);
			}
		}
		if(i<=mid) {
			for(int t=i;t<=mid;t++) {
				sort[k++]=input[t];
			}
		}else {
			for(int t=j;t<=end;t++) {
				sort[k++]=input[t];
			}
		}
		
		for(int t=start;t<=end;t++) {
			input[t]=sort[t];
		}
	}
}
