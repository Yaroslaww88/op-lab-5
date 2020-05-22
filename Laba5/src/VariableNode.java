public class VariableNode implements Node {

    String name;

    VariableNode(String name) {
        this.name = name;
    }

    @Override
    public int getType() {
        return Node.VARIABLE;
    }

    @Override
    public String toString() {
        return "VariableNode name: " + name;
    }
}
