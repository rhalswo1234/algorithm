package sds1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main수들의합2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		int n=Integer.parseInt(s[0]);
		int m=Integer.parseInt(s[1]);
		int[] ia=new int[n];
		int[] sums=new int[n];
		int answer=0;
		s=br.readLine().split(" ");
		for(int i=0;i<ia.length;i++) {
			ia[i]=Integer.parseInt(s[i]);
			for(int j=i;j>=0;j--) {
				if(sums[j]>=m) continue;
				sums[j]+=ia[i];
				if(sums[j]==m) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
		
	}
}
