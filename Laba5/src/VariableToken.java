public class VariableToken implements Token {

    String name;

    VariableToken(String name) {
        this.name = name;
    }

    @Override
    public int getType() {
        return Token.VARIABLE;
    }

    @Override
    public double getValue() throws Exception {
        throw new Exception("getValue method is not valid for variable token");
    }

    @Override
    public String toString() {
        return "Variable, name: " + this.name;
    }
}
