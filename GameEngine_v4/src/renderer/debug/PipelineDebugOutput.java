package renderer.debug;

import java.io.File;
import java.util.ArrayList;

import renderer.entities.IEntity;
import renderer.math.Vector3;

public class PipelineDebugOutput {

	public static File outputFile = new File("log_dump_2.txt");
	public static ArrayList<String> toOutput = new ArrayList<String>();
	
	private static boolean DUMP_SORTED_IENTITY = false;
	
	public static void DumpToFile(boolean resetFile) {
		if (resetFile == true) {
			DebugOutput.ResetFile(PipelineDebugOutput.outputFile);
		}
		DebugOutput.DumpToFile(PipelineDebugOutput.toOutput, PipelineDebugOutput.outputFile);
	}
	
	public static void DUMP_SORTED_IENTITIES(ArrayList<IEntity> entities) {
		if (!DUMP_SORTED_IENTITY) {
			return;
		}
		
		int counter = 1;
		for (IEntity sortedOrderEntity : entities) {
			Vector3 pos = sortedOrderEntity.getPosition();
			PipelineDebugOutput.toOutput.add(String.format("SIE,%s,%s,%s,%s,", counter, pos.x, pos.y, pos.z));
			counter += 1;
		}
		
	}
	
	
}
