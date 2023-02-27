package renderer.entity;

import java.util.List;

import renderer.primitives.Triangle;

public interface IEntity {

	void init();
	void update();
	
	List<Triangle> getTriangles();
	
	String toString();
}
