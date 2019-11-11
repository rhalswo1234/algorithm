import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class trie{
	public trie[] nodes;
	public boolean check;//지나감 체크
	public boolean check2;//여기서 끝
	public trie() {
		this.nodes=new trie[10];
		this.check=false;
		this.check2=false;
	}
}


public class Main전화번호목록 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			String[] phone=new String[n];
			for(int i=0;i<n;i++) {
				phone[i]=br.readLine();
			}
			boolean yes_or_no=true;
			trie trie=new trie();
			loop:for(int i=0;i<n;i++) {
				trie tmp_trie=trie;
				String tmp=phone[i];
				for(int j=0;j<tmp.length();j++) {
					if(tmp_trie.nodes[tmp.charAt(j)-'0']==null) {//없는 노드면 만들어준다.
						tmp_trie.nodes[tmp.charAt(j)-'0']=new trie();
						tmp_trie.check=true; //지나갔다고 체크
						tmp_trie=tmp_trie.nodes[tmp.charAt(j)-'0'];//다음 확인하러 이동
						continue;
					}
					if(tmp_trie.nodes[tmp.charAt(j)-'0'].check2) {//이미 누군가 끝난지점 = 해당 번호가 있다 ex 911 , 91111
						yes_or_no=false;
						break loop;
					}
					tmp_trie.check=true; //지나갔다고 체크
					tmp_trie=tmp_trie.nodes[tmp.charAt(j)-'0'];//다음 확인하러 이동
				}
				
				//마지막 위치에 도착했으니 그자리에서 끝났음 표시하는 코드
				if(tmp_trie.check) {//만약 누군가 지나간 자리인데 내가 끝난자리 = 겹친부분발생
					yes_or_no=false;
					break loop;
				}
				tmp_trie.check2=true;//그렇지 않으면 내가 끝난지점 표시하고 다음으로 넘어감
			}
			if(yes_or_no) {
				sb.append("YES").append("\n");
//				System.out.println("YES");
			}else {
				sb.append("NO").append("\n");
//				System.out.println("NO");
			}
		}
		System.out.println(sb);
	}
}
