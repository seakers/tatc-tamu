package tatc.architecture.specifications;

/**
 * An organizational entity responsible for operating a space mission or asset.
 * Used by: MissionConcept, Satellite, LaunchVehicle, GroundStation, Instrument
 */
public class Agency {

    private final String name;
    private final String acronym;
    private final String agencyType;

    public Agency(String name, String acronym, String agencyType) {
        this.name = name;
        this.acronym = acronym;
        this.agencyType = agencyType;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getAgencyType() {
        return agencyType;
    }
}
