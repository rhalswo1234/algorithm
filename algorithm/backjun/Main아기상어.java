import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Main아기상어 {
	public static int[][] map;
	public static int sy;
	public static int sx;
	public static int[] dy= {-1,0,0,1};//상좌우하
	public static int[] dx= {0,-1,1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		int sharksize=2;
		int sharkeat=0;
		int time=0;
		Queue<int[]> q=new LinkedList();
		for(int i=0;i<n;i++) {
			String[] s=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(s[j]);
				if(map[i][j]==9) {
					map[i][j]=0;
					sy=i;
					sx=j;
					q.add(new int[] {i,j,0});
				}
			}
		}
		boolean[][] check=new boolean[n][n];
//		check[sy][sx]=true;
		while(true) {
			boolean find=false;
			int[] eat=new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE,0};
			while(!q.isEmpty()) {
				int size=q.size();
				for(int i=0;i<size;i++) {
					int[] tmp=q.poll();
					for(int d=0;d<4;d++) {
						int yy=tmp[0]+dy[d];
						int xx=tmp[1]+dx[d];
						if(yy>=0 && yy<n && xx>=0 && xx<n && !check[yy][xx] && map[yy][xx]<=sharksize) {
							if(map[yy][xx]!=0 && map[yy][xx]<sharksize) {
								if(yy<eat[0]) {
									eat[0]=yy;
									eat[1]=xx;
									eat[2]=tmp[2]+1;
								}else if(yy==eat[0]) {
									if(xx<eat[1]) {
										eat[0]=yy;
										eat[1]=xx;
										eat[2]=tmp[2]+1;
									}
								}
								find=true;
							}else {
								check[yy][xx]=true;
								q.add(new int[] {yy,xx,tmp[2]+1});
							}
						}
					}
					
				}
				if(find) {
					sy=eat[0];
					sx=eat[1];
					map[sy][sx]=0;
					sharkeat++;
					if(sharkeat==sharksize) {
						sharkeat=0;
						sharksize++;
					}
					time+=eat[2];
					break;
				}
			}
			if(find) {
				q.clear();
//				System.out.println("사이즈"+q.size());
//				System.out.println(sy+" "+sx +" 상어크기, 상어 먹은수"+sharksize+" "+sharkeat);
				q.add(new int[] {sy,sx,0});
				check=new boolean[n][n];
//				check[sy][sx]=true;
			}else {
				break;
			}
		}
		System.out.println(time);
		
	}
}
