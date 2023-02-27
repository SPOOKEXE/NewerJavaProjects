package renderer;

import renderer.math.CFrame;
import renderer.math.Vector3;

public class Main {

	public static void main(String[] args) {
		Vector3 origin = new Vector3(0, 5, 0);
		System.out.println(origin);
		CFrame originCFrame = new CFrame(origin);
		System.out.println(originCFrame);
		CFrame rotationCFrame = CFrame.Angles( Math.toRadians(90), 0, 0);
		System.out.println(rotationCFrame);
		CFrame newOrigin = originCFrame.mult(rotationCFrame);
		System.out.println(newOrigin);
	}

}
