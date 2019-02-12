package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

/**
 *
 Defines a range of quantitative values with minimum and maximum values and either a step size of equally-spaced steps.
 Used by: Constellation, Orbit
 */
public class QuantitativeRange {

    @SerializedName("@type")
    private final String _type="QuantitativeRange";
    private final double minValue;
    private final double maxValue;
    private final double stepSize;
    private final int numberSteps;

    public QuantitativeRange(double minValue, double maxValue, double stepSize, int numberSteps) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.stepSize = stepSize;
        this.numberSteps = numberSteps;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getStepSize() {
        return stepSize;
    }

    public int getNumberSteps() {
        return numberSteps;
    }

    public ArrayList<Double> discretize() {

        double l = this.getMinValue();
        double u = this.getMaxValue();

        ArrayList<Double> values = new ArrayList<>();

        for (double value = l; value <= u; value = value + this.getStepSize()) {
            values.add(value);
        }
        return values;
    }

    public static QuantitativeRange createQuantitativeRangeFromLinkedTreeMap(LinkedTreeMap map) throws IllegalArgumentException{
        QuantitativeRange qr;
        double minValue = (double)map.get("minValue");
        double maxValue = (double)map.get("maxValue");
        if (map.get("stepSize")!=null){
            double stepSize = (double)map.get("stepSize");
            qr = new QuantitativeRange(minValue,maxValue,stepSize,0);
        }else if (map.get("numberSteps")!=null){
            int numberSteps = (int)map.get("numberSteps");
            qr = new QuantitativeRange(minValue,maxValue,0,numberSteps);
        }else{
            throw new IllegalArgumentException("QuantitativeRange must have either a step size or number of steps");
        }
        return qr;
    }
}
