package hash;



class Hashtable2{
	class Name{
		String[] users;
		boolean[] endcheck;
		int size;
		Name(){
			users=new String[1];
			endcheck=new boolean[1];
			size=0;
		}
		void adduser(String name) {
			if(size==users.length) {
				sizeUp();
			}
			users[size++]=name;
		}
		void addenduser(String name) {
			if(size==users.length) {
				sizeUp();
			}
			users[size]=name;
			endcheck[size++]=true;
		}
		
		void sizeUp() {
			String[] tmp=new String[size*2];
			boolean[] tmp2=new boolean[size*2];
			for(int i=0;i<size;i++) {
				tmp[i]=users[i];
				tmp2[i]=endcheck[i];
			}
			users=tmp;
			endcheck=tmp2;
		}
		void deleteuser(String name) {
			for(int i=0;i<size;i++) {
				if(users[i].equals(name)) {
					delete(i);
					return;
				}
			}
		}
		void delete(int index) {
			for(int i=index;i<size-1;i++) {
				users[i]=users[i+1];
				endcheck[i]=endcheck[i+1];
			}
			users[--size]="";
			endcheck[size]=false;
		}
		boolean findname(String name) {
			for(int i=0;i<size;i++) {
				if(users[i].equals(name)) {
//					delete(i);
					return true;
				}
			}
			return false;
		}
		boolean findendname(String name) {
			for(int i=0;i<size;i++) {
				if(users[i].equals(name) && endcheck[i]) {
					return true;
				}
			}
			return false;
		}
	}
	Name[] names;
	Hashtable2(){
		names=new Name[100003];
	}
	boolean addht(String name) {
		int index=0;
		int p=1;
		String parse="";
		for(int i=0;i<name.length()-1;i++) {
			index=index+(name.charAt(i)-'0')*p;
			index%=100003;
			p*=101;
			p%=100003;
			parse+=name.charAt(i);
			if(names[index]==null) {
				names[index]=new Name();
			}
			if(names[index].findendname(parse)) {
				return false;
			}
			names[index].adduser(parse);
		}
		index=index+(name.charAt(name.length()-1)-'0')*p;
		index%=100003;
		parse+=name.charAt(name.length()-1);
		if(names[index]==null) {
			names[index]=new Name();
		}
		if(names[index].findname(name)) {
			return false;
		}
		names[index].addenduser(name);
		return true;
	}
	
}

public class Solution전화번호목록 {
	
	public static void main(String[] args) {
		String[] input1 = {"119","119","97674223","1195524421"};
		System.out.println(solution(input1));
	}
	
	public static boolean solution(String[] phone_book) {
        boolean answer = true;
        Hashtable2 ht=new Hashtable2();
        for(int i=0;i<phone_book.length;i++) {
        	if(!ht.addht(phone_book[i])) {
        		answer=false;
        		break;
        	}
        }
        return answer;
    }
}
