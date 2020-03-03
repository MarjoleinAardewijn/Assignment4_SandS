package nl.hva.ict.se.sands;

public class BackwardsSearch {
    private int[] left;     // the bad-character skip array
    private String needle;      // or as a string

    private int countComparisonsForLastSearch = 0;

    /**
     * Preprocesses the pattern string.
     *
     * @param needle the pattern string
     */
    public BackwardsSearch(String needle) {
        int radix = 256;
        this.needle = needle;

        // position of leftmost occurrence of c in the pattern
        left = new int[radix]; // right = new int[R];
        for (int c = 0; c < radix; c++)
            left[c] = needle.length(); // right[c] = -1;
        for (int j = needle.length() - 1; j >= 0; j--) // for (int j = 0; j < pat.length(); j++)
            left[needle.charAt(j)] = j; // right[pat.charAt(j)] = j;
    }

    /**
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */
    public int findLocation(String haystack) {
        countComparisonsForLastSearch = 0;

        int pattern = needle.length();
        int text = haystack.length();
        int skip;
        for (int i = text - pattern; i >= 0; i -= skip) { // for (int i = 0; i <= text - pattern; i += skip) {
            skip = 0;
            for (int j = 0; j <= pattern - 1; j++) { // for (int j = pattern-1; j >= 0; j--) {
                countComparisonsForLastSearch++;
                System.out.println("Checking if " + needle.charAt(j) + " is " + haystack.charAt(i+j) + ".");

                if (needle.charAt(j) != haystack.charAt(i+j)) {
                    skip = Math.min(pattern, left[haystack.charAt(i+j)]); // skip = Math.max(1, j - right[haystack.charAt(i+j)]);
                    break;
                }
                System.out.println("Match for: " + needle.charAt(j));
            }
            if (skip == 0) return i;    // found
        }
        return -1;                       // not found
    }


    /**
     * Returns the number of character compared during the last search.
     * @return the number of character comparisons during the last search.
     */
    int getComparisonsForLastSearch() {
        return countComparisonsForLastSearch;
    }

    public static void main(String[] args) {
        final String needle = "needle";
        final String haystack = "whereistheneedle";

        BackwardsSearch backwards = new BackwardsSearch(needle);

        System.out.println("Boyer-Moore Backwards:");
        int matchltr = backwards.findLocation(haystack);
        System.out.println("Needle at position: " + matchltr);
        System.out.println("Number of comparisons: " + backwards.getComparisonsForLastSearch());
    }
}
