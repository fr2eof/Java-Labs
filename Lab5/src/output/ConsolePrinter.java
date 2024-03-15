package output;

import java.io.PrintStream;

public class ConsolePrinter {
    private final PrintStream printStream;
    public ConsolePrinter(PrintStream printStream) {
        this.printStream = printStream;
    }
    /**
     * Prints the given object to the standard output stream
     *
     * @param toOut The object to print.
     */
    public void print(Object toOut) {
        System.out.print(toOut);
    }


    /**
     * Prints the given object to the console
     *
     * @param toOut The object to print to the console.
     */
    public void println(Object toOut) {
        System.out.println(toOut);
    }


    /**
     * Prints the error message to the console
     *
     * @param toOut The object to print out.
     */
    public void printError(Object toOut) {
        System.out.println("Error: " + toOut);
    }
}
