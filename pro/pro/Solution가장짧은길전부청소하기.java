package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class heap{
	long[] start;
	long[] end;
	long[] length;
	int size;
	heap(){
		start=new long[1];
		end=new long[1];
		length=new long[1];
		size=0;
	}
	
	void push(long s,long e,long l) {
		if(size==start.length-1) {
			sizeUp();
		}
		
		start[++size]=s;
		end[size]=e;
		length[size]=l;
		
		int nodenum=size;
		
		while(nodenum>1) {
			int p=nodenum>>1;
			if(length[p]>length[nodenum]) {
				swap(p, nodenum);
				nodenum=p;
			}else {
				break;
			}
			
		}
	}
	
	long[] pop() {
		
		if(size==0) {
			System.out.println("힙비어있음");
		}
		
		long[] result= new long[]{start[1], end[1], length[1]};
		
		start[1]=start[size];
		end[1]=end[size];
		length[1]=length[size--];
		
		int nodenum=1;
		while(true) {
			int left=nodenum<<1;
			int right=left+1;
			boolean lcheck=left<=size && length[left]<length[nodenum];
			boolean rcheck=right<=size && length[right]<length[nodenum];
			
			if(lcheck && rcheck) {
				if(length[left]<length[right]) {
					swap(left,nodenum);
					nodenum=left;
				}else {
					swap(right,nodenum);
					nodenum=right;
				}
			}else if(lcheck) {
				swap(left,nodenum);
				nodenum=left;
			}else if(rcheck) {
				swap(right,nodenum);
				nodenum=right;
			}else {
				break;
			}
		}
		return result;
	}
	
	boolean isEmpty() {
		if(size==0) {
			return true;
		}else {
			return false;
		}
	}

	private void swap(int p, int nodenum) {
		long tmp1=start[p];
		long tmp2=end[p];
		long tmp3=length[p];
		start[p]=start[nodenum];
		end[p]=end[nodenum];
		length[p]=length[nodenum];
		
		start[nodenum]=tmp1;
		end[nodenum]=tmp2;
		length[nodenum]=tmp3;
		
	}

	private void sizeUp() {
		int nextsize=start.length<<1;
		long[] tmp1=new long[nextsize<<1];
		long[] tmp2=new long[nextsize<<1];
		long[] tmp3=new long[nextsize<<1];
		for(int i=0;i<size;i++) {
			tmp1[i]=start[i];
			tmp2[i]=end[i];
			tmp3[i]=length[i];
		}
		
		start=tmp1;
		end=tmp2;
		length=tmp3;
//		size=nextsize;
	}
}

class indexlist{
	int[] index;
	int[] length;
	int size;
	indexlist(){
		index=new int[1];
		length=new int[1];
		size=0;
	}
	
	void add(int idx, int d) {
		if(size==index.length) {
			sizeUp();
		}
		index[size]=idx;
		length[size++]=d;
	}
	void sizeUp() {
		int[] tmp=new int[size<<1];
		int[] tmp2=new int[size<<1];
		for(int i=0;i<size;i++) {
			tmp[i]=index[i];
			tmp2[i]=length[i];
		}
		index=tmp;
		length=tmp2;
//		size<<=1;
	}
}

public class Solution가장짧은길전부청소하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		//프림?
		for(int tc=1;tc<=T;tc++) {
			String[] s=br.readLine().split(" ");
			int n=Integer.parseInt(s[0]);
			int m=Integer.parseInt(s[1]);
			long answer=0;
			int max=0;
			long[] lengths=new long[n];
			for(int i=1;i<n;i++) {
				lengths[i]=2000000000;
			}
			boolean[] check=new boolean[n];
			indexlist[] lists=new indexlist[n];
			for(int i=0;i<n;i++) {
				lists[i]=new indexlist();
			}
			check[0]=true;
			for(int i=0;i<m;i++) {
				s=br.readLine().split(" ");
				int start=Integer.parseInt(s[0]);
				int end=Integer.parseInt(s[1]);
				int length=Integer.parseInt(s[2]);
//				System.out.println(length);
				lists[start-1].add(end-1, length);
				lists[end-1].add(start-1, length);
			}
			heap hp=new heap();
			int count=1;
			for(int i=0;i<lists[0].size;i++) {
//				System.out.println((lists[0].length[i]));
				lengths[lists[0].index[i]]=lists[0].length[i];
				hp.push(0, lists[0].index[i], lists[0].length[i]);
			}
			while(!hp.isEmpty()) {
				long[] tmp=hp.pop();
				int tmp1=(int)tmp[1];
				if(check[tmp1]) {
					System.out.println("걸러짐");
					continue;
				}
				check[tmp1]=true;
				for(int i=0;i<lists[tmp1].size;i++) {
					if(lengths[lists[tmp1].index[i]] > lengths[tmp1]+lists[tmp1].length[i]) {
						lengths[lists[tmp1].index[i]]=lengths[tmp1]+lists[tmp1].length[i];
						System.out.println(lengths[lists[tmp1].index[i]]);
						hp.push(0, lists[tmp1].index[i], lengths[lists[tmp1].index[i]]);
					}
				}
				
			}
			System.out.println(Arrays.toString(lengths));
//			System.out.println(Arrays.toString(lengths));
			for (int i=1;i<n;i++) {
				long min = 1000000000;
				for (int j=0;j<lists[i].size;j++) {
					int k = lists[i].index[j];
					int c = lists[i].length[j];
					if (lengths[k] + (long)c == lengths[i]) {
						min=Math.min(min, (long)c);
					}
				}
				answer+=(long)min;
			}
			System.out.println("#"+tc+" "+answer);
			
		}
	}
}
