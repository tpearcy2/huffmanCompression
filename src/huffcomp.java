
public class huffcomp {

	public static void main(String[] args) {
		if (args.length == 0) {
			printHelp();
		
		} else if (args[0].equals("-c")) {
			String[] mainArgs = {args[1], args[2]};
			huffmanCompression.main(mainArgs);
		} else if (args[0].equals("-d")) {
			if (args.length == 2) {
				String[] mainArgs = {args[1]};
				huffmanDecompression.main(mainArgs);
			} else if (args.length == 3) {
				String[] mainArgs = {args[1], args[2]};
				huffmanDecompression.main(mainArgs);
			}
		} else if (args[0].equals("-h")) {
			printHelp();
		} else {
			printHelp();
		}

	}
	
	public static void printHelp() {
		System.out.println("java huffcomp [paramater] [source] [destination]");
		System.out.println("-c    Parameter to compress [source] and save it to [destination].");
		System.out.println("-d    Parameter to decompress huffman files in [source] and save to [destination].");
		System.out.println("-h    Parameter to print this message.");
	}

}
