import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class list{
	int[] table;
	
}
public class Main듣보잡 {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		int n=Integer.parseInt(s[0]);
		String[] first=new String[n];
		int m=Integer.parseInt(s[1]);
		String[] second=new String[m];
		
		for(int i=0;i<n;i++) {
			first[i]=br.readLine();
		}
		for(int i=0;i<m;i++) {
			second[i]=br.readLine();
		}
		
	}
}
