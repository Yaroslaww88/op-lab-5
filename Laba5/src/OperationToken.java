public class OperationToken implements Token {

    private char operation;
    private int operationType = 1;

    OperationToken(char operation, int type) {
        this.operation = operation;
        this.operationType = type;
    }

    OperationToken(char operation) {
        this.operation = operation;

        switch (operation) {
            case '(':
                operationType = Token.OPERATION_OPEN_BRACKET;
                break;
            case ')':
                operationType = Token.OPERATION_CLOSE_BRACKET;
                break;
            case '+':
                operationType = Token.OPERATION_ADD;
                break;
            case '-':
                operationType = Token.OPERATION_SUBTRACT;
                break;
            case '*':
                operationType = Token.OPERATION_MULTIPLY;
                break;
            case '/':
                operationType = Token.OPERATION_DIVIDE;
                break;
        }
    }

    @Override
    public int getType() {
        return this.operationType;
    }

    @Override
    public double getValue() throws Exception {
        throw new Exception("getValue method is not valid for operation token");
    }

    @Override
    public String toString() {
        return "Operation: " + this.operation;
    }
}
