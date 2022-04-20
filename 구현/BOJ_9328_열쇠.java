package algo;

import java.io.*;
import java.util.*;

/**
 * 42252	316
 * @author CHO
 * @category BFS
 * @tip ����for������ ��� �� Ž�� X, �ʱ� �迭�� '.'���� ä�� 
 * Ű�� �ݰų� ������ �߰��� ���� '.', Ű�� �߰��ϸ� ������ �湮�迭 �ʱ�ȭ 
 */
public class BOJ_9328_���� {
	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int h, w, result, docs;
	static char[][] map;
	static boolean[] keys;
	static boolean[][] vis;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h + 2][w + 2];
			for (int i = 0; i < h + 2; i++) {
				for (int j = 0; j < w + 2; j++) {
					map[i][j] = '.';
				}
			}// �Ѻκ� .���� ä���
			docs=0;
			for (int i = 1; i < h + 1; i++) {
				String str = br.readLine();
				for (int j = 1; j < w + 1; j++) {
					map[i][j] = str.charAt(j - 1);
					if(map[i][j]=='$') docs+=1;
				}
			}
			String str2 = br.readLine();
			keys = new boolean[26]; // key����
			if (!str2.equals("0")) {
				for (int i = 0; i < str2.length(); i++) {
					keys[(str2.charAt(i) - 0) - 97] = true;
				}
			} // �Է� �Ϸ�
			vis = new boolean[h + 2][w + 2];
			int cnt=bfs(0,0);
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}

	private static int bfs(int x, int y) {
		int cnt = 0;
		if(map[x][y]=='$') cnt=1;
		vis[x][y] = true;
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(x, y));
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			x = cur.x;
			y = cur.y;
			for (int i = 0; i < dir.length; i++) {
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];
				if(cnt==docs) return cnt;
				if (nx >= 0 && ny >= 0 && nx <= h + 1 && ny <= w + 1 && !vis[nx][ny]) {
					if (map[nx][ny] == '.'
							|| ('A' <= map[nx][ny] && map[nx][ny] <= 'Z' && keys[(map[nx][ny] - 0) - 65])) {
					} else if (map[nx][ny] == '$') {
						map[nx][ny] = '.';
						cnt++;
					} else if ('a' <= map[nx][ny] && map[nx][ny] <= 'z') {
						// �ҹ����� ���, keys�� true�� �ٲٰ� ������ �湮�ߴ� ������ false�� �ٲ۴�.
						keys[(map[nx][ny] - 0) - 97] = true;
						vis = new boolean[h + 2][w + 2];
						map[nx][ny]='.';
					} else
						continue;
					vis[nx][ny] = true;
					q.add(new Pos(nx, ny));
				}
			}
		}
		return cnt;
	}
}
