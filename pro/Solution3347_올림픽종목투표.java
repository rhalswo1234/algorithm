import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution3347_올림픽종목투표 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("rs/input3347.txt"));
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			String[] s=br.readLine().split(" ");
			
			int alength=Integer.parseInt(s[0]);
			int blength=Integer.parseInt(s[1]);
			
			int[] ia=new int[alength];
			int[] acount=new int[alength];
			int[] ib=new int[blength];
			
			s=br.readLine().split(" ");
			for(int i=0;i<alength;i++) {
				ia[i]=Integer.parseInt(s[i]);
			}
			s=br.readLine().split(" ");
			for(int i=0;i<blength;i++) {
				ib[i]=Integer.parseInt(s[i]);
			}
			
			for(int i=0;i<blength;i++) {
				for(int j=0;j<alength;j++) {
					if(ib[i]>=ia[j]) {
						acount[j]++;
						break;
					}
				}
			}
			int answer=0;
			for(int i=0;i<alength;i++) {
				if(acount[answer]<acount[i]) {
					answer=i;
				}
			}
			answer++;
			System.out.println("#"+tc+" "+answer);
			
			
//			System.out.println(Arrays.toString(ia));
//			System.out.println(Arrays.toString(ib));
			
			
		}
	}
}
