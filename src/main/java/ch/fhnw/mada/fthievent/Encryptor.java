package ch.fhnw.mada.fthievent;

import ch.fhnw.mada.fthievent.keys.PublicKey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Encryptor {

    private final String        FILENAME_IN  = "text.txt";
    private final String        FILENAME_OUT = "chiffre.txt";
    private final StringBuilder encrypted    = new StringBuilder();

    public void encrypt() throws IOException {

        System.out.println("\n--- Starte Encryptor für den Text aus " + Paths.get(".").toAbsolutePath().normalize().toString() + System.getProperty("file.separator") + FILENAME_IN + " ---\n");

        PublicKey pk = new PublicKey();
        pk.read();

        BigInteger n = pk.getN();
        BigInteger e = pk.getE();

        String text = read();
        BigInteger[] asciitext = toASCII(text);


        for (BigInteger x : asciitext) {
            encrypted.append(Helper.encrypt(x, e, n));
            encrypted.append(",");
        }
        Files.write(Paths.get(FILENAME_OUT), encrypted.toString().getBytes());
        System.out.println("\n--- Encryptor ist fertig, der Chiffre Text findet sich unter " + Paths.get(".").toAbsolutePath().normalize().toString() + System.getProperty("file.separator") + FILENAME_OUT + " ---");

    }

    public String read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME_IN));
        return reader.readLine();
    }

    private BigInteger[] toASCII(String text) {
        BigInteger[] asciitext = new BigInteger[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciitext[i] = new BigInteger(String.valueOf((int) text.charAt(i)));
        }
        System.out.println(Arrays.toString(asciitext));
        return asciitext;
    }
}
