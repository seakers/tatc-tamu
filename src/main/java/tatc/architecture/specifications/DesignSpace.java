package tatc.architecture.specifications;

import java.util.List;

/**
 * Specification of fixed and variable quantities for a space mission architecture including both space and ground assets.
 * Used by: TradespaceSearch
 */
public class DesignSpace {

    private final List<Constellation> constellations;
    private final List<LaunchVehicle> launchers;
    private final List<Satellite> satellites;
    private final List<GroundNetwork> groundNetworks;
    private final List<GroundStation> groundStations;

    public DesignSpace(List<Constellation> constellations, List<LaunchVehicle> launchers, List<Satellite> satellites, List<GroundNetwork> groundNetworks, List<GroundStation> groundStations) {
        this.constellations = constellations;
        this.launchers = launchers;
        this.satellites = satellites;
        this.groundNetworks = groundNetworks;
        this.groundStations = groundStations;
    }

    public List<Constellation> getConstellations() {
        return constellations;
    }

    public List<LaunchVehicle> getLaunchers() {
        return launchers;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public List<GroundNetwork> getGroundNetworks() {
        return groundNetworks;
    }

    public List<GroundStation> getGroundStations() {
        return groundStations;
    }
}
