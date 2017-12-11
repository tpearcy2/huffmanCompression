/**
 * A class for constructing binary trees to use Huffman Compression.
 * @author trent
 *
 */
public class binaryTree {
	
	
	/** Current node's parent. May be null if I'm the root of the tree. */
    public binaryTree parent;

    /** Current node's left child, or null if none. */
    public binaryTree left;

    /**Current node's left child, or null if none. */
    public binaryTree right;

    /** Current node's value. */
    public int value;
    
    /** Current node's character value. */
    public char charVal;
	 

	public binaryTree() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Constructs a tree.
     * @param setValue value for the root of the tree.
     */
    public binaryTree(final int setValue) {
        this.parent = null;
        this.left = null;
        this.right = null;
        this.value = setValue;
        this.charVal = (char) 0;

    }

    /**
     * Constructs a tree node.
     * @param setValue value for the node of the tree.
     * @param setParent the parent of the node.
     */
    public binaryTree(final int setValue, final binaryTree setParent) {
        this.parent = setParent;
        this.left = null;
        this.right = null;
        this.value = setValue;
        this.charVal = (char) 0;
    }
    
    /**
     * Constructs a tree node.
     * @param setValue value for the node of the tree.
     * @param setParent the parent of the node.
     * @param serChar the character value of the node.
     */
    public binaryTree(final int setValue, final binaryTree setParent, final char setChar) {
        this.parent = setParent;
        this.left = null;
        this.right = null;
        this.value = setValue;
        this.charVal = setChar;
    }
    
    /**
     * Constructs a tree node.
     * @param setValue value for the node of the tree.
     * @param setChar the char value of the node.
     */
    public binaryTree(final int setValue, final char setChar) {
        this.parent = null;
        this.left = null;
        this.right = null;
        this.value = setValue;
        this.charVal = setChar;
    }
    
    /**
     * Constructs a tree node.
     * @param setValue value for the node of the tree.
     * @param setChar the char value of the node.
     */
    public binaryTree(final char setChar) {
        this.parent = null;
        this.left = null;
        this.right = null;
        this.value = 0;
        this.charVal = setChar;
    }
    
    /**
     * Constructs a tree node.
     * @param setValue value for the node of the tree.
     */
    public binaryTree(final binaryTree one, final binaryTree two) {
        this.parent = null;
        this.left = one;
        this.right = two;
        this.value = (one.value + two.value);
        this.charVal = (char) 0;
    }
    
    /**
     * Finds the node in a tree containing the search Character.
     * @param findChar the character to find.
     * @param parentNode the parentNode of the tree to search through.
     * @return
     * 
     * Tested 12/4/17 00:48
     */
    public static binaryTree find(final char findChar, final binaryTree parentNode) {
    	binaryTree result = null;
    	
    	if (parentNode == null) {
    		return null;
    	}
    	if (parentNode.charVal == findChar) {
    		return parentNode;
    	}
    	if (parentNode.left != null) {
    		result = find(findChar, parentNode.left);
    	}
    	if (result == null) {
    		result = find(findChar, parentNode.right);
    	}
    	return result;
    }
    
    /**
     * Determines the depth of the node from the root.
     * @return the depth relative to root.
     */
    public int depth() {
        if (this.parent == null) {
            return 0;
        } else {
            return (1 + this.parent.depth());
        }
    }
    
    
    
    
    
