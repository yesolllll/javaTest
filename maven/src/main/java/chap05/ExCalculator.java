package chap05;

public class ExCalculator implements Calculator {
	private Calculator delegrate;
	
	public ExCalculator(Calculator delegrate) {
		this.delegrate =  delegrate; 
	}
	@Override
	public long factorial(long num) {
		long start =System.nanoTime();
		long result = delegrate.factorial(num); //프록시라고 부름
		long end = System.nanoTime();
		System.out.println("Exe:"+(end-start));
		return 0;
	}

}
