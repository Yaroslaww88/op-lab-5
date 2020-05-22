import java.util.ArrayList;
import java.util.Stack;

public class RPN {
    public static ArrayList<Token> getRPN(ArrayList<Token> tokens) {
        ArrayList<Token> resultToken = new ArrayList<>();
        Stack<Token> operationsStack = new Stack<>();

        for (int i = 0; i < tokens.size(); i++) {
            Token currentToken = tokens.get(i);
            if (!isOperator(currentToken)) {
                resultToken.add(currentToken);
                continue;
            }

            if (currentToken.getType() == Token.OPERATION_OPEN_BRACKET) {
                operationsStack.push(currentToken);
            } else if (currentToken.getType() == Token.OPERATION_CLOSE_BRACKET) {
                while (operationsStack.peek().getType() != Token.OPERATION_OPEN_BRACKET) {
                    //Kappa
                    Token popToken = operationsStack.pop();
                    resultToken.add(popToken);
                }
                operationsStack.pop();
            } else {
                if (currentToken.getType() == Token.OPERATION_SUBTRACT &&
                        (i == 0 || tokens.get(i-1).getType() == Token.OPERATION_OPEN_BRACKET)) {
                    operationsStack.push(new OperationToken('-', Token.OPERATION_UNARY_MINUS)); //May be Token.OPERATION_UNARY_MINUS must be
                } else {
//                    if (currentToken.getType() == Token.OPERATION_SUBTRACT)
//                        currentToken = new OperationToken('-', Token.OPERATION_UNARY_MINUS);
                    while (!operationsStack.isEmpty() && operationsStack.peek().getType() != Token.OPERATION_OPEN_BRACKET &&
                        getTokenPrecedence(currentToken) < getTokenPrecedence(operationsStack.peek())) {
                        //kappa
                        Token popToken = operationsStack.pop();
                        resultToken.add(popToken);
                    }
                    operationsStack.push(currentToken);
                }
            }
        }

        while (!operationsStack.isEmpty()) {
            Token popToken = operationsStack.pop();
            resultToken.add(popToken);
        }

        return resultToken;
    }

    private static boolean isOperator(Token token) {
        if (token.getType() == Token.OPERATION_ADD ||
            token.getType() == Token.OPERATION_SUBTRACT ||
            token.getType() == Token.OPERATION_MULTIPLY ||
            token.getType() == Token.OPERATION_DIVIDE ||
            token.getType() == Token.OPERATION_OPEN_BRACKET ||
            token.getType() == Token.OPERATION_CLOSE_BRACKET)
            return true;
        return false;
    }

    private static int getTokenPrecedence(Token token) {
        if (token.getType() == Token.OPERATION_MULTIPLY || token.getType() == Token.OPERATION_DIVIDE)
            return 3;
        else if (token.getType() == Token.OPERATION_ADD || token.getType() == Token.OPERATION_SUBTRACT)
            return 2;
        else if (token.getType() == Token.OPERATION_OPEN_BRACKET)
            return 1;
        else if (token.getType() == Token.OPERATION_CLOSE_BRACKET)
            return -1;
        else if (token.getType() == Token.OPERATION_POWER)
            return 3;
        return 0;
    }

}