    /**
     * Constructs a complete tree from a tree array.
     * @param input
     * @return returns a parent node.
     * Tested  12/4/17 00:09.
     */
    public static binaryTree binaryTreeBuilder(final binaryTree[] input) {
    	
    	if (input.length == 1) {
    		System.out.println("binaryTreeBuilder has reached the base case.");
    		return input[0];
    	}
    	
    	/**
    	 * Sorts the array and then creates a second one with one less indexes
    	 * than the input.
    	 */
    	binaryTree[] array = sort(input);
    	binaryTree[] output = new binaryTree[array.length - 1];
    	
    	/**
    	 * Combines the two nodes.
    	 */
    	output[0] = new binaryTree(array[0], array[1]);
    	array[0].parent = output[0];
    	array[1].parent = output[0];
    	
    	for (int i = 1; i < output.length; i++) {
    		output[i] = array[i + 1];
    	}
    	binaryTree returnTree = binaryTreeBuilder(output);
    	return returnTree;
    }
    
    
    /**
     * Sorts a binary tree array by the value of the nodes in the array.
     * @param inputToSort
     * @return
     */
    public static binaryTree[] sort(final binaryTree[] inputToSort) {
		binaryTree[] input = new binaryTree[inputToSort.length];
		
		// System.out.println("The input length is " + input.length);
		
		for (int i = 0; i < input.length; i++) {
			input[i] = inputToSort[i];
		}
		while (!isSorted(input)) {
			for (int i = 0; i < input.length - 1; i++) {
	            int min = i;
	            for (int j = i + 1; j < input.length; j++) {
	                if (input[j].value < input[min].value) {
	                    min = j;
	                }
	            }
	            binaryTree temp = input[min];
	            input[min] = input[i];
	            input[i] = temp;
	        }
		}
		return input;
	}
    
    /**
     * Determines if a binary tree array is sorted.
     * @param input
     * @return
     */
    public static boolean isSorted(final binaryTree[] input) {
		boolean flag = true;
		for (int i = 0; i < (input.length - 1); i++) {
			if (input[i].value > input[i + 1].value) {
				flag = false;
				return flag;
			}
		}
		return flag;
	}
    
    
	
	/**
	 * Takes a string and turns it into a binary tree leaf node array.
	 * @param input The string to convert.
	 * @return the binaryTree array.
	 * 
	 * This function has been tested 12/3/17, 00:40.
	 */
	public static binaryTree[] binaryTreeArray(final String input) {
		
		int[] intArray = huffmanCompression.computeDistribution(input);
		
		//System.out.println(intArray.length);
		
		/**
		 * Counts the number of non zero characters in the input string.
		 */
		int numberOfNonZeros = 0;
		for (int i = 0; i < intArray.length; i++) {
			if (intArray[i] != 0) {
				numberOfNonZeros++;
			}
		}
		
		//System.out.println(numberOfNonZeros);
		
		/**
		 * Keeps the int representations of the non zero char values in the string.
		 */
		int[] nonZeroValues = new int[numberOfNonZeros];
		int counter = 0;
		for (int i = 0; i < intArray.length; i++) {
			if (intArray[i] != 0) {
				nonZeroValues[counter] = i;
				counter++;
			}	
		}
		
		//for (int i = 0; i < nonZeroValues.length; i++) {
		//	System.out.println(nonZeroValues[i]);
		//}
		
		/**
		 * Keeps track of the number of times a character shows up in the string
		 * by storing it in a parallel array.
		 */
		int[] nZVFrequency = new int[nonZeroValues.length];
		for (int i = 0; i < nZVFrequency.length; i++) {
			nZVFrequency[i] = intArray[nonZeroValues[i]];
		}
		

		/**
		 * Creates an array of binaryTrees with values from the string.
		 */
		binaryTree[] binaryArray = new binaryTree[nonZeroValues.length];

		for (int i = 0; i < binaryArray.length; i++) {
			binaryArray[i] = new binaryTree();
			binaryArray[i].charVal = (char) nonZeroValues[i];
			binaryArray[i].value = nZVFrequency[i];
		}
		return binaryArray;
	}
	
