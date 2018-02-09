package com.corejava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 *  
		 *  [abc]	      	a, b, or c (simple class)
		 *  [^abc]	      	Any character except a, b, or c (negation)
		 *  [a-zA-Z]	  	a through z or A through Z, inclusive (range)
		 *  [a-d[m-p]]	  	a through d, or m through p: [a-dm-p] (union)
		 *  [a-z&&[def]]	d, e, or f (intersection)
		 *  [a-z&&[^bc]]	a through z, except for b and c: [ad-z] (subtraction)
		 *  [a-z&&[^m-p]]	a through z, and not m through p: [a-lq-z](subtraction)
		 *  
		 *  X?	    X occurs once or not at all
			X+	    X occurs once or more times
			X*	    X occurs zero or more times
			X{n}	X occurs n times only
			X{n,}	X occurs n or more times
			X{y,z}	X occurs at least y times but less than z times
			
			.		Any character (may or may not match terminator)
			\d		Any digits, short of [0-9]
			\D		Any non-digit, short for [^0-9]
			\s		Any whitespace character, short for [\t\n\x0B\f\r]
			\S		Any non-whitespace character, short for [^\s]
			\w		Any word character, short for [a-zA-Z_0-9]
			\W		Any non-word character, short for [^\w]
			\b		A word boundary
			\B		A non word boundary
			
		 */

		/*//1st way  
		Pattern pttrn = Pattern.compile(".s");//. represents single character  
		Matcher mchr = pttrn.matcher("as");  
		boolean b1 = mchr.matches();  
		  
		//2nd way  
		boolean b2=Pattern.compile(".s").matcher("as").matches();  
		  
		//3rd way  
		boolean b3 = Pattern.matches(".s", "as");  
		  
		System.out.println(b1+" "+b2+" "+b3); */ 
		
		/*System.out.println(Pattern.matches("[a-zA-Z0-9]*", "Jamshed")); // TRUE for [a-zA-Z0-9]{+}, [a-zA-Z0-9]*, [a-zA-Z0-9]{0,}
		System.out.println(Pattern.matches("[+][0-9]{2}[-][0-9]{10}", "+91 9862638970"));
		System.out.println(Pattern.matches("[a-zA-Z0-9]*[.][a-zA-Z0-9]*[@][a-zA-Z]*[.][a-zA-Z]*", "jamshed.ansari@gmail.moc"));
		
		System.out.println(Pattern.matches("[789][0-9]{9}", "6953038949"));//false (starts from 6)  
		System.out.println(Pattern.matches("[789][0-9]{9}", "8853038949"));//true  
		System.out.println(Pattern.matches("[789]\\d{9}", "8853038949"));//true  
		
		System.out.println(Pattern.matches("[789]{1}\\d{9}", "3853038949"));//false (starts from 3)  
		*/		
		System.out.println(Pattern.matches("^[0-9]{5}-(NEU|PHY|GEN)$", "12345-NEU"));
		System.out.println(Pattern.matches("[0-9]{5}-(NEU|PHY|GEN)", "12345-NEU"));
	}

}
