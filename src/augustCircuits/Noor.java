//$Id$
package augustCircuits;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Noor {
	static class Pair implements Comparable<Pair>{
		int s;
		int e;
		public Pair(int s,int e) {
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Pair p) {
			if(this.s < p.s){
				return -1;
			}else if(this.s > p.s){
				return 1;
			}else{
				return 0;
			}
		}
	}
	static class PairComparator implements Comparator<Pair>{
		@Override
		public int compare(Pair o1, Pair o2) {
			return o1.compareTo(o2);
		}
		
	}
	static void find(List<Pair> fishes){
		for(Pair fish : fishes){
			find(fishes);
		}
	}
	static void updateFreq(int n, int[] freq){
		while(--n >= 0){
			freq[n]++;
		}
	}
	static void updateEatability(int[][] eatability){
		for(int i = 0;i < eatability.length;i++){
			int sum = 0;
		}
	}
	static int count(int e, int[][] eatability){
		int count = 0;
		for(int i = 0;i<=e;i++){
			for(int j = e+1; j < eatability.length;j++){
				count+=eatability[i][j];
			}
		}
		return count;
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i = 0;i < t;i++){
			int n = in.nextInt();
			List<Pair> fishes = new ArrayList<Noor.Pair>();
			int[][] inp = new int[n][2];
			int[][] eatability = new int[n][n];
			for(int j = 0 ;j < n ;j++){
				int s = in.nextInt();
				int e = in.nextInt();
				inp[j][0] = s;
				inp[j][1] = e;
				eatability[s][e] = 1;
//				List<Integer> list = new ArrayList<Integer>();
//				fishes.add(new Pair(s, e));
//				fishes.sort(new PairComparator());
			}
//			for(Pair fish:fishes){
//				//find the possible fishes for this pair
//				List<Pair> revisedList = new ArrayList<Noor.Pair>();
//				revisedList.addAll(fishes);
//				find(fishes);
//			}
			int max = 0;
			for(int k = 0;k < inp.length;k++){
				int count = count(inp[k][1], eatability);
				if(count > max)
					max = count;
			}
			System.out.println(max);
		}
	}
}
