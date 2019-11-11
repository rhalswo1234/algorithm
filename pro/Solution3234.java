import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution3234 {
	
	public static int N;
	
	public static int[] chus;
	
	public static int answer;
	
	public static boolean[] check;
	
	public static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("rs/input3234.txt"));
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			N=Integer.parseInt(br.readLine());
			
			chus=new int[N];
			max=0;
			String[] s=br.readLine().split(" ");
			
			for(int i=0;i<N;i++) {
				chus[i]=Integer.parseInt(s[i]);
				max+=chus[i];
			}
			answer=0;
			max=(max+1)/2;
			check=new boolean[N];
			solve(0,0,0);
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static void solve(int count,int left,int right) {
		
		
		if(left>=max) {
			int sum=1;
			for(int i=1;i<=N-count;i++) {
				sum*=2;
				sum*=i;
			}
			answer+=sum;
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(check[i]) continue;
			check[i]=true;
			if(right+chus[i]<=left) {
				solve(count+1,left,right+chus[i]);
			}
			solve(count+1,left+chus[i],right);
			check[i]=false;
		}
	}
}
