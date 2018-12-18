//$Id$
package septemberCircuitsEasy;

import java.util.Scanner;
/**
 * https://www.hackerearth.com/problem/algorithm/pair-recovery-f27a26a4/
 * @author gokul-4406
 *
 */
public class PairRecovery {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int a3 = in.nextInt();
		int a4 = in.nextInt();
		int a2 = a4 - a3;
		int a1 = a3 - a2;
		System.out.println(a1+" "+a2);
	}
}
