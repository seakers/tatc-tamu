package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A passive spectral imager observation instrument.
 * Used by: ArchitectureConstraints, Satellite
 */
public class SpectralImager {

    @SerializedName("@type")
    private final String _type="SpectralImager";
    private final String name;
    private final String acronym;
    private final Agency agency;
    private final Double mass;
    private final Double volume;
    private final Double power;
    private final List<Double> operatingWavelength;
    private final Integer pixelBitDepth;
    private final Double fieldOfView;
    private final String fieldOfViewShape;
    private final Integer numberPixels;
    private final Double dataRate;
    private final String solarConditions;
    private final Object orientation;
    // private final double orientation;
    // private final String orientation;
    private final Double minSignalNoiseRatio;
    private final Double detectiveQuantumEfficiency;

    public SpectralImager(String name, String acronym, Agency agency, double mass, double volume, double power, List<Double> operatingWavelength, int pixelBitDepth, double fieldOfView, String fieldOfViewShape, int numberPixels, double dataRate, String solarConditions, Object orientation, double minSignalNoiseRatio, double detectiveQuantumEfficiency) {
        this.name = name;
        this.acronym = acronym;
        this.agency = agency;
        this.mass = mass;
        this.volume = volume;
        this.power = power;
        this.operatingWavelength = operatingWavelength;
        this.pixelBitDepth = pixelBitDepth;
        this.fieldOfView = fieldOfView;
        this.fieldOfViewShape = fieldOfViewShape;
        this.numberPixels = numberPixels;
        this.dataRate = dataRate;
        this.solarConditions = solarConditions;
        this.orientation = orientation;
        this.minSignalNoiseRatio = minSignalNoiseRatio;
        this.detectiveQuantumEfficiency = detectiveQuantumEfficiency;
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

    public String getFieldOfViewShape() {
        return fieldOfViewShape;
    }

    public Object getOrientation() throws IllegalArgumentException{
        if (orientation instanceof Double){
            return orientation;
        }else if (orientation instanceof String){
            return orientation;
        }else {
            throw new IllegalArgumentException("Orientation has to be either a Double or a String in TradespaceSearch.json");
        }
    }

    public Class getDurationType() throws IllegalArgumentException{
        if (orientation instanceof String){
            return String.class;
        }else if (orientation instanceof Double){
            return Double.class;
        }else {
            throw new IllegalArgumentException("Orientation has to be either a Double or a String in TradespaceSearch.json");
        }
    }

    public double getMinSignalNoiseRatio() {
        return minSignalNoiseRatio;
    }

    public double getDetectiveQuantumEfficiency() {
        return detectiveQuantumEfficiency;
    }
}
