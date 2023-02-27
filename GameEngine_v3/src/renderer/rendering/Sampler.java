package renderer.rendering;

import java.awt.Color;

import renderer.camera.ICamera;
import renderer.color.ColorManager;
import renderer.debug.RenderingDebugOutput;
import renderer.math.CFrame;
import renderer.math.Vector2;
import renderer.raycast.Raycast;
import renderer.raycast.RaycastManager;
import renderer.raycast.RaycastResult;
import renderer.world.IWorld;

public class Sampler {

	public static RaycastResult doRaycast( IWorld world, CFrame rayCFrame ) {
		Raycast _raycast = new Raycast(rayCFrame.Position, rayCFrame.LookVector);
		RaycastResult result = RaycastManager.ResolveRaycast(world, _raycast);
		
		RenderingDebugOutput.DumpBoxIntersectLineCheck(_raycast.origin, _raycast.direction, result != null);
		RenderingDebugOutput.DumpRaycast(_raycast, result);
		
		return result;
	}
	
//	public static BaseMaterial getPixelMaterial( IWorld world, ICamera camera, Vector2 offset) {
//		CFrame cameraCFrame = camera.getCFrame();
//		Vector2 resolution = camera.getResolution();
//		
//		CFrame offsetCF = cameraCFrame.mult( CFrame.Angles(0, 0, 0) );
//		RaycastResult result = getPixelResult(world, offsetCF);
//		
//		if (result != null) {
//			return result.Triangle.material;
//		}
//		return new BaseMaterial();
//	}

	public static Color getPixelShadeColor(IWorld world, ICamera camera, Vector2 offset) {
		CFrame cameraCFrame = camera.getCFrame();
		Vector2 cameraResolution = camera.getResolution();

		float halfFOV = -(camera.getFOV() / 2);
		
		float thetaY = camera.getFOV() * (offset.x / cameraResolution.x); // horizontal
		float thetaXZ = camera.getFocalLength() * (offset.y / cameraResolution.y); // vertical
		
		CFrame rotationCFrame = CFrame.Angles(
			Math.toRadians( halfFOV + thetaXZ ),
			Math.toRadians( halfFOV + thetaY ),
			Math.toRadians( halfFOV + thetaXZ )
		);
		
		CFrame offsetCF = cameraCFrame.mult(rotationCFrame);
		
//		System.out.println(cameraCFrame.X + " - " + cameraCFrame.Y + " - " +  cameraCFrame.Z);
//		System.out.println(cameraCFrame.Position);
//		System.out.println(offsetCF.X + " - " + offsetCF.Y + " - " +  offsetCF.Z);
//		System.out.println(offsetCF.Position);
		
//		System.out.println(offsetCF.Position + " || " + offsetCF.LookVector);
		
		RenderingDebugOutput.DumpCFrame(offsetCF);
		
		RaycastResult result = doRaycast(world, offsetCF);
		if (result != null) {
			System.out.println(result);
			return ColorManager.getShade(result.Triangle.material.color, Math.abs(result.Normal.z));
		}
		return Color.black;
	}
	
}
