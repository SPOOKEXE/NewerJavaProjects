package main;

public class Main {

	public static void main(String[] args) {
		
		MidRunnable run = new MidRunnable(1, 900000000);
		run.run();
		System.out.println(run.value);
		
	}

}
