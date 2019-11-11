import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main달리기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb=new StringBuilder();

		int[] inputs = new int[n];
		int[] order= new int[n];
		int[] sortedinputs=new int[n];
		int[] sortedorder=new int[n];
		for (int i = 0; i < n; i++) {
			inputs[i] = Integer.parseInt(br.readLine());
			order[i]=i;
		}
		int height = 0;
		int sortedinputs1 = 1;
		while (sortedinputs1 < n) {
			sortedinputs1 <<= 1;
			height++;
		}
		int[] segTree = new int[n * height + 1];
		
		mergeSort(inputs,order,sortedinputs,sortedorder, 0, n - 1); // 0~10억 범위를 0~50만으로 줄이는 과정 1. 크기별로 소팅
		//각 input값들을 크기순으로 정렬하는대신 달리기 시작하는 위치값을 기억하기위해 sortedorder도 따라서 정렬해준다
		
		for(int i=0;i<n;i++) {
			sortedinputs[i]=i;
		} // 이 값들이 좌표압축된 값들
		mergeSort(sortedorder,sortedinputs,order,inputs,0,n-1); //다시 기존의 순서대로 돌린다.
		//inputs값들이 크기순이므로 0~50만기준으로 좌표압축을 해주고 다시 기존의 달리기 시작하는 순서대로 돌려주기위해 sortedorder를 이용해 재정렬해준다.
		//결과적으로 0~10억 범위의 inputs값들이 0~50만의 범위로 줄어들어있다.
//		System.out.println(Arrays.toString(sortedorder));
//		System.out.println(Arrays.toString(order));
//		System.out.println(Arrays.toString(sortedinputs));
		for(int i=0;i<n;i++) {
			sb.append((1+query(segTree,1,sortedinputs[i],0,n-1))).append("\n");
//			System.out.println(sortedinputs[sortedorder[i]]);
			update(segTree,1,sortedinputs[i],0,n-1);
		}
		System.out.println(sb);

	}

	private static int update(int[] segTree, int nodenum, int value, int start, int end) {
		if(value<start || value>end) return segTree[nodenum];
		if(start==end) return segTree[nodenum]=1;
		int mid=(start+end)/2;
		return segTree[nodenum]=update(segTree,nodenum*2,value,start,mid)+update(segTree,nodenum*2+1,value,mid+1,end);
	}

	private static int query(int[] segTree, int nodenum, int value, int start, int end) {
		if(value<start) return segTree[nodenum];
		if(value>end || start==end) return 0;
		
		int mid=(start+end)/2;
		return query(segTree,nodenum*2,value,start,mid)+query(segTree,nodenum*2+1,value,mid+1,end);
	}

	private static void mergeSort(int[] inputs,int[] order,int[] sortedinputs,int[] sortedorder, int start, int end) {
		if (start >= end)
			return;
		int mid = (start + end) / 2;
		mergeSort(inputs,order,sortedinputs,sortedorder, start, mid);
		mergeSort(inputs,order,sortedinputs,sortedorder, mid + 1, end);
		merge(inputs,order,sortedinputs,sortedorder, start, mid, end);
	}

	private static void merge(int[] inputs,int[] order,int[] sortedinputs,int[] sortedorder, int start, int mid, int end) {
		int i=start;
		int j=mid+1;
		int k=start;
		while(i<=mid && j<=end) {
			if(inputs[i]>inputs[j]) {
				sortedinputs[k]=inputs[j];
				sortedorder[k++]=order[j++];
			}else {
				sortedinputs[k]=inputs[i];
				sortedorder[k++]=order[i++];
			}
		}
		if(i<=mid) {
			for(int t=i;t<=mid;t++) {
				sortedinputs[k]=inputs[t];
				sortedorder[k++]=order[t];
			}
		}else {
			for(int t=j;t<=end;t++) {
				sortedinputs[k]=inputs[t];
				sortedorder[k++]=order[t];
			}
		}
		for(i=start;i<=end;i++) {
			inputs[i]=sortedinputs[i];
			order[i]=sortedorder[i];
		}
	}
}
