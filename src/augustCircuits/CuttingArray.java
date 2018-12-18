//$Id$
package augustCircuits;

import java.util.Scanner;

public class CuttingArray {
	static void findMax(int A[], int n, int k) {
		  int M[][] = new int[n+1][k+1];
		  int cum[] = new int[n+1];
		  for (int i = 1; i <= n; i++)
		    cum[i] = cum[i-1] + A[i-1];
		 
		  for (int i = 1; i <= n; i++)
		    M[i][1] = cum[i];
		  for (int i = 1; i <= k; i++)
		    M[1][i] = A[0];
		 
		  for (int i = 2; i <= k; i++) {
		    for (int j = 2; j <= n; j++) {
		      int best = Integer.MAX_VALUE;
		      for (int p = 1; p <= j; p++) {
		        best = Integer.min(best, Integer.max(M[p][i-1], cum[j]-cum[p]));
		      }
		      M[j][i] = best;
		    }
		  }
		  int[] cut = new int[k];
		  int result = M[n][k];
		  int pos = k - 2;
		  int i = k;
		  int j = n;
		  while (i > 1 && j > 0){
			  for(int p = j ; p >= 1;p--){
				  if(cum[j] - cum[p] == result){
					 cut[pos--] = p + 1;
					 j = p;
					 i = i - 1;
					 result = M[j][i];
					 break;
				  }else if(M[p][i-1] == result){
					  cut[pos--] = p + 1;
					  j = p;
					  i = i - 1;
					  result = M[j][i];
					  break;
				  }
			  }
		  }
		  for(int x = 0;x < cut.length;x++){
			  System.out.print(cut[x]+" ");
		  }
		}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for(int i = 0 ;i<n;i++){
			arr[i] = in.nextInt();
		}
		int k = in.nextInt();
		if(k <= Math.floor(n/2)){
			findMax(arr, n, k + 1);
		}
	}
}
