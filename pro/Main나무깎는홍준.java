import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main나무깎는홍준 {
	public static int answer;
	public static int n,m;
	public static int[] tree= new int[1000000];
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		for(int tc=1;tc<=T;tc++) {
			answer=0;
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			int max=0;
			int min=1000000000;
			for(int i=0;i<n;i++) {
				tree[i]=Integer.parseInt(st.nextToken());
				max=tree[i]>max ? tree[i] : max;
				min=tree[i]<min ? tree[i] : min;
			}
			search(min-(m/n+1) >=0 ? min-(m/n+1):0,max-(m/n)+1);
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
	private static void search(int start, int end) {
		if(start>=end) return;
		int mid=(start+end)/2;
		long sum=0;
		for(int i=0;i<n;i++) {
			if(tree[i]>mid) {
				sum+=tree[i]-mid;
			}
			if(sum>=m) {
				if(answer<mid) {
					answer=mid;
				}
				search(mid+1,end);
				return;
			}
		}
		search(start,mid);
		return;
	}
}
