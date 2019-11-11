import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class hashtable3{
//	
	char[] dna;
	hashtable3(char[] input){
		dna=input;
	}
	boolean check(char[] marker) {
		long index=dna[0]-'A';
		long markerindex=marker[0]-'A';
		long p=1;
		for(int i=1;i<marker.length;i++) {
			index=index*27;
			index%=1000000007;
			
			markerindex*=27;
			markerindex%=1000000007;
			
			index+=dna[i]-'A';
			markerindex+=marker[i]-'A';
			p*=27;
			p%=1000000007;
		}
		if(index==markerindex) {
			
				return true;
			
		}
		for(int i=1;i<dna.length-marker.length;i++) {
			index-=(dna[i-1]-'A')*p;
			index%=1000000007;
			if(index<0) {
				index=(index+1000000007)%1000000007;
			}
			index*=27;
			index+=dna[i]-'A';
			index%=1000000007;
			if(index==markerindex) {
				
					return true;
				
			}
		}
		return false;
	}
	
//	void add(String dn)
}

public class Maintest {
	public static int answer;
	public static hashtable3 table;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		for(int tc=0;tc<n;tc++) {
			String[] s=br.readLine().split(" ");
			int dna_length=Integer.parseInt(s[0]);
			int marker_length=Integer.parseInt(s[1]);
			answer=0;
			char[] dna=br.readLine().toCharArray();
			table=new hashtable3(dna);
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
