package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A surface facility providing uplink or downlink communication services to satellites.
 * Used by: ArchitectureConstraints, ArchitectureMethods
 */
public class GroundStation {

    @SerializedName("@type")
    private final String _type="GroundStation";
    private final String name;
    private final String acronym;
    private final Agency agency;
    private final Double latitude;
    private final Double longitude;
    private final Double elevation;
    private final List<String> commBand;

    public GroundStation(String name, String acronym, Agency agency, double latitude, double longitude, double elevation, List<String> commBand) {
        this.name = name;
        this.acronym = acronym;
        this.agency = agency;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.commBand = commBand;
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
