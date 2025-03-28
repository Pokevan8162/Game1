package tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import main.V;

public class MapManager {

	static // If there is an obstacle at line 1 of the first map, then to transition it, the obstacle must be at line 39 in the next map.

	String directoryPath;
	public static int mapIndex = 0;
	public static ArrayList<String> mapsToDelete = new ArrayList<String>();
	
	/**
	 * Constructor for File Manager, sets directory to read/save files as currentWorkingDirectory/Orders
	 */
	public MapManager() {
		String directory = new File(System.getProperty("user.dir"), "maps").getAbsolutePath();
		directoryPath = directory;
	}
	/**
	 * Constructor for File Manager, sets directory to iDirectory specify.
	 * @param iDirectory - Directory to save/read files.
	 */
	public MapManager(String iDirectory) {
		String directory = new File(iDirectory).getAbsolutePath();
		directoryPath = directory;
	}
		
	//CREATING FILES--------------------------------------------------------------------------------------------------------------------------------------------------
		
		
	/**
	 * Creates a text file with the name of the order number and writes the order details inside the text file,
	 * stored at the class's directory path.
	 * 
	 * @param
	 * @throws IOException
	 */
	public String createHardMap() throws IOException {
		Random rand = new Random();
		ArrayList<String> tileGenerator = new ArrayList<String>(10);
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("0 ");
		tileGenerator.add("1 ");
		tileGenerator.add("2 ");
		tileGenerator.add("3 ");
		
		if (mapIndex > 0) {
			ArrayList<String> prevMap = readPreviousMap();
			
			mapIndex++;
			String mapNumber = Integer.toString(mapIndex);
			String mapToDelete = "Map_" + mapNumber + ".txt";
			mapsToDelete.add(mapToDelete);  //This is so all maps are deleted at game end
			File mapFile = new File(directoryPath, "Map_" + mapNumber + ".txt"); // instantiates new file
			File directory = mapFile.getParentFile();                     // instantiates new directory
			
			mapFile.createNewFile();                                  // this line actually creates the file itself
			FileWriter fileWriter = new FileWriter(mapFile, false);        // true parameter means add to the end of file, false means add to the beginning + replace
			
			int col = 0;
			int row = 0;
			
			String line;
			
			while (col < V.maxWorldCol && row < 38) {
				if (col == 0 || col == V.maxWorldCol - 1) {
					fileWriter.write("2 ");
					col++;
				} else {
					int index = rand.nextInt(tileGenerator.size());
					String tile = tileGenerator.get(index);
					fileWriter.write(tile);
					col++;
				}
				if (col == V.maxWorldCol) {
					col = 0;
					row++;
					fileWriter.write("\r\n");
				}
			}
			
			int i = 0;
			while (row < V.maxWorldRow) {
				line = prevMap.get(i);
				fileWriter.write(line);
				if (row < V.maxWorldRow - 1) {
					fileWriter.write("\r\n");
				}
				row++;
				i++;
			}
			
			fileWriter.close();
			String fileName = new File(System.getProperty("user.dir"), "maps/Map_" + mapNumber + ".txt").getAbsolutePath();
			return fileName;
		} else {
			mapIndex++;
			String mapNumber = Integer.toString(mapIndex);
			String mapToDelete = "Map_" + mapNumber + ".txt";
			mapsToDelete.add(mapToDelete);  //This is so all maps are deleted at game end
			File mapFile = new File(directoryPath, "Map_" + mapNumber + ".txt"); // instantiates new file
			File directory = mapFile.getParentFile();                     // instantiates new directory
		
			if (!directory.exists()) {                                 // if the directory does not exist,
				directory.mkdirs();                                    // make the directory per the supposed parent file (first parameter) of the newly created file.
				System.out.println("Directory did not exist. Directory created at: " + directoryPath);
			}
		
			mapFile.createNewFile();                                  // this line actually creates the file itself
			FileWriter fileWriter = new FileWriter(mapFile, false);        // true parameter means add to the end of file, false means add to the beginning + replace
		
			int col = 0;
			int row = 0;
		
			String line;
		
			while (col < V.maxWorldCol && row < 36) {
				if (col == 0 || col == V.maxWorldCol - 1) {
					fileWriter.write("2 ");
					col++;
				} else {
					int index = rand.nextInt(tileGenerator.size());
					String tile = tileGenerator.get(index);
					fileWriter.write(tile);
					col++;
				}
				if (col == V.maxWorldCol) {
					col = 0;
					row++;
					if (!(row == V.maxWorldRow)) {
						fileWriter.write("\r\n");
					}
				}
			}
		
			while (col < V.maxWorldCol && row < V.maxWorldRow) {
				if (col == 0 || col == V.maxWorldCol - 1) {
					fileWriter.write("2 ");
					col++;
				} else {
					fileWriter.write("0 ");
					col++;
				}
				if (col == V.maxWorldCol) {
					col = 0;
					row++;
					if (!(row == V.maxWorldRow)) {
						fileWriter.write("\r\n");
					}
				}
			}
			
			fileWriter.close();
			String fileName = new File(System.getProperty("user.dir"), "maps/Map_" + mapNumber + ".txt").getAbsolutePath();
			return fileName;
		}
	}
	
