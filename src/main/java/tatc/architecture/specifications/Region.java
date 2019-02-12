package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

/**
 * A region or point designated by bounding latitudes and longitudes.
 * Used by: MissionConcept
 */

public class Region {

    @SerializedName("@type")
    private final String _type="Region";
    private final QuantitativeValue latitude;
    private final QuantitativeValue longitude;

    public Region(QuantitativeValue latitude, QuantitativeValue longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public QuantitativeValue getLatitude() {
        return latitude;
    }

    public QuantitativeValue getLongitude() {
        return longitude;
    }
}
