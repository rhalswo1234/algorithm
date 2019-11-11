package kakao;

import java.util.Arrays;

class stage{
	double fail;
	int floor;
}

public class Solution실패율 {
	public static void main(String[] args) {
		int n=4;
		int[] stages= {4,4,4,4,4};
		
		System.out.println(Arrays.toString(solution(n,stages)));
	}
	public static int[] solution(int N, int[] stages) {
		int[] stage_stop=new int[N+1];
		int[] stage_dodal=new int[N+1];
		double[] fail=new double[N];
		stage[] stage=new stage[N]; 
		stage[] tmp=new stage[N];
		for(int i=0;i<stages.length;i++) {
			stage_stop[stages[i]-1]++;
			stage_dodal[stages[i]-1]++;
		}
		for(int i=N-1;i>=0;i--) {
			stage_dodal[i]+=stage_dodal[i+1];
		}
		for(int i=0;i<N;i++) {
			stage[i]=new stage();
			stage[i].floor=i+1;
			tmp[i]=new stage();
			tmp[i].floor=i+1;
			if(stage_dodal[i]==0) {
				stage[i].fail=0;
			}else {
				stage[i].fail= (double) stage_stop[i]/stage_dodal[i];
			}
		}
		merge(stage,tmp,0,N-1);
        int[] answer = new int[N];
        for(int i=0;i<N;i++) {
        	answer[i]=tmp[i].floor;
        }
        return answer;
    }
	private static void merge(stage[] stage, stage[] tmp, int start, int end) {
		if(start>=end) return;
		int mid=(start+end)>>1;
		merge(stage,tmp,start,mid);
		merge(stage,tmp,mid+1,end);
		mergesort(stage,tmp,start,mid,end);
	}
	private static void mergesort(stage[] stage, stage[] tmp, int start, int mid, int end) {
		int i=start;
		int k=start;
		int j=mid+1;
		while(i<=mid && j<=end) {
			if(stage[i].fail>=stage[j].fail) {
				tmp[k]=stage[i++];
			}else {
				tmp[k]=stage[j++];
			}
			k++;
		}
		if(i<=mid) {
			for(int t=i;t<=mid;t++) {
				tmp[k++]=stage[t];
			}
		}else {
			for(int t=j;t<=end;t++) {
				tmp[k++]=stage[t];
			}
		}
		for(int t=start;t<=end;t++) {
			stage[t]=tmp[t];
		}
		
	}
}
