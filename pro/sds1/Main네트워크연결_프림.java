package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main네트워크연결_프림{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		
		
		ArrayList<ArrayList<int[]>> list=new ArrayList<ArrayList<int[]>>();
		for(int i=0;i<n;i++) {
			list.add(new ArrayList<int[]>());
		}
		ArrayList<Integer> link=new ArrayList<Integer>();
		boolean[] check=new boolean[n];
		int[] min=new int[] {0,0,Integer.MAX_VALUE};
		for(int i=0;i<m;i++) {
			String[] s=br.readLine().split(" ");
			int a=Integer.parseInt(s[0]);
			int b=Integer.parseInt(s[1]);
			int c=Integer.parseInt(s[2]);
			
			list.get(a-1).add(new int[] {b-1,c});
			list.get(b-1).add(new int[] {a-1,c});
			if(min[2]>c) {
				min[0]=a-1;
				min[1]=b-1;
				min[2]=c;
			}
		}
		link.add(min[0]);
		link.add(min[1]);
		check[min[0]]=true;
		check[min[1]]=true;
		int answer=min[2];
		while(link.size()!=n) {
			min[2]=Integer.MAX_VALUE;
			for(int i=0;i<link.size();i++) {
				for(int j=0;j<list.get(link.get(i)).size();j++) {
					if(check[list.get(link.get(i)).get(j)[0]]) continue;
					if(min[2]>list.get(link.get(i)).get(j)[1]) {
						min[1]=list.get(link.get(i)).get(j)[0];
						min[2]=list.get(link.get(i)).get(j)[1];
					}
				}
			}
			link.add(min[1]);
			check[min[1]]=true;
			answer+=min[2];
		}
		System.out.println(answer);
	}
}
