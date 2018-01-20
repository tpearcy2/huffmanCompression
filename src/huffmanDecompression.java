import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
/**
 * This class is used to decompress a compressed file. It requires a Huffman tree and a
 * compressed file.
 * @author trent
 *
 */
public class huffmanDecompression {
	
	/**
	 * Takes an integer n and finds the value of the bit at a
	 * given position from the right and returns it.
	 * @param position
	 * @param input
	 * @return
	 */
	public static byte getBit(int position, int n) {
		int output = (n >> position) & 1;
		return (byte) output;
	}


	/**
	 * Takes a Huffman tree and a compressed text file and creates and saves
	 * a new text file to the requested directory.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
        
        try {
        	/**
        	 * Prompts users for the path to the files necessary
        	 * to decompress the text.
        	 */
        	// System.out.println("Enter the path to the file in which the");
    		// System.out.println("copressed text and the binary tree are stored.");
    		// Scanner sc = new Scanner(System.in);
    		// String path = sc.nextLine();
        	String path = args[0];
        	
        	
    		
    		/**
    		 * Takes the path given above and adds the names of the
    		 * two files containing the tree and the text. It then 
    		 * creates a FileInputStream and an ObjectInputStream
    		 * and uses those to read the files. This
    		 * is only confirmed to work on linux.
    		 */
            File file = new File(path + "/huffman.txt");
            File fileObject = new File(path + "/huffmanTree.txt");
			FileInputStream fis = new FileInputStream(file);
			FileInputStream fois = new FileInputStream(fileObject);
			ObjectInputStream ois = new ObjectInputStream(fois);
			
			/**
			 * Creates a binaryTree from the saved bianryTree.
			 */
			binaryTree inputParentNode = (binaryTree) ois.readObject();
			
			
			/**
			 * Creates a byte array which will be used to store the bytes from
			 * the compressed text file.
			 */
			byte[] input = new byte[fis.available()];
			fis.read(input);
			
			 boolean[] byteBinary = new boolean[input.length * 8];
			
			 for (int i = 0; i < input.length; i++) {
		         int byteRep = (int) input[i];
		        
		         
		         
		         for (int j = 8 * (i); j < (8 * i + 8); j++) {
		        	 int pos = (8 * i) + 7 - j;
		        	 byte tem = getBit(pos, byteRep);
		        	 if (tem == 1) {
		        		 byteBinary[j] = true;
		        	 }
		        	 
		         }
		     }
			 FileWriter fileWriter = new FileWriter(path + "/uncompressed.txt");
			 if (args.length == 2) {
				 String destination = args[1];
				 fileWriter = new FileWriter(destination + "/uncompressed.txt");
			 }
			 
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
			 for (int i = 0; i < inputParentNode.value; i++) {
				 byteBinary = binaryTree.printChar(byteBinary, inputParentNode, bufferedWriter); 
			 }
			 System.out.println();
			 System.out.println("Finished decompressing.");
			 fis.close();
			 ois.close();
			 //sc.close();
			 
			
			 
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
