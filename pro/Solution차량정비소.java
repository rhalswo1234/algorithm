import java.io.*;
import java.util.*;
public class Solution차량정비소 {
	static int n,m,k,a[],b[],t[],tim,targetRec,targetRep,ans,temp[];
	static boolean [] vrec,vrep;
	static ArrayList<int[]> arr,rec,repwait,rep,fin;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=T;++tc) {
			tim=0; ans=-1;
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			k=Integer.parseInt(st.nextToken());
			a=new int[n+1];
			vrec=new boolean[n+1];
			vrep=new boolean[m+1];
			b=new int[m+1];
			t=new int[k+1];
			targetRec=Integer.parseInt(st.nextToken());
			targetRep=Integer.parseInt(st.nextToken());
			arr=new ArrayList<>();
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<a.length;++i) {
				a[i]=Integer.parseInt(st.nextToken());
			}
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<b.length;++i) {
				b[i]=Integer.parseInt(st.nextToken());
			}
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<t.length;++i) {
				t[i]=Integer.parseInt(st.nextToken());
			}
			for(int i=1;i<t.length;++i) {
				arr.add(new int[] {t[i],i,-1,-1,-1,-2,0});//도착시간,고객번호, 접수 창구번호, 
				//,접수창구 남은시간,정비창구번호,정비창구 남은시간,repwait에 들어간 시간
			}
			rec=new ArrayList<>(); rep=new ArrayList<>(); 
			repwait=new ArrayList<>();
			loop:while(true) {
				//고객 차량 정비소에 도착
				for(int i=0;i<arr.size();++i) {
					if(arr.get(i)[0]>0) arr.get(i)[0]--;
					else if(arr.get(i)[0]==0) {
						arr.get(i)[0]=-1;
					} 
				}
				
				
				//빈 고장 접수 창구 없으면 대기, 있으면 고장 접수(먼저 도착한 순으로 먼저감 고객번호도 도착한 순서로)
				//접수창구 여러개면 접수창구 번호 낮은 순으로
				
				//어레이리스트 인덱스 안맞는다
				for(int i=1;i<=n;++i) {
					if(!vrec[i] && arr.size()>=1) {
						if(arr.get(0)[0]==-1) {
							vrec[i]=true;
							temp=arr.remove(0);
							System.out.println("temp"+" "+Arrays.toString(temp));
							temp[2]=i;
							temp[3]=a[i];//접수 창구 남은 시간 설정
							rec.add(temp);
						}
					}
				}
				
				for(int i=0;i<rec.size();++i) {
					System.out.println("rec"+" "+Arrays.toString(rec.get(i)));
					if(rec.get(i)[3]>0)//rec에있는 남은 시간 1씩 줄여주기
						rec.get(i)[3]--;
					else if(rec.get(i)[3]==0) {
						rec.get(i)[3]=-1;
						rec.get(i)[6]=tim;
						repwait.add(rec.get(i));
						vrec[rec.get(i)[2]]=false;
						rec.remove(i);
						i--;
					}
				}
				for(int i=0;i<repwait.size();i++) {
					System.out.println("repwait"+Arrays.toString(repwait.get(i)));
				}
				System.out.println();
				Collections.sort(repwait,new Comparator<int[]>(){

					@Override
					public int compare(int[] o1, int[] o2) {
						if(o1[6]==o2[6]) {
							if(o1[2]>o2[2])
								return 1;
							else 
								return -1;
						}
						return 0;
					}
					
				});
				for(int i=0;i<repwait.size();i++) {
					System.out.println(Arrays.toString(repwait.get(i)));
				}
				System.out.println();
				for(int i=1;i<=m;++i) {
					if(!vrep[i] && repwait.size()>=1) {
						System.out.println("repwait2"+Arrays.toString(repwait.get(0)));
						if(repwait.get(0)[3]==-1) {
							System.out.println(Arrays.toString(vrep));
							vrep[i]=true;
							temp=repwait.remove(0);
							temp[4]=i;
							System.out.println(Arrays.toString(vrep));
							temp[5]=b[i];//접수 창구 남은 시간 설정
							System.out.println("temp2"+" "+Arrays.toString(temp));
							rep.add(temp);
						}
					}
				}
				
//				고장 접수 완료 -> 빈 정비창구 없으면 대기, 빈 정비창구 있으면 바로 정비창구로 이동
//				 * 먼저 온 고객 먼저 이동, 동시에 접수 완료했으면 접수 창구 번호 낮은애가 먼저
//				 * 정비창구 여러개면 정비창구 번호 낮은 순으로
				
				for(int i=0;i<rep.size();++i) {
					if(rep.get(i)[5]>0)
						rep.get(i)[5]--;
					else if(rep.get(i)[5]==0) {
						rep.get(i)[5]=-1;
						vrep[rep.get(i)[4]]=false;
					}
				}
				
				tim++;
				boolean flag=false;
				if(rep.size()==k) {
					flag=true;
					for(int i=0;i<rep.size();++i) {
						if(rep.get(i)[5]!=-1) {
							flag=false;
							break loop;
						}
					}
				}
				if(flag) 
				break loop;
			}
			for(int i=0;i<rep.size();++i) {
				System.out.println(Arrays.toString(rep.get(i)));
				if(rep.get(i)[2]==targetRec && rep.get(i)[4]==targetRep) {
					System.out.println(rep.get(i)[2]+" "+rep.get(i)[4]);
					ans+=rep.get(i)[1];
				}
			}
			sb.append("#").append(tc).append(" ").append(ans==-1?-1:ans+1).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
	}
}