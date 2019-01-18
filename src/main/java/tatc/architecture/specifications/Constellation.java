package tatc.architecture.specifications;

import java.util.List;

/**
 * Representation of a set of satellites orbiting in a coordinated motion. Specific types include Homogeneous
 * Walker (Delta), Heterogeneous Walker (Delta), Precessing, Ad hoc, and Train.
 * Used by: ArchitectureConstraints
 */
public class Constellation {

    private final String constellationType;
    private final QuantitativeRange numberSatellites;
    //TODO: figure out final type of numberSatellites
    // public final int numberSatellites;
    private final QuantitativeRange numberPlanes;
    //TODO: figure out final type of numberPlanes
    // public final int numberPlanes;
    private final QuantitativeRange relativeSpacing;
    //TODO: figure out final type of relativeSpacing
    // public final int relativeSpacing;
    private final List<Orbit> orbit;
    private final String satelliteInterval;
    //TODO: figure out final type of satelliteInterval
    // public final double satelliteInterval;
    private final List<Satellite> satellites;

    public Constellation(String constellationType, QuantitativeRange numberSatellites, QuantitativeRange numberPlanes, QuantitativeRange relativeSpacing, List<Orbit> orbit, String satelliteInterval, List<Satellite> satellites) {
        this.constellationType = constellationType;
        this.numberSatellites = numberSatellites;
        this.numberPlanes = numberPlanes;
        this.relativeSpacing = relativeSpacing;
        this.orbit = orbit;
        this.satelliteInterval = satelliteInterval;
        this.satellites = satellites;
    }

    public String getConstellationType() {
        return constellationType;
    }

    public QuantitativeRange getNumberSatellites() {
        return numberSatellites;
    }

    public QuantitativeRange getNumberPlanes() {
        return numberPlanes;
    }

    public QuantitativeRange getRelativeSpacing() {
        return relativeSpacing;
    }

    public List<Orbit> getOrbit() {
        return orbit;
    }

    public String getSatelliteInterval() {
        return satelliteInterval;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }
}
