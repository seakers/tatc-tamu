package tatc.architecture.specifications;


/**
 * Representation of an orbital trajectory about the Earth as a planetary body. Specific types include: Geosynchronous
 * (remains stationary above one location on the ground), sun synchronous (crosses the equator at the same local solar
 * time each orbit), Keplerian (specific orbital trajectory described by six orbital elements initialized at a
 * particular epoch), and drifting (a generic orbital trajectory which is not synchronized with any reference datum).
 * Used by: Constellation, Satellite
 */
public class Orbit {

    private final String orbitType;
    private final QuantitativeRange altitude;
    //TODO: figure out final type of altitude
    // public final double altitude;
    public final double semimajorAxis;
    private final QuantitativeRange inclination;
    //TODO: figure out final type of inclination
    // public final double inclination;
    // public final String inclination;
    public final double eccentricity;
    public final double periapsisArgument;
    public final double rightAscensionAscendingNode;
    public final double trueAnomaly;
    public final String epoch;
    public final String localSolarTimeAscendingNode;

    public Orbit(String orbitType, QuantitativeRange altitude, double semimajorAxis, QuantitativeRange inclination, double eccentricity, double periapsisArgument, double rightAscensionAscendingNode, double trueAnomaly, String epoch, String localSolarTimeAscendingNode) {
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

    public QuantitativeRange getAltitude() {
        return altitude;
    }

    public double getSemimajorAxis() {
        return semimajorAxis;
    }

    public QuantitativeRange getInclination() {
        return inclination;
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
