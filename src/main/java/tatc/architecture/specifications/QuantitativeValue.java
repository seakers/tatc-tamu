package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

public class QuantitativeValue {

    @SerializedName("@type")
    private final String _type="QuantitativeValue";
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
