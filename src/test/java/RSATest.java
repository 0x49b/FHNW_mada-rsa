import ch.fhnw.mada.fthievent.Generate;
import ch.fhnw.mada.fthievent.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class RSATest {

    @Test
    public void testExtendedEuklid() throws Exception {
        Generate generate = new Generate();
        BigInteger y0 = generate.euclidextended(new BigInteger("20"), new BigInteger("3"));
        Assertions.assertEquals(7, y0.intValue());
    }

    @Test
    public void testExtendedEuklidVorbedingungAkleinerB() {
        Generate generate = new Generate();
        Throwable exception = Assertions.assertThrows(Exception.class, () -> {
            generate.euclidextended(new BigInteger("3"), new BigInteger("20"));
        });

        String expected = "Vorbedingung nicht erfüllt. a ist kleiner als b. Bitte korrigieren";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expected));
    }

    @Test
    public void testExtendedEuklidVorbedingungBkleinerNull() {
        Generate generate = new Generate();
        Throwable exception = Assertions.assertThrows(Exception.class, () -> {
            generate.euclidextended(new BigInteger("3"), new BigInteger("-1"));
        });

        String expected = "Vorbedingung nicht erfüllt. b ist kleiner als 0. Bitte korrigieren";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expected));

    }

    @Test
    public void testPhi() {
        Generate tester = new Generate();
        BigInteger phiofn = tester.phiofn(new BigInteger("3"), new BigInteger("11"));
        Assertions.assertEquals(20, phiofn.intValue());
    }

    @Test
    public void testHelper(){
        BigInteger base = new BigInteger("5");
        BigInteger power = new BigInteger("22");
        BigInteger modulo = new BigInteger("11");
        Assertions.assertEquals(3, Helper.fastExponentiate(base, power, modulo).intValue());
    }
}
