package pr2;

public class TreeNode {
    int peso;
    TreeNode left, right;

    TreeNode(int peso) {
        this.peso = peso;
        this.left = null;
        this.right = null;
    }
    TreeNode() {
        this.peso = -1;
        this.left = null;
        this.right = null;
    }
}