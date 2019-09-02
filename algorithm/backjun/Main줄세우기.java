import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;

public class Main줄세우기{
	public static int n;
	public static int m;
	public static int[] check;
	public static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		n=Integer.parseInt(s[0]);
		m=Integer.parseInt(s[1]);
		
		check=new int[n+1];
		
		graph=new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<n+1;i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<m;i++) {
			s=br.readLine().split(" ");
			int a=Integer.parseInt(s[0]);
			int b=Integer.parseInt(s[1]);
			graph.get(a).add(b);
			check[b]++;
		}
		
		topologicalSort();
		
		
		
	}
	private static void topologicalSort() {
		Queue<Integer> queue=new LinkedList<>();
		
		for(int i=1;i<=n;i++) {
			if(check[i]==0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int tmp=queue.poll();
			System.out.print(tmp+" ");
			
			for(int v2: graph.get(tmp)) {
				check[v2]--;
				
				if(check[v2]==0) {
					queue.offer(v2);
				}
			}
		}
	}
}
