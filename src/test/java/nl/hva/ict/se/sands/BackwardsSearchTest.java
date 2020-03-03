package nl.hva.ict.se.sands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackwardsSearchTest {
    protected BackwardsSearch searchEngine;
    protected BoyerMoore searchEngine2;

    @BeforeEach
    public void setup() {
        searchEngine = new BackwardsSearch("needle");
        searchEngine2 = new BoyerMoore("needle");
    }

    @Test
    public void findSingleOccurrence() {
        int index = searchEngine.findLocation("whereistheneedleinthishaystack");

        assertEquals("whereisthe".length(), index);
    }

    @Test
    public void cantFindOccurrence() {
        int index = searchEngine.findLocation("thereisnothinginthishaystack");

        assertEquals(-1, index);
    }

    @Test
    public void simpleCharacterCount() {
        searchEngine.findLocation("whereistheneedle");

        assertEquals(6, searchEngine.getComparisonsForLastSearch());
    }

    @Test
    public void simpleCharacterCountBackwards() {
        searchEngine.findLocation("tofindthenumberofcomparisonsweneedtoknowwheretheneedleisinthishaystack");

        assertEquals(9, searchEngine.getComparisonsForLastSearch());
    }

    @Test
    public void simpleCharacterCountBMOriginal() {
        searchEngine2.findLocation("tofindthenumberofcomparisonsweneedtoknowwheretheneedleisinthishaystack");

        assertEquals(0, searchEngine.getComparisonsForLastSearch());
    }

    @Test
    public void simpleCharacterCountBackwards2() {
        searchEngine.findLocation("wheretheneedleisinthishaystackandhowmannycomparisonsareneeded");

        assertEquals(21, searchEngine.getComparisonsForLastSearch());
    }

    @Test
    public void simpleCharacterCountBMOriginal2() {
        searchEngine2.findLocation("wheretheneedleisinthishaystackandhowmannycomparisonsareneeded");

        assertEquals(0, searchEngine.getComparisonsForLastSearch());
    }
}