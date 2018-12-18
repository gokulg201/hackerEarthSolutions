//$Id$
package rubiqueHack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerearth.com/challenge/competitive/rubique-finhack/algorithm/cibil-score/
 * @author gokul-4406
 *
 */
public class CIBIL {
	static class Customer{
		String name;
		int CIBIL;
		int RISK;
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		if(t>=0 && t<=100){
			while(t-- > 0){
				List<Customer> customers = new ArrayList<CIBIL.Customer>();
				int n = in.nextInt();
				while(n-- > 0){
					Customer customer = new Customer();
					customer.name = in.next();
					customer.CIBIL = Integer.parseInt(in.next());
					customer.RISK = Integer.parseInt(in.next());
					customers.add(customer);
				}
				int k = in.nextInt();
				if(k > 0 && k <= 1000){
					compute(k, customers);
				}
			}
		}
	}
	static void compute(int n, List<Customer> customers){
		if(n < customers.size()){
			Collections.sort(customers, new CustomerComparator());
			Customer cus  = customers.get(n - 1);
			System.out.println(cus.name+" "+cus.CIBIL);
		}
	}
	static class CustomerComparator implements Comparator<Customer>{
		@Override
		public int compare(Customer o1, Customer o2) {
			if(o1.CIBIL > o2.CIBIL){
				return -1;
			}else if(o1.CIBIL < o2.CIBIL){
				return 1;
			}else{
				if(o1.RISK < o2.RISK){
					return -1;
				}else if(o1.RISK > o2.RISK){
					return 1;
				}else{
					String s1 = o1.name;
					String s2 = o2.name;
					return s2.compareTo(s1);
				}
			}
		}
		
	}
}
