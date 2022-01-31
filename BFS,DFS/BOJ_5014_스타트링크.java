import java.io.*;
import java.util.*;

/**
 * 59828	156
 * @author CHO
 * @see https://www.acmicpc.net/problem/5014
 * @category BFS
 */
public class BOJ_5014_��ŸƮ��ũ {
	 
	static StringTokenizer st;
	static int F,S,G,U,D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken()); //��ü ����
		S = Integer.parseInt(st.nextToken()); //���� ��ġ
		G = Integer.parseInt(st.nextToken()); //���� ��ġ
		U = Integer.parseInt(st.nextToken()); //���� U�� ���� ��ư
		D = Integer.parseInt(st.nextToken()); //�Ʒ��� D�� ���� ��ư
		
		bfs(S);
	}

	private static void bfs(int g) {
		Queue<int[]> q=new LinkedList<int[]>();
		q.add(new int[] {g,0});
		boolean[] vis=new boolean[F+1];
		vis[g]=true;
		
		while(!q.isEmpty()) {
			int[] cur=q.poll();
			int floor=cur[0]; //���� ��ġ
			int cnt=cur[1]; //��� ����������
			if(floor==G) {
				System.out.println(cnt);
				return;
			}
			if(floor+U<=F && !vis[floor+U]) {
				vis[floor+U]=true;
				q.add(new int[] {floor+U,cnt+1});
			}
			if(floor-D>=1 && !vis[floor-D]) {
				vis[floor-D]=true;
				q.add(new int[] {floor-D,cnt+1});
			}
		}
		System.out.println("use the stairs");
	}
}
