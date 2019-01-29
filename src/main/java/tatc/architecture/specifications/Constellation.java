package tatc.architecture.specifications;

import java.util.List;

/**
 * Representation of a set of satellites orbiting in a coordinated motion. Specific types include Homogeneous
 * Walker (Delta), Heterogeneous Walker (Delta), Precessing, Ad hoc, and Train.
 * Used by: ArchitectureConstraints
 */
public class Constellation {

    private final String constellationType;
    private final Object numberSatellites;
    // private final int numberSatellites;
    // private final QuantitativeRange numberSatellites;
    private final Object numberPlanes;
    // private final int numberPlanes;
    // private final QuantitativeRange numberPlanes;
    private final Object relativeSpacing;
    // private final int relativeSpacing;
    // private final QuantitativeRange relativeSpacing;
    private final List<Orbit> orbit;
    private final Object satelliteInterval;
    // private final double satelliteInterval;
    // private final String satelliteInterval;
    private final List<Satellite> satellites;

    public Constellation(String constellationType, Object numberSatellites, Object numberPlanes, Object relativeSpacing, List<Orbit> orbit, Object satelliteInterval, List<Satellite> satellites) {
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

    public Object getNumberSatellites() throws IllegalArgumentException{
        if (numberSatellites instanceof Integer){
            return numberSatellites;
        }else if (numberSatellites instanceof QuantitativeRange){
            return numberSatellites;
        }else {
            throw new IllegalArgumentException("NumberSatellites has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getNumberSatellitesType() throws IllegalArgumentException{
        if (numberSatellites instanceof Integer){
            return Integer.class;
        }else if (numberSatellites instanceof QuantitativeRange){
            return QuantitativeRange.class;
        }else {
            throw new IllegalArgumentException("NumberSatellites has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Object getNumberPlanes() throws IllegalArgumentException{
        if (numberPlanes instanceof Integer){
            return numberPlanes;
        }else if (numberPlanes instanceof QuantitativeRange){
            return numberPlanes;
        }else {
            throw new IllegalArgumentException("NumberPlanes has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getNumberPlanesType() throws IllegalArgumentException{
        if (numberPlanes instanceof Integer){
            return Integer.class;
        }else if (numberPlanes instanceof QuantitativeRange){
            return QuantitativeRange.class;
        }else {
            throw new IllegalArgumentException("NumberPlanes has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Object getRelativeSpacing() throws IllegalArgumentException{
        if (relativeSpacing instanceof Integer){
            return relativeSpacing;
        }else if (relativeSpacing instanceof QuantitativeRange){
            return relativeSpacing;
        }else {
            throw new IllegalArgumentException("RelativeSpacing has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getRelativeSpacingType() throws IllegalArgumentException{
        if (relativeSpacing instanceof Integer){
            return Integer.class;
        }else if (relativeSpacing instanceof QuantitativeRange){
            return QuantitativeRange.class;
        }else {
            throw new IllegalArgumentException("RelativeSpacing has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public List<Orbit> getOrbit() {
        return orbit;
    }

    public Object getSatelliteInterval() throws IllegalArgumentException{
        if (satelliteInterval instanceof Double){
            return satelliteInterval;
        }else if (satelliteInterval instanceof String){
            return satelliteInterval;
        }else {
            throw new IllegalArgumentException("SatelliteInterval has to be either a double or a String in TradespaceSearch.json");
        }
    }

    public Class getSatelliteIntervalType() throws IllegalArgumentException{
        if (satelliteInterval instanceof String){
            return String.class;
        }else if (satelliteInterval instanceof Double){
            return Double.class;
        }else {
            throw new IllegalArgumentException("SatelliteInterval has to be either a double or a String in TradespaceSearch.json");
        }
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }
}
