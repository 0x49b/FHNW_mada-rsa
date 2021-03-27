import ch.fhnw.mada.fthievent.Decryptor;
import ch.fhnw.mada.fthievent.Encryptor;
import ch.fhnw.mada.fthievent.Generate;

/**
 * RSA Implementierung von Florian Thiévent
 * <p>
 * Verwendung:
 * - Sollen beide Schlüsselpaare generiert, ein Text verschlüsselt und wieder entschlüsselt werden, müssen alle Teile ausgeführt werden
 * - Soll ein Chiffre Text mit einem Schlüssel entschlüsselt werden, müssen die Files in den root kopiert werden als chiffre.txt und sk.txt. Dann die Zeilen 18-22 auskommentieren.
 * - Das Programm arbeitet mit 2048 Bit Schlüsseln. Es ist möglich, dass im Generate Konstrukor eine ander Bit Länge angegeben wird.
 */
public class RSAMain {
    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        /**
        Generate teil1 = new Generate();
        teil1.generate();

        Encryptor teil2 = new Encryptor();
        teil2.encrypt();
        */
        Decryptor teil3 = new Decryptor();
        teil3.decrypt();

        long endTime = System.currentTimeMillis();
        System.out.println("RSA Implementierung brauchte " + (endTime - startTime) + " millisekunden.");

    }
}

