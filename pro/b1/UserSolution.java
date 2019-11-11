package b1;


class list{
	char[] value;
	int length;
	list(char[] val) {
		value=new char[19];
		length=0;
		for(int i=0;i<val.length;i++) {
			value[length++]=val[i];
		}
	}
	void change(char[] val) {
		length=0;
		for(int i=0;i<val.length;i++) {
			value[length++]=val[i];
		}
	}
}

class DB{
	list[][] dbs;
//	list[] numbers;
//	list[] births;
//	list[] emails;
//	list[] memos;
	int length;
	int size;
	DB(){
		dbs=new list[5][16];
//		names=new list[16];
//		numbers=new list[16];
//		births=new list[16];
//		emails=new list[16];
//		memos=new list[16];
		length=16;
		size=0;
	}
	void add(String name, String number, String birth,String email, String memo){
		if(size==length) {
			sizeUp();
		}
		dbs[0][size]=new list(name.toCharArray());
		dbs[1][size]=new list(number.toCharArray());
		dbs[2][size]=new list(birth.toCharArray());
		dbs[3][size]=new list(email.toCharArray());
		dbs[4][size++]=new list(memo.toCharArray());
	}
	void sizeUp() {
		list[][] tmp=new list[5][size<<1];
//		list[] tmp1=new list[size<<1];
//		list[] tmp2=new list[size<<1];
//		list[] tmp3=new list[size<<1];
//		list[] tmp4=new list[size<<1];
//		list[] tmp5=new list[size<<1];
		for(int i=0;i<size;i++) {
			tmp[0][i]=dbs[0][i];
			tmp[1][i]=dbs[1][i];
			tmp[2][i]=dbs[2][i];
			tmp[3][i]=dbs[3][i];
			tmp[4][i]=dbs[4][i];
		}
		length<<=1;
		
		dbs=tmp;
	}
	
	int delete(int field, String str) {
		int result=0;
		for(int i=0;i<size;i++) {
			if(isSame(dbs[field][i],str.toCharArray())) {
				remove(i);
				result++;
				i--;
			}
		}
		
		return result;
	}
	private void remove(int index) {
		for(int i=index;i<size;i++) {
			dbs[0][i]=dbs[0][i+1];
			dbs[1][i]=dbs[1][i+1];
			dbs[2][i]=dbs[2][i+1];
			dbs[3][i]=dbs[3][i+1];
			dbs[4][i]=dbs[4][i+1];
			size--;
		}
	}
	private boolean isSame(list list, char[] charArray) {
		if(list.length!=charArray.length) return false;
		for(int i=0;i<list.length;i++) {
			if(list.value[i]!=charArray[i]) {
				return false;
			}
		}
		return true;
	}
	
	int change(int field, String str, int changefield, String changestr) {
		int result=0;
		for(int i=0;i<size;i++) {
			if(isSame(dbs[field][i],str.toCharArray())) {
				dbs[changefield][i].change(changestr.toCharArray());
				result++;
			}
		}
		
		return result;
	}
	
	b1.Solution.Result Search(b1.Solution.Result result,int field, String str, int returnfield){
		for(int i=0;i<size;i++) {
			if(isSame(dbs[field][i],str.toCharArray())) {
				result.count++;
				for(int j=0;j<dbs[returnfield][i].length;j++) {
					result.str+=""+dbs[returnfield][i].value[j];
				}
			}
		}
		
		return result;
	}
	
}

class UserSolution {
	public static DB database;
	void InitDB()
	{
		database=new DB();
	}

	void Add(String name, String number, String birthday, String email, String memo)
	{
		database.add(name, number, birthday, email, memo);
	}

	int Delete(int field, String str)
	{
		return database.delete(field, str);
	}

	int Change(int field, String str, int changefield, String changestr)
	{
		return database.change(field, str, changefield, changestr);
	}

	b1.Solution.Result Search(int field, String str, int returnfield)
	{
		b1.Solution.Result result = new b1.Solution.Result();
//		result.count = -1;
//		result=database.Search(result, field, str, returnfield);

		return result;
	}
}
