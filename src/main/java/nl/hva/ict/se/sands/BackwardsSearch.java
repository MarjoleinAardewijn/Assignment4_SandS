package nl.hva.ict.se.sands;

public class BackwardsSearch {

    private static int countComparisonsForLastSearch = 0;

    /**
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     * @param needle the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */
    public static int findLocation(String needle, String haystack) {
        char[] pattern = returnCharArray(needle);
        char[] text = returnCharArray(haystack);

        int patternSize = pattern.length;

        int i = text.length, j = 1;

        while ((i - patternSize) >= 0) {
            System.out.println("Checking if '" + text[i - j] + "' is '" + pattern[patternSize - j] + "'");
            while (text[i - j] == pattern[patternSize - j]) {
                System.out.println("Match for " + pattern[patternSize - j]);
                countComparisonsForLastSearch++;
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
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the left end side of the text (<code>haystack</code>) and proceeds to the last character (right side).
     * @param needle the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     *
     * Original code from: https://www.baeldung.com/java-full-text-search-algorithms
     */
    public static int fincLocationBMOriginal(String needle, String haystack) {
        char[] pattern = returnCharArray(needle);
        char[] text = returnCharArray(haystack);

        int patternSize = pattern.length;
        int textSize = text.length;

        int i = 0, j = 0;

        while ((i + patternSize) <= textSize) {
            j = patternSize - 1;
            System.out.println("Checking if '" + text[i + j] + "' is '" + pattern[j] + "'");
            while (text[i + j] == pattern[j]) {
                System.out.println("Match for " + pattern[j]);
                countComparisonsForLastSearch++;
                j--;
                if (j < 0) {
                    return i;
                }
            }
            i++;
        }
        return -1;
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

    /**
     * Returns the number of character compared during the last search.
     * @return the number of character comparisons during the last search.
     */
    public static int getComparisonsForLastSearch() {
        return countComparisonsForLastSearch;
    }

    /**
     * Main class which runs the two versions of the Boyer Moore method.
     * @param args
     */
    public static void main(String[] args) {
        final String needle = "hay";
        final String haystack = "needleinthehaystack";

        System.out.println("Boyer-Moore Backwards:");
        final int matchltr = findLocation(needle, haystack);
        System.out.println("Needle at position: " + matchltr);
        System.out.println("Number of comparisons: " + countComparisonsForLastSearch);

        System.out.println("-------------------------------------------");

        System.out.println("Boyer-Moore:");
        final int matchrtl = fincLocationBMOriginal(needle, haystack);
        System.out.println("Needle at position: " + matchrtl);
        System.out.println("Number of comparisons: " + countComparisonsForLastSearch);
    }
}
