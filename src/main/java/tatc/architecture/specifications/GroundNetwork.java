package tatc.architecture.specifications;

/**
 * A network of ground stations providing uplink and downlink connectivity for a mission.
 * Used by: ArchitectureConstraints, Architecture
 */
public class GroundNetwork {

    private final String name;
    private final String acronym;
    private final Agency agency;
    private final Object numberStations;
    // private final int numberStations;
    // private final QuantitativeRange numberStations;
    private final GroundStation groundStation;

    public GroundNetwork(String name, String acronym, Agency agency, Object numberStations, GroundStation groundStation) {
        this.name = name;
        this.acronym = acronym;
        this.agency = agency;
        this.numberStations = numberStations;
        this.groundStation = groundStation;
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

    public Object getNumberStations() throws IllegalArgumentException{
        if (numberStations instanceof Integer){
            return numberStations;
        }else if (numberStations instanceof QuantitativeRange){
            return numberStations;
        }else {
            throw new IllegalArgumentException("NumberStations has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getNumberStationsType() throws IllegalArgumentException{
        if (numberStations instanceof Integer){
            return Integer.class;
        }else if (numberStations instanceof QuantitativeRange){
            return QuantitativeRange.class;
        }else {
            throw new IllegalArgumentException("NumberStations has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public GroundStation getGroundStation() {
        return groundStation;
    }
}
