package pro;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class list{
	int[] strs;
	int size;
	list(){
		strs=new int[1];
		size=0;
	}
	
	void add(int str) {
		if(size==strs.length) {
			sizeUp();
		}
		strs[size++]=str;
	}

	void sizeUp() {
		int[] tmp=new int[size*2];
		for(int i=0;i<size;i++) {
			tmp[i]=strs[i];
		}
		strs=tmp;
	}
}


class hashtable{
	list[] lists;
	hashtable(){
		lists=new list[10007];
	}
}


public class Solution두번이상등장하는문자열 {
	public static String str;
	public static int l;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			l=Integer.parseInt(br.readLine());
			str=br.readLine();
			int start=1;
			int end=l;
			int answer=0;
			while(start<end) {
				int mid=(start+end)>>1;
				if(check(mid)) {
					answer=mid;
					start=mid+1;
				}else {
					end=mid;
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static boolean check(int mid) {
		hashtable ht=new hashtable();
		
		int index=str.charAt(0)-'a';
		int p=1;
		for(int i=1;i<mid;i++) {
			index*=29;
			index%=10007;
			index+=str.charAt(i)-'a';
			index%=10007;
			p*=29;
			p%=10007;
		}
		ht.lists[index]=new list();
		ht.lists[index].add(0);
		for(int i=mid;i<l;i++) {
			index-=(str.charAt(i-mid)-'a')*p;
			index%=10007;
			index*=29;
			index%=10007;
			if(index<0) {
				index+=10007;
				index%=10007;
			}
			index+=str.charAt(i)-'a';
			index%=10007;
			
			
			if(ht.lists[index]!=null) {
				for(int val:ht.lists[index].strs) {
					if(realsame(val,i-mid+1,mid)) {
						return true;
					}
				}
				ht.lists[index].add(i-mid+1);
			}else {
				ht.lists[index]=new list();
				ht.lists[index].add(i-mid+1);
			}
		}
		return false;
	}

	private static boolean realsame(int val, int val2,int length) {
		for(int i=0;i<length;i++) {
			if(str.charAt(val+i)!=str.charAt(val2+i)) {
				return false;
			}
		}
		return true;
	}
}
