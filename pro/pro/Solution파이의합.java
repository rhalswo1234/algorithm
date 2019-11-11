package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution파이의합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		boolean[] check=new boolean[1000001];
		int[] count=new int[1000001];
		count[1]=1;
		for(int i=1;i<1000001;i++) {
			count[i]=i;
		}
		for(int i=2;i<1000001;i++) {
			if(!check[i]) {
				count[i]--;
				for(int j=i*2;j<1000001;j+=i) {
					check[j]=true;
					count[j]=(int) (count[j]*(1.0-1.0/i));
				}
			}
		}
		for(int tc=1;tc<=T;tc++) {
			String[] s=br.readLine().split(" ");
			int a=Integer.parseInt(s[0]);
			int b=Integer.parseInt(s[1]);
			long answer=0;
			for(int i=a;i<=b;i++) {
				answer+=count[i];
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
}
