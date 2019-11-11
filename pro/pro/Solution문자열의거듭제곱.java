package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution문자열의거듭제곱 {
	
	public static int mod=1000000007;
	public static char[] input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			input=br.readLine().toCharArray();
			
			int start=1;
			int end=input.length;
			int answer=0;
			while(start<end) {
				int mid=(start+end)>>1;
				System.out.println(mid);
				if(ok(mid)) {
					answer=mid;
					end=mid-1;
				}else {
					
				}
			}
			System.out.println("#"+tc+" "+input.length/answer);
		}
	}

	private static boolean ok(int mid) {
		if(input.length%mid!=0) {
			return false;
		}
		long hash=input[0];
		for(int i=1;i<mid;i++) {
			hash*=127;
			hash%=mod;
			hash+=input[i];
			hash%=mod;
		}
		for(int i=mid;i<input.length-mid+1;i+=mid) {
			long hash2=input[i];
			for(int j=1;j<mid;j++) {
				hash2*=127;
				hash2%=mod;
				hash2+=input[i+j];
				hash2%=mod;
			}
			if(hash==hash2) {
				if(!realSame(i,mid)) {
					return false;
				}
			}else {
				return false;
			}
		}
		return true;
	}

	private static boolean realSame(int i,int mid) {
		for(int j=0;j<mid;j++) {
			if(input[j]!=input[i+j]) {
				return false;
			}
		}
		return true;
	}
}
