package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ParseAddresses {
	
	private static String myJSon = "";
	private static String myNewJSon = "";
	
	public static String readLargeFile(String fileName){
		String line = null;
		int i = 0;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Note that write() does not automatically append a newline character.
			while((line = bufferedReader.readLine()) != null) {
				String[] eachRow = line.split(",");
				String lat = eachRow[0]; //change
				String lon = eachRow[1]; //change
				i++;
				
				myJSon += "{\"id\": " + i + "," + "\"latitude\": " + lat + "," + "\"longitude\": " + lon + "},";
			}
			
			if((line = bufferedReader.readLine()) == null){
				myNewJSon = "[" + myJSon.substring(0, myJSon.length()-1) + "]";
			}

			// Always close files.
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		System.out.println(myNewJSon);
		return myNewJSon;
	}
	
	public String readLargeFile2(String fileName){
		String line = null;
		int i = 0;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Note that write() does not automatically append a newline character.
			while((line = bufferedReader.readLine()) != null) {
				String[] eachRow = line.split(",");
				String lat = eachRow[0];
				String lon = eachRow[1];
				i++;
				
				myJSon += "{\"id\": " + "\"" + i + "\"," + "\"latitude\": " + "\"" + lat + "\"," + "\"longitude\": " + "\"" + lon + "\"},";
			}
			
			if((line = bufferedReader.readLine()) == null){
				myNewJSon = "[" + myJSon.substring(0, myJSon.length()-1) + "]";
			}

			// Always close files.
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		System.out.println(myNewJSon);
		return myNewJSon;
	}
	
//	public static void main (String[] args){
//		readLargeFile2("src/main/resources/data/Addresses_smaller.csv");
//	}
}
