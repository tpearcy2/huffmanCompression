import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class huffmanDecompression {
	
	public static byte getBit(int position, int input) {
		int output = (input >> position) & 1;
		return (byte) output;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
        byte[] input;
        
        try {
        	System.out.println("Enter the path to the file in which the copressed text and the");
    		System.out.println("binary tree are stored.");
    		Scanner sc = new Scanner(System.in);
    		String path = sc.nextLine();
            File file = new File(path + "/huffman.txt");
            File fileObject = new File(path + "/huffmanTree.txt");
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
			 
			 FileWriter fileWriter = new FileWriter(path + "/uncompressed.txt");
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
			 for (int i = 0; i < inputParentNode.value; i++) {
				 byteBinary = binaryTree.printChar(byteBinary, inputParentNode, bufferedWriter); 
			 }
			 System.out.println("FInished decompressing.");
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
