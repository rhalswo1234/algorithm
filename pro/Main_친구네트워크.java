import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main_친구네트워크 {
	public static int[] p;
	public static int[] counts;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		
		for(int tc=0;tc<n;tc++) {
			int nn=Integer.parseInt(br.readLine());
			counts=new int[200001];
//			Arrays.fill(counts, 1);
			p=new int[200001];
//			for(int i=0;i<200001;i++) {
//				p[i]=i;
//			}
			int index=0;
			HashMap<String, Integer> hm=new HashMap<String, Integer>();
			for(int ttc=0;ttc<nn;ttc++) {
				String[] names=br.readLine().split(" ");
//				System.out.println(Arrays.toString(names));
				if(hm.get(names[0])==null) {
					p[index]=index;
					counts[index]++;
					hm.put(names[0], index++);
					
				}
				if(hm.get(names[1])==null) {
					p[index]=index;
					counts[index]++;
					hm.put(names[1], index++);
				}
				int first=hm.get(names[0]);
				int second=hm.get(names[1]);
				union(first,second);
				if(first>second) {
					sb.append(counts[find(first)]+"\n");
				}else {
//					System.out.println(counts[find(second)]);
					sb.append(counts[find(second)]+"\n");
				}
				
			}
		}
		System.out.println(sb);
	}

	private static void union(int first, int second) {
		int fir=find(first);
		int sec=find(second);
		if(fir==sec) {
			return;
		}else {
			if(fir<sec) {
				p[sec]=fir;
			}else {
				p[fir]=sec;
			}
			int tmp=counts[fir];
			counts[fir]+=counts[sec];
			counts[sec]+=tmp;
			
		}
	}

	private static int find(int first) {
		if(p[first]==first) {
			return first;
		}else {
//			counts[first]++;
			return p[first]=find(p[first]);
		}
	}
}
