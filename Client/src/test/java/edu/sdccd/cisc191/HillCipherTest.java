package edu.sdccd.cisc191;

import edu.sdccd.cisc191.ciphers.Hill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HillCipherTest {
    private String plainText, cipherText, key;

    @Test
    public void testEncode() {
        plainText = "Please work, I'm begging you to work";
        key = "CISC";
        assertEquals("ogiuquaikoimiaiqqowaggugaiko", Hill.encode(plainText,key).toLowerCase());
    }
}
