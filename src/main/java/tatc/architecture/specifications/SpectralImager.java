package tatc.architecture.specifications;

import java.util.List;

/**
 * A passive spectral imager observation instrument.
 * Used by: ArchitectureConstraints, Satellite
 */
public class SpectralImager {

    private final String name;
    private final String acronym;
    private final Agency agency;
    private final double mass;
    private final double volume;
    private final double power;
    private final List<Double> operatingWavelength;
    private final int pixelBitDepth;
    private final double fieldOfView;
    private final String fieldOfViewShape;
    private final int numberPixels;
    private final double dataRate;
    private final String solarConditions;
    private final double orientation;
    //TODO: or it can be a string
    private final double minSignalNoiseRatio;
    private final double detectiveQuantumEfficiency;

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public Agency getAgency() {
        return agency;
    }

    public double getMass() {
        return mass;
    }

    public double getVolume() {
        return volume;
    }

    public double getPower() {
        return power;
    }

    public List<Double> getOperatingWavelength() {
        return operatingWavelength;
    }

    public int getPixelBitDepth() {
        return pixelBitDepth;
    }

    public double getFieldOfView() {
        return fieldOfView;
    }

    public int getNumberPixels() {
        return numberPixels;
    }

    public double getDataRate() {
        return dataRate;
    }

    public String getSolarConditions() {
        return solarConditions;
    }

    public String getFieldOfViewShape() {
        return fieldOfViewShape;
    }

    public double getOrientation() {
        return orientation;
    }

    public double getMinSignalNoiseRatio() {
        return minSignalNoiseRatio;
    }

    public double getDetectiveQuantumEfficiency() {
        return detectiveQuantumEfficiency;
    }
}
