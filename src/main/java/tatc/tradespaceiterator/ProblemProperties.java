package tatc.tradespaceiterator;

import org.hipparchus.util.FastMath;
import seakers.conmop.util.Bounds;
import tatc.architecture.specifications.*;
import java.util.ArrayList;
import java.util.Properties;


public class ProblemProperties {

    /**
     * The tradespace search request
     */
    public final TradespaceSearchRequest tsr;


    public ProblemProperties(TradespaceSearchRequest tsr, Properties properties) {
        this.tsr = tsr;
    }

    /**
     * This method gets inclinations for special orbits
     */
    protected double getSpecialOrbitInclinations(SpecialOrbit special) {

        switch (special.toString()) {
            //identifier of SSO = -1 so that we can calculate it using alt later on
            case "SSO":
                return -1;
            case "CriticallyInclined":
                return FastMath.toRadians(63.4);
            case "ISS":
                return FastMath.toRadians(51.6);
            default:
                throw new UnsupportedOperationException(
                        String.format("Expected token SSO, Frozen, "
                                + "CriticallyInclined, or ISS. Found %s.", special));
        }
    }

    /**
     * Discretizes a range of smas
     *
     * @return the discrete values for smas
     */
    protected ArrayList<Double> discretizeSemiMajorAxes(Bounds<Double> bounds) {

        double l = bounds.getLowerBound();
        double u = bounds.getUpperBound();

        ArrayList<Double> smas = new ArrayList<>();

        for (double count = l; count <= u; count = count + 50000) {
            smas.add(count);
        }
        return smas;
    }

    /**
     * Discretizes a range of inclination
     *
     * @return the discrete values for inclinations
     */
    protected ArrayList<Double> discretizeInclinations(Bounds<Double> bounds) {

        double l = FastMath.toDegrees(bounds.getLowerBound());
        double u = FastMath.toDegrees(bounds.getUpperBound());

        ArrayList<Double> incl = new ArrayList<>();

        for (double count = l; count <= u; count = count + 10) {
            incl.add(FastMath.toRadians(count));
        }
        return incl;
    }

    /**
     * Discretizes a range of number of satellites
     *
     * @return the discrete values for number of satellites
     */
    protected ArrayList<Integer> discretizeSatellite(Bounds<Integer> bounds) {
        int l = bounds.getLowerBound();
        int u = bounds.getUpperBound();
        ArrayList<Integer> sats = new ArrayList<>();

        for (int count = l; count <= u; count++) {
            sats.add(count);
        }
        return sats;
    }
}
