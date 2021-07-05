package app;

import chap05.Calculator;
import chap05.ExCalculator;
import chap05.ImplCalculator;

public class MainCalculator {

	public static void main(String[] args) {
		Calculator cal = new ExCalculator(new ImplCalculator());
		long r = cal.factorial(10);
		System.out.println(r);
	}

}
