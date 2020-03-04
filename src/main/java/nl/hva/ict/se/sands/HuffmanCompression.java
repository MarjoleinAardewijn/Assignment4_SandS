package nl.hva.ict.se.sands;

import java.io.InputStream;
import java.util.*;

class HuffManComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        return node1.getWeight() - node2.getWeight();
    }
}

// https://www.lavivienpost.com/huffman-encoding-java-code/
public class HuffmanCompression {

    private Map<Character, Integer> charPrefixHashMap = new HashMap<>();
    private Node root;
    private String text;

    public HuffmanCompression(String text) {
        this.text = text;
        compress();
    }

    public HuffmanCompression(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        text = sc.next();
        compress();
    }

    public static void main(String[] args) {
        new HuffmanCompression("Yes, we made it!");
    }

    /**
     * Returns the compression ratio assuming that every character in the text uses 8 bits.
     *
     * @return the compression ratio.
     */
    public double getCompressionRatio() {
        return (double) text.length() / (text.length() * 8);
    }

    /**
     * Compresses the text that was provided to the constructor.
     *
     * @return
     */
    public String compress() {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!charPrefixHashMap.containsKey(ch)) {
                charPrefixHashMap.put(ch, 1);
            } else {
                int val = charPrefixHashMap.get(ch);
                charPrefixHashMap.put(ch, ++val);
            }
        }

        root = buildTree(charPrefixHashMap);

        Map<Character, String> map = new HashMap<>();
        setPrefixCodes(root, map, "");

        return map.toString();
    }

    Node buildTree(Map<Character, Integer> map) {
        Queue<Node> nodeQueue = new PriorityQueue<>(11, new HuffManComparator());
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            nodeQueue.add(new Node(entry.getKey(), entry.getValue(), null, null));
        }

        while (nodeQueue.size() > 1) {
            Node node1 = nodeQueue.remove();
            Node node2 = nodeQueue.remove();
            Node node = new Node('\0', node1.getWeight() + node2.getWeight(), node1, node2);
            nodeQueue.add(node);
        }

        return nodeQueue.remove();
    }

    private void setPrefixCodes(Node node, Map<Character, String> map, String s) {
        if (node.getLeft() == null && node.getRight() == null) {
            map.put(node.getCharacter(), s);
            return;
        }
        setPrefixCodes(node.getLeft(), map, s + '0');
        setPrefixCodes(node.getRight(), map, s + '1');
    }

    /**
     * Returns the root of the compression tree.
     *
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return root;
    }

    /**
     * Returns a Map<Character, String> with the character and the code that is used to encode it.
     * For "aba" this would result in: ['b' -> "0", 'a' -> "1"]
     * And for "cacbcac" this would result in: ['b' -> "00", 'a' -> "01", 'c' -> "1"]
     *
     * @return the Huffman codes
     */
    Map<Character, Integer> getCodes() {
        return charPrefixHashMap;
    }
}
