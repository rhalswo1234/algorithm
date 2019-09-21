package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class loadlist{
	int[] nums; // 도착하는 정점 번호
	int[] lengths; //가중치
	int length;
	int size;
	loadlist(){
		nums=new int[1];
		lengths=new int[1];
		length=1;
		size=0;
	}
	
	void add(int e,int d){
		if(size==length) {
			sizeUp();
		}
		nums[size]=e;
		lengths[size++]=d;
	}

	private void sizeUp() {
		int[] tmp=new int[size*2];
		int[] tmp2=new int[size*2];
		for(int i=0;i<size;i++) {
			tmp[i]=nums[i];
			tmp2[i]=lengths[i];
		}
		length*=2;
		nums=tmp;
		lengths=tmp2;
	}
}

class heap{
	int[] list;
	int[] dlist;
	int length;
	int size;
	heap(int n){
		list=new int[n];
		dlist=new int[n];
		length=n;
		size=0;
	}
	
	boolean isEmpty() {
		if(size==0) {
			return true;
		}
		return false;
	}
	
	void push(int val, int dval) {
		if(size+1==length) {
			sizeUp();
		}
		list[++size]=val;
		dlist[size]=dval;
		
		int now=size;
		
		while(now!=1) {
			if(dlist[now>>1]>dlist[now]) {
				swap(now,now>>1);
				now>>=1;
			}else {
				break;
			}
		}
	}
	
	private void sizeUp() {
		int[] tmp=new int[length*2];
		int[] tmp2=new int[length*2];
		for(int i=0;i<length;i++) {
			tmp[i]=list[i];
			tmp2[i]=dlist[i];
		}
		for(int i=length;i<length*2;i++) {
			tmp[i]=1;
			tmp2[i]=Integer.MAX_VALUE;
		}
		length*=2;
		list=tmp;
		dlist=tmp2;
	}

	int[] pop() {
		int[] result= size>0 ? new int[]{list[1],dlist[1]} : null;
		
		if(size>0) {
			
			list[1]=list[size];
			dlist[1]=dlist[size--];
			
			int now=1;
			
			while(true) {
				int left=now<<1;
				int right=left+1;
				
				boolean leftcheck= left<=size && dlist[left]<dlist[now];
				boolean rightcheck= right<=size && dlist[right]<dlist[now];
				
				if(leftcheck && rightcheck) {
					if(dlist[left]<=dlist[right]) {
						swap(left,now);
						now=left;
					}else {
						swap(right,now);
						now=right;
					}
				}else if(leftcheck) {
					swap(left,now);
					now=left;
				}else if(rightcheck) {
					swap(right,now);
					now=right;
				}else {
					break;
				}
			}
		}
		
		return result;
	}

	private void swap(int now, int i) {
		int tmp=list[now];
		list[now]=list[i];
		list[i]=tmp;
		tmp=dlist[now];
		dlist[now]=dlist[i];
		dlist[i]=tmp;
	}
	
}


public class Solution간담회참석 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			String[] str=br.readLine().split(" ");
			int n=Integer.parseInt(str[0]);
			int m=Integer.parseInt(str[1]);
			int x=Integer.parseInt(str[2]);
			
			loadlist[] loads=new loadlist[n+1];
			loadlist[] loads2=new loadlist[n+1];
			for(int i=1;i<n+1;i++) {
				loads[i]=new loadlist();
				loads2[i]=new loadlist();
			}
			int[] xtime=new int[n+1];
			int[] xtime2=new int[n+1];
			for(int i=1;i<n+1;i++) {
				xtime[i]=1000000000;
				xtime2[i]=1000000000;
			}
			xtime[x]=0;
			xtime2[x]=0;
			
			heap hp=new heap(100);
			
			for(int i=0;i<m;i++) {
				str=br.readLine().split(" ");
				int s=Integer.parseInt(str[0]);
				int e=Integer.parseInt(str[1]);
				int t=Integer.parseInt(str[2]);
				loads[s].add(e, t);
				loads2[e].add(s, t);
				
			}
			for(int i=1;i<n+1;i++) {
				hp.push(i, xtime[i]);
			}
			boolean[] check=new boolean[n+1];
			while(!hp.isEmpty()) {
				int[] tmp=hp.pop();
				if(check[tmp[0]]) continue;
				check[tmp[0]]=true;
				for(int j=0;j<loads[tmp[0]].size;j++) {
					if(xtime[loads[tmp[0]].nums[j]]>xtime[tmp[0]]+loads[tmp[0]].lengths[j]) {
						xtime[loads[tmp[0]].nums[j]]=xtime[tmp[0]]+loads[tmp[0]].lengths[j];
						hp.push(loads[tmp[0]].nums[j], xtime[loads[tmp[0]].nums[j]]);
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
				for(int j=0;j<loads2[tmp[0]].size;j++) {
					if(xtime2[loads2[tmp[0]].nums[j]]>xtime2[tmp[0]]+loads2[tmp[0]].lengths[j]) {
						xtime2[loads2[tmp[0]].nums[j]]=xtime2[tmp[0]]+loads2[tmp[0]].lengths[j];
						hp.push(loads2[tmp[0]].nums[j], xtime2[loads2[tmp[0]].nums[j]]);
					}
				}
			}
			int answer=0;
//			
			for(int i=1;i<n+1;i++) {
				if(answer<xtime[i]+xtime2[i]) {
					answer=xtime[i]+xtime2[i];
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
}
