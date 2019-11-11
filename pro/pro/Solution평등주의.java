package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution평등주의 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			String[] s=br.readLine().split(" ");
			
			int n=Integer.parseInt(s[0]);
			int k=Integer.parseInt(s[1]);
			int[] input=new int[n];
			int[] tinput=new int[n];
			s=br.readLine().split(" ");
			for(int i=0;i<n;i++) {
				input[i]=Integer.parseInt(s[i]);
			}
			int start=1;
			int end=1000000000;
			int answer=0;
			while(start<end) {
				int mid=(start+end)>>1;
				int tk=k;
				boolean fail=false;
				copy(input,tinput);
				for(int i=0;i<n-1;i++) {
					int val=tinput[i+1]-tinput[i]-mid;
					if(val>0) {
						tk-=val;
						tinput[i+1]-=val;
					}
					if(tk<0) {
						fail=true;
						break;
					}
				}
				for(int i=n-1;i>0;i--) {
					int val=tinput[i-1]-tinput[i]-mid;
					if(val>0) {
						tk-=val;
						tinput[i-1]-=val;
					}
					if(tk<0) {
						fail=true;
						break;
					}
				}
				
				if(fail) {
					start=mid+1;
				}else {
					answer=mid;
					end=mid;
				}
				
			}
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static void copy(int[] input, int[] tinput) {
		for(int i=0;i<input.length;i++) {
			tinput[i]=input[i];
		}
	}

}
