package tatc.architecture.specifications;

/**
 * A network of ground stations providing uplink and downlink connectivity for a mission.
 * Used by: ArchitectureConstraints, Architecture
 */
public class GroundNetwork {

    private final String name;
    private final String acronym;
    private final Agency agency;
    private final QuantitativeRange numberStations;
    //TODO: can also be an integer
    private final GroundStation groundStation;

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public Agency getAgency() {
        return agency;
    }

    public QuantitativeRange getNumberStations() {
        return numberStations;
    }

    public GroundStation getGroundStation() {
        return groundStation;
    }
}
