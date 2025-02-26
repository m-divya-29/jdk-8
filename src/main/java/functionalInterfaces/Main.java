package functionalInterfaces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) {
        //read multi line behavior --
        System.out.println(processFile( (BufferedReader bf) -> {
            return bf.readLine() + bf.readLine();
        }));

        //read single line behavior --
        System.out.println(processFile( (BufferedReader bf) -> {
            return bf.readLine();
        }));
    }

    public  static String processFile(IBuffReaderProcessor iBuffReaderProcessor) {
        try(BufferedReader bf = new BufferedReader(new FileReader("pom.xml"))) {
            return iBuffReaderProcessor.process(bf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
