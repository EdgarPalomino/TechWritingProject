package techwritingproject;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class SRG {

    private long first;
    private long second;
    private long third;

    public SRG() {

        this.first = 13L;
        this.second = 17L;
        this.third = 5L;

    }

    public void writeByte(BufferedOutputStream writer) throws IOException {

        long number = 13;

        number ^= number << first;
        number ^= number >>> second;
        number ^= number << third;

        writer.write((byte)(number>>56));
        writer.write((byte)(number>>48));
        writer.write((byte)(number>>40));
        writer.write((byte)(number>>32));
        writer.write((byte)(number>>24));
        writer.write((byte)(number>>16));
        writer.write((byte)(number>>8));
        writer.write((byte)(number));

    }

    public void writeBytes(BufferedOutputStream writer, int numberBytes) throws IOException {

        long number = 13;

        for (int i=0; i<numberBytes; i++) {

            number ^= number << first;
            number ^= number >>> second;
            number ^= number << third;

            writer.write((byte)(number>>56));
            writer.write((byte)(number>>48));
            writer.write((byte)(number>>40));
            writer.write((byte)(number>>32));
            writer.write((byte)(number>>24));
            writer.write((byte)(number>>16));
            writer.write((byte)(number>>8));
            writer.write((byte)(number));

        }

    }

}
