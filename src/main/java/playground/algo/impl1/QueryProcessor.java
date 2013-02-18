package playground.algo.impl1;

import java.util.concurrent.LinkedBlockingQueue;

public class QueryProcessor implements Runnable {
	
	LinkedBlockingQueue<PositionEvent> inputEventQueue = new LinkedBlockingQueue<PositionEvent>();
	Thread runner;
	QuerySink querySink;
	int window;
	
	public QueryProcessor(/*String threadName, */QuerySink querySink, int window)
	{
		this.querySink = querySink;
		runner = new Thread(this/*, threadName*/); // (1) Create a new thread.
		System.out.println(runner.getName());
		runner.start(); // (2) Start the thread.
	}
	
	public void processInputEvent(PositionEvent posEvent)
	{
		try {
			inputEventQueue.put(posEvent);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		for(;;)
		{
			try {
				PositionEvent positionEvent = inputEventQueue.take();
				System.out.println("Thread= " + Thread.currentThread() + " Event= " + positionEvent.toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	

}
