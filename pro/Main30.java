import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main30 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String s=br.readLine();
		int sum=0;
		int[] nums=new int[10];
		int su=0;
		for(int i=0;i<s.length();i++) {
			su=s.charAt(i)-'0';
			sum+=su;
			nums[su]++;
		}
		StringBuilder answer=new StringBuilder();
		if(nums[0]!=0 && sum%3==0) {
			for(int i=9;i>=0;i--) {
				for(int j=0;j<nums[i];j++) {
					answer.append(i);
				}
			}
		}else {
			answer.append(-1);
		}
		System.out.println(answer);
	}
}
