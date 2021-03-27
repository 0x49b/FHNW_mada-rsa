package ch.fhnw.mada.fthievent;

import ch.fhnw.mada.fthievent.keys.PrivateKey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Decryptor {

    private final String        FILENAME_IN  = "chiffre.txt";
    private final String        FILENAME_OUT = "text-d.txt";
    private final StringBuilder decrypted    = new StringBuilder();

    public void decrypt() throws IOException {
        System.out.println("\n--- Starte Decryptor für den Text aus " + Paths.get(".").toAbsolutePath().normalize().toString() + System.getProperty("file.separator") + FILENAME_IN + " ---\n");

        PrivateKey sk = new PrivateKey();
        sk.read();

        BigInteger n = sk.getN();
        BigInteger d = sk.getD();

        String text = read();
        BigInteger[] chiffreArray = toChiffreArray(text);

        for (BigInteger y : chiffreArray) {
            BigInteger dec = Helper.decrypt(y, d, n);
            decrypted.append((char) dec.intValue());
        }

        Files.write(Paths.get(FILENAME_OUT), decrypted.toString().getBytes());
        System.out.println("*** Entschlüsselter Text: " + decrypted.toString() + " ***");
        System.out.println("--- Decryptor ist fertig, der Klartext findet sich unter " + Paths.get(".").toAbsolutePath().normalize().toString() + System.getProperty("file.separator") + FILENAME_OUT + " ---");

    }


    private String read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME_IN));
        return reader.readLine();
    }

    private BigInteger[] toChiffreArray(String text) {
        String[] textArr = text.split(",");
        BigInteger[] chiffreArr = new BigInteger[textArr.length];

        for (int i = 0; i < textArr.length; i++) {
            chiffreArr[i] = new BigInteger(textArr[i]);
        }
        return chiffreArr;
    }

}
