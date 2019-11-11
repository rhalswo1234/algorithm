import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main최솟값 {
	public static void main(String[] args) throws NumberFormatException, IOException {
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
		int[] segTree=new int[height*n+1];
		
		init(segTree,inputs,1,0,n-1);
		for(int i=0;i<m;i++) {
			s=br.readLine().split(" ");
			int a=Integer.parseInt(s[0]);
			int b=Integer.parseInt(s[1]);
			System.out.println(min(segTree,1,a-1,b-1,0,n-1));
		}
	}

	private static int min(int[] segTree, int nodenum, int left, int right, int start, int end) {
		if(left>end || right<start) {
			return 1000000000;
		}
		if(left<=start && right>=end) {
			return segTree[nodenum];
		}
		int a=min(segTree,nodenum*2,left,right,start,(start+end)/2);
		int b=min(segTree,nodenum*2+1,left,right,(start+end)/2+1,end);
		return a < b ? a:b;
	}

	private static int init(int[] segTree, int[] inputs, int nodenum, int start, int end) {
		if(start==end) {
			return segTree[nodenum]=inputs[start];
		}
		int a=init(segTree,inputs,nodenum*2,start,(start+end)/2);
		int b=init(segTree,inputs,nodenum*2+1,(start+end)/2+1,end);
		return segTree[nodenum]=a<b?a:b;
	}
}
