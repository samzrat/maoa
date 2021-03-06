package playground.algo.impl1;


public class PositionEvent 
{
	final String sid; //sensor id
	final long x, y, z; // position in mm
	final long speed;  //micrometer/sec
	final long vx, vy, vz; //direction vector with size of 10,000
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
	
	public PositionEvent(String sid, long x, long y, long z, long speed, long vx, long vy, long vz)
	{
		this.sid = sid;
		this.x = x;
		this.y = y;
		this.z = z;
		this.speed = speed;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}

}
