package tatc.architecture.specifications;

import java.util.List;

/**
 * A surface facility providing uplink or downlink communication services to satellites.
 * Used by: ArchitectureConstraints, Architecture
 */
public class GroundStation {

    private final String name;
    private final String acronym;
    private final Agency agency;
    private final double latitude;
    private final double longitude;
    private final double elevation;
    private final List<String> commBand;

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public Agency getAgency() {
        return agency;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public List<String> getCommBand() {
        return commBand;
    }
}
