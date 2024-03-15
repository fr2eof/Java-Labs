import Runner.Runner;
import output.ConsolePrinter;

import java.io.PrintStream;

/**
 * @author Ivanov Ilya "iiilya12@yandex.ru"
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Runner r = new Runner(new ConsolePrinter(new PrintStream(System.out)));
        r.run();
    }
}
/*
доп:
streamChainer в коде
как можно больше нио
buffers использовать в коде
https://docs.oracle.com/javase/8/docs/api/java/nio/package-summary.html
 */