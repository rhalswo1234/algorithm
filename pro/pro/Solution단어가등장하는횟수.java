package pro;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution단어가등장하는횟수 {
	public static char[] book;
	public static char[] word;
	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Scanner sc=new Scanner(System.in);
//		int T=Integer.parseInt(br.readLine());
		int T=sc.nextInt();
		sc.nextLine();
		
		for(int tc=1;tc<=T;tc++) {
//			book=br.readLine().toCharArray();
//			word=br.readLine().toCharArray();
			book=sc.nextLine().toCharArray();
			word=sc.nextLine().toCharArray();
//			System.out.println(book+" "+word);
			int answer=0;
			long index=word[0];
			for(int i=1;i<word.length;i++) {
				index*=302;
				index%=1000000007;
				index+=word[i];
//				index%=10007;
			}
			long bookindex=book[0];
			long p=1;
			for(int i=1;i<word.length;i++) {
				bookindex*=302;
				bookindex%=1000000007;
				bookindex+=book[i];
//				bookindex%=10007;
				p*=302;
				p%=1000000007;
			}
			if(index==bookindex) {
                    answer++;
			}
			for(int i=word.length;i<book.length;i++) {
				bookindex-=((long)book[i-word.length]*p)%1000000007;
				if(bookindex<0) {
					bookindex+=1000000007;
					bookindex%=1000000007;
				}
				bookindex*=302;
				bookindex%=1000000007;
				bookindex+=book[i];
//				bookindex%=10007;
				if(index==bookindex) {
    	                answer++;
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
	private static boolean realSame(int bookindex) {
		for(int i=0;i<word.length;i++) {
			if(word[i]!=book[bookindex+i]) {
				return false;
			}
		}
		return true;
	}
}
