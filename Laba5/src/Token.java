public class Token<T> {

    T value;

    Token (T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public TokenTypes getType() {
        if (value instanceof Double) {
            return TokenTypes.DOUBlE_VARIABLE;
        }
        if (value instanceof String) {
            return TokenTypes.STRING_VARIABLE;
        }
        return TokenTypes.OPERATION;
    }

    public String toString() {
        return getType().toString() + " " + this.value;
    }
}