package tatc.architecture.specifications;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import tatc.util.AlwaysListTypeAdapterFactory;

import java.util.List;

/**
 * An entity orbiting the Earth in support of mission objectives.
 * Used by: ArchitectureConstraints, ArchitectureMethods
 */

public class Satellite {

    @SerializedName("@type")
    private final String _type="Satellite";
    private final String name;
    private final String acronym;
    private final Agency agency;
    private final double mass;
    private final double volume;
    private final double power;
    private final List<String> commBand;
    @JsonAdapter(AlwaysListTypeAdapterFactory.class)
    private final List<Instrument> payload;
    private final Orbit orbit;

    public Satellite(String name, String acronym, Agency agency, double mass, double volume, double power, List<String> commBand, List<Instrument> payload, Orbit orbit) {
        this.name = name;
        this.acronym = acronym;
        this.agency = agency;
        this.mass = mass;
        this.volume = volume;
        this.power = power;
        this.commBand = commBand;
        this.payload = payload;
        this.orbit = orbit;
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

    public double getMass() {
        return mass;
    }

    public double getVolume() {
        return volume;
    }

    public double getPower() {
        return power;
    }

    public List<String> getCommBand() {
        return commBand;
    }

    public List<Instrument> getPayload() {
        return payload;
    }

    public Orbit getOrbit() {
        return orbit;
    }
}
