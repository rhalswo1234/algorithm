import java.util.Arrays;
import java.util.Scanner;

public class test {
	static int N, M, rx, ry, rd, cnt;
	static int[][] map;
	static int[][] vi;
	static int[] di = { -1, 0, 1, 0 }; // �� �� �� ��
	static int[] dj = { 0, 1, 0, -1 }; // 0,1,2,3

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		vi = new int[N][M];
		rx = sc.nextInt();
		ry = sc.nextInt();
		rd = sc.nextInt();
		cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		vi[rx][ry] = 1; // ������ġû��
		solve(0); // 0�� ���� �ѹ��� �ȹٲ�ܶ�

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (vi[i][j] == 1)
					cnt++;
			}
		}

//		for (int[] a : vi)
//			System.out.println(Arrays.toString(a));

		System.out.println(cnt);
	}

	private static void solve(int cnt) {
		if (cnt >= 4) { // �׹���ٺ�����
//			if (rx == 8 && ry == 6)
//				System.out.println("���");
			// �ٶ󺸴¹����� ��ĭ������ �������� �� 0->2 1->3 2->0 3->1
			int xx = rx + di[(rd + 2) % 4];
			int yy = ry + dj[(rd + 2) % 4];
			if (xx >= 0 && xx < N && yy >= 0 && yy < M && (map[xx][yy] != 1)) {
				rx = xx;
				ry = yy; // �����ϰ�
				// �׹��� �������� �ٽ�Ž��
				solve(0);
			}
			return;
		}
		// 0->3 1->0 2->1 3->2
		int newx = rx + di[(rd + 3) % 4];
		int newy = ry + dj[(rd + 3) % 4];
//		System.out.println(newx + " " + newy);
		if (newx >= 0 && newx < N && newy >= 0 && newy < M && map[newx][newy] != 1 && (vi[newx][newy] == 0)) {
			// ���ݹ��⿡�� ���ʹ����� �����հ� û������ �ʾ�����
			rd = (rd + 3) % 4; // �׹������� ȸ���ϰ�
			rx = rx + di[rd];
			ry = ry + dj[rd]; // �׹������� ��ĭ����
			vi[rx][ry] = 1;
			solve(0); // û���ϰ� ��ĭ�������� �ٽ� ����
		} else {
			rd = (rd + 3) % 4; // �׹������� ȸ�����ϰ�
			solve(cnt + 1); // �ٸ�����Ž��
		}
	}
}
