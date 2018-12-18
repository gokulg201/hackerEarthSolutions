//$Id$
package julyCircuits;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EasySum {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for(int i = 0 ;i < n ;i++){
			a[i] = in.nextInt();
		}
		int m = in.nextInt();
		List<Integer> c = new ArrayList<Integer>();
		for(int i = 0 ;i < m ;i++){
			c.add(in.nextInt());
		}
		for(int i = 1;i < 100;i++){
			boolean ok = true;
			for(int j = 0 ;j < n ;j++){
				if(c.contains(i + a[j])){
					ok = true;
				}else{
					ok = false;
					break;
				}
			}
			if(ok){
				System.out.print(i);
				System.out.print(" ");
			}
		}
	}
}
