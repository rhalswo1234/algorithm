package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution아나그램 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			String[] s=br.readLine().split(" ");
			int[] s1check=new int[26];
			int answer=0;
			char[] s1=s[0].toCharArray();
			for(int i=0;i<s1.length;i++) {
				s1check[s1[i]-'a']++;
			}
//			System.out.println(Arrays.toString(countcheck));
			char[] s2=s[1].toCharArray();
			for(int i=0;i<=s2.length-s1.length;i++) {
				int[] s2check=new int[26];
				boolean check=true;
				for(int j=0;j<s1.length;j++) {
					s2check[s2[i+j]-'a']++;
				}
				for(int j=0;j<26;j++) {
					if(s1check[j]!=s2check[j]) {
						check=false;
						break;
					}
				}
				if(check) {
					answer++;
				}
			}
			System.out.println("#"+tc+" "+answer);
			
		}
	}
}
