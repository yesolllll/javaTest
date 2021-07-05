package chap05;

public class ImplCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		//4! -> 4*(4-1)*(3-1)*(2-1)
		long result = 1; //0으로 하면 xx
		long start = System.currentTimeMillis();
		for(long i=1; i<=num; i++) {
			result *= i;
		}
		long end = System.currentTimeMillis();
		System.out.println("for문:"+(end-start));
		return result;
	}

}
