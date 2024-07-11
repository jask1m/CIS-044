import java.util.Stack;

public class BinarySearchTreeLab<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        // Test cases for isBalanced(), isBST(), and getPredecessor()
        
        // Test isBalanced()
        System.out.println("Now testing the method 'isBalanced'.");
        
        BinarySearchTreeLab<Integer> balancedTree = new BinarySearchTreeLab<>();
        balancedTree.add(5);
        balancedTree.add(3);
        balancedTree.add(7);
        balancedTree.add(2);
        balancedTree.add(4);
        balancedTree.add(6);
        balancedTree.add(8);
        
        System.out.println("Test 1 (Balanced tree):");
        System.out.println("Expected Output: true");
        System.out.println("Actual Output: " + balancedTree.isBalanced());
        
        BinarySearchTreeLab<Integer> unbalancedTree = new BinarySearchTreeLab<>();
        unbalancedTree.add(5);
        unbalancedTree.add(3);
        unbalancedTree.add(7);
        unbalancedTree.add(2);
        unbalancedTree.add(1);
        
        System.out.println("\nTest 2 (Unbalanced tree):");
        System.out.println("Expected Output: false");
        System.out.println("Actual Output: " + unbalancedTree.isBalanced());
        
        BinarySearchTreeLab<Integer> emptyTree = new BinarySearchTreeLab<>();
        System.out.println("\nTest 3 (Empty tree):");
        System.out.println("Expected Output: true");
        System.out.println("Actual Output: " + emptyTree.isBalanced());
        
        // Test isBST()
        System.out.println("\nNow testing the method 'isBST'.");
        
        System.out.println("Test 1 (Valid BST):");
        System.out.println("Expected Output: true");
        System.out.println("Actual Output: " + balancedTree.isBST());
        
        // Create an invalid BST by manually setting nodes
        BinarySearchTreeLab<Integer> invalidBST = new BinarySearchTreeLab<>(5);
        invalidBST.root.setLeftChild(new BinaryNode<>(3));
        invalidBST.root.setRightChild(new BinaryNode<>(7));
        invalidBST.root.getLeftChild().setRightChild(new BinaryNode<>(6));  // This makes it invalid
        
        System.out.println("\nTest 2 (Invalid BST):");
        System.out.println("Expected Output: false");
        System.out.println("Actual Output: " + invalidBST.isBST());
        
        System.out.println("\nTest 3 (Empty tree):");
        System.out.println("Expected Output: true");
        System.out.println("Actual Output: " + emptyTree.isBST());
        
        // Test getPredecessor()
        System.out.println("\nNow testing the method 'getPredecessor'.");
        
        BinarySearchTreeLab<Integer> bst = new BinarySearchTreeLab<>();
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        bst.add(6);
        bst.add(8);
        
        System.out.println("Test 1 (Middle element):");
        System.out.println("Expected Output: 4");
        System.out.println("Actual Output: " + bst.getPredecessor(5));
        
        System.out.println("\nTest 2 (Smallest element):");
        System.out.println("Expected Output: null");
        System.out.println("Actual Output: " + bst.getPredecessor(2));
        
        System.out.println("\nTest 3 (Largest element):");
        System.out.println("Expected Output: 7");
        System.out.println("Actual Output: " + bst.getPredecessor(8));
        
        System.out.println("\nTest 4 (Non-existent element):");
        System.out.println("Expected Output: null");
        System.out.println("Actual Output: " + bst.getPredecessor(10));
        
        System.out.println("\nTest 5 (Empty tree):");
        System.out.println("Expected Output: null");
        System.out.println("Actual Output: " + emptyTree.getPredecessor(1));
    }

    // P1
    public boolean isBalanced() {
        return isBalanced(root) >= 0;
    }

    private int isBalanced(BinaryNode<T> node) {
        if (node == null) {
            return 0; // Height of null node is 0
        }

        int leftHeight = isBalanced(node.getLeftChild());
        if (leftHeight == -1) {
            return -1; // Left subtree is unbalanced
        }

        int rightHeight = isBalanced(node.getRightChild());
        if (rightHeight == -1) {
            return -1; // Right subtree is unbalanced
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current node is unbalanced
        }

        return Math.max(leftHeight, rightHeight) + 1; // Return height of current node
    }

    // P2
    public boolean isBST() {
        if (root == null) {
            return true;  // An empty tree is considered a valid BST
        }
    
        Stack<BinaryNode<T>> stack = new Stack<>();
        BinaryNode<T> current = root;
        T prev = null;
    
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeftChild();
            }
    
            current = stack.pop();
    
            // If this node is less than or equal to the previous node, it's not a BST
            if (prev != null && current.getData().compareTo(prev) <= 0) {
                return false;
            }
    
            prev = current.getData();
            current = current.getRightChild();
        }
    
        return true;
    }

    // P3
    public T getPredecessor(T entry) {
        if (root == null) {
            return null;
        }

        BinaryNode<T> current = root;
        T last = null; // reference to keep track of possible predecessors

        while (current != null) {
            int result = entry.compareTo(current.getData());

            if (result == 0) {
                if (current.hasLeftChild()) {
                    BinaryNode<T> subNode = current.getLeftChild();
                    while (subNode.hasRightChild()) {
                        subNode = subNode.getRightChild();
                    }
                    return subNode.getData();
                } else {
                    return last;
                }
            } else if (result < 0) {
                current = current.getLeftChild();
            } else {
                last = current.getData();
                current = current.getRightChild();
            }
        }
        return null;
    }

    private BinaryNode<T> root;

    public BinarySearchTreeLab() {
        root = null;
    }

    public BinarySearchTreeLab(T rootData) {
        root = new BinaryNode<T>(rootData);
    }

    public T get(T entry) {
        return getEntry(root, entry);
    }

    private T getEntry(BinaryNode<T> rootNode, T entry) {
        T result = null;
        if (rootNode != null) {
            T rootEntry = rootNode.getData();
            if (entry.compareTo(rootEntry) == 0)
                result = rootEntry;
            else if (entry.compareTo(rootEntry) < 0)
                result = getEntry(rootNode.getLeftChild(), entry);
            else
                result = getEntry(rootNode.getRightChild(), entry);
        }
        return result;
    }

    public boolean contains(T entry) {
        return get(entry) != null;
    }

    // Adds newEntry to the nonempty subtree rooted at rootNode.
    private T addEntry(BinaryNode<T> rootNode, T newEntry) {
        // assume that rootNode is NOT null
        T result = null;
        int comparison = newEntry.compareTo(rootNode.getData());
        if (comparison == 0) { // duplicates NOT allowed
            result = rootNode.getData();
            rootNode.setData(newEntry);
        } else if (comparison < 0) {
            if (rootNode.hasLeftChild())
                result = addEntry(rootNode.getLeftChild(), newEntry);
            else
                rootNode.setLeftChild(new BinaryNode<T>(newEntry));
        } else {
            if (rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), newEntry);
            else
                rootNode.setRightChild(new BinaryNode<T>(newEntry));
        } // end if
        return result;
    } // end addEntry

    public T add(T newEntry) {
        T result = null;
        if (root == null)
            root = new BinaryNode<T>(newEntry);
        else
            result = addEntry(root, newEntry);
        return result;
    } // end add

    class ReturnObject {
        T data;

        public void set(T newData) {
            data = newData;
        }

        public T get() {
            return data;
        }
    }

    public T remove(T entry) {
        ReturnObject oldEntry = new ReturnObject();
        BinaryNode<T> newRoot = removeEntry(root, entry, oldEntry);
        root = newRoot;
        return oldEntry.get();
    } // end remove

    // Removes an entry from the tree rooted at a given node.
    // rootNode is a reference to the root of a tree.
    // entry is the object to be removed.
    // oldEntry is an object whose data field is null.
    // Returns the root node of the resulting tree; if entry matches
    // an entry in the tree, oldEntry's data field is the entry
    // that was removed from the tree; otherwise it is null.
    //
    // Why removeEntry returns BinaryNode<T>
    // Answer: To return a new modified tree: example root node removed so root of
    // tree will change
    private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, ReturnObject oldEntry) {
        if (rootNode != null) {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if (comparison == 0) { // entry == root entry
                oldEntry.set(rootData);
                rootNode = removeFromRoot(rootNode);
            } else if (comparison < 0) { // entry < root entry
                BinaryNode<T> leftChild = rootNode.getLeftChild();
                BinaryNode<T> newLeftChild = removeEntry(leftChild, entry, oldEntry);
                rootNode.setLeftChild(newLeftChild);
            } else { // entry > root entry
                BinaryNode<T> rightChild = rootNode.getRightChild();
                BinaryNode<T> newRightChild = removeEntry(rightChild, entry, oldEntry);
                rootNode.setRightChild(newRightChild);
            }
        }
        return rootNode;
    }

    // Removes the entry in a given root node of a subtree.
    // rootNode is the root node of the subtree.
    // Returns the root node of the revised subtree.
    private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {
        // Case 1: rootNode has two children
        if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
            // find node with largest entry in left subtree
            BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
            // replace entry in root
            rootNode.setData(largestNode.getData());
            // remove node with largest entry in left subtree
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        } // end if
          // Case 2: rootNode has at most one child
        else if (rootNode.hasRightChild())
            rootNode = rootNode.getRightChild();
        else
            rootNode = rootNode.getLeftChild();
        return rootNode;
    }

    // Finds the node containing the largest entry in a given tree.
    // rootNode is the root node of the tree.
    // Returns the node containing the largest entry in the tree.
    private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
        if (rootNode.hasRightChild())
            rootNode = findLargest(rootNode.getRightChild());
        return rootNode;
    }

    // Removes the node containing the largest entry in a given tree.
    // rootNode is the root node of the tree.
    // Returns the root node of the revised tree.
    private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) {
        if (rootNode.hasRightChild()) {
            BinaryNode<T> rightChild = rootNode.getRightChild();
            BinaryNode<T> root = removeLargest(rightChild);
            rootNode.setRightChild(root);
        } else
            rootNode = rootNode.getLeftChild();
        return rootNode;
    }
}
