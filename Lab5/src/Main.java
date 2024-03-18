import Runner.Runner;
import output.ConsolePrinter;

import java.io.IOException;
import java.io.PrintStream;


/**
 * @author Ivanov Ilya "iiilya12@yandex.ru"
 * @version 2.0
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Runner r = new Runner(new ConsolePrinter(new PrintStream(System.out)));
        r.runMethods(System.in, false);}
}
/*
доп:
streamChainer в коде
min by id
print ascending
remove any by salary
file writing


как можно больше нио
file reading
file writing
 */