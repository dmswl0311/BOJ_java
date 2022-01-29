import java.io.*;
import java.util.*;

/**
 * 13940	116
 * @author CHO
 * @see https://www.acmicpc.net/problem/16472
 * @category �� ������
 */
public class BOJ_16472_����� {

	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine(); // �Է¿Ϸ�
		int[] vis = new int[26];
		int cnt = 0;
		int max = 0;
		if (str.length() == 1) {
			System.out.println(1);
		} else {
			int start=0;
			vis[str.charAt(start)-97]+=1;
			cnt+=1;
			for (int j = 1; j < str.length(); j++) {
				int next = str.charAt(j) - 97;
				if (vis[next] == 0 && cnt + 1 <= N) {
					// ������� ���� ���ĺ�
					vis[next] += 1;
					cnt += 1;
					max = max < j - start+1 ? j - start+1 : max;
				} else if (vis[next] > 0) {
					// ����� ���ĺ�
					vis[next] += 1;
					max = max < j - start+1 ? j - start+1 : max;
				} else {
					// cnt>N�� ���
					int tmp=start; //���� ��ġ 
					while(true) {
						vis[str.charAt(tmp)-97]-=1;
						if(vis[str.charAt(tmp)-97]==0) cnt--;
						if(cnt<N) break;
						tmp++;
					}
					max = max < j - start ? j - start : max;
					start = tmp+1;
					vis[str.charAt(j)-97]+=1;
					cnt+=1;
				}
			}
			System.out.println(max);
		}

	}
}
