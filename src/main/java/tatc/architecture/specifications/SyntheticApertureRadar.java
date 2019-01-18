package tatc.architecture.specifications;

import java.util.List;

/**
 * A payload component that performs scientific observation functions. Examples include imagers which observe and produce one or more image products.
 * Used by: ArchitectureConstraints, Satellite
 */
public class SyntheticApertureRadar {

    private final String name;
    private final String acronym;
    private final Agency agency;
    private final double mass;
    private final double volume;
    private final double power;
    private final String solarConditions;
    private final double dataRate;
    private final double fieldOfView;
    private final int numberPixels;
    private final int pixelBitDepth;
    private final List<Double> operatingWavelength;
    private final double sideLookAngle;
    private final QuantitativeValue pulseFrequency;
    private final double pulseWidth;
    private final double antennaSize;
    private final double antennaEfficiency;
    private final double antennaEfficiency;
    private final double operatingFrequency;
    private final double peakTransmitPower;
    private final double chirpBandwidth;
    private final double systemLoss;
    private final double Lr;
    private final double La;
    private final double awa;
    private final double awr;
    private final double atmosphericLoss;
    private final double sceneTemperature;
    private final double systemNoiseFactor;
    private final double maxSigmaNoiseEquivalentReflective;

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

    public String getSolarConditions() {
        return solarConditions;
    }

    public double getDataRate() {
        return dataRate;
    }

    public double getFieldOfView() {
        return fieldOfView;
    }

    public int getNumberPixels() {
        return numberPixels;
    }

    public int getPixelBitDepth() {
        return pixelBitDepth;
    }

    public List<Double> getOperatingWavelength() {
        return operatingWavelength;
    }

    public double getSideLookAngle() {
        return sideLookAngle;
    }

    public QuantitativeValue getPulseFrequency() {
        return pulseFrequency;
    }

    public double getPulseWidth() {
        return pulseWidth;
    }

    public double getAntennaSize() {
        return antennaSize;
    }

    public double getAntennaEfficiency() {
        return antennaEfficiency;
    }

    public double getOperatingFrequency() {
        return operatingFrequency;
    }

    public double getPeakTransmitPower() {
        return peakTransmitPower;
    }

    public double getChirpBandwidth() {
        return chirpBandwidth;
    }

    public double getSystemLoss() {
        return systemLoss;
    }

    public double getLr() {
        return Lr;
    }

    public double getLa() {
        return La;
    }

    public double getAwa() {
        return awa;
    }

    public double getAwr() {
        return awr;
    }

    public double getAtmosphericLoss() {
        return atmosphericLoss;
    }

    public double getSceneTemperature() {
        return sceneTemperature;
    }

    public double getSystemNoiseFactor() {
        return systemNoiseFactor;
    }

    public double getMaxSigmaNoiseEquivalentReflective() {
        return maxSigmaNoiseEquivalentReflective;
    }
}
