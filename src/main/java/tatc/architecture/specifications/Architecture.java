package tatc.architecture.specifications;

/**
 * Instantiation of a space mission composed of a constellation and a ground network.
 * Used by: TradespaceSearch
 */
public class Architecture {

    private final Constellation constellation;
    private final GroundNetwork groundNetwork;

    public Constellation getConstellation() {
        return constellation;
    }

    public GroundNetwork getGroundNetwork() {
        return groundNetwork;
    }
}
