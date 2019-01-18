package tatc.architecture.specifications;

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
}
