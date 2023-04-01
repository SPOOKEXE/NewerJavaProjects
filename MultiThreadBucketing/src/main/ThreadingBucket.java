package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class LowestRunnable implements Runnable {
	public int value = 0;
	public int min;
	public int max;
	
	public LowestRunnable(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public void run() {
		for (int n = min; n < max; n++) {
			this.value += n;
		}
	}
}

class MidRunnable implements Runnable {
	public static int thread_sep_amount = 1000000;
	public static int NThreads = 16;
	
	public int value = 0;
	public int min;
	public int max;
	
	public MidRunnable(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public void run() {
		
		int available_thread_amount = (int) Math.floor( (max - min) / thread_sep_amount );
		System.out.println(available_thread_amount);
		
		// not enough numbers to bother doing it in multiple threads
		if (available_thread_amount <= 1) { 
			for (int n = min; n < max; n++) {
				this.value += n;
			}
			return;
		}
		
		// enough numbers for multiple threads
		
		ExecutorService executor = Executors.newFixedThreadPool(NThreads);
		LowestRunnable[] runnables = new LowestRunnable[available_thread_amount-1];
		for (int i = 0; i < available_thread_amount-1; i++) {
			int minn = (int) (min + (thread_sep_amount * i));
			LowestRunnable worker = new LowestRunnable(minn, minn + thread_sep_amount);
			runnables[i] = worker;
			executor.execute(worker);
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (LowestRunnable worker : runnables) {
			this.value += worker.value;
		}
		
		System.out.println("Finished all threads");
		
	}
}

public class ThreadingBucket {

	// Fields //
	ExecutorService executor;
	boolean isRunning = false;
	
	// Constructors //
	public ThreadingBucket() {
		
	}
	
	// Methods //
	
}
