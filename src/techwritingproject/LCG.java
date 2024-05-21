package techwritingproject;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class LCG {

    private long first;
    private long second;
    private long third;

    public LCG() {

        this.first = 25214903917L;
        this.second = 11L;
        this.third = (long) Math.pow(2.0, 48.0);

    }

    public void writeByte(BufferedOutputStream writer) throws IOException {

        long number = 13;

        number *= first;
        number += second;
        number %= third;

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

            number *= first;
            number += second;
            number %= third;

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
