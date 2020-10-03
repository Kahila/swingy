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
	private String data = "";
	private static String line = "";
	private static List<String> collection = null;
	
	//writing to file
	public static void writter(String info){
		String old = readFile();
		try {
		      FileWriter myWriter = new FileWriter(fileName);
		      myWriter.write(old + (++count) +"\t"+info+"\t1\t1000\t10\t10\t0");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}

	//getter for count
	public static String getCount() {
		return (count+"\tNew");
	}
	
	//reading file
	private static String readFile() {
		String data = "";
		try {
			File myObj = new File(fileName);
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
	public static String ReadLine(String id) {
		try {
			line = "";
			String tmp = "";
			myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);
			
			while (myReader.hasNextLine()) {
				tmp = myReader.nextLine();
				String[] coll = tmp.split("\t");
				if (coll[0].equals(id)) {
					line = tmp + "\n";
					break;
				}
			}
			myReader.close();
		}catch (FileNotFoundException e) {
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
				collection.add( coll[0] + "\t" + coll[2] + "\t\tL" + coll[3]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return (collection);
	}

	public static void update(String update, String ID) {
		String content = readFile();
		System.out.println("++++++"+ content +"++++++++");
		String broken[] = content.trim().split("\n");
		String temp[] = null;
		String contents = "";
		for (String c : broken) {
			temp = c.trim().split("\t");
//			System.out.println("count = "+ count);
			if (temp[0].trim().equals(ID)) {
				c = update;
			}
			contents += c + "\n";
			System.out.println("---"+ c);
		}
		System.out.println("---\n"+ contents.trim()+"-- count ="+count+"\n---");
		//writing to file
		try {
		      FileWriter myWriter = new FileWriter(fileName);
		      myWriter.write(contents);
		      myWriter.close();

		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
}