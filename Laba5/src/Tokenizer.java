import java.util.ArrayList;
import java.util.TreeMap;

public class Tokenizer {

    String input = "";
    TreeMap<String, Double> variablesMap = new TreeMap<>();

    Tokenizer(String input, TreeMap<String, Double> variablesMap) {
        this.input = input;
        this.variablesMap = variablesMap;
    }

    public ArrayList<Token> getTokens() {
        ArrayList<Token> tokens = new ArrayList<>();

        String temp = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ')
                continue;

            if (isEndOfLine(c)) {
                if (!temp.equals("")) {
                    if (isDouble(temp)) {
                        //add saved double variable
//                        System.out.println("double: " + c);
                        Double doubleVariable = Double.parseDouble(temp);
                        ConstantToken variableToken = new ConstantToken(doubleVariable);
                        tokens.add(variableToken);
                        temp = "";
                    } else {
                        //add saved variable like constant value
//                        Double doubleVariable = variablesMap.get(temp);
                        VariableToken variableToken = new VariableToken(temp);
                        tokens.add(variableToken);
                        temp = "";
                    }
                }
                break;
            }

            if (c == '>') {
                String variableName = "";
                for (int j = i + 1; j < input.length() - 1; j++) {
                    variableName += input.charAt(j);
                }
                FunctionToken functionToken = new FunctionToken(">");
                tokens.add(functionToken);
                VariableToken variableToken = new VariableToken(variableName);
                tokens.add(variableToken);
                break;
            }

            if (c == '=') {
//                System.out.println("function: " + c);
                if (!temp.equals("")) {
                    VariableToken variableToken = new VariableToken(temp);
                    tokens.add(variableToken);
                    temp = "";
                }
                FunctionToken functionToken = new FunctionToken("=");
                tokens.add(functionToken);
                temp = "";
                continue;
            }

            if (isOperation(c)) {
//                System.out.println("operation: " + c);
                if (!temp.equals("")) {
                    if (isDouble(temp)) {
                        //add saved double variable
//                        System.out.println("double: " + c);
                        Double doubleVariable = Double.parseDouble(temp);
                        ConstantToken variableToken = new ConstantToken(doubleVariable);
                        tokens.add(variableToken);
                        temp = "";
                    } else {
                        //add saved variable like constant value
                        VariableToken variableToken = new VariableToken(temp);
                        tokens.add(variableToken);
                        temp = "";
                    }
                }

                //add operator token
                OperationToken operationToken = new OperationToken(c);
                tokens.add(operationToken);

                continue;
            }

            temp += c;

//            if (isOperation(c) || isEndOfLine(c)) {
//                if (isDouble(temp)) {
//                    // get double value like 10.0
//
//                } else if (temp.length() > 0) {
//                    // get value of string variable like abc from Map
//                    double variableValue = variablesMap.get(temp);
//                    tokens.add(new ConstantToken(variableValue));
//                }
//                // dont add ';' to tokens
//                if (isEndOfLine(c))
//                    break;
//                OperationToken token = new OperationToken(c);
//                tokens.add(token);
//                temp = "";
//            } else {
//                temp += c;
//            }
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

    private boolean isFunctionOperator(char c) {
//        if (str.equals("if") || str.equals("="))
//            return true;

        if (c == '=')
            return true;

        return false;
    }

    private boolean isOperation(char c) {
        if (c == '+' ||
            c == '-' ||
            c == '*' ||
            c == '^' ||
            c == '/' ||
            c == '(' ||
            c == ')')
            return true;

        return false;
    }

    private boolean isEndOfLine(char c) {
        if (c == ';')
            return true;

        return false;
    }

}
