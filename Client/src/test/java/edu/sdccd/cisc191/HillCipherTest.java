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
    public void testEncode2x2() {
        plainText = "Please work, I'm begging you to work";
        key = "LION";
        assertEquals("ogiuquaikoimiaiqqowaggugaiko", Hill.encode(plainText,key));
    }

    @Test
    public void testEncodeNonDivisibleText() {
        plainText = "Please work, I'm begging you to work";
        key = "PROCEDURE";
        assertEquals("AIJYGKAVSMKULKISFWSUMNCZAVSPNX", Hill.encode(plainText,key));
    }

    @Test
    public void testDecode2x2() {
        cipherText = "TPSEWSQWHECIROKGKVYGCOJGQWHE";
        key = "LION";
        assertEquals("PLEASEWORKIMBEGGINGYOUTOWORK", Hill.decode(cipherText,key));
    }

    @Test
    public void testDecode3x3(){
        cipherText = "AIJYGKAVSMKULKISFWSUMNCZAVSPNX";
        key = "PROCEDURE";
        assertEquals("PLEASEWORKIMBEGGINGYOUTOWORKZZ", Hill.decode(cipherText,key));
    }

    @Test
    public void testDecode4x4(){
        cipherText = "EHQAMSQQWRLEWNXSGSZCMHMJITOV";
        key = "COUNTEROFFENSIVE";
        assertEquals("PLEASEWORKIMBEGGINGYOUTOWORK", Hill.decode(cipherText,key));
    }

    @Test
    public void testIncompleteKey() {

    }
}
