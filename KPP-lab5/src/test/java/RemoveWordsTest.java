import org.example.RemoveWords;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveWordsTest {

    private final RemoveWords textManipulator = new RemoveWords();

    @Test
    public void testRemoveSpecifiedWordsEmptyString() {
        String text = "";
        String result = textManipulator.removeSpecifiedWords(text);
        assertEquals("", result);
    }

    @Test
    public void testRemoveSpecifiedWordsNullString() {
        String text = null;
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            textManipulator.removeSpecifiedWords(text);
        });
    }

    @Test
    public void testRemoveSpecifiedWordsNoRemovals() {
        String text = "Quick brown fox jumps";
        String result = textManipulator.removeSpecifiedWords(text);
        assertEquals("Quick brown fox jumps", result);
    }

    @Test
    public void testRemoveSpecifiedWordsRemovals() {
        String text = "The quick brown fox jumps over the lazy dog. Or does it?";
        String result = textManipulator.removeSpecifiedWords(text);
        assertEquals("Quick brown fox jumps over lazy dog. Does it?", result);
    }

    @Test
    public void testRemoveSpecifiedWordsMixedCase() {
        String text = "The quick OR brown fox jumps. or does it?";
        String result = textManipulator.removeSpecifiedWords(text);
        assertEquals("Quick brown fox jumps. Does it?", result);
    }

    @Test
    public void testRemoveSpecifiedWordsOnlyRemovalWords() {
        String text = "The or are on in out";
        String result = textManipulator.removeSpecifiedWords(text);
        assertEquals("", result);
    }

    @Test
    public void testRemoveSpecifiedWordsStartEndWithRemovals() {
        String text = "The quick. Or the fox.";
        String result = textManipulator.removeSpecifiedWords(text);
        assertEquals("Quick. Fox.", result);
    }

    @Test
    public void testRemoveSpecifiedWordsComplexSentences() {
        String text = "On a sunny day, the birds are chirping. Out in the garden, flowers are blooming.";
        String result = textManipulator.removeSpecifiedWords(text);
        assertEquals("Sunny day, birds chirping. Garden, flowers blooming.", result);
    }

    @Test
    public void testRemoveSpecifiedWordsSingleCharacter() {
        String text = "A";
        String result = textManipulator.removeSpecifiedWords(text);
        assertEquals("", result);
    }

    @Test
    public void testRemoveSpecifiedWordsMultipleSpaces() {
        String text = "The    quick brown   fox";
        String result = textManipulator.removeSpecifiedWords(text);
        assertEquals("Quick brown fox", result);
    }

}
