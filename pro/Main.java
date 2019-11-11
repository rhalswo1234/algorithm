import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static int n;
	public static int[][] players;
	
	public static int[] order;
	
	public static boolean[] check;
	
	public static int answer;
	
	public static int tajanum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		n=Integer.parseInt(br.readLine());
		players=new int[n][9];
		order=new int[9];
		check=new boolean[9];
		answer=0;
		for(int i=0;i<n;i++) {
			String[] s=br.readLine().split(" ");
			for(int j=0;j<9;j++) {
				players[i][j]=Integer.parseInt(s[j]);
			}
		}
		//5~9번 선수의 타순을 결정 1~4번은 이미 결정되어있음
		dfs(0);
		
		System.out.println(answer);
	}

	private static void dfs(int count) {
		if(count==3) {
			count++;
		}
		if(count==9) { //8명 정하면된다.
			System.out.println(Arrays.toString(order));
			tajanum=-1;
			game(0,0);
//			System.out.println(answer);
			return;
		}
		for(int i=1;i<9;i++) {
			if(check[i]) continue;
			check[i]=true;
			order[count]=i;
			dfs(count+1);
			check[i]=false;
		}
		
	}

	private static void game(int out,int score) {
		boolean[] zuja=new boolean[3];
		loop:for(int inning=0;inning<n;inning++) {
			while(true) {
				tajanum++;
//				System.out.println(i);
//				System.out.println(players[inning][order[i%9]]);
				switch(players[inning][order[tajanum%9]]) {
				case 0: //아웃
					out++;
					if(out==3) {
						out=0;
						zuja=new boolean[3];
						continue loop;
					}
					break;
				case 1: // 안타
					if(zuja[2]) {
						zuja[2]=false;
						score++;
					}
					for(int i=1;i>=0;i--) {
						if(zuja[i]) {
							zuja[i+1]=true;
							zuja[i]=false;
						}
					}
					zuja[0]=true;
					break;
				case 2: // 2루타
					for(int i=2;i>=1;i--) {
						if(zuja[i]) {
							score++;
							zuja[i]=false;
						}
					}
					if(zuja[0]) {
						zuja[2]=true;
						zuja[0]=false;
					}
					zuja[1]=true;
					break;
				case 3: // 3루타
					for(int i=2;i>=0;i--) {
						if(zuja[i]) {
							score++;
							zuja[i]=false;
						}
					}
					zuja[2]=true;
					break;
				case 4: // 홈런
					for(int i=2;i>=0;i--) {
						if(zuja[i]) {
							score++;
							zuja[i]=false;
						}
					}
					score++;
					break;
				}
			}
			
		}
		if(score>answer) {
			answer=score;
		}
	}
}