	public static void deleteMaps() {
		for (int i = 0; i < mapsToDelete.size(); i++) {
    		File file = new File(directoryPath, mapsToDelete.get(i));     //file itself to be deleted
    		file.delete();
    	}
	}
		
	/**
	 * Creates a text file with the name of the order number, stored at the class's directory path.
	 * 
	 * @param
	 * @throws IOException
	 */
//	public void addOrder(int orderNumber) throws IOException {
//		String numToString = Integer.toString(orderNumber);
//		File orderFile = new File(directoryPath, numToString + ".txt");
//		File orders  = new File(directoryPath, "OrderNumbers.txt");
//		File directory = orderFile.getParentFile();
//		
//		if (!directory.exists()) {
//			directory.mkdirs();
//			System.out.println("Directory did not exist. Directory created at: " + directoryPath);
//		}
//		
//		if (!fileExists(orderNumber)) {
//			orderFile.createNewFile();
//			System.out.println("File saved successfully to: " + orderFile.getAbsolutePath());
//			FileWriter ordersWriter = new FileWriter(orders, true);
//			if(!orders.exists()) {
//				orders.createNewFile();
//			}
//			ordersWriter.write("\r\n");
//			ordersWriter.write(numToString);
//			ordersWriter.close();
//		} else {
//			System.out.println("File already exists.");
//		}
//	}
	
	
	//VALIDATING FILES--------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * @param
	 * @return true if element does exist, false if element does not exist.
	 * @throws IOException
	 */
//	public boolean elementExists(int orderNumber, String element) throws IOException {
//		if (orderExists(orderNumber)) {
//			String numToString = Integer.toString(orderNumber);
//			File file = new File(directoryPath, numToString + ".txt"); // finds the file based off the directory path and the order number
//
//			FileReader reader = new FileReader(file);                   // opens the file from the order number requested
//			BufferedReader bufferedReader = new BufferedReader(reader); // instantiates a buffered reader that goes through each line in the file
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {        // goes through each line in the file
//				if (line.contains(element)) {
//					return true;                                        // element does exist
//				}
//			}
//			reader.close();
//			bufferedReader.close();
//		}
//		
//		return false;                                                   // element does not exist
//	}
		
	public ArrayList<String> readPreviousMap() throws IOException {
		String mapNumber = Integer.toString(mapIndex);
		ArrayList<String> lines = new ArrayList<String>();
		int i = 16;
		File prevMap = new File(directoryPath, "Map_" + mapNumber + ".txt");
		File directory = prevMap.getParentFile();
		prevMap.createNewFile();
		FileReader reader = new FileReader(prevMap);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;

		while (i > 0) {
			line = bufferedReader.readLine();
			lines.add(line);
			i--;
		}
		reader.close();
		bufferedReader.close();	
		return lines;
	}
		
		/**
		 * @param
		 * @return true if the order exists, false if order does not exist.
		 */
		public static boolean fileExists(String fileName) {
			File file = new File(directoryPath, fileName);
			if (file.exists()) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * @param
		 * @return ArrayList<String> of every line in a file.
		 * @throws IOException
		 */
//		public String getName(int orderNumber) throws IOException {
//			
//			if (orderExists(orderNumber)) {
//				String numToString = Integer.toString(orderNumber);
//				File file = new File(directoryPath, numToString + ".txt");
//
//				FileReader reader = new FileReader(file);
//				BufferedReader bufferedReader = new BufferedReader(reader);
//
//				String name = bufferedReader.readLine();
//				reader.close();
//				bufferedReader.close();
//				return name;
//
//			} else {
//				throw new IOException("Order not found."); // this wont happen because it will be found
//			}
//		}
		
		/**
		 * @return ArrayList<String> of every order number in the OrderNumbers.txt file.
		 * @throws IOException
		 */
		public ArrayList<String> getOrders() throws IOException {
			
				File file = new File(directoryPath, "OrderNumbers.txt");
				FileReader reader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(reader);
				ArrayList<String> numbers = new ArrayList<String>();
				String number;
				
				while ((number = bufferedReader.readLine()) != null) {
					numbers.add(number);
				}
				reader.close();
				bufferedReader.close();
				return numbers;
			}



	//EDITING FILES--------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		/**
		 * If the file exists, then clear file and add user element to file.
		 * 
		 * @param integer order number, string user element
		 */
//		public void rewriteFile(int orderNumber, String userElement) {
//			if (orderExists(orderNumber)) {
//				String numToString = Integer.toString(orderNumber);
//				File file = new File(directoryPath, numToString + ".txt");
//
//				try {
//					// possibly print every line in the file and allow user to edit line
//					FileWriter writer = new FileWriter(file, false);
//					writer.write(userElement);
//					writer.write("\r\n");
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		/**
//		 * If the file exists, then add user element to the end of the file.
//		 * 
//		 * @param integer order number, string user element
//		 */
//		public void addToFile(int orderNumber, String userElement) {
//			if (orderExists(orderNumber)) {
//				String numToString = Integer.toString(orderNumber);
//				File file = new File(directoryPath, numToString + ".txt");
//
//				try {
//					// possibly print every line in the file and allow user to edit line
//					FileWriter writer = new FileWriter(file, true);
//					writer.write(userElement);
//					writer.write("\r\n");
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		/**
//		 * If the file exists, clear the file..
//		 * @param integer order number
//		 */
//		public void clearFile(int orderNumber) throws IOException {
//			if (orderExists(orderNumber)) {
//				String numToString = Integer.toString(orderNumber);
//				File file = new File(directoryPath, numToString + ".txt");
//				if (file.delete()) {
//					file.createNewFile();
//				} else {
//					System.out.println("File could not be cleared.");
//				}
//			}
//	}
//	public void deleteOrder(int orderNumber) throws IOException {
//		if (orderExists(orderNumber)) {
//			String numToString = Integer.toString(orderNumber);
//			File file = new File(directoryPath, numToString + ".txt");     //file itself to be deleted
//			file.delete();
//				
//			//------------------------------------------------				
//			File ordersOrig = new File(directoryPath, "OrderNumbers.txt"); //original orders file
////			File temp = new File("OrderNumbers.txt");
//			File ordersNew = new File(directoryPath, "OrderNumbersNew.txt");  //new orders file
//			
//			FileReader fileReader = new FileReader(ordersOrig);
//			FileWriter lineWriter = new FileWriter(ordersNew);
//			BufferedReader lineReader = new BufferedReader(fileReader);
//			
//			ordersNew.createNewFile();                                      //creates new order file
//			String line;
//			
//			while ((line = lineReader.readLine()) != null) {
//				if (!line.equals(numToString)) {
//					lineWriter.write(line + "\r\n");
//				}
//			}
//				
//				
//			lineWriter.close();
//			lineReader.close();
//			ordersOrig.delete();
//			ordersNew.renameTo(ordersOrig);
//		}
//	}
}
