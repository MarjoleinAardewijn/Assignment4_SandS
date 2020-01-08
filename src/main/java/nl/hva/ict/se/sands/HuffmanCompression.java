package nl.hva.ict.se.sands;

import java.io.InputStream;
import java.util.*;

public class HuffmanCompression {

    private static Map<Character, String> charPrefixHashMap = new HashMap<>();
    static Node root;
    private final String text;

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
        Map<Character, Integer> weight = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if (!weight.containsKey(text.charAt(i))) {
                weight.put(text.charAt(i), 0);
            }
            weight.put(text.charAt(i), weight.get(text.charAt(i)) + 1);
        }

        root = buildTree(weight);

        setPrefixCodes(root, new StringBuilder());
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            s.append(charPrefixHashMap.get(c));
        }

        System.out.println("Weight: " + weight);
        System.out.println("charPrefixHashMap: " + charPrefixHashMap);
        System.out.println("Output: " + s.toString());

        return s.toString();
    }

    private static Node buildTree(Map<Character, Integer> weight) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = weight.keySet();
        for (Character c : keySet) {
            Node Node = new Node(weight.get(c), c);
            priorityQueue.offer(Node);
        }
        assert priorityQueue.size() > 0;

        while (priorityQueue.size() > 1) {

            Node x = priorityQueue.peek();
            priorityQueue.poll();

            Node y = priorityQueue.peek();
            priorityQueue.poll();

            Node sum = new Node(y, x);
            root = sum;

            priorityQueue.offer(sum);
        }

        return priorityQueue.poll();
    }

    private static void setPrefixCodes(Node node, StringBuilder prefix) {

        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                charPrefixHashMap.put(node.getCharacter(), prefix.toString());

            } else {
                prefix.append('0');
                setPrefixCodes(node.getLeft(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setPrefixCodes(node.getRight(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

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
    Map<Character, String> getCodes() {
        return charPrefixHashMap;
    }

}
