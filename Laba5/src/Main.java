import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("./Laba5/src/input2.txt"))) {
            String input = scanner.nextLine();
            Tokenizer tokenizer = new Tokenizer(input, new TreeMap<>());
            ArrayList<Token> tokens = tokenizer.getTokens();
            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
