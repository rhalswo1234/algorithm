import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main낚시왕 {
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,1,-1};
	public static ArrayList<int[]> list;
	public static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		String[] input=br.readLine().split(" ");
		int R=Integer.parseInt(input[0]);
		int C=Integer.parseInt(input[1]);
		int M=Integer.parseInt(input[2]);
		
		
		list=new ArrayList();
		answer=0;
		for(int i=0;i<M;i++) {
			input=br.readLine().split(" ");
			int r=Integer.parseInt(input[0])-1;
			int c=Integer.parseInt(input[1])-1;
			int s=Integer.parseInt(input[2]);
			int d=Integer.parseInt(input[3]);
			int z=Integer.parseInt(input[4]);
			list.add(new int[] {r,c,s,d,z});
		}
//		for(int k=0;k<list.size();k++) {
//			System.out.println(Arrays.toString(list.get(k)));
//		}
		for(int i=0;i<C;i++) {
			//낚시왕이 상어하나 잡고
			int catchidx=-1;
			for(int j=0;j<list.size();j++) {
				if(list.get(j)[1]==i) {
					if(catchidx==-1) {
						catchidx=j;
					}else {
						if(list.get(j)[0]<list.get(catchidx)[0]) {
							catchidx=j;
						}
					}
				}
			}
			if(catchidx!=-1) {
				int[] tmp=list.remove(catchidx);
				System.out.println("잡힌거"+Arrays.toString(tmp));
//				System.out.println("잡은크기"+tmp[4]);
				answer+=tmp[4];
			}
			//상어 이동한다.
			for(int j=0;j<list.size();j++) {
				int[] tmp=list.get(j);
				if(tmp[3]==1 || tmp[3]==2) {
					tmp[2]%=2*(R-1);
				}else {
					tmp[2]%=2*(C-1);
				}
				for(int k=0;k<tmp[2];k++) {
					switch(tmp[3]) {
					case 1:
						if(tmp[0]==0) {
							tmp[3]=2;
							tmp[0]=1;
						}else {
							tmp[0]+=dy[0];
							if(tmp[0]==0) {
								tmp[3]=2;
							}
						}
						break;
					case 2:
						if(tmp[0]==R-1) {
							tmp[3]=1;
							tmp[0]=R-2;
						}else {
							tmp[0]+=dy[1];
							if(tmp[0]==R-1) {
								tmp[3]=1;
							}
						}
						break;
					case 3:
						if(tmp[1]==C-1) {
							tmp[3]=4;
							tmp[1]=C-2;
						}else {
							tmp[1]+=dx[2];
							if(tmp[1]==C-1) {
								tmp[3]=4;
							}
						}
						break;
					case 4:
						if(tmp[1]==0) {
							tmp[3]=3;
							tmp[1]=1;
						}else {
							tmp[1]+=dx[3];
							if(tmp[1]==0) {
								tmp[3]=3;
							}
						}
						break;
					}
				}
				list.get(j)[0]=tmp[0];
				list.get(j)[1]=tmp[1];
				list.get(j)[3]=tmp[3];
				
			}
			
			int[][][] map=new int[R][C][3];
			for(int k=0;k<list.size();k++) {
				int r=list.get(k)[0];
				int c=list.get(k)[1];
				
				if(map[r][c][0]==0) {
					map[r][c][0]=list.get(k)[4];
					map[r][c][1]=list.get(k)[3];
					map[r][c][2]=list.get(k)[2];
				}else {
					if(map[r][c][0]<list.get(k)[4]) {
						map[r][c][0]=list.get(k)[4];
						map[r][c][1]=list.get(k)[3];
						map[r][c][2]=list.get(k)[2];
					}
				}
			}
			list.clear();
			for(int k=0;k<R;k++) {
				for(int l=0;l<C;l++) {
					if(map[k][l][0]!=0) {
						list.add(new int[] {k,l,map[k][l][2],map[k][l][1],map[k][l][0]});
					}
				}
			}
//			}
//			System.out.println();
			
		}
		System.out.println(answer);
		
	}
}
