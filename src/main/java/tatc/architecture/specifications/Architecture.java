package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Instantiation of a space mission composed of a constellation and a ground network.
 * Used by: TradespaceSearch
 */
public class Architecture {

    @SerializedName("@type")
    private final String _type="Architecture";
    private final List<Constellation> constellation;
    private final List<GroundNetwork> groundNetwork;

    public Architecture(List<Constellation>  constellations, List<GroundNetwork> groundNetworks) {
        this.constellation = constellations;
        this.groundNetwork = groundNetworks;
    }

    public List<Constellation> getConstellation() {
        return constellation;
    }

    public List<GroundNetwork> getGroundNetwork() {
        return groundNetwork;
    }
}
