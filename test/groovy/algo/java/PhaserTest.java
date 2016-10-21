package algo.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Phaser;

/**
 * Created by andreytsarevskiy on 22/10/16.
 */
public class PhaserTest extends Assert{

    private static int lines = 10;
    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static StringBuffer randomAlphabet = new StringBuffer();

    @Test
    public void test() {

        final Phaser phaser = new Phaser() {
            protected boolean onAdvance(int phase, int parties) {
                // John Nash mode
				/* print "deviations"
				for (int i = 0; i < alphabet.length(); i++) {
					System.out.printf("%d ", randomAlphabet.indexOf(
							  alphabet.charAt(i) + "") - i);
				}
				System.out.println();
				*/
                System.out.println(randomAlphabet);
                //
                randomAlphabet = new StringBuffer();

                return phase >= lines; //loop count managing here
            }
        };

        // everyone have to wait for the main thread
        phaser.register();

        for (int i = 0; i < alphabet.length(); i++) {

            final char next = alphabet.charAt(i);
            phaser.register(); // ticket for the following thread

            new Thread() {
                public void run() {
                    do {
                        randomAlphabet.append(next);
                        phaser.arriveAndAwaitAdvance(); // checkout
                    } while ( !phaser.isTerminated() );
                }
            }.start();
        }

        System.out.println("Write this by hand and carry in the pocket:");
        // some additional preparations may be done here
        // release
        phaser.arriveAndDeregister();
    }


}
