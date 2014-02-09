package playground.algo.impl1;

public class Grid {
	
	int[] streamWindows;
	QueryProcessor[] queryProcessors;
	
	public Grid(int[] streamWindows)
	{
		this.streamWindows = streamWindows;
		queryProcessors = new QueryProcessor[streamWindows.length];
		
		for(int i=0; i<queryProcessors.length; i++)
		{
			queryProcessors[i] = new QueryProcessor(/*null,*/ null, streamWindows[i]);
		}
	}


	public void processInputEvent(PositionEvent posEvent)
	{
		for(QueryProcessor queryProcessor: queryProcessors)
			queryProcessor.processInputEvent(posEvent);
	}

}
