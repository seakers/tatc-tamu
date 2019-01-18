package tatc.architecture.specifications;

/**
 * An entity that delivers satellites to orbit.
 * Used by: ArchitectureConstraints, Satellite
 */

public class LaunchVehicle {

    private final String name;
    private final String acronym;
    private final Agency agency;
    private final double payloadMass;
    private final double payloadVolume;
    private final double dryMass;
    private final double propellantMass;
    private final double specificImpulse;
    private final double massToLEO;
    private final double reliability;
    private final double cost;
    private final String meanTimeBetweenLaunches;
    //TODO: figure out final type of meanTimeBetweenLaunches
    // public final double meanTimeBetweenLaunches;


    public LaunchVehicle(String name, String acronym, Agency agency, double payloadMass, double payloadVolume, double dryMass, double propellantMass, double specificImpulse, double massToLEO, double reliability, double cost, String meanTimeBetweenLaunches) {
        this.name = name;
        this.acronym = acronym;
        this.agency = agency;
        this.payloadMass = payloadMass;
        this.payloadVolume = payloadVolume;
        this.dryMass = dryMass;
        this.propellantMass = propellantMass;
        this.specificImpulse = specificImpulse;
        this.massToLEO = massToLEO;
        this.reliability = reliability;
        this.cost = cost;
        this.meanTimeBetweenLaunches = meanTimeBetweenLaunches;
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

    public double getPayloadMass() {
        return payloadMass;
    }

    public double getPayloadVolume() {
        return payloadVolume;
    }

    public double getDryMass() {
        return dryMass;
    }

    public double getPropellantMass() {
        return propellantMass;
    }

    public double getSpecificImpulse() {
        return specificImpulse;
    }

    public double getMassToLEO() {
        return massToLEO;
    }

    public double getReliability() {
        return reliability;
    }

    public double getCost() {
        return cost;
    }

    public String getMeanTimeBetweenLaunches() {
        return meanTimeBetweenLaunches;
    }
}
