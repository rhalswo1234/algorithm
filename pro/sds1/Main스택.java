package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Stacktest{
	private static int[] stack;
	private static int top;
	
	public Stacktest() {
		stack=new int[10000];
		top=-1;
	}
	
	void push(int x) {
		stack[++top]=x;
	}
	int pop() {
		if(empty()==1) {
			return -1;
		}
		return stack[top--];
	}
	int size() {
		return top+1;
	}
	int empty() {
		if(top==-1) {
			return 1;
		}
		return 0;
	}
	int top() {
		if(empty()==1) {
			return -1;
		}
		return stack[top];
	}
}

public class MainΩ∫≈√ {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		
		Stacktest st=new Stacktest();
		for(int i=0;i<n;i++) {
			String[] s=br.readLine().split(" ");
			if(s.length==2) {
				st.push(Integer.parseInt(s[1]));
			}else {
				switch(s[0]) {
				case "top":
					System.out.println(st.top());
					break;
				case "size":
					System.out.println(st.size());
					break;
				case "empty":
					System.out.println(st.empty());
					break;
				case "pop":
					System.out.println(st.pop());
					break;
				}
			}
		}
	}
}
