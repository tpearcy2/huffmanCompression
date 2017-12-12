import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
/**
 * The class used for compressing and decompressing a string of text.
 * @author trent
 *
 */
public class huffmanCompression {
	

	/** Number of unicode characters easily handled by java. */
	private static final int UNICODE = 0xFFFF;
	
	
	/**
	 * This functions takes a string as input and returns an array of integers. 
	 * @param input the string to take the character distribution for.
	 * @return Returns an array for which the values in each slot represents the
	 * number of times the unicode character represented by that slot number shows
	 * up in the input string.
	 * 
	 * This function has been tested 12/3/17, 00:40.
	 */
	public static int[] computeDistribution(final String input) {
        char[] inputArray = input.toCharArray();
        int length = inputArray.length;
        int[] frequency = new int[UNICODE];
        for (int i = 0; i < length; i++) {
        	int value = (int) inputArray[i];
        	frequency[value]++;
            }
        return frequency;
    }
	
	
	public static byte getBit(int position, int input) {
		int output = (input >> position) & 1;
		return (byte) output;
	}
	
	
	
	public static String textToString(String input) {
		
		try {
			String output = new String(Files.readAllBytes(Paths.get(input)));
			return output;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		/**
		 * the text to compress.
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the path to the text file to be compressed.");
		String path = sc.nextLine();
		
		String text = textToString(path);
		
		System.out.println("##################");
		
		
		/**
		 * Creates a binary tree out of the test.
		 */
		binaryTree parentNode = binaryTree.textToTree(text);
		
		
		byte[] output = binaryTree.textToBytes(text, parentNode);
		System.out.println("Enter the path to the file in which the copressed text and the");
		System.out.println("binary tree should be stored.");
		path = sc.nextLine();
        File file = new File(path + "/huffman.txt");
        File fileObject = new File(path + "/huffmanTree.txt");
        
        try {
			FileOutputStream fos = new FileOutputStream(file);
			FileOutputStream fos2 = new FileOutputStream(fileObject);
			ObjectOutputStream oos = new ObjectOutputStream(fos2);
			
			fos.write(output);
			oos.writeObject(parentNode);
			
			fos.close();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
        
        
        
        byte[] input;
        
        try {
        	System.out.println("Enter the path to the file in which the copressed text and the");
    		System.out.println("binary tree are stored.");
    		path = sc.nextLine();
            file = new File(path + "/huffman.txt");
            fileObject = new File(path + "/huffmanTree.txt");
			FileInputStream fis = new FileInputStream(file);
			FileInputStream fois = new FileInputStream(fileObject);
			
			ObjectInputStream ois = new ObjectInputStream(fois);
			
			binaryTree inputParentNode = (binaryTree) ois.readObject();
			
			
			
			input = new byte[fis.available()];
			
			fis.read(input);
			
			 boolean[] byteBinary = new boolean[input.length * 8];
			
			 for (int i = 0; i < input.length; i++) {
		         int byteRep = (int) input[i];
		         byte bytes = (byte) byteRep;
		         int byteRepTemp = byteRep;
		         
		         
		         for (int j = 8 * (i); j < (8 * i + 8); j++) {
		        	 int pos = (8 * i) + 7 - j;
		        	 byte tem = getBit(pos, byteRep);
		        	 if (tem == 1) {
		        		 byteBinary[j] = true;
		        	 }
		        	 
		         }
		     }
		
			 
			 for (int i = 0; i < parentNode.value; i++) {
				 byteBinary = binaryTree.printChar(byteBinary, inputParentNode); 
			 }
			 fis.close();
			 ois.close();
			 
			
			 
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
        
      
        
       
        
	}
	
	
	
	
}
        
      

