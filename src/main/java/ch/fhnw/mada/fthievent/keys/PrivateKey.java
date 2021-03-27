package ch.fhnw.mada.fthievent.keys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

// Klasse für den privaten Schlüssel
public class PrivateKey {
    private BigInteger n, d;
    private final String FILENAME = "sk.txt";

    public PrivateKey() {
    }

    public PrivateKey(BigInteger n, BigInteger d) {
        this.n = n;
        this.d = d;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getD() {
        return d;
    }

    public void save() throws IOException {
        Files.write(Paths.get(FILENAME), ("(" + n + "," + d + ")").getBytes());
    }

    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        String[] ne = reader.readLine().split(",");

        this.n = new BigInteger(ne[0].substring(1));
        this.d = new BigInteger(ne[1].substring(0, ne[1].length() - 1));
    }
}
