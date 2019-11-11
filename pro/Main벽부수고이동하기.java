import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main벽부수고이동하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		int ylength=Integer.parseInt(s[0]);
		int xlength=Integer.parseInt(s[1]);
		
		int[] dy= {-1,1,0,0};
		int[] dx= {0,0,-1,1};
		
		char[][] map=new char[ylength][xlength];
		boolean[][][] check=new boolean[ylength][xlength][2];
		for(int i=0;i<ylength;i++) {
			map[i]=br.readLine().toCharArray();
		}
//		for(char[] ia:map) {
//			System.out.println(Arrays.toString(ia));
//		}
		int answer=-1;
		Queue<int[]> q=new LinkedList<int[]>();
		q.offer(new int[] {0,0,1,1});  //0,0부터 시작 {y,x,벽뚫을수있는 개수,거리}
		check[0][0][0]=true; //세번째는 
		while(!q.isEmpty()) {
			int[] tmp=q.poll();
			if(tmp[0]==ylength-1 && tmp[1]==xlength-1) {
				answer=tmp[3];
				break;
			}
			for(int d=0;d<4;d++) {
				int y=tmp[0]+dy[d];
				int x=tmp[1]+dx[d];
				if(y>=0 && y<ylength && x>=0 && x<xlength && check[y][x][tmp[2]]==false) {
					if(map[y][x]=='1') {
						if(tmp[2]==0) {
							continue;
						}else {
							check[y][x][tmp[2]]=true;
							q.offer(new int[] {y,x,tmp[2]-1,tmp[3]+1});
						}
					}else {
						check[y][x][tmp[2]]=true;
						q.offer(new int[] {y,x,tmp[2],tmp[3]+1});
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}
