package main.java.playground.algo.impl1;


import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class EventManager {
	
	private final int playerCount;
	private final Pair<Integer, Integer>[] gridDimensions;
	private final int[] streamWindows;
	
	private final PlayerStream[] playerStreams;
	
	
	public EventManager(int playerCount, Pair<Integer, Integer>[] gridDimensions, int[] streamWindows )
	{
		this.playerCount = playerCount;
		this.gridDimensions = gridDimensions; 
		this.streamWindows = streamWindows;
		
		playerStreams = new PlayerStream[playerCount];
		
		for(int i=0; i<playerCount; i++)
		{
			ArrayList<GridStream> gridStreams = new ArrayList<GridStream>(gridDimensions.length);
			for(int j=0; j<gridDimensions.length; j++)
			{
				ArrayList<LinkedBlockingQueue<PositionEvent>> windowedStreams = new ArrayList<LinkedBlockingQueue<PositionEvent>>(streamWindows.length);
				for(int k=0; k<streamWindows.length ; k++)
				{
					windowedStreams.add(new LinkedBlockingQueue<PositionEvent>());
				}
				gridStreams.add(new GridStream(windowedStreams));
			}
			playerStreams[i] = new PlayerStream(gridStreams);
			
		}	
					
	}

}
