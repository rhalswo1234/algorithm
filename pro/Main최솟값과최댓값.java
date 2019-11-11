import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main최솟값과최댓값 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		int n=Integer.parseInt(s[0]);
		int m=Integer.parseInt(s[1]);
		
		int[] inputs=new int[n];
		for(int i=0;i<n;i++) {
			inputs[i]=Integer.parseInt(br.readLine());
		}
		int height=0;
		int tmp=1;
		while(tmp<n) {
			tmp<<=1;
			height++;
		}
		int[] minTree=new int[height*n+1];
		int[] maxTree=new int[height*n+1];
		min_init(minTree,inputs,1,0,n-1);
		max_init(maxTree,inputs,1,0,n-1);
		for(int i=0;i<m;i++) {
			s=br.readLine().split(" ");
			int a=Integer.parseInt(s[0]);
			int b=Integer.parseInt(s[1]);
			System.out.print(min(minTree,1,a-1,b-1,0,n-1)+" ");
			System.out.println(max(maxTree,1,a-1,b-1,0,n-1));
		}
	}

	private static int max(int[] maxTree, int nodenum, int left, int right, int start, int end) {
		if(left>end || right<start) {
			return 0;
		}
		if(left<=start && right>=end) {
			return maxTree[nodenum];
		}
		int a=max(maxTree,nodenum*2,left,right,start,(start+end)/2);
		int b=max(maxTree,nodenum*2+1,left,right,(start+end)/2+1,end);
		return a>b?a:b;
	}

	private static int min(int[] minTree, int nodenum, int left, int right, int start, int end) {
		if(left>end || right<start) {
			return 1000000000;
		}
		if(left<=start && right>=end) {
			return minTree[nodenum];
		}
		int a=min(minTree,nodenum*2,left,right,start,(start+end)/2);
		int b=min(minTree,nodenum*2+1,left,right,(start+end)/2+1,end);
		return a<b?a:b;
	}

	private static int max_init(int[] maxTree, int[] inputs, int nodenum, int start, int end) {
		if(start==end) {
			return maxTree[nodenum]=inputs[start];
		}
		int a=max_init(maxTree,inputs,nodenum*2,start,(start+end)/2);
		int b=max_init(maxTree,inputs,nodenum*2+1,(start+end)/2+1,end);
		return maxTree[nodenum]=a>b?a:b;
	}

	private static int min_init(int[] minTree, int[] inputs, int nodenum, int start, int end) {
		if(start==end) {
			return minTree[nodenum]=inputs[start];
		}
		int a=min_init(minTree,inputs,nodenum*2,start,(start+end)/2);
		int b=min_init(minTree,inputs,nodenum*2+1,(start+end)/2+1,end);
		return minTree[nodenum]=a<b?a:b;
	}

}
