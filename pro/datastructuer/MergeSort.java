package datastructuer;

import java.util.Arrays;

public class MergeSort {
	public static int[] sorted;
	public static void main(String[] args) {
		int[] input= {9,8,7,6,5,4,3,2,1};
		sorted=new int[input.length];
		merge(input,0, input.length-1);
		System.out.println(Arrays.toString(sorted));
	}

	private static void merge(int[] input, int start, int end) {
		if(start>=end) return;
		int mid=(start+end)>>1;
		System.out.println(mid);
		merge(input, start,mid);
		merge(input, mid+1,end);
		mergesort(input,start,mid,end);
		
		
	}

	private static void mergesort(int[] input, int start, int mid, int end) {
		int i=start;
		int j=mid+1;
		int k=start;
		while(i<=mid && j<=end) {
			if(input[i]<input[j]) {
				sorted[k]=input[i++];
			}else {
				sorted[k]=input[j++];
			}
			k++;
		}
		if(i<=mid) {
			for(int l=i;l<=mid;l++) {
				sorted[k++]=input[l];
			}
		}else {
			for(int l=j;l<=end;l++) {
				sorted[k++]=input[j];
			}
		}
		for(int t=start;t<=end;t++) {
			input[t]=sorted[t];
		}
	}
}
