package tatc.architecture.specifications;

import java.util.ArrayList;

/**
 *
 Defines a range of quantitative values with minimum and maximum values and either a step size of equally-spaced steps.
 Used by: Constellation, Orbit
 */
public class QuantitativeRange {

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
}
