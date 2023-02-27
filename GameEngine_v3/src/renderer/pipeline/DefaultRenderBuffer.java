package renderer.pipeline;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import renderer.camera.ICamera;
import renderer.debug.PipelineDebugOutput;
import renderer.entities.IEntity;
import renderer.geometry.BoundingBox;
import renderer.intersect.IntersectManager;
import renderer.math.Vector3;
import renderer.utility.SortingAlgorithms;
import renderer.world.IWorld;

public class DefaultRenderBuffer {
	public static final float EPSILON_SIZE = 0.01f; // Once the size's magnitude is smaller than this, it is not drawn on the image.
	
	public IWorld world;
	public ICamera camera;
	public HashMap<BoundingBox, IEntity> boundsToEntity;
	
	// Constructor //
	public DefaultRenderBuffer(IWorld world, ICamera camera) {
		this.world = world;
		this.camera = camera;
		this.boundsToEntity = new HashMap<BoundingBox, IEntity>();
		this.update();
	}
	
	// Methods //
	public BufferedImage drawEntitiesToImage(BufferedImage image) {
		ArrayList<IEntity> ordered_bufferEntities = new ArrayList<IEntity>();
		ArrayList<Vector3> ordered_zBufferOffset = new ArrayList<Vector3>();
		HashMap<IEntity, Vector3> entityToZOffsetMap = new HashMap<IEntity, Vector3>();
		
		// Draw the entities on the buffered image after doing manipulations
		for (Map.Entry<BoundingBox, IEntity> entry : this.boundsToEntity.entrySet()) {
			//BoundingBox entityBox = entry.getKey();
			IEntity entityI = entry.getValue();
			Vector3 zOffset = camera.getPosition().sub(entityI.getPosition());
			ordered_zBufferOffset.add(zOffset);
			ordered_bufferEntities.add(entityI);
			entityToZOffsetMap.put(entityI, zOffset);
		}
		
		SortingAlgorithms.SortIEntityByZBufferOffsets(ordered_bufferEntities, ordered_zBufferOffset);
//		System.out.println("AFTER SORT");
//		System.out.println(Arrays.toString(bufferEntities.toArray()));
//		System.out.println(Arrays.toString(zBufferOffset.toArray()));
		
		PipelineDebugOutput.DUMP_SORTED_IENTITIES(ordered_bufferEntities);
		
//		Graphics imgGraphics = image.getGraphics();
//		for (IEntity entity : ordered_bufferEntities) {
//			for (Triangle tri : entity.getTriangles()) {
//				// draw each triangle after scaling (make it smaller the further it is, epsilon value)
//			}
//		}
		
		// Return the buffered image
		return image;
	}
	
	public void update() {
		ArrayList<IEntity> toAddEntities = new ArrayList<IEntity>( (int) (world.getEntities().size() / 2 ) );
		
		// For all entities in the world
		for (IEntity entity : world.getEntities()) {
			BoundingBox bounds = entity.getBounds();
			// if it is already in the render arraylist, skip it
			if (boundsToEntity.containsKey(bounds)) {
				continue;
			}
			// if its within the FOV, add it to the render queue
			if (IntersectManager.IsBoxWithinCameraFOV(camera, bounds.p0, bounds.p1)) {
				toAddEntities.add(entity);
			}
		}
		
		// Check if all stored bounds are within the camera perspective
		for (Map.Entry<BoundingBox, IEntity> entry : this.boundsToEntity.entrySet()) {
			// If it is not within the field of view, remove it from the rendering items
			BoundingBox entityBox = entry.getKey();
			if (!IntersectManager.IsBoxWithinCameraFOV(camera, entityBox.p0, entityBox.p1)) {
				this.boundsToEntity.remove(entityBox);
			}
        }
		
		// Check all new queued additions and add them
		for (IEntity queuedEntity : toAddEntities) {
			this.boundsToEntity.put(queuedEntity.getBounds(), queuedEntity);
		}
	}
	
	
}
