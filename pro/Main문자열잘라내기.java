import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Trie{
	Trie[] words;
	boolean isend;
	Trie(){
		words=new Trie[26];
		isend=false;
	}
}

public class Main문자열잘라내기 {
	public static Trie trie;
	public static int ylength;
	public static int xlength;
	public static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] s=br.readLine().split(" ");
		
		ylength=Integer.parseInt(s[0]);
		xlength=Integer.parseInt(s[1]);
		map=new char[ylength][xlength];
		for(int i=0;i<ylength;i++) {
			map[i]=br.readLine().toCharArray();
		}
		trie=new Trie();
		int start=0;
		int end=ylength-1;
		int answer=0;
		while(start<end) {
			int mid=(start+end)/2+1;
			if(ok(mid)) {
				start=mid;
				answer=mid;
			}else {
				end=mid-1;
			}
		}
//		System.out.println(start+" "+end+" "+answer);
		System.out.println(ylength-start);
	}

	private static boolean ok(int h) {
		int index=0;
		for(int i=0;i<xlength;i++) {
			Trie tmp=trie;
			for(int j=h;j<ylength;j++) {
				index=map[j][i]-'a';
				if(tmp.words[index]==null) {
					tmp.words[index]=new Trie();
				}
				tmp=tmp.words[index];
			}
			if(tmp.isend) {
				return true;
			}else {
				tmp.isend=true;
			}
		}
		return false;
	}
}
