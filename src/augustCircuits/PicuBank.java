//$Id$
package augustCircuits;

import java.util.Scanner;

public class PicuBank {
	public static void main(StringOp[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i = 0; i < t;i++){
			long D = in.nextLong();
			long A = in.nextLong();
			long M = in.nextLong();
			long B = in.nextLong();
			long X = in.nextLong();
			X = X - D;
			long noOfMonths = 0;
			if(X > 0){
				if(B < A){
					if(A != 0 && B != 0){
						if(X == A*M){
							System.out.println(M);
							continue;
						}
						if(X < A*M){
							long x = X/A;
							noOfMonths = x;
							if(x*A < X) noOfMonths += 1;
							System.out.println(noOfMonths);
							continue;
						}
						if(X == A*M + B){
							System.out.println(M + 1);
							continue;
						}
						if(X < A*M + B){
							long x = X/A;
							noOfMonths = M;
							if(x*A < X) noOfMonths += 1;
							System.out.println(noOfMonths);
							continue;
						}
						long x = X / (A*M + B);
						long res =x*(A*M +B);
						if(res <= X){
							noOfMonths = x*M + x;
							long diff = X - res;
							if(diff != 0){
								if(diff < A*M){
									if(diff % A == 0)
										noOfMonths+= diff/A;
									else{
										noOfMonths+= (diff/A + 1);
									}
								}else{
									if(diff % A == 0)
										noOfMonths+= diff/A;
									else{
										noOfMonths+= (diff/A + 1);
									}
								}
							}
						}else{
							//It Should not come into this loop 
							
						}
					}else{
						if(A == 0){
							long x = X/B;
							noOfMonths = x*B;
							if(x*B < X) noOfMonths += 1;
						}else{
							long x = X/A;
							noOfMonths = x*A;
							if(x*A < X) noOfMonths += 1;
						}
					}
				}
			}
			System.out.println(noOfMonths);
		}
	}
}
