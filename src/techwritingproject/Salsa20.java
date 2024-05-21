package techwritingproject;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class Salsa20 {

    private int numberWords;
    private int numberRounds;
    private long fixed1;
    private long fixed2;
    private long fixed3;
    private long fixed4;
    private long nonce1;
    private long nonce2;
    private long counter1;
    private long counter2;

    public Salsa20() {

        this.numberWords = 16;
        this.numberRounds = 20;
        this.fixed1 = 0x6578707165787071L;
        this.fixed2 = 0x6E6420336E642033L;
        this.fixed3 = 0x322D6279322D6279L;
        this.fixed4 = 0x7465206B7465206BL;
        this.nonce1 = 0L;
        this.nonce2 = 0L;
        this.counter1 = 0L;
        this.counter2 = 0L;

    }

    public void writeByte(BufferedOutputStream writer) throws IOException {

        long[] numbers = {fixed1, 13, 13, 13, 13, fixed2, nonce1, nonce2, counter1, counter2, fixed3, 13, 13, 13, 13, fixed4};

        long[] state = new long[numberWords];

        System.arraycopy(numbers, 0, state, 0, numberWords);

        for (int i=0; i<numberRounds; i++) {

            QR(state, 0, 4, 8, 12);
            QR(state, 5, 9, 13, 1);
            QR(state, 10, 14, 2, 6);
            QR(state, 15, 3, 7, 11);

            QR(state, 0, 1, 2, 3);
            QR(state, 5, 6, 7, 4);
            QR(state, 10, 11, 8, 9);
            QR(state, 15, 12, 13, 14);

        }

        for (int i=0; i<numberWords; i++) {
            numbers[i] += state[i];
        }

        for (int i=1; i<5; i++) {

            writer.write((byte)(numbers[i]>>56));
            writer.write((byte)(numbers[i]>>48));
            writer.write((byte)(numbers[i]>>40));
            writer.write((byte)(numbers[i]>>32));
            writer.write((byte)(numbers[i]>>24));
            writer.write((byte)(numbers[i]>>16));
            writer.write((byte)(numbers[i]>>8));
            writer.write((byte)(numbers[i]));

        }

        for (int i=11; i<15; i++) {

            writer.write((byte)(numbers[i]>>56));
            writer.write((byte)(numbers[i]>>48));
            writer.write((byte)(numbers[i]>>40));
            writer.write((byte)(numbers[i]>>32));
            writer.write((byte)(numbers[i]>>24));
            writer.write((byte)(numbers[i]>>16));
            writer.write((byte)(numbers[i]>>8));
            writer.write((byte)(numbers[i]));

        }

    }

    public void writeBytes(BufferedOutputStream writer, int numberBytes) throws IOException {

        long[] numbers = {fixed1, 13, 13, 13, 13, fixed2, nonce1, nonce2, counter1, counter2, fixed3, 13, 13, 13, 13, fixed4};

        long number = 13;

        for (int i=0; i<numberBytes; i++) {

            long[] state = new long[numberWords];

            System.arraycopy(numbers, 0, state, 0, numberWords);

            for (int j=0; j<numberRounds; j++) {

                QR(state, 0, 4, 8, 12);
                QR(state, 5, 9, 13, 1);
                QR(state, 10, 14, 2, 6);
                QR(state, 15, 3, 7, 11);

                QR(state, 0, 1, 2, 3);
                QR(state, 5, 6, 7, 4);
                QR(state, 10, 11, 8, 9);
                QR(state, 15, 12, 13, 14);

            }

            for (int j=0; j<numberWords; j++) {
                numbers[j] += state[j];
            }

            for (int j=1; j<5; j++) {

                writer.write((byte)(numbers[j]>>56));
                writer.write((byte)(numbers[j]>>48));
                writer.write((byte)(numbers[j]>>40));
                writer.write((byte)(numbers[j]>>32));
                writer.write((byte)(numbers[j]>>24));
                writer.write((byte)(numbers[j]>>16));
                writer.write((byte)(numbers[j]>>8));
                writer.write((byte)(numbers[j]));

            }

            for (int j=11; j<15; j++) {

                writer.write((byte)(numbers[j]>>56));
                writer.write((byte)(numbers[j]>>48));
                writer.write((byte)(numbers[j]>>40));
                writer.write((byte)(numbers[j]>>32));
                writer.write((byte)(numbers[j]>>24));
                writer.write((byte)(numbers[j]>>16));
                writer.write((byte)(numbers[j]>>8));
                writer.write((byte)(numbers[j]));

            }

        }

    }

    private void QR(long[] numbers, int first, int second, int third, int fourth) {

        numbers[second] ^= ROTL(numbers[first]+numbers[fourth], 7);
        numbers[third] ^= ROTL(numbers[second]+numbers[first], 9);
        numbers[fourth] ^= ROTL(numbers[third]+numbers[second], 13);
        numbers[first] ^= ROTL(numbers[fourth]+numbers[third], 18);

    }

    private long ROTL(long first, long second) {

        long results = (first<<second) | (first>>(64-second));
        return results;

    }

}
