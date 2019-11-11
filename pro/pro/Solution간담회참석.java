package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class loadlist{
	int[] nums;
	int[] lengths;
	int length;
	int size;
	loadlist(){
		nums=new int[1];
		lengths=new int[1];
		length=1;
		size=0;
	}
	
	void add(int num,int d) {
		if(size==length) {
			sizeUp();
		}
		nums[size]=num;
		lengths[size++]=d;
	}

	private void sizeUp() {
		int[] tmp1=new int[size*2];
		int[] tmp2=new int[size*2];
		for(int i=0;i<size;i++) {
			tmp1[i]=nums[i];
			tmp2[i]=lengths[i];
		}
		length*=2;
		nums=tmp1;
		lengths=tmp2;
	}
}

class loadheap{
	int[] nums;
	int[] lengths;
	int length;
	int size;
	loadheap(int n){
		nums=new int[n+1];
		lengths=new int[n+1];
		length=n+1;
		size=0; 
	}
	
	void push(int num,int d) {
		if(size+1==length) {
			sizeUp();
		}
		nums[++size]=num;
		lengths[size]=d;
		
		int now=size;
		
		while(now!=1) {
			int parent=now>>1;
			if(lengths[parent]>lengths[now]) {
				swap(now,parent);
				now=parent;
			}else {
				break;
			}
		}
	}
	int[] pop() {
		int[] result= size>0 ? new int[] {nums[1],lengths[1]} : null;
		
		if(size>0) {
			
			nums[1]=nums[size];
			lengths[1]=lengths[size--];
			
			int now=1;
			
			while(true) {
				int left=now<<1;
				int right=left+1;
				
				boolean leftcheck= left<size && lengths[now]>lengths[left];
				boolean rightcheck= right<size && lengths[now]>lengths[right];
				
				if(leftcheck && rightcheck) {
					if(lengths[left]<lengths[right]) {
						swap(left,now);
						now=left;
					}else {
						swap(right,now);
						now=right;
					}
				}else if(leftcheck) {
					swap(left,now);
					now=left;
				}else if(rightcheck){
					swap(right,now);
					now=right;
				}else {
					break;
				}
			}
		}
		
		
		return result;
	}
	
	boolean isEmpty() {
		if(size==0) return true;
		return false;
	}

	private void swap(int now, int parent) {
		int tmp=nums[now];
		nums[now]=nums[parent];
		nums[parent]=tmp;
		
		tmp=lengths[now];
		lengths[now]=lengths[parent];
		lengths[parent]=tmp;
	}

	private void sizeUp() {
		int newsize=length<<1;
		int[] tmp1=new int[newsize];
		int[] tmp2=new int[newsize];
		for(int i=0;i<length;i++) {
			tmp1[i]=nums[i];
			tmp2[i]=lengths[i];
		}
		length=newsize;
		nums=tmp1;
		lengths=tmp2;
	}
}

public class Solution간담회참석 {
	public static int max=1000000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			String[] ss=br.readLine().split(" ");
			
			int n=Integer.parseInt(ss[0]);
			int m=Integer.parseInt(ss[1]);
			int x=Integer.parseInt(ss[2]);
			loadlist[] lists1=new loadlist[n+1];
			loadlist[] lists2=new loadlist[n+1];
			for(int i=1;i<n+1;i++) {
				lists1[i]=new loadlist();
				lists2[i]=new loadlist();
			}
			
			int[] xtime=new int[n+1];
			int[] xtime2=new int[n+1];
			for(int i=1;i<n+1;i++) {
				if(i==x) continue;
				xtime[i]=max;
				xtime2[i]=max;
			}
			loadheap hp=new loadheap(m);
			
			for(int i=0;i<m;i++) {
				ss=br.readLine().split(" ");
				int s=Integer.parseInt(ss[0]);
				int e=Integer.parseInt(ss[1]);
				int t=Integer.parseInt(ss[2]);
				
//				hp.push(s, e, t);
//				hp2.push(e, s, t);
				
				lists1[s].add(e, t);
				lists2[e].add(s, t);
			}
			
//			System.out.println(Arrays.toString(xtime));
//			System.out.println(Arrays.toString(xtime2));
			for(int i=1;i<n+1;i++) {
				hp.push(i, xtime[i]);
			}
			boolean[] check=new boolean[n+1];
			while(!hp.isEmpty()) {
				int[] tmp=hp.pop();
				if(check[tmp[0]]) continue;
				check[tmp[0]]=true;
				
				for(int i=0;i<lists1[tmp[0]].size;i++) {
					if(xtime[lists1[tmp[0]].nums[i]]>xtime[tmp[0]]+lists1[tmp[0]].lengths[i]) {
						xtime[lists1[tmp[0]].nums[i]]=xtime[tmp[0]]+lists1[tmp[0]].lengths[i];
						hp.push(lists1[tmp[0]].nums[i], xtime[lists1[tmp[0]].nums[i]]);
					}
				}
			}
			for(int i=1;i<n+1;i++) {
				hp.push(i, xtime2[i]);
			}
			check=new boolean[n+1];
			
			while(!hp.isEmpty()) {
				int[] tmp=hp.pop();
				if(check[tmp[0]]) continue;
				check[tmp[0]]=true;
				
				for(int i=0;i<lists2[tmp[0]].size;i++) {
					if(xtime2[lists2[tmp[0]].nums[i]]>xtime2[tmp[0]]+lists2[tmp[0]].lengths[i]) {
						xtime2[lists2[tmp[0]].nums[i]]=xtime2[tmp[0]]+lists2[tmp[0]].lengths[i];
						hp.push(lists2[tmp[0]].nums[i], xtime2[lists2[tmp[0]].nums[i]]);
					}
				}
			}
			
			int answer=0;
//			System.out.println(Arrays.toString(xtime));
//			System.out.println(Arrays.toString(xtime2));
			for(int i=1;i<n+1;i++) {
				if(answer<xtime[i]+xtime2[i]) {
					answer=xtime[i]+xtime2[i];
				}
			}
			
			System.out.println("#"+tc+" "+answer);
		}
	}
}
