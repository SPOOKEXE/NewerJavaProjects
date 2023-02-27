package renderer.debug;

import java.io.File;
import java.util.ArrayList;

import renderer.entities.IEntity;
import renderer.geometry.BoundingBox;
import renderer.geometry.Triangle;
import renderer.geometry.Vertex;
import renderer.math.CFrame;
import renderer.math.Vector3;
import renderer.raycast.Raycast;
import renderer.raycast.RaycastResult;

public class RenderingDebugOutput {

	public static File outputFile = new File("log_dump.txt");
	public static ArrayList<String> toOutput = new ArrayList<String>();
	
	public static boolean DumpIEntities = false;
	public static boolean DumpRaycasts = false;
	public static boolean DumpBoundBoxIntersectChecks = false;
	public static boolean DumpBoundBoxIntersectLineChecks = false;
	public static boolean DumpCFrames = false;
	public static boolean DumpLineTriangleIntersect = false;
	
//	public static double roundToDecimals( float input, int decimal_places ) {
//		double places = input * Math.pow(10, decimal_places);
//		return (double) (Math.floor(places) / places);
//	}
	
	public static void DumpToFile(boolean resetFile) {
		if (resetFile == true) {
			DebugOutput.ResetFile(RenderingDebugOutput.outputFile);
		}
		DebugOutput.DumpToFile(RenderingDebugOutput.toOutput, RenderingDebugOutput.outputFile);
	}
	
	public static void DumpLineTriangleIntersect(Triangle triangle, Vector3 raycastOrigin, Vector3 raycastDirection, boolean succeeded) {
		if (!DumpLineTriangleIntersect) {
			return;
		}
		
		Vertex v1 = triangle.v1;
		Vertex v2 = triangle.v2;
		Vertex v3 = triangle.v3;
		String dumpedBounds = String.format("TI(,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,)",
			v1.x, v1.y, v1.z,
			v2.x, v2.y, v2.z,
			v3.x, v3.y, v3.z,
			raycastOrigin.x, raycastOrigin.y, raycastOrigin.z,
			raycastDirection.x, raycastDirection.y, raycastDirection.z,
			succeeded
		);
		RenderingDebugOutput.toOutput.add(dumpedBounds);
	}
	
	public static void DumpIEntity(IEntity entity) {
		if (!DumpIEntities) {
			return;
		}
		
		Vector3 pos = entity.getPosition();
//		String dumpedEntity = String.format("E(%s)", pos);
//		DumpToFile(dumpedEntity);
		
		BoundingBox bounds = entity.getBounds();
		Vector3 bounds0 = bounds.p0.add(pos);
		Vector3 bounds1 = bounds.p1.add(pos);
		
		String dumpedBounds = String.format("BB(,%s,%s,%s,%s,%s,%s,)", bounds0.x, bounds0.y, bounds0.z, bounds1.x, bounds1.y, bounds1.z);
		RenderingDebugOutput.toOutput.add(dumpedBounds);
	}
	
	public static void DumpRaycast(Raycast raycast, RaycastResult result) {
		if (!DumpRaycasts) {
			return;
		}
		
		String dumpedRaycast = String.format("RC(,%s,%s,%s,%s,%s,%s,)",
			raycast.origin.x, raycast.origin.y, raycast.origin.z,
			raycast.direction.x, raycast.direction.y, raycast.direction.z
		);
		RenderingDebugOutput.toOutput.add(dumpedRaycast);
	}
	
	public static void DumpBoxIntersectCheck(Vector3 ray_origin, Vector3 direction, Vector3 B1, Vector3 B2) {
		if (!DumpBoundBoxIntersectChecks) {
			return;
		}
		
		String dumpedBoundIntrsect = String.format("BIC(,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,)",
			ray_origin.x, ray_origin.y, ray_origin.z,
			direction.x, direction.y, direction.z,
			B1.x, B1.y, B1.z,
			B2.x, B2.y, B2.z
		);
		RenderingDebugOutput.toOutput.add(dumpedBoundIntrsect);
	}
	
	public static void DumpBoxIntersectLineCheck(Vector3 ray_origin, Vector3 direction, boolean b) {
		if (!DumpBoundBoxIntersectLineChecks) {
			return;
		}
		
		String dumpedBoundLineIntrsect = String.format("BLIC(,%s,%s,%s,%s,%s,%s,%s,)",
			ray_origin.x, ray_origin.y, ray_origin.z,
			direction.x, direction.y, direction.z,
			b
		);
		RenderingDebugOutput.toOutput.add(dumpedBoundLineIntrsect);
	}

	public static void DumpCFrame(CFrame CF) {
		if (!DumpCFrames) {
			return;
		}
		
		String dumpedCFrame = String.format("CF(,%s,%s,%s,%s,%s,%s,)",
			CF.X, CF.Y, CF.Z,
			CF.LookVector.x, CF.LookVector.y, CF.LookVector.z
		);
		RenderingDebugOutput.toOutput.add(dumpedCFrame);
	}
	
}
