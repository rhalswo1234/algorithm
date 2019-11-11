package kakao;

import java.util.Arrays;

class user{
	String[] uid;
	String[] nickname;
	int size;
	user(){
		uid=new String[1];
		nickname=new String[1];
		size=0;
	}
	void add(String userid, String nick) {
		if(size==nickname.length) {
			sizeUp();
		}
		uid[size]=userid;
		nickname[size++]=nick;
	}
	void sizeUp() {
		String[] tmpuid=new String[size<<1];
		String[] tmp=new String[size<<1];
		for(int i=0;i<size;i++) {
			tmpuid[i]=uid[i];
			tmp[i]=nickname[i];
		}
		uid=tmpuid;
		nickname=tmp;
	}
}

class usertable{
	user[] users;
	usertable(){
		users=new user[100003];
	}
	int hash(String userid) {
		int p=1;
		int index=0;
		for(int i=0;i<userid.length();i++) {
			index=(index+p*(userid.charAt(i)-'0')) % 100003;
			if(index<0) {
				index=(index+100003)%100003;
			}
			p*=101;
			p%=100003;
		}
		return index;
	}
}

public class Solution오픈채팅방 {
	
	public static usertable ut;
	public static void main(String[] args) {
		String[] s= {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		System.out.println(Arrays.toString(solution(s)));
	}
	
	public static String[] solution(String[] record) {
		ut=new usertable();
		int answersize=record.length;
		loop:for(int i=0;i<record.length;i++) {
			String[] s=record[i].split(" ");
			String inout=s[0];
			if(inout.equals("Leave")) continue;
			int userid=ut.hash(s[1]);
			String nick=s[2];
			if(inout.equals("Enter")) {
				if(ut.users[userid]!=null) {
					boolean find=false;
					for(int j=0;j<ut.users[userid].size;j++) {
						if(ut.users[userid].uid[j].equals(s[1])) {
							ut.users[userid].nickname[j]=nick;
							find=true;
							break;
						}
					}
					if(!find) {
						ut.users[userid].add(s[1], nick);
					}
					continue loop;
				}
				ut.users[userid]=new user();
				ut.users[userid].add(s[1], nick);
			}else if(inout.equals("Change")) {
				answersize--;
				for(int j=0;j<ut.users[userid].size;j++) {
					if(ut.users[userid].uid[j].equals(s[1])) {
						ut.users[userid].nickname[j]=nick;
						break;
					}
				}
			}
		}
		String[] answer = new String[answersize];
		int answerindex=0;
		for(int i=0;i<record.length;i++) {
			String[] s=record[i].split(" ");
			String inout=s[0];
			if(inout.equals("Change")) continue;
			int userid=ut.hash(s[1]);
			for(int j=0;j<ut.users[userid].size;j++) {
				if(ut.users[userid].uid[j].equals(s[1])) {
					answer[answerindex]=ut.users[userid].nickname[j]+"님이";
					break;
				}
			}
			if(inout.equals("Leave")) {
				answer[answerindex++]+=" 나갔습니다.";
			}
			if(inout.equals("Enter")) {
				answer[answerindex++]+=" 들어왔습니다.";
			}
		}
		
        return answer;
    }
}
