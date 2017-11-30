/**
 * A class for constructing binary trees to use Huffman Compression.
 * @author trent
 *
 */
public class binaryTree {
	
	/** Current node's parent. May be null if I'm the root of the tree. */
    private binaryTree parent;

    /** Current node's left child, or null if none. */
    private binaryTree left;

    /**Current node's left child, or null if none. */
    private binaryTree right;

    /** Current node's value. */
    private int value;
	 

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
    }

}
