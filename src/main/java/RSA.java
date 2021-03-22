import java.math.BigInteger;

public class RSA {

    public Boolean generatePrim(int n) {
        return new BigInteger(String.valueOf(n)).isProbablePrime(n / 2);
    }

}
