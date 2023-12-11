public class TanH implements TransferFunction {
    @Override
    public double evaluate(double value) {
        return Math.tanh(value);
    }

    @Override
    public double evaluateDer(double value) {
        return 1.0 - evaluate(value) * evaluate(value);
    }
}
