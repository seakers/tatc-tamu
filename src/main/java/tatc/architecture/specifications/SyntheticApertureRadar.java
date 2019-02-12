package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A payload component that performs scientific observation functions. Examples include imagers which observe and produce one or more image products.
 * Used by: ArchitectureConstraints, Satellite
 */
public class SyntheticApertureRadar {

    @SerializedName("@type")
    private final String _type="SyntheticApertureRadar";
    private final String name;
    private final String acronym;
    private final Agency agency;
    private final Double mass;
    private final Double volume;
    private final Double power;
    private final String solarConditions;
    private final Double dataRate;
    private final Double fieldOfView;
    private final Integer numberPixels;
    private final Integer pixelBitDepth;
    private final List<Double> operatingWavelength;
    private final Double sideLookAngle;
    private final QuantitativeValue pulseFrequency;
    private final Double pulseWidth;
    private final Double antennaSize;
    private final Double antennaEfficiency;
    private final Double operatingFrequency;
    private final Double peakTransmitPower;
    private final Double chirpBandwidth;
    private final Double systemLoss;
    private final Double Lr;
    private final Double La;
    private final Double awa;
    private final Double awr;
    private final Double atmosphericLoss;
    private final Double sceneTemperature;
    private final Double systemNoiseFactor;
    private final Double maxSigmaNoiseEquivalentReflective;

    public SyntheticApertureRadar(String name, String acronym, Agency agency, double mass, double volume, double power, String solarConditions, double dataRate, double fieldOfView, int numberPixels, int pixelBitDepth, List<Double> operatingWavelength, double sideLookAngle, QuantitativeValue pulseFrequency, double pulseWidth, double antennaSize, double antennaEfficiency, double operatingFrequency, double peakTransmitPower, double chirpBandwidth, double systemLoss, double lr, double la, double awa, double awr, double atmosphericLoss, double sceneTemperature, double systemNoiseFactor, double maxSigmaNoiseEquivalentReflective) {
        this.name = name;
        this.acronym = acronym;
        this.agency = agency;
        this.mass = mass;
        this.volume = volume;
        this.power = power;
        this.solarConditions = solarConditions;
        this.dataRate = dataRate;
        this.fieldOfView = fieldOfView;
        this.numberPixels = numberPixels;
        this.pixelBitDepth = pixelBitDepth;
        this.operatingWavelength = operatingWavelength;
        this.sideLookAngle = sideLookAngle;
        this.pulseFrequency = pulseFrequency;
        this.pulseWidth = pulseWidth;
        this.antennaSize = antennaSize;
        this.antennaEfficiency = antennaEfficiency;
        this.operatingFrequency = operatingFrequency;
        this.peakTransmitPower = peakTransmitPower;
        this.chirpBandwidth = chirpBandwidth;
        this.systemLoss = systemLoss;
        Lr = lr;
        La = la;
        this.awa = awa;
        this.awr = awr;
        this.atmosphericLoss = atmosphericLoss;
        this.sceneTemperature = sceneTemperature;
        this.systemNoiseFactor = systemNoiseFactor;
        this.maxSigmaNoiseEquivalentReflective = maxSigmaNoiseEquivalentReflective;
    }

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
