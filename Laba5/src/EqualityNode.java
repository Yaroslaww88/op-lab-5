public class EqualityNode implements Node {

    VariableNode variableNode;
    Node valueNode;

    EqualityNode(VariableNode variableNode, Node valueNode) {
        this.valueNode = valueNode;
        this.variableNode = variableNode;
    }

    @Override
    public int getType() {
        return Node.EQUALITY_SIGN;
    }

    @Override
    public String toString() {
        return "EqualityNode VariableNode: " + variableNode + " valueNode: " + valueNode;
    }
}
