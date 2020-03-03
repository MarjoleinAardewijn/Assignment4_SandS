package nl.hva.ict.se.sands;

public class BoyerMoore {
        private int[] right;     // the bad-character skip array
        private String needle;      // or as a string

        private int countComparisonsForLastSearch = 0;

        /**
         * Preprocesses the pattern string.
         *
         * @param needle the pattern string
         */
        public BoyerMoore(String needle) {
            int radix = 256;
            this.needle = needle;

            // position of rightmost occurrence of c in the pattern
            right = new int[radix];
            for (int c = 0; c < radix; c++)
                right[c] = -1;
            for (int j = 0; j < needle.length(); j++)
                right[needle.charAt(j)] = j;
        }

        /**
         * Returns the index of the first occurrrence of the pattern string
         * in the text string.
         *
         * @param  haystack the text string
         * @return the index of the first occurrence of the pattern string
         *         in the text string; n if no such match
         */
        public int findLocation(String haystack) {
            countComparisonsForLastSearch = 0;

            int pattern = needle.length();
            int text = haystack.length();
            int skip;
            for (int i = 0; i <= text - pattern; i += skip) {
                skip = 0;
                for (int j = pattern -1; j >= 0; j--) {
                    countComparisonsForLastSearch++;
                    System.out.println("Checking if " + needle.charAt(j) + " is " + haystack.charAt(i+j) + ".");

                    if (needle.charAt(j) != haystack.charAt(i+j)) {
                        skip = Math.max(1, j - right[haystack.charAt(i+j)]);
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
        int matchrtl;

        BoyerMoore normal = new BoyerMoore(needle);

        System.out.println("Boyer-Moore:");
        matchrtl = normal.findLocation(haystack);
        System.out.println("Needle at position: " + matchrtl);
        System.out.println("Number of comparisons: " + normal.getComparisonsForLastSearch());
    }

}
