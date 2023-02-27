package renderer.math;

public class Vector2 {

	public float x, y;

	//OBJECT-BASED //
	public Vector2( float x, float y ) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2() {
		this.x = this.y = 0;
	}
	
	// OBJECT METHODS //
	public Vector2 add( Vector2 other ) {
		return new Vector2(this.x + other.x, this.y + other.y);
	}
	
	public Vector2 sub( Vector2 other ) {
		return new Vector2(this.x - other.x, this.y - other.y);
	}
	
	public Vector2 mult( Vector2 other ) {
		return new Vector2(this.x * other.x, this.y * other.y);
	}
	
	public Vector2 mult( float n ) {
		return new Vector2(this.x * n, this.y * n);
	}
	
	public Vector2 div( Vector2 other ) {
		return new Vector2(this.x / other.x, this.y / other.y);
	}
	
	public Vector2 div( float n ) {
		return new Vector2(this.x / n, this.y / n);
	}
	
	public float dist( Vector2 other ) {
		return this.sub(other).mag();
	}
	
	public float mag() {
		return (float)Math.sqrt(this.dot(this));
	}
	
	public Vector2 unit() {
		float mag = this.mag();
		return new Vector2(this.x / mag, this.y / mag);
	}
	
	public float dot( Vector2 v2 ) {
		return (this.x * v2.x) + (this.y * v2.y); 
	}
	
	@Override
	public String toString() {
		return String.format("Vector2(%d,%d)", Math.round(this.x), Math.round(this.y));
	}
	
}
