package techwritingproject;

import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class TechWritingProject {

    public static void main(String[] args) {

        LCG lcgGenerator = new LCG();
        SRG srgGenerator = new SRG();
        Salsa20 salsa20Generator = new Salsa20();

        try {

            FileOutputStream lcgFile = new FileOutputStream("./tests/LCG.txt");
            FileOutputStream srgFile = new FileOutputStream("./tests/SRG.txt");
            FileOutputStream salsa20File = new FileOutputStream("./tests/Salsa20.txt");

            BufferedOutputStream lcgWriter = new BufferedOutputStream(lcgFile);
            BufferedOutputStream srgWriter = new BufferedOutputStream(srgFile);
            BufferedOutputStream salsa20Writer = new BufferedOutputStream(salsa20File);

            lcgGenerator.writeBytes(lcgWriter, 1024);
            srgGenerator.writeBytes(srgWriter, 1024);
            salsa20Generator.writeBytes(salsa20Writer, 128);

            lcgWriter.close();
            srgWriter.close();
            salsa20Writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
