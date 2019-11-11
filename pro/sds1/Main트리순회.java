package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
class TreeNode{
	char value;
	char left;
	char right;
	public TreeNode(char val,char left,char right) {
		this.value=val;
		this.left=left;
		this.right=right;
	}
}

class Tree{
	TreeNode[] nodes;
	public Tree() {
		nodes=new TreeNode[26];
	}
}

public class Main트리순회 {
	public static Tree t;
	public static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		t=new Tree();
		for(int i=0;i<n;i++) {
			char[] ca=br.readLine().replace(" ", "").toCharArray();
			TreeNode tnode=new TreeNode(ca[0],ca[1],ca[2]);
			t.nodes[ca[0]-65]=tnode;
		}
		sb=new StringBuilder();
		preorder(t.nodes[0]);
		sb.append("\n");
		inorder(t.nodes[0]);
		sb.append("\n");
		postorder(t.nodes[0]);
		System.out.println(sb);
		
	}

	private static void postorder(TreeNode tnode) {
		if(tnode.left!='.') {
			postorder(t.nodes[tnode.left-65]);
		}
		if(tnode.right!='.') {
			postorder(t.nodes[tnode.right-65]);
		}
		sb.append(tnode.value);
		
	}

	private static void inorder(TreeNode tnode) {
		if(tnode.left!='.') {
			inorder(t.nodes[tnode.left-65]);
		}
		sb.append(tnode.value);
		if(tnode.right!='.') {
			inorder(t.nodes[tnode.right-65]);
		}
		
	}

	private static void preorder(TreeNode tnode) {
		sb.append(tnode.value);
		if(tnode.left!='.') {
			preorder(t.nodes[tnode.left-65]);
		}
		if(tnode.right!='.') {
			preorder(t.nodes[tnode.right-65]);
		}
	}
}
