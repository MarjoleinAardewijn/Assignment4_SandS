package nl.hva.ict.se.sands;

/**
 * NOTE: You are NOT allowed to implement the Serializable interface!!
 */
public class Node implements Comparable<Node> {
    private Node left;
    private Node right;
    private int weight;
    private Character character;

    public Node(char ch, int weight,  Node left,  Node right) {
        this.character = ch;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getWeight() {
        return weight;
    }

    public Character getCharacter() {
        return character;
    }
}
