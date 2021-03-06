package tatc.architecture.specifications;


import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

/**
 * Representation of an orbital trajectory about the Earth as a planetary body. Specific types include: Geosynchronous
 * (remains stationary above one location on the ground), sun synchronous (crosses the equator at the same local solar
 * time each orbit), Keplerian (specific orbital trajectory described by six orbital elements initialized at a
 * particular epoch), and drifting (a generic orbital trajectory which is not synchronized with any reference datum).
 * Used by: Constellation, Satellite
 */
public class Orbit {

    @SerializedName("@type")
    private final String _type="Orbit";
    private final String orbitType;
    private final Object altitude;
    private final Double semimajorAxis;
    private final Object inclination;
    private final Double eccentricity;
    private final Double periapsisArgument;
    private final Double rightAscensionAscendingNode;
    private final Double trueAnomaly;
    private final String epoch;
    private final String localSolarTimeAscendingNode;

    public Orbit(String orbitType, Object altitude, double semimajorAxis, Object inclination, double eccentricity, double periapsisArgument, double rightAscensionAscendingNode, double trueAnomaly, String epoch, String localSolarTimeAscendingNode) {
        this.orbitType = orbitType;
        this.altitude = altitude;
        this.semimajorAxis = semimajorAxis;
        this.inclination = inclination;
        this.eccentricity = eccentricity;
        this.periapsisArgument = periapsisArgument;
        this.rightAscensionAscendingNode = rightAscensionAscendingNode;
        this.trueAnomaly = trueAnomaly;
        this.epoch = epoch;
        this.localSolarTimeAscendingNode = localSolarTimeAscendingNode;
    }

    public String getOrbitType() {
        return orbitType;
    }

    public Object getAltitude() throws IllegalArgumentException{
        if (altitude instanceof Double) {
            return altitude;
        }else if (altitude instanceof List){
            return altitude;
        }else if (altitude instanceof LinkedTreeMap && ((LinkedTreeMap) altitude).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.createQuantitativeRangeFromLinkedTreeMap((LinkedTreeMap)altitude);
        }else {
            throw new IllegalArgumentException("Altitude has to be either a Double or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getAltitudeType() throws IllegalArgumentException{
        if (altitude instanceof Double){
            return Double.class;
        }else if (altitude instanceof List){
            return List.class;
        }else if (altitude instanceof LinkedTreeMap && ((LinkedTreeMap) altitude).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.class;
        }else {
            throw new IllegalArgumentException("Altitude has to be either a Double or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public double getSemimajorAxis() {
        return semimajorAxis;
    }

    public Object getInclination() throws IllegalArgumentException{
        if (inclination instanceof Double){
            return inclination;
        }else if (inclination instanceof String){
            return inclination;
        }else if (inclination instanceof List){
            return inclination;
        }else if (inclination instanceof LinkedTreeMap && ((LinkedTreeMap) inclination).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.createQuantitativeRangeFromLinkedTreeMap((LinkedTreeMap)inclination);
        }else {
            throw new IllegalArgumentException("Inclination has to be either a Double, a String or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getInclinationType() throws IllegalArgumentException{
        if (inclination instanceof Double){
            return Double.class;
        }else if (inclination instanceof String){
            return String.class;
        }else if (inclination instanceof List){
            return List.class;
        }else if (inclination instanceof LinkedTreeMap && ((LinkedTreeMap) inclination).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.class;
        }else {
            throw new IllegalArgumentException("Inclination has to be either a Double, a String or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public double getPeriapsisArgument() {
        return periapsisArgument;
    }

    public double getRightAscensionAscendingNode() {
        return rightAscensionAscendingNode;
    }

    public double getTrueAnomaly() {
        return trueAnomaly;
    }

    public String getEpoch() {
        return epoch;
    }

    public String getLocalSolarTimeAscendingNode() {
        return localSolarTimeAscendingNode;
    }
}
