package tatc.tradespaceiterator;

import org.hipparchus.util.FastMath;
import org.orekit.utils.Constants;
import tatc.architecture.specifications.Constellation;
import tatc.architecture.specifications.Orbit;
import tatc.architecture.specifications.QuantitativeRange;
import tatc.architecture.specifications.TradespaceSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemPropertiesWalker implements ProblemProperties{
    private final ArrayList<Double> smas;
    private final ArrayList<Double> inclinations;
    private final ArrayList<Integer> numberOfSats;
    private final TradespaceSearch tradespaceSearch;

    public ProblemPropertiesWalker(TradespaceSearch tsr) {
        this.tradespaceSearch = tsr;
        Constellation constellation=tsr.getDesignSpace().getConstellations().get(0);
        Orbit orbits=constellation.getOrbit().get(0);

        if (orbits.getAltitudeType()== QuantitativeRange.class) {
            QuantitativeRange altitudes = (QuantitativeRange) orbits.getAltitude();
            ArrayList<Double> altitudesDiscrete = altitudes.discretize();
            this.smas = new ArrayList<>();
            for (Double alt : altitudesDiscrete) {
                this.smas.add(Constants.WGS84_EARTH_EQUATORIAL_RADIUS + alt);
            }
        }else if (orbits.getAltitudeType()== List.class){
            this.smas=(ArrayList)orbits.getAltitude();
        } else{
            this.smas=new ArrayList<>();
            this.smas.add(Constants.WGS84_EARTH_EQUATORIAL_RADIUS + (Double)orbits.getAltitude());
        }

        if (orbits.getInclinationType() == QuantitativeRange.class){
            QuantitativeRange inc = (QuantitativeRange)orbits.getInclination();
            this.inclinations = inc.discretize();
        } else if (orbits.getInclinationType() == Double.class){
            this.inclinations=new ArrayList<>();
            this.inclinations.add((Double)orbits.getInclination());
        } else {
            String specialInclination = (String)orbits.getInclination();
            this.inclinations=new ArrayList<>();
            switch (specialInclination) {
                //identifier of SSO = -1 so that we can calculate it using alt later on
                case "SSO":
                    inclinations.add(-1.0);
                case "CriticallyInclined":
                    inclinations.add(FastMath.toRadians(63.4));
                case "ISS":
                    inclinations.add(FastMath.toRadians(51.6));
                default:
                    throw new UnsupportedOperationException(
                            String.format("Expected token SSO, CriticallyInclined, or ISS. Found %s.", specialInclination));
            }
        }

        if (constellation.getNumberSatellitesType() == QuantitativeRange.class){
            QuantitativeRange nsats = (QuantitativeRange)constellation.getNumberSatellites();
             ArrayList<Double> nSatsDiscret= nsats.discretize();
            List<Integer>  nSatsDiscretIntegerList=  nSatsDiscret.stream().map(p -> p.intValue()).collect(Collectors.toList());
            this.numberOfSats = (ArrayList<Integer>) nSatsDiscretIntegerList;
        }else if (constellation.getNumberSatellitesType()== List.class){
            this.numberOfSats=(ArrayList)constellation.getNumberSatellites();
        } else{
            this.numberOfSats=new ArrayList<>();
            this.numberOfSats.add((Integer)constellation.getNumberSatellites());
        }
    }

    public ArrayList<Double> getSmas() {
        return smas;
    }

    public ArrayList<Double> getInclinations() {
        return inclinations;
    }

    public ArrayList<Integer> getNumberOfSats() {
        return numberOfSats;
    }

    public TradespaceSearch getTradespaceSearch() {
        return tradespaceSearch;
    }
}
