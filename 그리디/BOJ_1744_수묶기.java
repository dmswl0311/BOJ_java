package algo;

import java.io.*;
import java.util.*;

/**
 * 11540	76
 * @author CHO
 * @see https://www.acmicpc.net/problem/1744
 * @category �׸���
 */
public class BOJ_1744_������ {

	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder()); // ��� ��������
		PriorityQueue<Integer> minus = new PriorityQueue<>(); // ���� ��������
		boolean zero = false;
		for (int n = 0; n < N; n++) {
			int num = Integer.parseInt(br.readLine());
			if (num > 0)
				plus.add(num);
			else if (num < 0)
				minus.add(num);
			else
				zero = true;
		}
		int result = 0;
		while (!plus.isEmpty()) {
			int x = plus.poll();
			if(plus.isEmpty()) {
				// ���� �������̶��
				result+=x;
			}else {
				int y = plus.poll();
				if (x + y > (x * y)) {
					result += (x + y);
				}else result += (x * y);
			}
		}
		while (!minus.isEmpty()) {
			int x = minus.poll();
			if(minus.isEmpty()) {
				if(!zero) result+=x; //0�� ������ -�� ���ϴ� �ͺ��� 0�� ���ؼ� ���ϴ°��� �� ū ���ڸ� ���� �� �ֱ� ������
			}else {
				int y = minus.poll();
				if (x + y > (x * y)) {
					result += (x + y);
				}else result += (x * y);
			}
		}
		System.out.println(result);
	}
}