	/**
	 * Determines if the this is on the right or left.
	 * @return true if it is a right node.
	 */
	public boolean isRightChild() {
		if (this.parent.right == this) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Determines the path to the desred node.
	 * tested 12/4/17 02:35.
	 * @return
	 */
	public boolean[] path() {
		
		boolean[] path = new boolean[this.depth()];
		
		if (this.depth() == 1) {
			path[0] = this.isRightChild();
			return path;
		}
		
		boolean[] restOfPath = this.parent.path();
		
		path[path.length - 1] = this.isRightChild();
		
		for (int i = 0; i < restOfPath.length; i++) {
			path[i] = restOfPath[i];
		}
		return path;
	}
	
	
	public static binaryTree textToTree(String text) {
		/**
		 * makes an array of leaf nodes each containing a character and a frequency value.
		 */
		binaryTree[] array = binaryTreeArray(text);
		
		/**
		 * combines those leaf nodes into one tree.
		 */
		binaryTree parentNode = binaryTreeBuilder(array);
		
		return parentNode;
	}
	
	public static byte[] textToBytes(String text, binaryTree parentNode) {
        int count = 0;
		
		/**
		 * creates a 2D boolean array. It has one dimension that is lined up with
		 * each character in the text. It then contains the path to that character. For
		 * example; the text "Hello world!" would make a boolean[][] with .length = 12, and
		 * [i].length equal to the number of bits required to get to the character at i in
		 * the tree.
		 */
	    boolean[][] binaryArray = new boolean[text.length()][];
		for (int i = 0; i < text.length(); i++) {
			binaryTree leafNode = binaryTree.find(text.charAt(i), parentNode);
			boolean[] path = leafNode.path();
			binaryArray[i] = path;
		}
		
		int counting = 0;
		for (int i = 0; i < binaryArray.length; i++) {
			for (int j = 0; j < binaryArray[i].length; j++) {
				counting++;
			}
		}
		System.out.println(counting);
		
		/**
		 * determines the number of bytes required to transmit the entire word's path.
		 * NOT COMPLETE, AND WILL NOT WORK FOR ANYTHING OTHER THAN "Hello world!"
		 */
        int numberOfBytes = counting / 8;
        if (counting / 8 != 0) {
        	numberOfBytes += 1;
        }
        System.out.println(numberOfBytes);
        
        /**
         * a normal boolean array used for turning the 2D array into a single string of bits.
         */
        boolean[] binary = new boolean[numberOfBytes * 8];
        
        /**
         * used to represent the binary array as a string of bytes to be transmitted.
         */
        byte[] output = new byte[numberOfBytes];
        
        /**
         * converts the 2d binaryArray into the 1D boolean binary array.
         */
        count = 0;
        for (int i = 0; i < binaryArray.length; i++) {
			for (int j = 0; j < binaryArray[i].length; j++) {
				binary[count] = binaryArray[i][j];
				count++;
			}
        }
        
        /**
         * Same value as binary but its length % 8 is 0. It will
         * have falses added to the end.
         */
        boolean[] byteBinary = new boolean[numberOfBytes * 8];    
        for (int i = 0; i < binary.length; i++) {
        	byteBinary[i] = binary[i];
        }
        
        /**
         * creates an int that represents a byte. it turns byteBinary
         * into usable bytes.
         */
        for (int i = 0; i < output.length; i++) {
        	int byteRep = 0;
        	for (int j = 8 * (i); j < (8 * i + 8); j++) {
        		byteRep = byteRep << 1;
        		
        		if (byteBinary[j]) {
        			byteRep++;
        		}
        	}
        	output[i] = (byte) byteRep;
        }
        
        
        /**
         * checks the value of each byte in output.
         */
        for (int i = 0; i < output.length; i++) {
        	System.out.printf("0x%02X", output[i]);
        	System.out.println();
        }
        return output;
	}
	
	public static boolean[] printChar(boolean[] path, binaryTree parentNode) {
		if (parentNode.charVal != (char) 0) {
			System.out.print(parentNode.charVal);
			return path;
		} else if (path[0]) {
			boolean[] shortPath = new boolean[path.length - 1];
			for (int i = 0; i < shortPath.length; i++) {
				shortPath[i] = path[i + 1];
			}
			return (printChar(shortPath, parentNode.right));
		} else {
			boolean[] shortPath = new boolean[path.length - 1];
			for (int i = 0; i < shortPath.length; i++) {
				shortPath[i] = path[i + 1];
			}
			return (printChar(shortPath, parentNode.left));
		}
	}
	
    
    

}
