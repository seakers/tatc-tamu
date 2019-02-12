package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A payload component that performs scientific observation functions. Examples include imagers which observe and produce one or more image products.
 * Used by: ArchitectureConstraints, Satellite
 */
public class Instrument {

    @SerializedName("@type")
    private final String _type="Instrument";
    private final String name;
    private final String acronym;
    private final Agency agency;
    private final Double mass;
    private final Double volume;
    private final Double power;
    private final List<Double> operatingWavelength;
    private final Integer pixelBitDepth;
    private final Double fieldOfView;
    private final Integer numberPixels;
    private final Double dataRate;
    private final String solarConditions;

    public Instrument(String name, String acronym, Agency agency, double mass, double volume, double power, List<Double> operatingWavelength, int pixelBitDepth, double fieldOfView, int numberPixels, double dataRate, String solarConditions) {
        this.name = name;
        this.acronym = acronym;
        this.agency = agency;
        this.mass = mass;
        this.volume = volume;
        this.power = power;
        this.operatingWavelength = operatingWavelength;
        this.pixelBitDepth = pixelBitDepth;
        this.fieldOfView = fieldOfView;
        this.numberPixels = numberPixels;
        this.dataRate = dataRate;
        this.solarConditions = solarConditions;
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
