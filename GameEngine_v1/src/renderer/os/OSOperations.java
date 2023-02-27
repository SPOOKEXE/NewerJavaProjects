package renderer.os;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OSOperations {
	
	public static boolean doesFileExist( String filePath ) {
		try {
			return !(new File(filePath).exists());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	
	public static FileReader getJSONFile( String filePath ) {
		if (!doesFileExist(filePath)) {
			return null;
		}
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filePath + ".json");
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		return fileReader;
	}
	
}