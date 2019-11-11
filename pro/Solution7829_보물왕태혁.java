import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution7829_보물왕태혁 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			int[] list=new int[n];
			int[] sort=new int[n];
			String[] s=br.readLine().split(" ");
			for(int i=0;i<n;i++) {
				list[i]=Integer.parseInt(s[i]);
			}
			mergeSort(list,sort, 0, n-1);
//			Arrays.sort(list);
//			System.out.println(Arrays.toString(list));
			long answer=0;
			answer=(long)list[0]*(long)list[n-1];
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static void mergeSort(int[] list,int[] sort, int start, int end) {
		if(start>=end) return;
		int mid=(start+end)>>1;
		mergeSort(list,sort,start,mid);
		mergeSort(list,sort,mid+1,end);
		merge(list,sort,start,mid,end);
	}

	private static void merge(int[] list, int[] sort, int start, int mid, int end) {
		int i=start;
		int j=mid+1;
		int k=start;
		
		while(i<=mid && j<=end) {
			if(list[i]<list[j]) {
				sort[k++]=list[i++];
			}else {
				sort[k++]=list[j++];
			}
		}
		
		if(i<=mid) {
			for(int t=i;t<=mid;t++) {
				sort[k++]=list[t];
			}
		}else {
			for(int t=j;t<=end;t++) {
				sort[k++]=list[t];
			}
		}
		
		for(int t=start;t<=end;t++) {
			list[t]=sort[t];
		}
	}
}
