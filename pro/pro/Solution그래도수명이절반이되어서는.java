package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution그래도수명이절반이되어서는 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			String[] s=br.readLine().split(" ");
			
			int n=Integer.parseInt(s[0]);
			int k=Integer.parseInt(s[1]);
			
			int[] memory=new int[n];
			int[] blocks=new int[k];
			s=br.readLine().split(" ");
			for(int i=0;i<n;i++) {
				memory[i]=Integer.parseInt(s[i]);
			}
			s=br.readLine().split(" ");
			for(int i=0;i<k;i++) {
				blocks[i]=Integer.parseInt(s[i]);
			}
			
			int start=1;
			int end=200000;
			int answer=0;
			while(start<end) {
				int mid=(start+end)>>1;
				boolean check=false;
				int blockindex=0;
				loop:for(int i=0;i<n;i++) {
					boolean blockcheck=true;
					for(int j=0;j<blocks[blockindex];j++) {
						if(i+j>=n) {
							blockcheck=false;
							break loop;
						}else {
							if(memory[i+j]>mid) {
								blockcheck=false;
								break;
							}
						}
					}
					if(blockcheck) {
						i+=blocks[blockindex++]-1;
					}
					if(blockindex==k) {
						check=true;
						break;
					}
					
				}
				if(check) {
					end=mid;
					answer=mid;
				}else {
					start=mid+1;
				}
			}
			
			System.out.println("#"+tc+" "+answer);
			
		}
	}
}
