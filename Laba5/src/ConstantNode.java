public class ConstantNode implements Node {

    double value = 0.0;

    ConstantNode(double value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return Node.CONSTANT;
    }
}
