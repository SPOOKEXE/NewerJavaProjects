package renderer.math;

public class CFrame {

	private Vector3 position;
	private Quaternion quaternion;
	
	public CFrame() {
		this.position = new Vector3();
		this.quaternion = Quaternion.IDENTITY;
	}
	
	public CFrame(Vector3 Position) {
		this.position = Position;
		this.quaternion = Quaternion.IDENTITY;
	}
	
	public CFrame(Vector3 Position, Vector3 LookAt) {
		this.position = Position;
		this._LookAt(LookAt, new Vector3(0, 1, 0));
	}
	
	public CFrame(Vector3 Position, Vector3 LookAt, Vector3 up) {
		this.position = Position;
		this._LookAt(LookAt, up);
	}
	
	// Private Methods //
	private void _LookAt(Vector3 LookAt, Vector3 up) {
		float dot = (float) Vector3.dot(this.position, LookAt);
		if (Math.abs(dot - (-1.0f)) < 0.000001f) {
			this.quaternion = Quaternion.fromAxisAndAngle(up, MathUtility.toRadians(180.0f));
		} else if (Math.abs(dot - (1.0f)) < 0.000001f) {
			this.quaternion = Quaternion.IDENTITY;
		} else {
			float rotAngle = (float)Math.acos(dot);
			Vector3 rotAxis = Vector3.cross(this.position, LookAt);
			rotAxis = rotAxis.normalize();
			this.quaternion = Quaternion.fromAxisAndAngle(rotAxis, rotAngle);
		}
	}
	
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	public void setDirection(Vector3 lookAt) {
		this._LookAt(lookAt, new Vector3(0, 1, 0));
	}
	
	public void setDirection(Vector3 lookAt, Vector3 up) {
		this._LookAt(lookAt, up);
	}
	
	public Vector3 getPosition() {
		return this.position;
	}
	
	public Vector3 getDirection() {
		return new Vector3(this.quaternion.getVectorPart());
	}
	
	@Override
	public String toString() {
		String vectorString = String.format("%f, %f, %f", this.position.x, this.position.y, this.position.z);
		float[] mat = this.quaternion.toMatrix();
		String quaternionString = String.format("%f, %f, %f, %f, %f, %f, %f, %f, %f", mat[0], mat[1], mat[2], mat[3], mat[4], mat[5], mat[6], mat[7], mat[8], mat[9], mat[10], mat[11]);
		return String.format("CFrame(%s, %s)", vectorString, quaternionString);
	}
	
}
