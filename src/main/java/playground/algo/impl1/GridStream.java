package playground.algo.impl1;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class GridStream {

	ArrayList<LinkedBlockingQueue<PositionEvent>> windowedStreams;
	
	public GridStream(ArrayList<LinkedBlockingQueue<PositionEvent>> windowedStreams)
	{
		this.windowedStreams = windowedStreams;
	}
	
	public void postEvent(PositionEvent posEvent)
	{
		for(int i=0; i<windowedStreams.size(); i++)
			try
			{
				windowedStreams.get(i).put(posEvent);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	
}
