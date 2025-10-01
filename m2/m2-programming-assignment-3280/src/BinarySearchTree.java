import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BinarySearchTree {

    // Node class. holds a key (value), and left and right Nodes
    private static class Node {
        int key;
        Node left;
        Node right;
        Node p;

        public Node(int key) {
            this.key = key;
            left = null;
            right = null;
            p = null;
        }
    }

    // Fields
    Node root;

    // Constructor
    public BinarySearchTree() { this.root = null;}

    // Methods
    private static void discardTree(BinarySearchTree T) { T.root = null;}

    private static int getTreeHeight(BinarySearchTree T) {
        if (T.root == null) { return 0;}

        Queue<Node> queue = new LinkedList<>();
        queue.add(T.root);
        int height = -1;

        while (!queue.isEmpty()) {
            int nodesAtLevel = queue.size();
            for (int i = 0; i < nodesAtLevel; i++) {
                Node curr = queue.poll();
                if (curr.left != null) { queue.add(curr.left);}
                if (curr.right != null) { queue.add(curr.right);}
            }
            height++;
        }
        return height;
    }

    // Insert method for a Tree. Takes in a Node
    private static void treeInsert(BinarySearchTree T, Node z) {
        Node y = null;
        Node x = T.root;
        while (x != null) {
            y = x;
            if (z.key < x.key) { x = x.left;}
            else { x = x.right; }
        }
        z.p = y;
        if (y == null) { T.root = z;}
        else if (z.key < y.key) { y.left = z;}
        else { y.right = z;}
    }

    // collecting data method.
    private static void collectData(int m) {
        // UPDATE FILE PATH BEFORE RUNNING.
        File fileF = new File("/home/u2/hbp0020/3280/m2/FileF.csv");

        try (PrintWriter pw = new PrintWriter(fileF)) {
            // writes first line of csv file and creates a new Tree for data collection and Random class
            pw.println("n, Height(n)");
            Random random = new Random();

            // runs 15 times with high n values (2000 to 30000)
            for (int n = 2000; n <= 30000; n += 2000) {
                // sum of m heights of m random trees with n Nodes
                int sumHeight = 0;
                // runs m times (default should be 5) for average of m heights
                for (int j = 1; j <= m; j++) {
                    BinarySearchTree T = new BinarySearchTree();
                    // runs n times to create a random tree with n Nodes
                    for (int i = 1; i <= n; i++) {
                        int p = random.nextInt(15001);
                        Node z = new Node(p);
                        treeInsert(T, z);
                    }
                    // gets height of tree, clears memory for next iteration, adds height to sum of the m heights
                    int h = getTreeHeight(T);
                    discardTree(T);
                    sumHeight += h;

                }
                // calculates the average height of m rand trees with n Nodes, writes n and average height to csv file
                double collectHeight = (double) sumHeight / m;
                pw.println(n + "," + collectHeight);
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int m = 5; // m = 5 measurements of each random tree with n Nodes
        collectData(m);
    }

}
