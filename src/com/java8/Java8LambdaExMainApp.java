package com.java8;

public class Java8LambdaExMainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MathOperation addition = (int a, int b) -> (a+b);
		MathOperation substraction = (a,b) -> (a-b);
		
		MathOperation multiplication = (int a, int b) -> {return (a*b); };
		MathOperation division = (int a, int b) -> (a/b);
	}
	
	interface MathOperation{
		int Operation(int a, int b);
	}
	
	interface GreetingService{
		void sayMessage(String Message);
	}

	private int operate(int a, int b, MathOperation mathOperation){
		return mathOperation.Operation(a, b);
	}
}
