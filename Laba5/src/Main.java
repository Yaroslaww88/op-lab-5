import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        parseTree.makeTree("+ / ^ 2 - 1 5 * 2 4 3");
        try (Scanner scanner = new Scanner(new File("./Laba5/src/input2.txt"))) {
            String input = scanner.nextLine();
            Tokenizer tokenizer = new Tokenizer(input);
            ArrayList<Token> tokens = tokenizer.getTokens();
            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
