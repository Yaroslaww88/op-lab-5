public class ConstantToken implements Token {

    private double value = 0;

    ConstantToken(double value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return Token.VALUE_CONSTANT;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Constant: " + this.value;
    }
}
