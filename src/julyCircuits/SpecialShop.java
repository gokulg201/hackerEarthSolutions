//$Id$
package julyCircuits;

import java.util.Scanner;

//https://www.hackerearth.com/practice/algorithms/searching/linear-search/practice-problems/algorithm/special-shop-69904c91/
public class SpecialShop {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- >0){
			double n = in.nextLong();
			double a = in.nextLong();
			double b = in.nextLong();
			double x = Math.round(b*n/(a+b));
			double y = n - x;
			System.out.println((long)(a*x*x + b*y*y));
		}
	}
}
