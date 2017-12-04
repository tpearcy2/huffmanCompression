
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

	public static void main(String[] args) {
		
	}


}
