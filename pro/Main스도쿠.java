import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main½ºµµÄí {
	public static int[][] map;
	public static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		map=new int[9][9];
		ArrayList<int[]> list= new ArrayList<int[]>();
		for(int i=0;i<9;i++) {
			char[] ca=br.readLine().toCharArray();
			for(int j=0;j<ca.length;j+=2) {
				map[i][j/2]=ca[j]-'0';
				if(map[i][j/2]==0) {
					list.add(new int[] {i,j});
				}
			}
		}
		for(int[] ia:map) {
			System.out.println(Arrays.toString(ia));
		}
		for(int i=0;i<list.size();i++) {
			int[] tmp=list.get(i);
			check=new boolean[10];
			if(check(tmp[0],tmp[1])==1){
				
			}
		}
	}
	private static int check(int y, int x) {
		for(int i=0;i<9;i++) {
			check[map[i][x]]=true;
			check[map[y][i]]=true;
		}
		y=y/3;
		x=x/3;
		for(int i=y;i<y+3;i++) {
			for(int j=x;j<x+3;j++) {
				check[map[i][j]]=true;
			}
		}
		int cnt=0;
		for(boolean b:check) {
			if(!b) cnt++;
		}
		return cnt;
	}
}
