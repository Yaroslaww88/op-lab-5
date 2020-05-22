import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static HashMap<String, Double> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(new File("./Laba5/src/input2.txt"))) {
            String input = scanner.nextLine();
            Tokenizer tokenizer = new Tokenizer(input, new TreeMap<>());
            ArrayList<Token> tokens = tokenizer.getTokens();
            for (Token token : tokens) {
                System.out.println(token);
            }
            System.out.println("-------------------------------------------");
            ArrayList<Token> rpnTokens = RPN.getRPN(tokens);
            for (Token token : rpnTokens) {
                System.out.println(token);
            }

            MultiplyExpressionNode mainNode = new MultiplyExpressionNode();
            Node node = getExpressionTree(rpnTokens);
            Double value = calculateTree(node);
            System.out.println(value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TODO: move to another class
    //TODO: add optimizations
    public static Node getExpressionTree(ArrayList<Token> expressionTokens) throws Exception {
        Stack<Node> stack = new Stack<>();
        for (Token token : expressionTokens) {
            if (!isOperator(token)) {
                if (token.getType() == Token.VARIABLE) {
                    VariableNode variableNode = new VariableNode(((VariableToken)token).name);
                    stack.push(variableNode);
                } else {
                    System.out.println(token.toString() + " " + token.getType());
                    ConstantNode constantNode = new ConstantNode(token.getValue());
                    stack.push(constantNode);
                }
            } else {
                Node rightNode = stack.pop();
                Node leftNode = stack.pop();
                //TODO: ADD OPTIMIZATIONS
                BinaryOperationNode binaryOperationNode = new BinaryOperationNode((OperationToken)token, leftNode, rightNode);
                stack.push(binaryOperationNode);
            }
        }
        return stack.pop();
    }

    public static Double calculateTree(Node tree) throws Exception {
        //TODO add setting variable Node, add Unary Operation Node
        if (tree.getType() == Node.BINARY_EXPRESSION) {
            Double leftValue = calculateTree(((BinaryOperationNode)tree).left);
            Double rightValue = calculateTree(((BinaryOperationNode)tree).right);
            OperationToken operationToken = (OperationToken)((BinaryOperationNode)tree).operator;
            if (operationToken.getType() == Token.OPERATION_ADD) {
                return  leftValue + rightValue;
            }
            if (operationToken.getType() == Token.OPERATION_SUBTRACT) {
                return  leftValue - rightValue;
            }
            if (operationToken.getType() == Token.OPERATION_MULTIPLY) {
                return  leftValue * rightValue;
            }
            if (operationToken.getType() == Token.OPERATION_DIVIDE) {
                return  leftValue / rightValue;
            }
        }
        if (tree.getType() == Node.VARIABLE) {
            String name = ((VariableNode)tree).name;
            return map.get(name);
        }
        if (tree.getType() == Node.CONSTANT) {
            return ((ConstantNode)tree).value;
        }

        throw new Exception("Type of Node is not defined: " + tree.getType());
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
