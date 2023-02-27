package renderer.utility;

import java.util.ArrayList;
import java.util.Comparator;

import renderer.entities.IEntity;
import renderer.math.Vector3;

public class SortingAlgorithms {
	
	public static void SortIEntityByZBufferOffsets(ArrayList<IEntity> entities, ArrayList<Vector3> deltaPositions) {
		// Create a blank ArrayList for the delta positions to be sorted
		ArrayList<Vector3> sortedArrayList = new ArrayList<Vector3>(deltaPositions.size());
		sortedArrayList.addAll(deltaPositions);
		
		// Sort the delta positions (ascending order)
		sortedArrayList.sort(new Comparator<Vector3>() {
			@Override
			public int compare(Vector3 a, Vector3 b) {
				return (int) (a.mag() - b.mag());
			}
		});
		
		// Create a new arraylist for the sorted entities
		ArrayList<IEntity> sortedEntities = new ArrayList<IEntity>(entities.size());
		// For all the entities
		for (int sortedIndex = 0; sortedIndex < sortedArrayList.size(); sortedIndex++) {
			// get the sorted old index value so we can match the IEntity from the magnitudes ArrayList
			int oldIndex = deltaPositions.indexOf( sortedArrayList.get(sortedIndex) );
			// find the IEntity by the old index value
			IEntity oldEntityMatch = entities.get(oldIndex); 
			// append matching IEntity to ArrayList
			sortedEntities.add(oldEntityMatch); 
		}
		
		// Clear the entities ArrayList and replace them with the sorted ones
		entities.clear();
		entities.addAll(sortedEntities);
		
		// Clear the delta positions and replace them with the sorted delta positions
		deltaPositions.clear();
		deltaPositions.addAll(sortedArrayList);
	}
	
}
