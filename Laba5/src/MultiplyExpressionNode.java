import java.util.ArrayList;

public class MultiplyExpressionNode implements Node {

    ArrayList<Node> childrenNodes;

    MultiplyExpressionNode() {
        childrenNodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        childrenNodes.add(node);
    }

    @Override
    public int getType() {
        return Node.MULTIPLY_EXPRESSIONS;
    }
}
