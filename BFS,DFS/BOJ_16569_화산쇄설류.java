package algo;

import java.io.*;
import java.util.*;

/**
 * 18196	184
 * @author CHO
 * @see https://www.acmicpc.net/problem/16569
 * @category BFS
 * @tip ȭ�� ���� ���� ����, ArrayIndexOutOfBounds ����, �� �ð����� ������ �� �ִ� ȭ����ġ q�� �ֱ�
 */
public class BOJ_16569_ȭ��⼳�� {

	static StringTokenizer st;
	static int M, N, max, max_value, min_value, result;
	static int[][] map, time, volcano;
	static ArrayList<ArrayList<int[]>> list;
	static boolean[][] vis;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		map = new int[M + 1][N + 1];
		for (int m = 1; m < M + 1; m++) {
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n < N + 1; n++) {
				map[m][n] = Integer.parseInt(st.nextToken());
			}
		}
		time = new int[M + 1][N + 1];
		vis = new boolean[M + 1][N + 1];
		volcano = new int[M + 1][N + 1]; // ȭ�� �ð� ���
		list = new ArrayList<ArrayList<int[]>>();
		// ArrayList ���� ó������ 201�� ������. �ִ� h�� 201�� ���´ٰ� �ؼ�
		// �ٵ� ArrayIndexOutOfBounds�� ����, �����غ��� ������ �ִ� �ð��� h�ΰ��� �ð��� �帣�� �ִ� (M+1)*(N+1)���� ������
		for (int i = 0; i < (M + 1) * (N + 1); i++) {
			list.add(i, new ArrayList<int[]>());
		}
		max_value = 0;
		min_value = Integer.MAX_VALUE;
		for (int v = 0; v < V; v++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			time[i][j] = h;
			max_value = max_value < h ? h : max_value;
			min_value = min_value > h ? h : min_value;
			// �ð��븶�� ������ �� �ִ� ȭ���� ����
			list.get(h).add(new int[] { i, j });
		}
		cover();
		max = 0;
		// ȭ�� ����
		result = 0;
		bfs(X, Y);
		System.out.println(max + " " + result);
	}

	private static void bfs(int x, int y) {
		int cnt = 1;
		boolean[][] bfs_vis = new boolean[M + 1][N + 1];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		bfs_vis[x][y] = true;
		while (!q.isEmpty()) {
			int s = q.size();
			while (s-- > 0) {
				int[] cur = q.poll();
				if (max < map[cur[0]][cur[1]]) {
					max = map[cur[0]][cur[1]];
					result = cnt - 1;
				}
				for (int i = 0; i < dir.length; i++) {
					int nx = cur[0] + dir[i][0];
					int ny = cur[1] + dir[i][1];
					if (nx > 0 && ny > 0 && nx <= M && ny <= N && !bfs_vis[nx][ny] && time[nx][ny] == 0) {
						if (volcano[nx][ny] > cnt || volcano[nx][ny] == 0) {
							bfs_vis[nx][ny] = true;
							q.add(new int[] { nx, ny });
						}
					}
				}
			}
			cnt++;
		}
	}

	private static void cover() {
		// ȭ�� ���� �ּ� �ð� q�� �ֱ� 
		int t = min_value;
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> c = list.get(t);
		for (int k = 0; k < c.size(); k++) {
			q.add(new int[] { c.get(k)[0], c.get(k)[1] });
			vis[c.get(k)[0]][c.get(k)[1]] = true;
		}
		while (!q.isEmpty()) {
			int s = q.size();
			while (s-- > 0) {
				int[] cur = q.poll();
				for (int d = 0; d < dir.length; d++) {
					int nx = cur[0] + dir[d][0];
					int ny = cur[1] + dir[d][1];
					if (nx > 0 && ny > 0 && nx <= M && ny <= N && !vis[nx][ny]) {
						vis[nx][ny] = true;
						volcano[nx][ny] = t + 1;
						q.add(new int[] { nx, ny });
					}
				}
			}
			// ��Ÿ�� ����
			// ���� �ð��� ������ �� �ִ� ȭ���� ������ q�� ����
			t += 1;
			c = list.get(t);
			for (int k = 0; k < c.size(); k++) {
				q.add(new int[] { c.get(k)[0], c.get(k)[1] });
				vis[c.get(k)[0]][c.get(k)[1]] = true;
			}

		}

	}
}
