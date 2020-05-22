public interface Node {
    int VARIABLE = 1;
    int CONSTANT = 2;
    int BINARY_EXPRESSION = 3;
    int UNARY_EXPRESSION = 4;
    int FUNCTION = 5;
    int MULTIPLY_EXPRESSIONS = 6;
    int EQUALITY_SIGN = 7;

    int getType();
    String toString();
}