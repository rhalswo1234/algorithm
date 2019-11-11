package datastructuer;

class heapex{
	int[] list;
	int size;
	heapex(int n){
		list=new int[n];
		size=0;
	}
	
	void push(int val) {
		list[++size]=val;
		
		int now=size;
		
		while(now!=1) {
			if(list[now/2]>list[now]) {
				swap(now,now/2);
				now/=2;
			}else {
				break;
			}
		}
	}
	int pop() {
		int result=size>0 ? list[1] : 0;
		
		
		if(size>0) {
			list[1]=list[size--];
			int now=1;
			while(true) {
				int left=now*2;
				int right=left+1;
				boolean lcheck= left<=size && list[left]<list[now];
				boolean rcheck= right<=size && list[right]<list[now];
				
				if(lcheck && rcheck) {
					if(list[left]<=list[right]) {
						swap(now,left);
						now=left;
					}else {
						swap(now,right);
						now=right;
					}
				}else if(lcheck){
					swap(now,left);
					now=left;
				}
				else if(rcheck) {
					swap(now,right);
					now=right;
				}else {
					break;
				}
			}
		}
		return result;
	}

	void swap(int now, int i) {
		int tmp=list[now];
		list[now]=list[i];
		list[i]=tmp;
	}
}

public class heap {
	public static void main(String[] args) {
		heapex hp=new heapex(10);
		hp.push(10);
		hp.push(9);
		hp.push(8);
		hp.push(7);
		hp.push(6);
		hp.push(5);
		hp.push(4);
		System.out.println(hp.pop());
		System.out.println(hp.pop());
		System.out.println(hp.pop());
		System.out.println(hp.pop());
		System.out.println(hp.pop());
		System.out.println(hp.pop());
		System.out.println(hp.pop());
		System.out.println(hp.pop());
		System.out.println(hp.pop());
	}
}
