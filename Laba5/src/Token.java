public interface Token {
    int OPERATION_OPEN_BRACKET = 1;
    int OPERATION_CLOSE_BRACKET = 2;
    int OPERATION_ADD = 3;
    int OPERATION_SUBTRACT = 4;
    int OPERATION_MULTIPLY = 5;
    int OPERATION_DIVIDE = 6;

    int VALUE_CONSTANT = 20;

    int FUNCTION_IF = 30;

    int getType();
    double getValue() throws Exception;
    String toString();
}
