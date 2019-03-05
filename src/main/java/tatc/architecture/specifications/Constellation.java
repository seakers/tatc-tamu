package tatc.architecture.specifications;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import org.moeaframework.problem.misc.Lis;
import tatc.util.AlwaysListTypeAdapterFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a set of satellites orbiting in a coordinated motion. Specific types include Homogeneous
 * Walker (Delta), Heterogeneous Walker (Delta), Precessing, Ad hoc, and Train.
 * Used by: ArchitectureConstraints
 */
public class Constellation {

    @SerializedName("@type")
    private final String _type="Constellation";
    private final String constellationType;
    private final Object numberSatellites;
    private final Object numberPlanes;
    private final Object relativeSpacing;
    @JsonAdapter(AlwaysListTypeAdapterFactory.class)
    private final List<Orbit> orbit;
    private final Object satelliteInterval;
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
        if (numberSatellites instanceof Double) {
            return ((Double) numberSatellites).intValue();
        }else if (numberSatellites instanceof List){
            ArrayList<Integer> nsat=new ArrayList<>();
            for(Double d : (List<Double>)numberSatellites){
                nsat.add(d.intValue());
            }
            return nsat;
        }else if (numberSatellites instanceof LinkedTreeMap && ((LinkedTreeMap) numberSatellites).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.createQuantitativeRangeFromLinkedTreeMap((LinkedTreeMap)numberSatellites);
        }else {
            throw new IllegalArgumentException("NumberSatellites has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getNumberSatellitesType() throws IllegalArgumentException{
        if (numberSatellites instanceof Integer || numberSatellites instanceof Double){
            return Integer.class;
        }else if (numberSatellites instanceof List){
            return List.class;
        }else if (numberSatellites instanceof LinkedTreeMap && ((LinkedTreeMap) numberSatellites).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.class;
        }else {
            throw new IllegalArgumentException("NumberSatellites has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Object getNumberPlanes() throws IllegalArgumentException{
        if (numberPlanes instanceof Double) {
            return ((Double) numberPlanes).intValue();
        }else if (numberPlanes instanceof List){
            ArrayList<Integer> nplanes=new ArrayList<>();
            for(Double d : (List<Double>)numberPlanes){
                nplanes.add(d.intValue());
            }
            return nplanes;
        }else if (numberPlanes instanceof LinkedTreeMap && ((LinkedTreeMap) numberPlanes).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.createQuantitativeRangeFromLinkedTreeMap((LinkedTreeMap)numberPlanes);
        }else {
            throw new IllegalArgumentException("NumberPlanes has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getNumberPlanesType() throws IllegalArgumentException{
        if (numberPlanes instanceof Integer || numberPlanes instanceof Double){
            return Integer.class;
        }else if (numberPlanes instanceof List){
            return List.class;
        }else if (numberPlanes instanceof LinkedTreeMap && ((LinkedTreeMap) numberPlanes).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.class;
        }else {
            throw new IllegalArgumentException("NumberPlanes has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Object getRelativeSpacing() throws IllegalArgumentException{
        if (relativeSpacing instanceof Double) {
            return ((Double) relativeSpacing).intValue();
        }else if (relativeSpacing instanceof List){
            ArrayList<Integer> relSpacing=new ArrayList<>();
            for(Double d : (List<Double>)relativeSpacing){
                relSpacing.add(d.intValue());
            }
            return relSpacing;
        }else if (relativeSpacing instanceof LinkedTreeMap && ((LinkedTreeMap) relativeSpacing).get("@type").equals("QuantitativeRange")) {
            return QuantitativeRange.createQuantitativeRangeFromLinkedTreeMap((LinkedTreeMap) relativeSpacing);
        }else if (relativeSpacing == null){
            return null;
        }else {
            throw new IllegalArgumentException("RelativeSpacing has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getRelativeSpacingType() throws IllegalArgumentException{
        if (relativeSpacing instanceof Integer || relativeSpacing instanceof Double){
            return Integer.class;
        }else if (relativeSpacing instanceof List){
            return List.class;
        }else if (relativeSpacing instanceof LinkedTreeMap && ((LinkedTreeMap) relativeSpacing).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.class;
        }else if (relativeSpacing == null){
            return null;
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
