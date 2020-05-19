public class FunctionToken implements Token {

    String function;

    FunctionToken(String function) {
        this.function = function;
    }

    @Override
    public int getType() {
        if (function.equals("if"))
            return Token.FUNCTION_IF;

        //TODO fix
        return -1;
    }

    @Override
    public double getValue() throws Exception {
        throw new Exception("getValue method is not valid for function token");
    }

    @Override
    public String toString() {
        return "Function: " + this.function;
    }
}
