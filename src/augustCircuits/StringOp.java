//$Id$
package augustCircuits;

import java.util.Scanner;

public class StringOp {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int N = Integer.parseInt(in.nextLine());
		int[] charFrequency = new int[26];
		int max = 0;
		if(N >=1 && N <= 100000){
			String str = in.nextLine();
			if(str.length() == N){
				for(char c: str.toCharArray()){
					charFrequency[c -'a']++;
					if(max < charFrequency[c -'a'])
						max = charFrequency[c - 'a'];
				}
			}
			System.out.println(str.length() - max);
		}
	}
}
