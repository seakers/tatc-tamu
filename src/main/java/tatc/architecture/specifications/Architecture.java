package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

/**
 * Instantiation of a space mission composed of a constellation and a ground network.
 * Used by: TradespaceSearch
 */
public class Architecture {

    @SerializedName("@type")
    private final String _type="Architecture";
    private final Constellation constellation;
    private final GroundNetwork groundNetwork;

    public Architecture(Constellation constellation, GroundNetwork groundNetwork) {
        this.constellation = constellation;
        this.groundNetwork = groundNetwork;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public GroundNetwork getGroundNetwork() {
        return groundNetwork;
    }
}
