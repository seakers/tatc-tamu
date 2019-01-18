package tatc.architecture.specifications;

public class QuantitativeValue {

    private final double minValue;
    private final double maxValue;

    public QuantitativeValue(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }
}
