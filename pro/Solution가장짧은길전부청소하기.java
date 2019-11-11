package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class heap{
	int[] start;
	int[] end;
	int[] length;
	int size;
	heap(){
		start=new int[1];
		end=new int[1];
		length=new int[1];
		size=0;
	}
	
	void push(int s,int e,int l) {
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
	
	int[] pop() {
		
		if(size==0) {
			System.out.println("힙비어있음");
			return null;
		}
		
		int[] result= new int[]{start[1], end[1], length[1]};
		
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
		int tmp1=start[p];
		int tmp2=end[p];
		int tmp3=length[p];
		start[p]=start[nodenum];
		end[p]=end[nodenum];
		length[p]=length[nodenum];
		
		start[nodenum]=tmp1;
		end[nodenum]=tmp2;
		length[nodenum]=tmp3;
		
	}

	private void sizeUp() {
		int nextsize=start.length<<1;
		int[] tmp1=new int[nextsize<<1];
		int[] tmp2=new int[nextsize<<1];
		int[] tmp3=new int[nextsize<<1];
		for(int i=0;i<size;i++) {
			tmp1[i]=start[i];
			tmp2[i]=end[i];
			tmp3[i]=length[i];
		}
		
		start=tmp1;
		end=tmp2;
		length=tmp3;
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
			int answer=0;
			int max=0;
			int[] lengths=new int[n];
			for(int i=1;i<n;i++) {
				lengths[i]=987654321;
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
				lists[start-1].add(end-1, length);
				lists[end-1].add(start-1, length);
			}
			heap hp=new heap();
			int count=1;
			for(int i=0;i<lists[0].size;i++) {
				lengths[lists[0].index[i]]=lists[0].length[i];
				hp.push(0, lists[0].index[i], lists[0].length[i]);
			}
			while(!hp.isEmpty()) {
				int[] tmp=hp.pop();
				if(check[tmp[1]]) {
					System.out.println("걸러짐");
					continue;
				}
				check[tmp[1]]=true;
				for(int i=0;i<lists[tmp[1]].size;i++) {
					if(lengths[lists[tmp[1]].index[i]] > lengths[tmp[1]]+lists[tmp[1]].length[i]) {
						lengths[lists[tmp[1]].index[i]]=lengths[tmp[1]]+lists[tmp[1]].length[i];
						System.out.println(lengths[lists[tmp[1]].index[1]]);
						hp.push(0, lists[tmp[1]].index[i], lengths[lists[tmp[1]].index[i]]);
					}
				}
				
			}
			System.out.println(Arrays.toString(lengths));
			for(int i=0;i<lists[0].size;i++) {
				hp.push(0, lists[0].index[i], lists[0].length[i]);
			}
			check=new boolean[n];
			check[0]=true;
			while(!hp.isEmpty()) {
				int[] tmp=hp.pop();
				if(check[tmp[1]]) {
					continue;
				}
				for(int i=0;i<lists[tmp[1]].size;i++) {
					
				}
			}
			
		}
	}
}
