package ch.fhnw.mada.fthievent.keys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

// Klasse für den öffentlichen Schlüssel
public class PublicKey {
    private BigInteger n, e;
    private final String FILENAME = "pk.txt";

    public PublicKey() {
    }

    public PublicKey(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    public BigInteger getN() {
        return n;
    }
    public BigInteger getE() {
        return e;
    }

    public void save() throws IOException {
        Files.write(Paths.get(FILENAME), ("(" + n + "," + e + ")").getBytes());
    }

    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        String[] ne = reader.readLine().split(",");

        this.n = new BigInteger(ne[0].substring(1));
        this.e = new BigInteger(ne[1].substring(0, ne[1].length()-1));
    }
}
