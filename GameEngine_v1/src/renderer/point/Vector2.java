package renderer.point;

public class Vector2 {

	public int x;
	public int y;
	
	public Vector2() {
		this.x = this.y = 0;
	}
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static double dist( Vector2 p1, Vector2 p2 ) {
		return Math.sqrt( Math.pow( p1.x - p2.x , 2) + Math.pow( p1.y - p2.y , 2) );
	}
	
}
