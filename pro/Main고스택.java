import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class goStack {
	long[] stack;
	int top;
	boolean error;

	goStack() {
		stack = new long[1000];
		top = 0;
		error = false;
	}

	void push(long value) {
		if(top>=1000) {
			error=true;
			return;
		}
		stack[top++] = value;
		return;
	}

	long pop() {
		if (top > 0) {
			return stack[--top];
		} else {
			error = true;
			return 0;
		}
	}

	long top() {
		if (top > 0) {
			return stack[top - 1];
		} else {
//			error = true;
			return 0;
		}
	}

	void swap() {
		if (top > 1) {
			long tmp = stack[top - 1];
			stack[top - 1] = stack[top - 2];
			stack[top - 2] = tmp;
		} else {
			error = true;
		}
	}

	void add() {
		if (top > 1) {
			stack[top - 2] += stack[top - 1];
			top--;
			return;
		} else {
			error = true;
			return;
		}
	}

	void sub() {
		if (top > 1) {
			stack[top - 2] -= stack[top - 1];
			top--;
			return;
		} else {
			error = true;
			return;
		}
	}

	void mul() {
		if (top > 1) {
			stack[top - 2] *= stack[top - 1];
			top--;
			return;
		} else {
			error = true;
			return;
		}
	}

	void div() {
		if (top <= 1 || stack[top - 1] == 0) { // 수가 부족하거나 0으로 나눌때
			error = true;
			return;
		} else {
			int minus_cnt = 0;
			if (stack[top - 1] < 0) {
				minus_cnt++;
				stack[top - 1] *= -1;
			}
			if (stack[top - 2] < 0) {
				minus_cnt++;
				stack[top - 2] *= -1;
			}
			stack[top - 2] /= stack[top - 1];
			if (minus_cnt == 1) {
				stack[top - 2] *= -1;
			}
			top--;
		}
	}

	void mod() {
		if (top <= 1 || stack[top - 1] == 0) {
			error = true;
			return;
		} else {
			boolean minus = false;
			if (stack[top - 2] < 0) {
				minus = true;
				stack[top - 2] *= -1;
			}
			if(stack[top-1]<0) {
				stack[top-1]*=-1;
			}
			stack[top - 2] %= stack[top - 1];
			if (minus) {
				stack[top - 2] *= -1;
			}
			top--;
			return;
		}
	}
	boolean end() {
		if(top!=1) {
			error=true;
		}
		return error;
	}
}

public class Main고스택 {
	public static String[] op = new String[100000];
	public static int[] num_val = new int[100000];

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int op_index=0;
		int num_index=0;
		while(true) {
			String[] s=br.readLine().split(" ");
			op[op_index++]=s[0];
//			System.out.println(s[0]);
			if(s.length==2) {
				num_val[num_index++]=Integer.parseInt(s[1]);
			}
			if(op[op_index-1].equals("QUIT")) {
				break;
			}
			if(op[op_index-1].equals("END")) {//명령어 입력 다 받았다.
//				System.out.println("숫자들"+Arrays.toString(num_val));
				int n=Integer.parseInt(br.readLine());
//				System.out.println("n="+n);
				for(int i=0;i<n;i++) { //프로그램 실행시작
					int input=Integer.parseInt(br.readLine());
					goStack gost=new goStack();
					gost.push(input);
//					for(int j=0;j<op_index;j++) {
//						System.out.print(op[j]+",");
//					}
//					System.out.println();
					int n_index=0;
					for(int j=0;j<op_index;j++) {
						
						switch(op[j]) {
						case "NUM":
//							System.out.println("number="+num_val[n_index]);
							gost.push(num_val[n_index++]);
//							System.out.println("num"+gost.top());
//							System.out.println("number1="+num_val[n_index]);
//							System.out.println("top="+gost.top);
							break;
						case "POP":
							gost.pop();
//							System.out.println("pop"+gost.top());
							break;
						case "INV":
							gost.push(gost.pop()*-1);
//							System.out.println("inv"+gost.top());
							break;
						case "DUP":
							gost.push(gost.top());
//							System.out.println("dup"+gost.top());
							break;
						case "SWP":
							gost.swap();
//							System.out.println("swp"+gost.top());
							break;
						case "ADD":
							gost.add();
//							System.out.println("add"+gost.top());
							break;
						case "SUB":
							gost.sub();
//							System.out.println("sub"+gost.top());
							break;
						case "MUL":
							gost.mul();
//							System.out.println("mul"+gost.top());
							break;
						case "DIV":
							gost.div();
//							System.out.println("div"+gost.top());
							break;
						case "MOD":
							gost.mod();
//							System.out.println("mod"+gost.top());
							break;
						case "END":
							if(!gost.end()) {
//								System.out.println(Arrays.toString(gost.stack));
								System.out.println(gost.top());
							}
							break;
						}
						if(gost.error || gost.top()<-1000000000 || gost.top() >1000000000) {
//							System.out.println("걸림?");
							System.out.println("ERROR");
							break;
						}
					}
				}
				System.out.println();
				br.readLine();
				op_index=0;
				num_index=0;
			}
		}
	}
}
