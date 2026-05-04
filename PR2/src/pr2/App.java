package pr2;

import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        String treeString = null;
        TreeNode nodo = new TreeNode();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the tree string:");
        treeString = scanner.nextLine();
        TreeNode root = ArmarArbol.armarArbolDesdeString(treeString, nodo);
        //inorderTraversal(root);
    }

    // Helper function for inorder traversal (for verification)
    public static void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.peso + " ");
        inorderTraversal(node.right);
    }
}
