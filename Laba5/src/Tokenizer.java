import java.util.ArrayList;

public class Tokenizer {

    String input = "";

    Tokenizer(String input) {
        this.input = input;
    }

    public ArrayList<Token> getTokens() {
        ArrayList<Token> tokens = new ArrayList<>();

        String temp = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ')
                continue;

            if (isOperation(c) || isEndOfLine(c)) {
                if (isDouble(temp)) {
                    // get double value like 10.0
                    Double doubleVariable = Double.parseDouble(temp);
                    Token<Double> variableToken = new Token<>(doubleVariable);
                    tokens.add(variableToken);
                } else if (temp.length() > 0) {
                    // get string variable like abc
                    Token<String> variableToken = new Token<>(temp);
                    tokens.add(variableToken);
                }
                // dont add ';' to tokens
                if (isEndOfLine(c))
                    break;
                Token<Character> token = new Token<>(c);
                tokens.add(token);
                temp = "";
            } else {
                temp += c;
            }
        }

        return tokens;
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperation(char c) {
        if (c == '+' ||
            c == '-' ||
            c == '*' ||
            c == '^' ||
            c == '/' ||
            c == '(' ||
            c == ')') {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEndOfLine(char c) {
        if (c == ';')
            return true;
        else
            return false;
    }

}
