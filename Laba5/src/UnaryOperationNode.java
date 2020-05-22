public class UnaryOperationNode implements Node {

    Node child;

    UnaryOperationNode(Node child) {
        this.child = child;
    }

    @Override
    public int getType() {
        return Node.UNARY_EXPRESSION;
    }
}
