package main.java.playground.algo.impl1;

import java.util.ArrayList;


public class PlayerStream {

	ArrayList<GridStream> gridStreams;
	
	public PlayerStream(ArrayList<GridStream> gridStreams)
	{
		this.gridStreams = gridStreams;
	}

	public void processEvent(PositionEvent posEvent)
	{
		for(int i=0; i<gridStreams.size(); i++)
			gridStreams.get(i).postEvent(posEvent);
			
	}
}
