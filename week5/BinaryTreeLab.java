
//package TreePackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack; // for Stack

public class BinaryTreeLab<T> {

    public static void main(String[] args) {
        // Create test trees
        BinaryTreeLab<Integer> tree1 = new BinaryTreeLab<>(1);
        tree1.root.setLeftChild(new BinaryNode<>(2));
        tree1.root.setRightChild(new BinaryNode<>(3));
        tree1.root.getLeftChild().setLeftChild(new BinaryNode<>(4));
        tree1.root.getLeftChild().setRightChild(new BinaryNode<>(2));
    
        BinaryTreeLab<Integer> tree2 = new BinaryTreeLab<>(5);
        tree2.root.setLeftChild(new BinaryNode<>(6));
        tree2.root.setRightChild(new BinaryNode<>(7));
        tree2.root.getLeftChild().setLeftChild(new BinaryNode<>(8));
        tree2.root.getLeftChild().setRightChild(new BinaryNode<>(9));
    
        BinaryTreeLab<Integer> tree3 = new BinaryTreeLab<>(10);
        tree3.root.setLeftChild(new BinaryNode<>(11));
        tree3.root.setRightChild(new BinaryNode<>(12));
        tree3.root.getLeftChild().setLeftChild(new BinaryNode<>(13));
        tree3.root.getLeftChild().setRightChild(new BinaryNode<>(14));
    
        BinaryTreeLab<Integer> emptyTree = new BinaryTreeLab<>();
    
        // Test count1
        System.out.println("Running test for 'count1'");
        System.out.println("Tree: " + tree1.getRootData());
        System.out.println("Expected result: 2");
        System.out.println("Actual result: " + tree1.count1(2));
        System.out.println();
    
        // Test count2
        System.out.println("Running test for 'count2'");
        System.out.println("Tree: " + tree1.getRootData());
        System.out.println("Expected result: 2");
        System.out.println("Actual result: " + tree1.count2(2));
        System.out.println();
    
        // Test isIsomorphic
        System.out.println("Running test for 'isIsomorphic'");
        System.out.println("Tree1: " + tree2.getRootData() + ", Tree2: " + tree3.getRootData());
        System.out.println("Expected result: true");
        System.out.println("Actual result: " + isIsomorphic(tree2, tree3));
        System.out.println();
    
        // Additional tests
        System.out.println("Running additional tests:");
        System.out.println("Empty tree count1 test:");
        System.out.println("Expected result: 0");
        System.out.println("Actual result: " + emptyTree.count1(1));
        System.out.println("Empty tree count2 test:");
        System.out.println("Expected result: 0");
        System.out.println("Actual result: " + emptyTree.count2(1));
        System.out.println("Empty trees isIsomorphic test:");
        System.out.println("Expected result: true");
        System.out.println("Actual result: " + isIsomorphic(emptyTree, emptyTree));
        System.out.println();
    }

    // O() analysis of time and memory:
    // O(n) time and O(h) space. This method calls the private method without any
    // other specifications, so refer to the private method for explanation.
    public int count1(T anObject) {
        return count1(root, anObject);
    }

    // O() analysis of time and memory:
    // O(n) time complexity as this method visits each node in the tree and performs
    // a constant operation in each visit. O(h), where h is the height of the tree
    // because we call the method recursively based on the depth h of the tree.
    private int count1(BinaryNode<T> rootNode, T anObject) {
        int count = 0;
        // handle case when tree is empty
        if (rootNode == null)
            return count;
    
        // count occurrences
        if (rootNode.getData().equals(anObject)) {
            count = 1;
        }  
    
        return count + count1(rootNode.getLeftChild(), anObject) + count1(rootNode.getRightChild(), anObject);
    }

    // O() analysis of time and memory:
    // O(n) time complexity as we search traverse each element in the tree, where
    // the number of elemets in the tree is n. O(h) space complexity,
    // where h is the height of tree, as the iterator
    // implements a stack interface to keep track of its traversal. In the worst
    // case binary tree, the tree would look like a linked list, which would take up
    // space directly proportional to the size n of elements in the tree.
    public int count2(T anObject) {
        // initialize count and iterator
        int count = 0;
        Iterator<T> iter = getPreorderIterator();

        while (iter.hasNext()) {
            T obj = iter.next();
            if (obj.equals(anObject))
                count++;
        }
        return count;
    }

