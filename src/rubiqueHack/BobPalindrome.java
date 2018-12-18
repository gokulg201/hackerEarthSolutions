//$Id$
package rubiqueHack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * https://www.hackerearth.com/challenge/competitive/rubique-finhack/algorithm/bob-and-palindrome-ways-b7da73ea/
 * @author gokul-4406
 *
 */
public class BobPalindrome {
	public static void main(String[] args){
		Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int i = 0;
		while(i < 26){
			int j = in.nextInt();
			List<Character> temp = new ArrayList<Character>();
			for(int k = j; k > 0 ;k--){
				temp.add(in.next().charAt(0));
			}
			map.put((char)(i + 'a'), temp);
			i++;
		}
		Set<String> resultSet = new HashSet<String>();
		for(Character ch : map.keySet()){
			Set<Character> keys = new HashSet<Character>();
			keys.addAll(map.get(ch));
			formPal(keys, ""+ch, map, n, resultSet);
		}
		int count = 0;
		for(String _str:resultSet){
			if(isPalindrome(_str)){
				count++;
			}
		}
		System.out.println(count);
	}
	static void formPal(Set<Character> keys, String str,Map<Character, List<Character>> map, int n,Set<String> resultSet){
		String _str = str;
		for(Character _ch: keys){
			if(n > str.length()){
				_str+=_ch;
				Set<Character> keySet = new HashSet<Character>();
				keySet.addAll(map.get(_ch));
				formPal(keySet, _str, map, n,resultSet);
			}else{
				resultSet.add(str);
			}
		}
	}
	static int NO_OF_CHARS = 256;
	static boolean isPalindrome(String str){
		// Create a count array and initialize all
	    // values as 0
	    int count[] = new int[NO_OF_CHARS];
	    Arrays.fill(count, 0);
	 
	    // For each character in input strings,
	    // increment count in the corresponding
	    // count array
	    for (int i = 0; i < str.length(); i++)
	    count[(int)(str.charAt(i))]++;
	 
	    // Count odd occurring characters
	    int odd = 0;
	    for (int i = 0; i < NO_OF_CHARS; i++) 
	    {
	    if ((count[i] & 1) == 1)
	        odd++;
	 
	    if (odd > 1)
	        return false;
	    }
	 
	    // Return true if odd count is 0 or 1,
	    return true;
	}
}
