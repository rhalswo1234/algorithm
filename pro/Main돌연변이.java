
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class hashtable{
	class list{
		String[] values;
		boolean[] check;
		int size;
		list(){
			values=new String[1];
			check=new boolean[1];
			size=0;
		}
		void add(String dna) {
			if(size==values.length) {
				sizeUp(size*2);
			}
			values[size++]=dna;
		}
		void sizeUp(int newsize) {
			String[] tmp=new String[newsize];
			boolean[] tmpcheck=new boolean[newsize];
			for(int i=0;i<size;i++) {
				tmp[i]=values[i];
				tmpcheck[i]=check[i];
			}
			values=tmp;
			check=tmpcheck;
		}
	}
//	
	list[] ht;
	String dna;
	hashtable(String input){
		ht=new list[1000007];
		dna=input;
	}
	boolean check(char[] marker) {
		int index=dna.charAt(0)-'A';
		int markerindex=marker[0]-'A';
		int p=1;
		for(int i=1;i<marker.length;i++) {
			index=index*27;
			index%=1000007;
			
			markerindex*=27;
			markerindex%=1000007;
			
			index+=dna.charAt(i)-'A';
			markerindex+=marker[i]-'A';
			p*=27;
			p%=1000007;
		}
		if(index==markerindex) {
			if(realSame(0,marker)) {
				return true;
			}
		}
		for(int i=1;i<dna.length()-marker.length;i++) {
			index-=(dna.charAt(i-1)-'A')*p;
			index%=1000007;
			if(index<0) {
				index=(index+1000007)%1000007;
			}
			index*=27;
			index+=dna.charAt(i)-'A';
			index%=1000007;
			if(index==markerindex) {
				if(realSame(i,marker)) {
					return true;
				}
			}
		}
		return false;
	}
	
	boolean realSame(int index,char[] marker) {
		for(int i=0;i<marker.length;i++) {
			if(dna.charAt(i)!=marker[i]) {
				return false;
			}
		}
		return true;
	}
//	void add(String dn)
}

public class Main {
	public static int answer;
	public static hashtable table;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		for(int tc=0;tc<n;tc++) {
			String[] s=br.readLine().split(" ");
			int dna_length=Integer.parseInt(s[0]);
			int marker_length=Integer.parseInt(s[1]);
			answer=0;
			String dna=br.readLine();
			table=new hashtable(dna);
			char[] marker=br.readLine().toCharArray();
			if(table.check(marker)) {
				answer++;
			}
			for(int i=2;i<=marker.length;i++) {
				char[] change=new char[i];
				for(int j=0;j<=marker.length-i;j++) {
					for(int k=0;k<i;k++) {
						change[k]=marker[j+k];
					}
					for(int k=0;k<i;k++) {
						marker[j+i-k-1]=change[k];
					}
//					System.out.println(Arrays.toString(marker));
					if(table.check(marker)) {
						answer++;
					}
					for(int k=0;k<i;k++) {
						marker[j+k]=change[k];
					}
//					System.out.println(Arrays.toString(marker));
				}
			}
			System.out.println(answer);
		}
	}
}
