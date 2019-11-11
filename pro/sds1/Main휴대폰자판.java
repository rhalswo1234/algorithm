package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class trie{
	trie[] nodes;
	int cnt;
	boolean word;
	public trie() {
		this.nodes=new trie[26];
		this.cnt=0;
		word=false;
	}
	void insert(String s,trie p) {
		for(int i=0;i<s.length();i++) {
			int index=s.charAt(i)-'a';
			if(p.nodes[index]==null) {
				p.nodes[index]=new trie();
				p.cnt++;
			}
			p=p.nodes[index];
		}
		p.word=true;
	}
	int search(String s,trie p) {
		int count=1;
		for(int i=1;i<s.length();i++) {
			if(p.cnt>1 || p.word) {
				count++;
			}
			int index=s.charAt(i)-'a';
			p=p.nodes[index];
		}
		return count;
	}
}

public class Main휴대폰자판 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		String input;
		while((input=br.readLine())!=null) {
			int n=Integer.parseInt(input);
			trie trie=new trie();
			String[] words=new String[n];
			for(int i=0;i<n;i++) {
				words[i]=br.readLine();
				trie.insert(words[i], trie);
			}
			double sum=0;
			for(int i=0;i<n;i++) {
				int index=words[i].charAt(0)-'a';
				sum+=trie.nodes[index].search(words[i], trie.nodes[index]);
			}
//			System.out.println(String.format("%.2f", sum/n));
			sb.append(String.format("%.2f", sum/n)).append("\n");
		}
		System.out.println(sb);
	}
}
