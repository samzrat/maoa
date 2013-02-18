package playground.algo.impl1;


import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class EventManager {
	
	private final int playerCount;
	private final ArrayList<Pair<Integer, Integer>> gridDimensions;
	private final int[] streamWindows;
	
	private final PlayerStream[] playerStreams;
	
	
	public EventManager(int playerCount, ArrayList<Pair<Integer, Integer>> gridDimensions, int[] streamWindows )
	{
		this.playerCount = playerCount;
		this.gridDimensions = gridDimensions; 
		this.streamWindows = streamWindows;
		
		playerStreams = new PlayerStream[playerCount];
		
		for(int i=0; i<playerCount; i++)
		{
			ArrayList<GridStream> gridStreams = new ArrayList<GridStream>(gridDimensions.size());
			for(int j=0; j<gridDimensions.size(); j++)
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
	
	public static void main( String[] args )
    {
        System.out.println( "Booting Event Manager" );
        
        ArrayList<Pair<Integer, Integer>> gridDimensions = createGridDimensions();
        int[] streamWindows = new int[]{1, 5, 10, 40};
        
        EventManager eventManager = new EventManager(14, gridDimensions, streamWindows );
    }
	
	private static ArrayList<Pair<Integer, Integer>> createGridDimensions()
	{
		ArrayList<Pair<Integer, Integer>> gridDimensions = new ArrayList<Pair<Integer, Integer>>(4);
        gridDimensions.add(new Pair<Integer, Integer>(8, 13));
        gridDimensions.add(new Pair<Integer, Integer>(16, 25));
        gridDimensions.add(new Pair<Integer, Integer>(32, 50));
        gridDimensions.add(new Pair<Integer, Integer>(64, 100));
        
        return gridDimensions;
	}

}
