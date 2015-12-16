package com.example.helloworld.mediademo;

public class Test {
	MainActivity.A a = new MainActivity.A();
	MainActivity.C c = new MainActivity().new C();
	B b;
	
	public static void main(String[] args) {
		Class clazzA = MainActivity.A.class;
		Class clazzC = MainActivity.C.class;
		new MainActivity.A().fn1();
		MainActivity.A.fn2();
	}
}
