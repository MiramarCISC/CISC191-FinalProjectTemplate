package edu.sdccd.cisc191;

import edu.sdccd.cisc191.ciphers.Caesar;
import edu.sdccd.cisc191.ciphers.Hill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CaesarCipherTest {
    private String plainText, cipherText;
    private String key;

    @Test
    public void testEncode() {
        plainText = "abcdefghijklmnopqrstuvwxyz";
        key = "2";
        assertEquals("cdefghijklmnopqrstuvwxyzab", Caesar.encode(plainText,key).toLowerCase());
    }

    @Test
    public void testDecode() {
        plainText = "cdefghijklmnopqrstuvwxyzab";
        key = "2";
        assertEquals("abcdefghijklmnopqrstuvwxyz", Caesar.decode(plainText, key).toLowerCase());
    }
}
