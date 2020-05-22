import java.util.ArrayList;
import java.util.HashMap;

public class MultiplyExpressionNode implements Node {

    public static HashMap<String, Double> map = new HashMap<>();
    ArrayList<Node> childrenNodes;

    MultiplyExpressionNode() {
        childrenNodes = new ArrayList<>();
    }

    public Double calculateExpressions() throws Exception {
        for (Node tree : childrenNodes) {
            if (tree.getType() == Node.EQUALITY_SIGN) {
                EqualityNode equalityNode = (EqualityNode)tree;
                String name = equalityNode.variableNode.name;
                Double value = calculateTree(equalityNode.valueNode);
                map.put(name, value);
                System.out.println("ExpressionNode Name: " + name + " Value: " + value);
                continue;
            }
            if (tree.getType() == Node.BINARY_EXPRESSION) {
                BinaryOperationNode binaryOperationNode = (BinaryOperationNode)tree;
                Double result = calculateTree(binaryOperationNode);
                System.out.println("Result: " + result);
                return result;
            }
            if (tree.getType() == Node.VARIABLE) {
                return map.get(((VariableNode)tree).name);
            }
            throw new Exception("Note type is not EQUALITY_SIGN and BINARY_EXPRESSION " + tree.getType());
        }

        throw new Exception("Note type is not EQUALITY_SIGN and BINARY_EXPRESSION");
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

    public void addNode(Node node) {
        childrenNodes.add(node);
    }

    @Override
    public int getType() {
        return Node.MULTIPLY_EXPRESSIONS;
    }
}
