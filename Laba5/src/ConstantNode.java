public class ConstantNode extends Node {
    double value;

    public ConstantNode(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public NodeTypes getType() {
        return NodeTypes.CONSTANT_NODE;
    }
}
