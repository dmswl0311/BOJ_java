import java.io.*;
import java.util.*;

/**
 * 30360	644
 * @author CHO
 * @see https://www.acmicpc.net/problem/8972
 * @category ����
 * 51�� 27��
 */

public class BOJ_8972_��ģ�Ƶ��̳� {
	static class Pos implements Comparable<Pos>{
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {
			if(this.x==o.x) {
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(this.x, o.x);
		}
	}

	static StringTokenizer st;
	static int dir[][] = { { 0, 0 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 0 }, { 0, 1 }, { -1, -1 },
			{ -1, 0 }, { -1, 1 } }; // 0�� �ӽð���

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		int[][] cntMap = new int[R][C]; // ��ģ �Ƶ��̳��� ��ġ ����
		int startX = 0, startY = 0;
		PriorityQueue<Pos> crazy = new PriorityQueue<>();
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'I') {
					startX = i;
					startY = j;
				} else if (map[i][j] == 'R') {
					crazy.add(new Pos(i, j));
					cntMap[i][j] += 1;
				}
			}
		}
		String str = br.readLine(); // �Է� �Ϸ�
		PriorityQueue<Pos> tmp;
		// ��� ����
		int cnt = 0; // �̵��� Ƚ��
		for (int i = 0; i < str.length(); i++) {
			int d = str.charAt(i) - '0'; // ����
			// 1. ���� �Ƶ��̳� �̵�
			startX += dir[d][0];
			startY += dir[d][1];
			cnt++;
			tmp=new PriorityQueue<>(); //Ȯ���ϰ� �ٽ� ���� tmp pq
			// 2. ���� �Ƶ��̳밡 ��ģ �Ƶ��̳뿡�� ������ Ȯ��
			while(!crazy.isEmpty()) {
				Pos cur = crazy.poll();
				if (cur.x == startX && cur.y == startY) {
					System.out.println("kraj " + cnt);
					System.exit(0);
				}
				tmp.add(cur);
			}
			// 3. ��ģ �Ƶ��̳� �̵�
			while(!tmp.isEmpty()) {
				Pos cur = tmp.poll();
				int min = Integer.MAX_VALUE;
				int crazyD = 0;
				for (int k = 1; k <= 9; k++) {
					if (k == 5)
						continue; // ���ڸ��� �� ���� ��
					int nx = cur.x + dir[k][0];
					int ny = cur.y + dir[k][1];
					if (min > Math.abs(startX - nx) + Math.abs(startY - ny)) {
						min = Math.abs(startX - nx) + Math.abs(startY - ny);
						crazyD = k;
					}
				} // ���� ���� ������ ���� ���ϱ�
				cntMap[cur.x][cur.y]-=1;
				cur.x = cur.x + dir[crazyD][0];
				cur.y = cur.y + dir[crazyD][1]; // ��ģ �Ƶ��̳� ���� ������Ʈ
				cntMap[cur.x][cur.y]+=1;
				crazy.add(cur);
				// 4. ��ģ �Ƶ��̳밡 ���� �Ƶ��̳� ��ġ�� �̵��Ѵٸ�..
				if (cur.x == startX && cur.y == startY) {
					System.out.println("kraj " + cnt);
					System.exit(0);
				}
			}
			// 5. �ߺ��� ��ġ�� �ִ� ��ģ �Ƶ��̳� ����
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if(cntMap[r][c]==1) {
						Pos cur=crazy.poll();
						tmp.add(cur);
					}else if(cntMap[r][c]>1){
						// ������ŭ crazy���� ��
						for (int j = 0; j < cntMap[r][c]; j++) {
							crazy.poll();
						}
						cntMap[r][c]=0;
					}
				}
			}
			while(!tmp.isEmpty()) {
				crazy.add(tmp.poll());
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(i==startX && j==startY) System.out.print("I");
				else if(cntMap[i][j]==1) System.out.print("R");
				else System.out.print(".");
			}
			System.out.println();
		}

	}
}
