package renderer.lib;

import renderer.math.Utility;
import renderer.math.Vector3;

public class Camera {
	
	// Fields //
	public Vector3 origin;
	public Vector3 horizontal;
	public Vector3 vertical;
	public Vector3 lower_left_corner;
	
	public Vector3 u;
	public Vector3 v;
	public Vector3 w;
	public float lens_radius;
	
	// Constructors //
	public Camera(Vector3 origin, Vector3 lookAt, Vector3 up, float vfov, float aspect_ratio, float aperture, float focus_distance) {
		this.setFromValues(origin, lookAt, up, vfov, aspect_ratio, aperture, focus_distance);
	}
	
	// Methods //
	public void setFromValues(Vector3 origin, Vector3 lookAt, Vector3 up, float vfov, float aspect_ratio, float aperture, float focus_distance) {
		float theta = Utility.degrees_to_radians(vfov);
		float h = (float) Math.tan(theta / 2.0f);
		float viewport_height = 2.0f * h;
		float viewport_width = aspect_ratio * viewport_height;
		
		this.w = origin.sub(lookAt).unit();
		this.u = up.cross(w).unit();
		this.v = w.cross(u);
		
		this.origin = origin;
		this.horizontal = u.mult(focus_distance * viewport_width);
		this.vertical = v.mult(focus_distance * viewport_height);
		this.lower_left_corner = origin.sub( horizontal.mult(0.5f) ).sub( vertical.mult(0.5f) ).sub( w.mult(focus_distance) );

		this.lens_radius = (aperture / 2f);
	}
	
	public Ray getRay(float s, float t) {
		Vector3 rd = Utility.random_in_unit_disk().mult(lens_radius);
		Vector3 offset = u.mult(rd.x).add(v.mult(rd.y));

        return new Ray(
            origin.add(offset),
            lower_left_corner.add(horizontal.mult(s)).add(vertical.mult(t)).sub(origin).sub(offset)
        );
	}
	
}