    public static <T> boolean isIsomorphic(BinaryTreeLab<T> t1, BinaryTreeLab<T> t2) {
        // Base cases
        if (t1 == null && t2 == null) {
            return true;
        }
    
        if (t1 == null || t2 == null) {
            return false;
        }
    
        if (t1.root == null && t2.root == null) {
            return true;
        }
    
        if ((t1.root == null) != (t2.root == null)) {
            return false;
        }
    
        // Recursively check subtrees
        boolean leftIsomorphic = false;
        boolean rightIsomorphic = false;
    
        if (t1.root.getLeftChild() != null && t2.root.getLeftChild() != null) {
            leftIsomorphic = isIsomorphic(new BinaryTreeLab<>(t1.root.getLeftChild().getData()), new BinaryTreeLab<>(t2.root.getLeftChild().getData()));
        } else if (t1.root.getLeftChild() == null && t2.root.getLeftChild() == null) {
            leftIsomorphic = true;
        }
    
        if (t1.root.getRightChild() != null && t2.root.getRightChild() != null) {
            rightIsomorphic = isIsomorphic(new BinaryTreeLab<>(t1.root.getRightChild().getData()), new BinaryTreeLab<>(t2.root.getRightChild().getData()));
        } else if (t1.root.getRightChild() == null && t2.root.getRightChild() == null) {
            rightIsomorphic = true;
        }
    
        return leftIsomorphic && rightIsomorphic;
    }
    
    

    protected BinaryNode<T> root;

    public BinaryTreeLab() {
        root = null;
    } // end default constructor

    public BinaryTreeLab(T rootData) {
        root = new BinaryNode<T>(rootData);
    } // end constructor

    public BinaryTreeLab(T rootData, BinaryTreeLab<T> leftTree,
            BinaryTreeLab<T> rightTree) {
        privateSetTree(rootData, leftTree, rightTree);
    } // end constructor

    public void setTree(T rootData) {
        root = new BinaryNode<T>(rootData);
    } // end setTree

    public void setTree(T rootData, BinaryTreeLab<T> leftTree,
            BinaryTreeLab<T> rightTree) {
        privateSetTree(rootData, leftTree, rightTree);
    } // end setTree

    private void privateSetTree(T rootData, BinaryTreeLab<T> leftTree,
            BinaryTreeLab<T> rightTree) {

        root = new BinaryNode<T>(rootData);

        if (leftTree != null)
            root.setLeftChild(leftTree.root);

        if (rightTree != null)
            root.setRightChild(rightTree.root);
    }

    public T getRootData() {
        T rootData = null;
        if (root != null)
            rootData = root.getData();
        return rootData;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    // getHeight and getNumberOfNodes call same functions in BinaryNode<T>
    public int getHeight() {
        return root.getHeight();
    }

    public int getNumberOfNodes() {
        return root.getNumberOfNodes();
    }

    public void inorderTraversal() {
        Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
        BinaryNode<T> currentNode = root;

        while (!nodeStack.empty() || currentNode != null) {
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            if (!nodeStack.empty()) {
                BinaryNode<T> nextNode = nodeStack.pop();
                System.out.println(nextNode.getData());
                currentNode = nextNode.getRightChild();
            }
        }
    }

    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    }

    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

    private class PreorderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> nodeStack;

        public PreorderIterator() {
            nodeStack = new Stack<BinaryNode<T>>();
            if (root != null)
                nodeStack.push(root);
        } // end default constructor

        public boolean hasNext() {
            return !nodeStack.isEmpty();
        } // end hasNext

        public T next() {
            BinaryNode<T> nextNode;

            if (hasNext()) {
                nextNode = nodeStack.pop();
                BinaryNode<T> leftChild = nextNode.getLeftChild();
                BinaryNode<T> rightChild = nextNode.getRightChild();

                // push into stack in reverse order of recursive calls
                if (rightChild != null)
                    nodeStack.push(rightChild);

                if (leftChild != null)
                    nodeStack.push(leftChild);
            } else {
                throw new NoSuchElementException();
            }
            return nextNode.getData();
        } // end next

        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove
    } // end PreorderIterator

    private class InorderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public InorderIterator() {
            nodeStack = new Stack<BinaryNode<T>>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next() {
            BinaryNode<T> nextNode = null;
            // find leftmost node with no left child
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            } // end while
              // get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                currentNode = nextNode.getRightChild();
            } else
                throw new NoSuchElementException();
            return nextNode.getData();
        } // end next

        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove

    } // end InorderIterator

} // end BinaryTreeLab
