import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
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
	
	
	
	
	
	
	public static void main(String[] args) {
		/**
		 * the text to compress.
		 */
		String text = "The quick brown fox jumped over the lazy dog.";
		
		System.out.println(text);
		
		
		/**
		 * Creates a binary tree out of the test.
		 */
		binaryTree parentNode = binaryTree.textToTree(text);
		
		
		byte[] output = binaryTree.textToBytes(text, parentNode);
        
        File file = new File("/home/trent/huffman.txt");
        
        try {
			FileOutputStream fos = new FileOutputStream(file);
			
			fos.write(output);
			
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
        
        
        
        byte[] input;
        
        try {
			FileInputStream fis = new FileInputStream(file);
			
			input = new byte[fis.available()];
			
			fis.read(input);
			System.out.println();
			
			 for (int i = 0; i < input.length; i++) {
		        	System.out.printf("0x%02X", input[i]);
		        	System.out.println();
		        }
			 
			 boolean[] byteBinary = new boolean[input.length * 8];
			 System.out.println("input");	
			 System.out.println();
			 for (int i = 0; i < input.length; i++) {
		         int byteRep = (int) input[i];
		         byte bytes = (byte) byteRep;
		         int byteRepTemp = byteRep;
		         
		         
		         System.out.printf("0x%02X", byteRep);
		         System.out.println();
		         for (int j = 8 * (i); j < (8 * i + 8); j++) {
		        	 int pos = (8 * i) + 7 - j;
		        	 byte tem = getBit(pos, byteRep);
		        	 if (tem == 1) {
		        		 byteBinary[j] = true;
		        	 }
		        	 
		         }
		     }
			 System.out.println();
			 for (int i = 0; i < byteBinary.length; i++) {
				 System.out.println(byteBinary[i]);
			 }
			 
			 for (int i = 0; i < parentNode.value; i++) {
				 byteBinary = binaryTree.printChar(byteBinary, parentNode); 
			 }
			 
			
			 
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      
        
       
        
	}
	
	
	
	
}
        
      

