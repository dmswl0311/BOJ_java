import java.io.*;
import java.util.*;

/**
 * 11620	84
 * @author CHO
 * @see https://www.acmicpc.net/problem/16954
 * @category BFS
 * �ݷ� 
 * 	..###.##
	##...#.#
	..#.#..#
	#.#...#.
	.#...#.#
	.#.#..##
	#..#..#.
	..#....#
 * �޸� �ʰ� -> �湮ó�� x -> vis�迭 ���� ���� ���� ������ �ʱ�ȭ
 */
public class BOJ_16594_�����̴¹̷�Ż�� {
	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static StringTokenizer st;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }, };
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[8][8];
		for (int i = 0; i < 8; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = str.charAt(j);
			}
		} // �Է� �Ϸ�
		System.out.println(bfs(7, 0) == true ? 1 : 0);
	}

	private static boolean bfs(int i, int j) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(i, j));
		boolean[][] vis=new boolean[8][8];
		vis[i][j]=true;
//		map[i][j] = '*';
		
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Pos cur = q.poll();
				int x = cur.x;
				int y = cur.y;
				if (map[x][y] == '#')
					continue; 
				q.add(new Pos(x, y));
				for (int k = 0; k < 8; k++) {
					int nx = x + dir[k][0];
					int ny = y + dir[k][1];

					if (x == 0 && y == 7) {
						return true;
					}
					if (ok(nx, ny) && map[nx][ny] == '.' && !vis[nx][ny]) {
//						map[nx][ny] = '*';
						vis[nx][ny]=true;
						q.add(new Pos(nx, ny));
					}
				}
			} // �� ���� ����
			for (int xx = 7; xx >= 0; xx--) {
				for (int yy = 0; yy < 8; yy++) {
					int x = xx + 1;
					int y = yy;
					if (ok(x, y) && map[xx][yy] == '#') {
						map[x][y] = '#';
					}
					map[xx][yy] = '.';
				}
			} // �� ������
			vis=new boolean[8][8]; // �� �� ���� ������ �ʱ�ȭ 
		}
		return false;
	}

	private static boolean ok(int x, int y) {
		return x >= 0 && y >= 0 && x < 8 && y < 8;
	}
}
