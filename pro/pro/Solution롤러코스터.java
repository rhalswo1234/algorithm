package pro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class ab{
	int a;
	int b;
}

public class Solution롤러코스터 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			ab[] input=new ab[n];
			ab[] sort=new ab[n];
			for(int i=0;i<n;i++) {
				String[] s=br.readLine().split(" ");
				input[i]=new ab();
				sort[i]=new ab();
				input[i].a=Integer.parseInt(s[0]);
				input[i].b=Integer.parseInt(s[1]);
			}
			mergeSort(input,sort,0,n-1);
			long answer=1;
			for(int i=0;i<n;i++) {
//				System.out.println(input[i].a+" "+input[i].b);
				answer*=input[i].a;
				answer%=1000000007;
				answer+=input[i].b;
				answer%=1000000007;
			}
			
			System.out.println("#"+tc+" "+answer);
//			for(int i=0;i<n;i++) {
//				System.out.println(input[i].a+" "+input[i].b);
//			}
			
			
			
		}
	}

	private static void mergeSort(ab[] input, ab[] sort, int start, int end) {
		if(start>=end) return;
		int mid=(start+end)>>1;
		mergeSort(input,sort,start,mid);
		mergeSort(input,sort,mid+1,end);
		merge(input,sort,start,mid,end);
	}

	private static void merge(ab[] input, ab[] sort, int start, int mid, int end) {
		int i=start;
		int j=mid+1;
		int k=start;
		
		while(i<=mid && j<=end) {
			double tmpi=(double)(input[i].a-1)/(double)input[i].b;
			double tmpj=(double)(input[j].a-1)/(double)input[j].b;
			if(tmpi>=tmpj) {
				sort[k].a=input[i].a;
				sort[k++].b=input[i++].b;
			}else {
				sort[k].a=input[j].a;
				sort[k++].b=input[j++].b;
			}
		}
		
		if(i<=mid) {
			for(int t=i;t<=mid;t++) {
				sort[k].a=input[t].a;
				sort[k++].b=input[t].b;
			}
		}else {
			for(int t=j;t<=end;t++) {
				sort[k].a=input[t].a;
				sort[k++].b=input[t].b;
			}
		}
		
		for(int t=start;t<=end;t++) {
			input[t].a=sort[t].a;
			input[t].b=sort[t].b;
		}
	}
}
