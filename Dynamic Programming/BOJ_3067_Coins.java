package algo;

import java.io.*;
import java.util.*;

/**
 * 11588	84
 * @author CHO
 * @see https://www.acmicpc.net/problem/3067
 * @category DP
 */
public class BOJ_3067_Coins {
	
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] list=new int[N];
			st=new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				list[n]=Integer.parseInt(st.nextToken());
			}
			int m = Integer.parseInt(br.readLine());
			int[] dp=new int[m+1];// �Է� �Ϸ�
			
			dp[0]=1;
			for (int i = 0; i < N; i++) {
				int n=list[i];
				for (int j = n; j < m+1; j++) {
					dp[j]=dp[j-n]+dp[j];
				}
			}
			sb.append(dp[m]+"\n");
		}
		System.out.println(sb);
	}
}

