public class BinaryOperationNode implements Node {

    Token operator = null;
    Node left;
    Node right;

    BinaryOperationNode(OperationToken operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public int getType() {
        return Node.BINARY_EXPRESSION;
    }
}
