public class ConstantNode implements Node {

    double value = 0.0;

    ConstantNode(double value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return Node.CONSTANT;
    }

    @Override
    public String toString() {
        return "ConstantNode Value: " + value;
    }
}
