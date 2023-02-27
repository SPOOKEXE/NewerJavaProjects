package renderer.debug;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import renderer.camera.ICamera;
import renderer.math.Vector3;
import renderer.primitives.Mesh;
import renderer.primitives.Quad;
import renderer.primitives.Triangle;
import renderer.raycast.RaycastParams;
import renderer.raycast.RaycastResult;

public class DebugDump {

	public static ArrayList<String> DUMP_DATA = new ArrayList<String>();
	
	public static String TriangleToString(Triangle tri) {
		return String.format("T(%s,%s,%s)", tri.v1, tri.v2, tri.v3);
	}
	
	public static String QuadToString(Quad quad) {
		Triangle[] tris = quad.trianglify();
		return String.format("Q(%s,%s)", TriangleToString(tris[0]), TriangleToString(tris[1]));
	}
	
	public static String MeshToString(Mesh mesh) {
		ArrayList<String> TriangleStringArray = new ArrayList<String>();
		for (Triangle tri : mesh.triangles) {
			TriangleStringArray.add(TriangleToString(tri));
		}
		return String.format("Msh(%s)", String.join(",", TriangleStringArray));
	}
	
	public static String RaycastParamsToString(RaycastParams params) {
		return String.format("RayParams(%d,%s)", params.Meshes.size(), params.RaycastType.toString());
	}
	
	public static void DumpVector3List( Vector3[][] vectors ) {
		//ArrayList<ArrayList<Vector3>> vectorList = new ArrayList<ArrayList<Vector3>>();
		for (int index = 0; index < vectors.length; index++) {
			//ArrayList<Vector3> innerVectorList = new ArrayList<Vector3>();
			for (int index2 = 0; index2 < vectors[index].length; index2++) {
				//innerVectorList.add(vectors[index][index2]);
				DUMP_DATA.add(vectors[index][index2].toString());
			}
			//vectorList.add(innerVectorList);
;		}
		
	}
	
	public static String RaycastResultToString(RaycastResult result) {
		if (result == null) {
			return "Result(null)";
		}
		return String.format(
			"RayResult(%s,%s,%s,%s)",
			MeshToString(result.Mesh),
			result.Position.toString(),
			result.Normal.toString(),
			TriangleToString(result.Triangle)
		);
	}
	
	public static void DumpRaycastAttempt( Vector3 origin, Vector3 direction, RaycastParams params, RaycastResult result ) {
		String raycastString = String.format("Ray%d,%d,%d,%d,%d,%d,%s", (int) origin.x, (int) origin.y, (int) origin.z, (int) direction.x, (int) direction.y, (int) direction.z, RaycastResultToString(result));
		DUMP_DATA.add(raycastString);
//		ArrayList<String> strArray = new ArrayList<String>();
//		strArray.add(origin.toString());
//		strArray.add(direction.toString());
//		strArray.add(RaycastParamsToString(params));
//		strArray.add(RaycastResultToString(result));
//		DUMP_DATA.add(String.format("Ray(%s)", String.join(",", strArray)));
	}
	
	public static void DumpMeshes( List<Mesh> meshes ) {
		ArrayList<String> strArray = new ArrayList<String>();
		for (Mesh mesh : meshes) {
			strArray.add( MeshToString(mesh) );
		}
		DUMP_DATA.add(String.join("\n", strArray));
	}
	
	public static void DumpCamera( ICamera camera ) {
		ArrayList<String> strArray = new ArrayList<String>();
		strArray.add(camera.getPosition().toString());
		strArray.add(camera.getDirection().toString());
		strArray.add(camera.getResolution().toString());
		// strArray.add(String.format("FOV(%s)", camera.getFOV()));
		DUMP_DATA.add(String.format("Cam(%s)", String.join(",", strArray)));
	}
	
	public static void DumpImageToFile(BufferedImage bufferedImage) {
		File outputfile = new File("image.png");
		try {
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(outputfile.getAbsoluteFile());
	}
	
	public static void DumpAllToFile() {
		File outputfile = new File("output_dump.txt");
		try {
			FileOutputStream out = new FileOutputStream(outputfile);
			out.write(String.join("\n", DUMP_DATA).getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
