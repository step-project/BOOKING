package booking;

import java.io.PrintStream;
import java.util.Scanner;

public class SystemConsole implements Console {

    private final Scanner in = new Scanner(System.in);
    private final PrintStream out = System.out;

    @Override
    public void printLn(String s) {
        out.println(s);
    }

    @Override
    public String readLn() {
        return in.nextLine().trim();
    }

    @Override
    public String readNotEmpty() {
        String input = "";
        while (input.length() == 0) {
            input = in.nextLine();
        }
        return input.trim();
    }
}
