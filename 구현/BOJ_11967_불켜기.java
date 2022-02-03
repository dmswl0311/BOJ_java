import java.io.*;
import java.util.*;

/**
 * 299040 3640 -> 300188 1388
 * 
 * @author CHO
 * @see https://www.acmicpc.net/problem/11967
 * @category ����, BFS 
 * @warning �� ���� ���� �������� �����ؾ� �� -> ���߿��� �湮�� �� �ִ��� ���� Ȯ������
 * 1�ð� 5��
 */

public class BOJ_11967_���ѱ� {
	static class Pos {
		int x;
		int y;
		int a;
		int b;

		public Pos(int x, int y, int a, int b) {
			this.x = x;
			this.y = y;
			this.a = a;
			this.b = b;
		}
	}

	static StringTokenizer st;
	static int N;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] light = new boolean[N + 1][N + 1]; // �� �����ִ��� ����
		light[1][1] = true; // (1,1)�� ���������Ƿ�
		ArrayList<Pos> list = new ArrayList<>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new Pos(x, y, a, b));
		} // �Է� �Ϸ�
		int result = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { 1, 1 });
		boolean[][] hubo = new boolean[N + 1][N + 1]; // ** �� ���� ���� ��ŷ. ���߿��� �� �� �ֱ� ������ �����Ǿ�� ��
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			// ���� �濡�� �� �� �ִ� light �ѱ�
			for (int i = 0; i < list.size(); i++) {
				Pos next = list.get(i);
				if (next.x == cur[0] && next.y == cur[1]) {
					if (!light[next.a][next.b]) {
						light[next.a][next.b] = true;
						list.remove(i--); // �� �����Ƿ� �ٽ� ���� ���� -> ����
						hubo[next.a][next.b] = true; // �Ʊ� ��Ų�� ��ŷ
						result++;
					}
				}
			}
			// �� �� Ű���� �̵��� �� �ִ� �� ��
			// �̵� �������� �Ǵ��ϴ� �ڵ�
			boolean flag = false;
			Queue<int[]> q2 = new LinkedList<int[]>();
			q2.add(new int[] { cur[0], cur[1] });
			boolean[][] vis = new boolean[N + 1][N + 1]; // ��� ���⿡�� �Դ��� �湮ǥ��
			vis[cur[0]][cur[1]] = true;
			while (!q2.isEmpty()) {
				int[] cur2 = q2.poll();
				if (hubo[cur2[0]][cur2[1]]) {
					hubo[cur2[0]][cur2[1]] = false;
					q.add(new int[] { cur2[0], cur2[1] });
				}
				for (int j = 0; j < 4; j++) {
					int nx = cur2[0] + dir[j][0];
					int ny = cur2[1] + dir[j][1];
					if (isOkay(nx, ny) && !vis[nx][ny] && light[nx][ny]) {
						vis[nx][ny] = true;
						q2.add(new int[] { nx, ny });
					}
				}
			}
		}
		// ���� ������ ����
		System.out.println(result);
	}

	private static boolean isOkay(int nx, int ny) {
		return nx > 0 && nx < N + 1 && ny > 0 && ny < N + 1;
	}
}
