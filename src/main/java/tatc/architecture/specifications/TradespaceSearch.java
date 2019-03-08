package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;
import tatc.architecture.variable.Decision;
import tatc.util.Combinatorics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A set of constraints and parameters to bound and define a tradespace search.
 * Used by: N/A (top level)
 */
public class TradespaceSearch {

    @SerializedName("@type")
    private final String _type="TradespaceSearch";
    private final MissionConcept mission;
    private final DesignSpace designSpace;
    private final AnalysisSettings settings;

    public TradespaceSearch(MissionConcept mission, DesignSpace designSpace, AnalysisSettings settings) {
        this.mission = mission;
        this.designSpace = designSpace;
        this.settings = settings;
    }

    public MissionConcept getMission() {
        return mission;
    }

    public DesignSpace getDesignSpace() {
        return designSpace;
    }

    public AnalysisSettings getSettings() {
        return settings;
    }

    public HashMap<String,Decision<?>> TradespaceSearch2Decisions(){

        HashMap<String,Decision<?>> decisions = new HashMap<>();

        int constellationCount=1;
        for (Constellation c : this.getDesignSpace().getConstellations()){
            Orbit orbitSpecification = c.getOrbit().get(0);
            if (orbitSpecification.getAltitudeType()==List.class){
                decisions.put(String.format("altitude%d",constellationCount),new Decision<>("Integer",(List<Double>) orbitSpecification.getAltitude()));
            }else if (orbitSpecification.getAltitudeType()==QuantitativeRange.class){
                decisions.put(String.format("altitude%d",constellationCount),new Decision<>("Integer", ((QuantitativeRange)orbitSpecification.getAltitude()).discretize()));
            }else {
                // Not a decision
                List<Double> altitude = new ArrayList<>();
                altitude.add((Double) orbitSpecification.getAltitude());
                decisions.put(String.format("altitude%d",constellationCount),new Decision<>("Integer", altitude));
            }

            if (orbitSpecification.getInclinationType()==List.class){
                decisions.put(String.format("inclination%d",constellationCount),new Decision<>("Integer",(List<Object>) orbitSpecification.getInclination()));
            }else if (orbitSpecification.getInclinationType()==QuantitativeRange.class){
                decisions.put(String.format("inclination%d",constellationCount),new Decision<>("Integer", ((QuantitativeRange)orbitSpecification.getInclination()).discretize()));
            }else {
                // Not a decision
                List<Object> inclination = new ArrayList<>();
                inclination.add(orbitSpecification.getInclination());
                decisions.put(String.format("inclination%d",constellationCount),new Decision<>("Integer", inclination));
            }

            if (c.getNumberSatellitesType()==List.class){
                decisions.put(String.format("numberSatellites%d",constellationCount),new Decision<>("Integer",(List<Integer>) c.getNumberSatellites()));
            }else if (c.getNumberSatellitesType()==QuantitativeRange.class){
                List <Double> numberSatellitesQuantitativeRange = ((QuantitativeRange)c.getNumberSatellites()).discretize();
                List <Integer> numberSatellites = new ArrayList<>();
                for (Double d : numberSatellitesQuantitativeRange){
                    numberSatellites.add(d.intValue());
                }
                decisions.put(String.format("numberSatellites%d",constellationCount),new Decision<>("Integer", numberSatellites));
            }else {
                // Not a decision
                List<Integer> nsat = new ArrayList<>();
                nsat.add((Integer) c.getNumberSatellites());
                decisions.put(String.format("numberSatellites%d",constellationCount),new Decision<>("Integer", nsat));
            }

            if (c.getNumberPlanesType()==List.class){
                decisions.put(String.format("numberPlanes%d",constellationCount),new Decision<>("Integer",(List<Integer>) c.getNumberPlanes()));
            }else if (c.getNumberPlanesType()==QuantitativeRange.class){
                List <Double> numberPlanesQuantitativeRange = ((QuantitativeRange)c.getNumberPlanes()).discretize();
                List <Integer> numberPlanes = new ArrayList<>();
                for (Double d : numberPlanesQuantitativeRange){
                    numberPlanes.add(d.intValue());
                }
                decisions.put(String.format("numberPlanes%d",constellationCount),new Decision<>("Integer", numberPlanes));
            }else if (c.getRelativeSpacingType()==null){
                decisions.put(String.format("numberPlanes%d",constellationCount),new Decision<>("Integer", null));
            }else {
                // Not a decision
                List<Integer> nplanes = new ArrayList<>();
                nplanes.add((Integer) c.getNumberPlanes());
                decisions.put(String.format("numberPlanes%d",constellationCount),new Decision<>("Integer", nplanes));
            }

            if (c.getRelativeSpacingType()==List.class){
                decisions.put(String.format("relativeSpacing%d",constellationCount),new Decision<>("Integer",(List<Integer>) c.getRelativeSpacing()));
            }else if (c.getRelativeSpacingType()==QuantitativeRange.class) {
                List <Double> relSpacingQuantitativeRange = ((QuantitativeRange)c.getRelativeSpacing()).discretize();
                List <Integer> relSpacing = new ArrayList<>();
                for (Double d : relSpacingQuantitativeRange){
                    relSpacing.add(d.intValue());
                }
                decisions.put(String.format("numberPlanes%d",constellationCount),new Decision<>("Integer", relSpacing));
            }else if (c.getRelativeSpacingType()==null){
                decisions.put(String.format("relativeSpacing%d",constellationCount),new Decision<>("Integer", null));
            }else {
                // Not a decision
                List<Integer> relspac = new ArrayList<>();
                relspac.add((Integer) c.getRelativeSpacing());
                decisions.put(String.format("relativeSpacing%d",constellationCount),new Decision<>("Integer", relspac));
            }
            constellationCount++;
        }

        int groundNetworkCount=1;

        for (GroundNetwork gn : this.getDesignSpace().getGroundNetworks()){

            if (gn.getNumberStationsType()==List.class){

                List<Integer> numberGroundStations = (List) gn.getNumberStations();
                List<GroundNetwork> groundNetworks = new ArrayList<>();
                for (Integer ngs : numberGroundStations){
                    List<List<GroundStation>> combinations = Combinatorics.combination(this.getDesignSpace().getGroundStations(),ngs);
                    for (List<GroundStation> gs : combinations){
                        groundNetworks.add(new GroundNetwork(gn.getName(),
                                gn.getAcronym(),
                                gn.getAgency(),
                                gs.size(),
                                gs));
                    }
                }
                decisions.put(String.format("groundNetwork%d",groundNetworkCount),new Decision<>("Integer", groundNetworks));
                groundNetworkCount++;

            }else if (gn.getNumberStationsType()==QuantitativeRange.class){

                QuantitativeRange numberGroundStationsQuantitativeRange = (QuantitativeRange) gn.getNumberStations();
                List<Double> numberGroundStations = numberGroundStationsQuantitativeRange.discretize();
                List<GroundNetwork> groundNetworks = new ArrayList<>();
                for (Double ngs : numberGroundStations){
                    List<List<GroundStation>> combinations = Combinatorics.combination(this.getDesignSpace().getGroundStations(),ngs.intValue());
                    for (List<GroundStation> gs : combinations){
                        groundNetworks.add(new GroundNetwork(gn.getName(),
                                gn.getAcronym(),
                                gn.getAgency(),
                                gs.size(),
                                gs));
                    }
                }
                decisions.put(String.format("groundNetwork%d",groundNetworkCount),new Decision<>("Integer", groundNetworks));
                groundNetworkCount++;

            }else {

                List<List<GroundStation>> combinations = Combinatorics.combination(this.getDesignSpace().getGroundStations(),(int) gn.getNumberStations());
                List<GroundNetwork> groundNetworks = new ArrayList<>();
                for (List<GroundStation> gs : combinations){
                    groundNetworks.add(new GroundNetwork(gn.getName(),
                                                            gn.getAcronym(),
                                                            gn.getAgency(),
                                                            gs.size(),
                                                            gs));
                }
                decisions.put(String.format("groundNetwork%d",groundNetworkCount),new Decision<>("Integer", groundNetworks));
                groundNetworkCount++;

            }

        }

        decisions.put("satellite",new Decision<>("Integer", this.getDesignSpace().getSatellites()));

        return decisions;
    }
}
