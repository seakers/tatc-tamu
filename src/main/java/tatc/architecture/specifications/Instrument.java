package tatc.architecture.specifications;

import java.util.List;

/**
 * A payload component that performs scientific observation functions. Examples include imagers which observe and produce one or more image products.
 * Used by: ArchitectureConstraints, Satellite
 */
public class Instrument {

    private final String name;
    private final String acronym;
    private final Agency agency;
    private final double mass;
    private final double volume;
    private final double power;
    private final List<Double> operatingWavelength;
    private final int pixelBitDepth;
    private final double fieldOfView;
    private final int numberPixels;
    private final double dataRate;
    private final String solarConditions;

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
}
