package edu.sdccd.cisc191;

import edu.sdccd.cisc191.ciphers.Vigenere;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VigenereCipherTest {
    private String inputText, key;
    @Test
    public void testBasicEncode() {
        inputText = "Please work, I'm begging you to work";
        key = "pLeAsE";
        assertEquals("Ewiaki lzvk, A'q qpkgarv jsu ls lzvk", Vigenere.encode(inputText,key));
    }

    @Test
    public void testBasicDecode() {
        inputText = "Ewiaki lzvk, A'q qpkgarv jsu ls lzvk";
        key = "pLeAsE";
        assertEquals("Please work, I'm begging you to work", Vigenere.decode(inputText,key));
    }
}