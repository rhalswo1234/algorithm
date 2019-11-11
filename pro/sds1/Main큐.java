package sds1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Que{
	private static int[] qlist;
	private static int front;
	private static int rear;
	
	public Que() {
		qlist=new int[100000];
		front=0;
		rear=0;
	}
	public int isEmpty() {
		if(front==rear) {
			return 1;
		}
		return 0;
	}
	
	public void push(int x) {
		qlist[rear++%100000]=x;
	}
	public int size() {
		if(front>rear) {
			return rear+100000-front;
		}
		return rear-front;
	}
	public int pop() {
		if(isEmpty()==1) {
			return -1;
		}
		return qlist[front++%100000];
	}
	public int front() {
		if(isEmpty()==1) {
			return -1;
		}
		return qlist[front%100000];
	}
	public int back() {
		if(isEmpty()==1) {
			return -1;
		}
		return qlist[(rear-1)%100000];
	}
}

public class Mainť {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		Que q=new Que();
		for(int i=0;i<n;i++) {
			String[] s=br.readLine().split(" ");
			if(s.length==2) {
				q.push(Integer.parseInt(s[1]));
			}else {
				switch(s[0]) {
				case "front":
					System.out.println(q.front());
					break;
				case "back":
					System.out.println(q.back());
					break;
				case "size":
					System.out.println(q.size());
					break;
				case "empty":
					System.out.println(q.isEmpty());
					break;
				case "pop":
					System.out.println(q.pop());
					break;
				}
			}
		}
	}
}
