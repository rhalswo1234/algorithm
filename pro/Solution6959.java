import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution6959 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("rs/input6959.txt"));
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			char[] ca=br.readLine().toCharArray();
			System.out.println(Arrays.toString(ca));
			boolean[] check=new boolean[ca.length-1];
			for(int i=0;i<ca.length;i++) {
//				solve(ca,0,i); //0Àº ¾Ù¸®½º 1Àº Åä³¢
			}
		}
	}
}
