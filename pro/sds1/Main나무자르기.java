package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main�����ڸ��� {
	public static int n;
	public static int m;
	public static int[] namus;
	public static long max;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		n=Integer.parseInt(s[0]);
		m=Integer.parseInt(s[1]);
		namus=new int[n];
		
		s=br.readLine().split(" ");
		max=0;
		for(int i=0;i<n;i++) {
			namus[i]=Integer.parseInt(s[i]);
			if(namus[i]>max) {
				max=namus[i];
			}
		}
		//����Ž���������� �Ẹ��
		solve(0,max);
		System.out.println(max);
		
 	}

	private static void solve(long start, long end) {
		if(start>end) {
			return;
		}
		long mid=(start+end)/2;
//		System.out.println(max);
		long sum=0;
		for(int i=0;i<n;i++) {
			if(namus[i]>mid) {
				sum+=namus[i]-mid;
			}
		}
		if(sum<m) { //�����ϴ� �� ���� ������
			solve(start,mid-1);
		}else { //��������� �ִ��� ���ƾ��ϱ⶧���� ������ �Ǵ��� Ȯ���غ���
			max=mid;
			solve(mid+1,end);
		}
	}
}
