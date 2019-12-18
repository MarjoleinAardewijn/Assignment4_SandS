package nl.hva.ict.se.sands;

public class BackwardsSearch {

    private static int comparisonsForLastSearch = 0;

    /**
     * Boyer Moore algorithm which matches a given pattern in a given string by order left to right (backwards).
     * @param needle to look for
     * @param haystack to check if the pattern is in here
     * @return the position of the pattern if found, if not it returns -1
     */
    public static int BoyerMooreLeftToRight(String needle, String haystack) {
        char[] pattern = returnCharArray(needle);
        char[] text = returnCharArray(haystack);

        int patternSize = pattern.length;
        int textSize = text.length;

        int i = textSize;

        while ((i - patternSize) >= 0) {
            int j = 1;
            System.out.println("Checking if '" + text[i - j] + "' is '" + pattern[patternSize - j] + "'");
            while (text[i - j] == pattern[patternSize - j]) {
                System.out.println("Match for " + pattern[patternSize - j]);
                comparisonsForLastSearch++;
                j++;
                if (j >= patternSize + 1) {
                    return i - patternSize;
                }
            }
            i--;
        }
        return -1;
    }

    /**
     * Boyer Moore algorithm which matches a given pattern in a given string by order right to left.
     * @param needle to look for
     * @param haystack to check if the pattern is in here
     * @return the position of the pattern if found, if not it returns -1
     */
    public static int BoyerMooreRightToLeft(String needle, String haystack) {
        char[] pattern = returnCharArray(needle);
        char[] text = returnCharArray(haystack);

        int patternSize = pattern.length;
        int textSize = text.length;

        int i = 0;

        while ((i + patternSize) <= textSize) {
            int j = 0;
            System.out.println("Checking if '" + text[i + j] + "' is '" + pattern[j] + "'");
            while (text[i + j] == pattern[j]) {
                System.out.println("Match for " + pattern[j]);
                j++;
                if (j >= patternSize) {
                    comparisonsForLastSearch = i;
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    public static int getComparisonsForLastSearch() {
        return comparisonsForLastSearch;
    }

    /**
     * Main class which runs the two versions of the Boyer Moore method.
     * @param args
     */
    public static void main(String[] args) {
        final String needle = "hay";
        final String haystack = "needleinthehaystack";

        System.out.println("Left to Right:");
        final int matchltr = BoyerMooreLeftToRight(needle, haystack);
        System.out.println("Needle at position: " + matchltr);
        System.out.println("Number of comparisons: " + comparisonsForLastSearch);

        System.out.println("-------------------------------------------");

        System.out.println("Right to Left:");
        final int matchrtl = BoyerMooreRightToLeft(needle, haystack);
        System.out.println("Needle at position: " + matchrtl);
        System.out.println("Number of comparisons: " + comparisonsForLastSearch);
    }

    /**
     * Method which returns an array of characters from a given string
     * @param str is the given string
     * @return ch[] is an array of characters
     */
    private static char[] returnCharArray(String str) {
        // Creating array of string length
        char[] ch = new char[str.length()];

        // Copy character by character into array
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }
        return ch;
    }
}
