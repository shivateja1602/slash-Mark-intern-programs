import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GeneratorTest {

    private final Password password = new Password("Secret");
    private final Alphabet firstAlphabet = new Alphabet(true, false, false, false);
    private final Alphabet secondAlphabet = new Alphabet(false, true, true, true);
    private final Generator generator = new Generator(firstAlphabet);

    @Test
    void testPasswordToString() {
        assertEquals("Secret", password.toString());
    }

    @Test
    void testFirstAlphabet() {
        assertEquals(Alphabet.UPPERCASE_LETTERS, firstAlphabet.getAlphabet());
    }

    @Test
    void testSecondAlphabet() {
        assertEquals(Alphabet.LOWERCASE_LETTERS + Alphabet.NUMBERS + Alphabet.SYMBOLS, secondAlphabet.getAlphabet());
    }

    @Test
    void testGeneratorAlphabet() {
        assertEquals(firstAlphabet.getAlphabet(), generator.alphabet.getAlphabet());
    }

    @Test
    void testGeneratorAlphabetLength() {
        assertEquals(firstAlphabet.getAlphabet().length(), generator.alphabet.getAlphabet().length());
    }
}
