import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
//class pre{
//	int[] pres;
//	pre(){
//		pres=new int[100000];
//		pres[0]=1;
//		for(int i=1;i<100000;i++) {
//			pres[i]=pres[i-1]*29%10007;
//		}
//	}
//}

class list{
	int[] index;
	int size;
	list(){
		index=new int[1];
		size=0;
	}
	void addlist(int i) {
		if(index.length==size) {
			listSizeUp();
		}
		index[size++]=i;
	}
	void listSizeUp() {
		int[] tmp=new int[index.length*2];
		for(int i=0;i<index.length;i++) {
			tmp[i]=index[i];
		}
		index=tmp;
	}
}

class hashTable{
	list[] hash;
	hashTable(){
		hash=new list[10007];
	}
	void clear() {
		for(int i=0;i<10007;i++) {
			hash[i]=null;
		}
	}
}

public class Main반복부분문자열 {
	public static char[] ca;
	public static hashTable ht=new hashTable();
	public static int n;
//	public static pre pre=new pre();
//	public static pre pre=new pre();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		ca=br.readLine().toCharArray();
//		ht=new hashTable();
		int start=0;
		int end=n;
		ht.hash[0]=new list();
//		for(int i=0;i<10;i++) {
//			ht.hash[0].addlist(i);
//		}
//		System.out.println(Arrays.toString(ht.hash[0].index));
		
		while(start<end) {
			int mid=(start+1+end)>>1;
			if(ok(mid)) start=mid;
			else end=mid-1;
		}
		System.out.println(start);
	}

	private static boolean ok(int mid) {
		ht.clear();
		int index=ca[0]-'a';
		int pre=1;
		for(int i=1;i<mid;i++) {
			index*=29;
			index+=ca[i]-'a';
//			System.out.println(index);
			index%=10007;
			pre*=29;
			pre%=10007;
		}
		ht.hash[index]=new list();
		ht.hash[index].addlist(0);
//		System.out.println(mid+";; "+index);
		for(int i=mid;i<n;i++) {
//			System.out.println(index);
			index-=(ca[i-mid]-'a')*pre%10007;
//			System.out.println(index);
			if(index<0) {
				index=(index+10007)%10007;
			}
			index*=29;
//			System.out.println(index);
			index+=ca[i]-'a';
//			System.out.println(index);
			index%=10007;
//			System.out.println(mid+" "+index);
			if(ht.hash[index]!=null) {
//				System.out.println(Arrays.toString(ht.hash[index].index));
				for(int idx:ht.hash[index].index) {
					if(realmatch(idx,i-mid+1,mid)) {
						return true;
					}
				}
				ht.hash[index].addlist(i-mid+1);
			}else {
				ht.hash[index]=new list();
				ht.hash[index].addlist(i-mid+1);
			}
		}
		return false;
	}

	private static boolean realmatch(int idx, int idx2, int length) {
		for(int i=0;i<length;i++) {
			if(ca[idx+i]!=ca[idx2+i]) {
				return false;
			}
		}
		return true;
	}

}
