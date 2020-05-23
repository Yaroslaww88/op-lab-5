import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(new File("./Laba5/src/input2.txt"))) {

            //Main tree
            MultiplyExpressionNode mainNode = new MultiplyExpressionNode();

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                Tokenizer tokenizer = new Tokenizer(input, new TreeMap<>());
                ArrayList<Token> tokens = tokenizer.getTokens();
//                System.out.println("Tokens: ");
//                for (Token token : tokens) {
//                    System.out.println(token);
//                }
                Boolean isSettingExpression = false;
                Boolean isReading = false;
                for (int i = 0; i < tokens.size(); i++) {
                    if (tokens.get(i).getType() == Token.FUNCTION_EQUALITY) {
                        if (i != 1) {
                            System.out.println("Equality is not second in line");
                            exit(1);
                        }
                        isSettingExpression = true;
                    }
                    if (tokens.get(i).getType() == Token.FUNCTION_READ) {
                        if (tokens.size() != 2) {
                            System.out.println("Reading line has more than two tokens");
                            exit(1);
                        }
                        String variableName = ((VariableToken)tokens.get(1)).name;
                        System.out.println("Enter variable with name: " + variableName);
                        Scanner consoleScanner = new Scanner(System.in);
                        Double variableValue = consoleScanner.nextDouble();
//                        Node valueNode = new ConstantNode(variableValue);
//                        VariableNode variableNode = new VariableNode(((VariableToken)tokens.get(1)).name);
//                        Node node = new EqualityNode(variableNode, valueNode);
//                        mainNode.addNode(node);
                        tokens.clear();
                        tokens.add(new VariableToken(variableName));
                        tokens.add(new FunctionToken("="));
                        tokens.add(new ConstantToken(variableValue));
                        isSettingExpression = true;
                    }
                }
                if (isReading) {
                    continue;
                }
                if (isSettingExpression) {
                    VariableToken variableToken = (VariableToken)tokens.get(0);
                    ArrayList<Token> expressionTokens = new ArrayList<>();
                    for (int i = 2; i < tokens.size(); i++) {
                        expressionTokens.add(tokens.get(i));
                    }
                    tokens = expressionTokens;
                    ArrayList<Token> rpnTokens = RPN.getRPN(tokens);
                    Node valueNode = getExpressionTree(rpnTokens);
                    VariableNode variableNode = new VariableNode(variableToken.name);
                    Node node = new EqualityNode(variableNode, valueNode);
                    mainNode.addNode(node);
                    continue;
                }
                ArrayList<Token> rpnTokens = RPN.getRPN(tokens);
                Node node = getExpressionTree(rpnTokens);
                mainNode.addNode(node);
            }

            System.out.println(mainNode.calculateExpressions());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TODO: move to another class
    //TODO: add optimizations
    public static Node getExpressionTree(ArrayList<Token> expressionTokens) throws Exception {
        Stack<Node> stack = new Stack<>();
        for (Token token : expressionTokens) {
            if (token.getType() == Token.OPERATION_UNARY_MINUS) {
                Node node = stack.pop();
                UnaryOperationNode unaryOperationNode = new UnaryOperationNode(node);
                stack.push(unaryOperationNode);
                continue;
            }
            if (!isOperator(token)) {
                if (token.getType() == Token.VARIABLE) {
                    VariableNode variableNode = new VariableNode(((VariableToken)token).name);
                    stack.push(variableNode);
                } else {
//                    System.out.println(token.toString() + " " + token.getType());
                    ConstantNode constantNode = new ConstantNode(token.getValue());
                    stack.push(constantNode);
                }
            } else {
                Node rightNode = stack.pop();
                Node leftNode = stack.pop();
//                if (token.getType() == Token.OPERATION_MULTIPLY) {
//                    if (leftNode.getType() == Node.CONSTANT) {
//                        if ( ((ConstantNode)leftNode).value == 0.0 ) {
//                            stack.push(new ConstantNode(0.0));
//                            continue;
//                        }
//                    }
//                    if (rightNode.getType() == Node.CONSTANT) {
//                        if ( ((ConstantNode)rightNode).value == 0.0 ) {
//                            stack.push(new ConstantNode(0.0));
//                            continue;
//                        }
//                    }
//                }
//                if (token.getType() == Token.OPERATION_DIVIDE) {
//                    if (leftNode.getType() == Node.CONSTANT) {
//                        if ( ((ConstantNode)leftNode).value == 1.0 ) {
//                            stack.push(rightNode);
//                            continue;
//                        }
//                    }
//                    if (rightNode.getType() == Node.CONSTANT) {
//                        if ( ((ConstantNode)rightNode).value == 1.0 ) {
//                            stack.push(leftNode);
//                            continue;
//                        }
//                    }
//                }
                BinaryOperationNode binaryOperationNode = new BinaryOperationNode((OperationToken)token, leftNode, rightNode);
                stack.push(binaryOperationNode);
            }
        }
        return stack.pop();
    }

    //TODO: move to Utils
    private static boolean isOperator(Token token) {
        if (token.getType() == Token.OPERATION_ADD ||
            token.getType() == Token.OPERATION_SUBTRACT ||
            token.getType() == Token.OPERATION_MULTIPLY ||
            token.getType() == Token.OPERATION_DIVIDE)
            return true;
        return false;
    }
}
