package swingyModel.swingyDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class DatabaseSetup {
	private static File myObj;
	private static String fileName = "data.txt";
	private static int count = 0;
	private static String data = "";
	private static String line = "";
	private static List<String> collection = null;
	
	//writing to file
	public static void writter(String info){
		String old = readFile();
		try {
		      FileWriter myWriter = new FileWriter(fileName);
		      myWriter.write(old + (++count) +"\t"+info+"\t1\t0\t10\t10\t0");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}

	//reading file
	private static String readFile() {
		try {
		      myObj = new File(fileName);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data += myReader.nextLine() + "\n";
		        count++;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return (data);
	}
	
	//get content by id
	public static String ReadLine(int id) {
		try {
		      myObj = new File(fileName);
		      Scanner myReader = new Scanner(myObj);
		      
		      while (myReader.hasNextLine()) {
		    	  String[] coll = myReader.nextLine().split("\t");
		    	  if (coll[0].equals(id)) {
		    		  line = myReader.nextLine() + "\n";
		    		  break;
		    	  }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return (line);
	}
	
	//get names of collection
	public static List<String> collect() {
		try {
			collection = new ArrayList<>();
			myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);
			
			while (myReader.hasNextLine()) {
				String[] coll = myReader.nextLine().split("\t");
				collection.add( coll[0] + "->\t" + coll[2] + "\t\tL" + coll[4]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return (collection);
	}
	
}