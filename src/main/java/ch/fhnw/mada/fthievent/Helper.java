package ch.fhnw.mada.fthievent;

import java.math.BigInteger;

/**
 * Die Helperklasse ist eigentlich nur eine Abstraktion um encrypt und decrypt mit den "bekannten" Variablen anzubieten.
 * Also für encrypt xᵉ mod n und für decrypt yᵈ mod n
 */
public class Helper {

    static BigInteger encrypt(BigInteger x, BigInteger e, BigInteger n) {
        return fastExponentiate(x, e, n);
    }

    static BigInteger decrypt(BigInteger y, BigInteger d, BigInteger n) {
        return fastExponentiate(y, d, n);
    }

    // Es wäre einfacher wenn man das modPow von BigInteger verwenden würde :-)
    // Mit Hilfe von https://stackoverflow.com/questions/22392541/modular-exponentiation-in-java-the-algorithm-gives-a-wrong-answer
    public static BigInteger fastExponentiate(BigInteger base, BigInteger power, BigInteger modulo) {
        BigInteger result = new BigInteger("1");
        base = base.mod(modulo);
        for (int i = 0; i < power.bitLength(); i++) {
            if (power.testBit(i)) {
                result = result.multiply(base).mod(modulo);
            }
            base = base.multiply(base).mod(modulo);
        }
        return result;
    }
}
