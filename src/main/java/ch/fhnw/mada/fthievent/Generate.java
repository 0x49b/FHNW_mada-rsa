package ch.fhnw.mada.fthievent;

import ch.fhnw.mada.fthievent.keys.PrivateKey;
import ch.fhnw.mada.fthievent.keys.PublicKey;

import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.Random;

public class Generate {

    private int bitLength = 2048;

    public Generate(){}
    public Generate(int bit_length) {
        bitLength = bit_length;
    }

    public void generate() throws Exception {


        System.out.println("\n--- Starte Generierung eines Public & Private Schlüsselpaares mit " + bitLength + " Bit Länge---\n");
        // Generiere den öffentlichen RSA Schlüssel
        System.out.println("*** Starte mit dem  RSA Public Key");

        // Generiere zwei neue Primzahlen p, q mit der Bitlänge von BIT_LENGTH
        BigInteger p = BigInteger.probablePrime(bitLength, new Random());
        BigInteger q = BigInteger.probablePrime(bitLength, new Random());
        System.out.println("Prime Number p: " + p);
        System.out.println("Prime Number q: " + q);

        // Definiere n = p * q
        BigInteger n = p.multiply(q);
        System.out.println("n: " + n);

        // berechne phi(n)
        BigInteger phiofn = phiofn(p, q);

        // Lege ein e fest
        BigInteger e = BigInteger.probablePrime(bitLength, new Random());


        // lege eine Klasse PublicKey an --> wird gebraucht um zu speichern
        PublicKey publickey = new PublicKey(n, e);

        // Generiere den privaten RSA Schlüssel
        System.out.println("*** Starte mit dem RSA Private Key");

        BigInteger d = euclidextended(phiofn, e);
        System.out.println("d is " + d);

        // Lege eine Klasse PrivateKey an --> wird gebraucht um zu speichern
        PrivateKey privatekey = new PrivateKey(n, d);

        // speichere die beiden Schlüssel in pk.txt und sk.txt
        publickey.save();
        privatekey.save();

        System.out.println("\n--- Fertig mit generieren, die Schlüssel sind in " + Paths.get(".").toAbsolutePath().normalize().toString() + System.getProperty("file.separator") + "pk.txt [public] " +
                "und " + Paths.get(".").toAbsolutePath().normalize().toString() + System.getProperty("file.separator") + "sk.txt [private] ---");
    }

    // Berechne phi(n) mit den beiden gewählten Prim Zahlen
    public BigInteger phiofn(BigInteger p, BigInteger q) {
        return p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
    }

    // Erweiterter Euklid
    public BigInteger euclidextended(BigInteger a, BigInteger b) throws Exception {
        BigInteger a_ = a;
        BigInteger b_ = b;

        // Teste Vorbedingung: a ≥ b ≥ 0. Könnte mit assert gelöst werden, aber ich finde es schöner so, es sagt dem User mehr
        if (a.compareTo(b) < 0) {
            throw new Exception("Vorbedingung nicht erfüllt. a ist kleiner als b. Bitte korrigieren");
        }
        if (b.compareTo(new BigInteger("0")) < 1) {
            throw new Exception("Vorbedingung nicht erfüllt. b ist kleiner als 0. Bitte korrigieren");
        }


        BigInteger x1 = new BigInteger("0");
        BigInteger y0 = new BigInteger("0");
        BigInteger x0 = new BigInteger("1");
        BigInteger y1 = new BigInteger("1");
        BigInteger q;
        BigInteger r;
        BigInteger x0n;
        BigInteger y0n;

        while (!b_.equals(BigInteger.ZERO)) {
            q = a_.divide(b_);
            r = a_.mod(b_);
            a_ = b_;
            b_ = r;
            x0n = x0;
            y0n = y0;
            x0 = x1;
            y0 = y1;
            x1 = x0n.subtract((q.multiply(x1)));
            y1 = y0n.subtract((q.multiply(y1)));
        }


        // Teste Nachbedingung
        if (a_.compareTo((x0.multiply(a)).add(y0.multiply(b))) == 0) {
            // Teste ob y₀ kleiner als 0 ist, falls dies der Fall ist, dann rechne a (phi(n)) darauf, so dass es kein negatives d gibt
            if (y0.compareTo(BigInteger.ZERO) < 0) {
                return y0.add(a);
            }
            return y0;
        } else {
            throw new Exception("Nachbedingung [a' = ggt(a,b) = x0*a + y0*b] in erweiterter Euklid ist nicht erfüllt");
        }
    }
}
