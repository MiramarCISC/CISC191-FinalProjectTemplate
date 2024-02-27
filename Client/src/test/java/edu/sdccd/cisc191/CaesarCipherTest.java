package edu.sdccd.cisc191;

import edu.sdccd.cisc191.ciphers.Caesar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        cipherText = "cdefghijklmnopqrstuvwxyzab";
        key = "2";
        assertEquals("abcdefghijklmnopqrstuvwxyz", Caesar.decode(cipherText, key).toLowerCase());
    }

    @Test
    public void testCryptanalysis() {
        cipherText = "Fn cqn Ynxyun xo cqn Dwrcnm Bcjcnb, rw Xamna cx oxav j vxan ynaonlc Dwrxw, nbcjkurbq Sdbcrln, rwbdan mxvnbcrl Cajwzdrurch, yaxermn oxa cqn lxvvxw mnonwbn, yaxvxcn cqn pnwnaju Fnuojan, jwm bnldan cqn Kunbbrwpb xo Urknach cx xdabnuenb jwm xda Yxbcnarch, mx xamjrw jwm nbcjkurbq cqrb Lxwbcrcdcrxw oxa cqn Dwrcnm Bcjcnb xo Jvnarlj.";
        key = "";
        assertEquals("We the People of the United States, in Order to form a more perfect Union, establish Justice, insure domestic Tranquility, provide for the common defense, promote the general Welfare, and secure the Blessings of Liberty to ourselves and our Posterity, do ordain and establish this Constitution for the United States of America."
        , Caesar.decode(cipherText, key));
    }
}
