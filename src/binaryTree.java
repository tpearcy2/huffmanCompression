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
     * Constructs a complete tree from a tree array.
     * @param input
     * @return returns a parent node.
     */
    public static binaryTree binaryTreeBuilder(final binaryTree[] input) {
    	
    	if (input.length == 1) {
    		System.out.println("binaryTreeBuilder has reached the base case.");
    		return input[0];
    	}
    	binaryTree[] array = sort(input);
    	binaryTree[] output = new binaryTree[array.length - 1];
    	
    	output[0] = new binaryTree(array[0], array[1]);
    	array[0].parent = output[0];
    	array[1].parent = output[0];
    	
    	for (int i = 1; i < output.length; i++) {
    		output[i] = array[i + 1];
    	}
    	
    	binaryTreeBuilder(output);
    	
    	return null;
    }
    
    
    /**
     * Sorts a binary tree array by the value of the nodes in the array.
     * @param inputToSort
     * @return
     */
    public static binaryTree[] sort(final binaryTree[] inputToSort) {
		
    	
		binaryTree[] input = new binaryTree[inputToSort.length];
		
		System.out.println("The input length is " + input.length);
		
		
		
		
		
		for (int i = 0; i < input.length; i++) {
			input[i] = inputToSort[i];
		}
		
		for (int i = 0; i < input.length; i++) {
			System.out.println(input[i].value);
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
    
    

}
