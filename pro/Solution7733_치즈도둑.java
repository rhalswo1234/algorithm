import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution7733_Ä¡ÁîµµµÏ {
   private static int[][] map;
   private static boolean[][] eat;

   public static int[] dx = {-1,1,0,0};
   public static int[] dy = {0,0,-1,1};
   private static int N;
   private static boolean[][] v;

   public static void dfs(int x, int y) {
       v[x][y] = true;
       for (int i = 0; i < 4; i++) {
    	   int xx=x+dx[i];
    	   int yy=y+dy[i];
           if(xx>=0 && xx<N && yy>=0 && yy<N &&!v[xx][yy])
               dfs(x+dx[i],y+dy[i]);
       }
   }

   public static void main(String[] args) throws Exception {
	   
	   System.setIn(new FileInputStream("rs/input7733.txt"));
	   
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int T = Integer.parseInt(br.readLine());

       for (int tc = 1; tc <= T; tc++) {
           N = Integer.parseInt(br.readLine());

           map = new int[N][N];
           eat = new boolean[N][N];
           v = new boolean[N][N];


           for (int i =0 ; i < N; i++) {
        	   String[] s=br.readLine().split(" ");
               for (int j =0; j < N; j++) {
                   map[i][j] = Integer.parseInt(s[j]);
               }
           }//input

           int result = 1;

           for (int date = 1; date <= 100; date++) {
               int cnt = 0;
               for (int i = 0; i < N; i++) {
                   for (int j = 0; j < N; j++) {
                       if(map[i][j] == date) {
                           eat[i][j] = true; // Ä¡ÁîÃÄ¸ÔÀ½
                       }
                       v[i][j]=eat[i][j];
                   }
               }

               for (int i = 0; i < N; i++) {
                   for (int j = 0; j < N; j++) {
                       if(!v[i][j]) {
                           cnt++;
                           dfs(i,j);
                       }
                   }
               }
               if(cnt==0) {
            	   break;
               }
               if(cnt>result)
                   result = cnt;
           }

           System.out.println("#"+tc+" "+result);

       }//tc
   }//main
}//class