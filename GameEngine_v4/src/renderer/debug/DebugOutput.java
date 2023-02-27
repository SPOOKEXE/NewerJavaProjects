package renderer.debug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DebugOutput {

	public static String getFileContent(FileInputStream fis, String encoding ) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader(fis, encoding));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append( line );
				sb.append( '\n' );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	private static String readFile(File inputFile) {
		String data = "";
		if (inputFile.exists()) {
			try {
				FileInputStream in = new FileInputStream(inputFile);
				data = getFileContent(in, "utf-8");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public static void ResetFile(File outputFile) {
		if (outputFile.exists()) {
			outputFile.delete();
		}
	}
	
	public static void DumpToFile(ArrayList<String> toOutput, File outputFile) {
		String inputString = readFile(outputFile);
		
		StringBuilder builder = new StringBuilder();
		for (String item : toOutput) {
			builder.append(item + "\n");
		}
		RenderingDebugOutput.toOutput = new ArrayList<String>();
		
		inputString += builder.toString();
		
		try {
			FileOutputStream out = new FileOutputStream(outputFile);
			out.write(inputString.getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
