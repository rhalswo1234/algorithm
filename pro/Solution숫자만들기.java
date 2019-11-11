import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution숫자만들기 {
	public static int n;
	public static int[] inputs;
	public static int max;
	public static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("rs/input4008.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			n=Integer.parseInt(br.readLine());
			int plus=0;// 사용가능한 + 개수
			int minus=0;// 사용가능한 - 개수
			int mul=0;// 사용가능한 * 개수
			int div=0;// 사용가능한 / 개수
			inputs=new int[n];
			max=Integer.MIN_VALUE;
			min=Integer.MAX_VALUE;
			String[] s=br.readLine().split(" ");
			plus=Integer.parseInt(s[0]);
			minus=Integer.parseInt(s[1]);
			mul=Integer.parseInt(s[2]);
			div=Integer.parseInt(s[3]);
			s=br.readLine().split(" ");
			for(int i=0;i<n;i++) {
				inputs[i]=Integer.parseInt(s[i]);
			}
//			System.out.println(Arrays.toString(inputs));
			int[] oa=new int[n-1]; // 연산자 배치 순서
			dfs(oa,plus,minus,mul,div,0);
			System.out.println("#"+tc+" "+(max-min));
		}
	}

	private static void dfs(int[] oa, int plus, int minus, int mul, int div, int cnt) {
		if(cnt==n-1) { //
//			System.out.println(Arrays.toString(oa));
			sum(oa);//완성된 연산자 배치로 계산 시작
			return;
		}
		if(plus!=0) { // + 사용가능하면
			oa[cnt]=0; // 0은 +
			dfs(oa,plus-1,minus,mul,div,cnt+1);
		}
		if(minus!=0) {// - 사용가능하면
			oa[cnt]=1; // 1은 -
			dfs(oa,plus,minus-1,mul,div,cnt+1);
		}
		if(mul!=0) {// * 사용가능하면
			oa[cnt]=2; // 2는 *
			dfs(oa,plus,minus,mul-1,div,cnt+1);
		}
		if(div!=0) {// / 사용가능하면
			oa[cnt]=3;// 3은 /
			dfs(oa,plus,minus,mul,div-1,cnt+1);
		}
	}

	private static void sum(int[] oa) { //계산
		int sum=inputs[0];
		for(int i=0;i<n-1;i++) {
			switch(oa[i]) {
			case 0: // +
				sum+=inputs[i+1];
				break;
			case 1: // -
				sum-=inputs[i+1];
				break;
			case 2: // *
				sum*=inputs[i+1];
				break;
			case 3: // /
				sum/=inputs[i+1];
				break;
			}
		}
//		System.out.println(sum);
		if(sum>max) { //최대값
			max=sum;
		}
		if(sum<min) { //최소값
			min=sum;
		}
	}

}
