package tatc.tradespaceiterator;

import org.orekit.utils.Constants;
import tatc.architecture.specifications.Constellation;
import tatc.architecture.specifications.Orbit;
import tatc.architecture.specifications.QuantitativeRange;
import tatc.architecture.specifications.TradespaceSearch;

import java.util.ArrayList;
import java.util.Properties;

public class ProblemPropertiesTrain implements ProblemProperties{
    private final ArrayList<Double> smas;
    private final ArrayList<Double> LTANs;
    private final TradespaceSearch tradespaceSearch;

    public ProblemPropertiesTrain(TradespaceSearch tsr) {
        this.tradespaceSearch = tsr;
        Constellation constellation=tsr.getDesignSpace().getConstellations().get(0);
        Orbit orbits=constellation.getOrbit().get(0);

        if (orbits.getAltitudeType()== QuantitativeRange.class){
            QuantitativeRange altitudes = (QuantitativeRange)orbits.getAltitude();
            ArrayList<Double> altitudesDiscrete= altitudes.discretize();
            this.smas = new ArrayList<>();
            for (Double alt : altitudesDiscrete){
                this.smas.add(Constants.WGS84_EARTH_EQUATORIAL_RADIUS/1000 + alt);
            }
        } else{
            this.smas=new ArrayList<>();
            this.smas.add(Constants.WGS84_EARTH_EQUATORIAL_RADIUS/1000 + (Double)orbits.getAltitude());
        }

        LTANs=new ArrayList<>();
        String LTAN = orbits.getLocalSolarTimeAscendingNode();
        if (constellation.getSatelliteIntervalType()== String.class){
            String interval = (String)constellation.getSatelliteInterval();
        } else{
            Double interval = (Double) constellation.getSatelliteInterval();
        }
        String[] str = LTAN.split(":");
        LTANs.add(Double.valueOf(str[0]) + Double.valueOf(str[1]) / 60.0);

        //TODO: add the rest of LTANs taking into account interval parameter.
    }

    public ArrayList<Double> getSmas() {
        return smas;
    }

    public ArrayList<Double> getLTANs() {
        return LTANs;
    }

    public TradespaceSearch getTradespaceSearch() {
        return tradespaceSearch;
    }
}
