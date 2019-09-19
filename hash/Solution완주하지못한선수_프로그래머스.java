package hash;



class Hashtable{
	class Name{
		String[] users;
		int size;
		Name(){
			users=new String[1];
			size=0;
		}
		void adduser(String name) {
			if(size==users.length) {
				sizeUp();
			}
			users[size++]=name;
		}
		
		void sizeUp() {
			String[] tmp=new String[size*2];
			for(int i=0;i<size;i++) {
				tmp[i]=users[i];
			}
			users=tmp;
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
			}
			users[--size]="";
		}
		boolean findname(String name) {
			for(int i=0;i<size;i++) {
				if(users[i].equals(name)) {
					delete(i);
					return true;
				}
			}
			return false;
		}
	}
	Name[] names;
	Hashtable(){
		names=new Name[100003];
	}
	void addht(String name) {
		int index=0;
		int p=1;
		for(int i=0;i<name.length();i++) {
			index=index+(name.charAt(i)-'0')*p;
			index%=100003;
			p*=101;
			p%=100003;
		}
		if(names[index]==null) {
			names[index]=new Name();
		}
		names[index].adduser(name);
	}
	void deleteht(String name) {
		int index=0;
		int p=1;
		for(int i=0;i<name.length();i++) {
			index=index+(name.charAt(i)-'0')*p;
			index%=100003;
			p*=101;
			p%=100003;
		}
		names[index].deleteuser(name);
	}
	
	boolean isexist(String name) {
		int index=0;
		int p=1;
		for(int i=0;i<name.length();i++) {
			index=index+(name.charAt(i)-'0')*p;
			index%=100003;
			p*=101;
			p%=100003;
		}
		if(names[index].findname(name)) {
			return true;
		}
		return false;
	}
	
}

public class Solution완주하지못한선수 {
	
	public static void main(String[] args) {
		String[] input1 = {"mislav","stanko","mislav","ana"};
		String[] input2 = {"stanko","ana","mislav"};
		System.out.println(solution(input1,input2));
	}
	
	public static String solution(String[] participant, String[] completion) {
		Hashtable ht=new Hashtable();
		for(int i=0;i<participant.length;i++) {
			ht.addht(participant[i]);
		}
		for(int i=0;i<completion.length;i++){
			ht.deleteht(completion[i]);
		}
        String answer = "";
        for(int i=0;i<participant.length;i++) {
        	if(ht.isexist(participant[i])) {
        		answer+=participant[i];
        	}
        }
        return answer;
    }
}
