import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class RSAMain {

    public static void main(String[] args) throws IOException{

        final int BIT_LENGTH = 2048;


        System.out.println("Starting RSA Public Key");
        // get two new primenumbers based on the BIT_LENGTH
        BigInteger p = new BigInteger(BIT_LENGTH, new Random());
        BigInteger q = new BigInteger(BIT_LENGTH, new Random());
        System.out.println("Prime Number p: " + p);
        System.out.println("Prime Number q: " + q);

        // define n by multiply p with q
        BigInteger n = p.multiply(q);
        System.out.println("n: " + n);

        // PHI(N)
        BigInteger phiofn = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = new BigInteger(BIT_LENGTH, new Random());
        while (e.compareTo(phiofn) >= 0) {
            e = new BigInteger(BIT_LENGTH, new Random());
        }

        // Make PublicKey Class to "save"
        PublicKey publickey = new PublicKey(n,e,phiofn);

        // Create PrivateKey
        System.out.println("RSA Private Key");

        BigInteger d = extendedEuclid(phiofn, e);
        PrivateKey privatekey = new PrivateKey(n,d);

        publickey.save();
        privatekey.save();

    }

    public static BigInteger extendedEuclid(BigInteger a, BigInteger b) {
        while(!b.equals(BigInteger.ZERO)){
            BigInteger intermediate = a % b;
            a = b;
            b = c;
        }
        return a;
    }
}

class PublicKey {
    private BigInteger n, e, phiofN;

    public PublicKey(BigInteger n, BigInteger e, BigInteger phiofn) {
        this.n = n;
        this.e = e;
        this.phiofN = phiofn;
    }

    public BigInteger getN() { return n; }
    public BigInteger getE() { return e; }
    public BigInteger getPhiofN() { return phiofN; }
    public void save() throws IOException {
        Files.write(Paths.get("pk.txt"), ("("+n+","+e+")").getBytes());
    }
}


class PrivateKey{
    private BigInteger n, d;
    public PrivateKey(BigInteger n, BigInteger d){
        this.n = n;
        this.d = d;
    }

    public BigInteger getN() { return n; }
    public BigInteger getD() { return d; }
    public void save() throws IOException{
        Files.write(Paths.get("sk.txt"), ("("+n+","+d+")").getBytes());
    }
}
