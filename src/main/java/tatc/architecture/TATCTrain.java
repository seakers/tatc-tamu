package tatc.architecture;

import org.hipparchus.util.FastMath;
import org.orekit.errors.OrekitException;
import org.orekit.time.TimeScalesFactory;
import org.orekit.utils.Constants;
import seakers.conmop.util.Bounds;
import seakers.orekit.util.Orbits;
import org.orekit.time.AbsoluteDate;
import tatc.architecture.specifications.*;
import tatc.tradespaceiterator.ProblemProperties;
import tatc.util.JSONIO;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class TATCTrain implements ArchitectureMethods {

    private final ProblemProperties properties;

    private final List<Orbit> orbits;

    private final double sma;

    private final double inclination;

    private final ArrayList<Double> LTANs;

    /**
     * Creates a train constellation given the semi-major axis, the date of the launch and an array of LTANs in decimal
     * hours. The satellites contained in this constellation are not assigned any instrumentation nor are any
     * steering/attitude laws.
     */
    public TATCTrain(double semiMajorAxis, int dayLaunch, int monthLaunch, int yearLaunch, ArrayList<Double> LTANs, AbsoluteDate startDate, ProblemProperties props) throws OrekitException {
        this.properties = props;
        final double inc = Orbits.incSSO(semiMajorAxis - Constants.WGS84_EARTH_EQUATORIAL_RADIUS);
        this.orbits = new ArrayList<>(LTANs.size());
        double raanRef0 = Orbits.LTAN2RAAN(semiMajorAxis - Constants.WGS84_EARTH_EQUATORIAL_RADIUS, LTANs.get(0), dayLaunch, monthLaunch, yearLaunch);
        int hourLTAN = LTANs.get(0).intValue();
        int minLTAN = (int) (LTANs.get(0) * 60) % 60;
        int secLTAN = (int) (LTANs.get(0) * (60 * 60)) % 60;
        AbsoluteDate launchDate = new AbsoluteDate(yearLaunch, monthLaunch, dayLaunch, hourLTAN, minLTAN, secLTAN, TimeScalesFactory.getUTC());
        double timeLaunchStartDate = startDate.durationFrom(launchDate) / 3600;
        double raanRef = (raanRef0 + (timeLaunchStartDate / 24 * 2 * Math.PI)) % (2 * Math.PI);
        for (int i = 0; i < LTANs.size(); i++) {
            Orbit orbit = new Orbit("KEPLERIAN",semiMajorAxis- Constants.WGS84_EARTH_EQUATORIAL_RADIUS,
                    semiMajorAxis,inc,0.0,0.0,
                    (raanRef + ((LTANs.get(i) - LTANs.get(0)) / 24 * 2 * Math.PI)) % (2 * Math.PI),
                    (2 * Math.PI - ((LTANs.get(i) - LTANs.get(0)) / (Orbits.circularOrbitPeriod(semiMajorAxis) / 3600) * 2 * Math.PI)) % (2 * Math.PI),
                    properties.getTradespaceSearch().getMission().getStart(),Double.toString(LTANs.get(i)));

            this.orbits.add(orbit);
        }
        this.inclination = inc;
        this.sma = semiMajorAxis;
        this.LTANs = LTANs;
    }

    public TATCTrain(double semiMajorAxis, ArrayList<Double> LTANs, AbsoluteDate startDate, ProblemProperties props) throws OrekitException {
        this.properties = props;
        final double inc = Orbits.incSSO(semiMajorAxis - Constants.WGS84_EARTH_EQUATORIAL_RADIUS);
        this.orbits = new ArrayList<>(LTANs.size());
        double raanRef0 = Orbits.LTAN2RAAN(semiMajorAxis - Constants.WGS84_EARTH_EQUATORIAL_RADIUS, LTANs.get(0),
                startDate.getComponents(TimeScalesFactory.getUTC()).getDate().getDay(),
                startDate.getComponents(TimeScalesFactory.getUTC()).getDate().getMonth(),
                startDate.getComponents(TimeScalesFactory.getUTC()).getDate().getYear());
        int hourLTAN = LTANs.get(0).intValue();
        int minLTAN = (int) (LTANs.get(0) * 60) % 60;
        int secLTAN = (int) (LTANs.get(0) * (60 * 60)) % 60;
        AbsoluteDate launchDate = new AbsoluteDate(startDate.getComponents(TimeScalesFactory.getUTC()).getDate().getYear(),
                startDate.getComponents(TimeScalesFactory.getUTC()).getDate().getMonth(),
                startDate.getComponents(TimeScalesFactory.getUTC()).getDate().getDay(),
                hourLTAN, minLTAN, secLTAN, TimeScalesFactory.getUTC());
        double timeLaunchStartDate = startDate.durationFrom(launchDate) / 3600;
        double raanRef = (raanRef0 + (timeLaunchStartDate / 24 * 2 * Math.PI)) % (2 * Math.PI);
        for (int i = 0; i < LTANs.size(); i++) {
            Orbit orbit = new Orbit("KEPLERIAN",semiMajorAxis- Constants.WGS84_EARTH_EQUATORIAL_RADIUS,
                    semiMajorAxis,inc,0.0,0.0,
                    (raanRef + ((LTANs.get(i) - LTANs.get(0)) / 24 * 2 * Math.PI)) % (2 * Math.PI),
                    (2 * Math.PI - ((LTANs.get(i) - LTANs.get(0)) / (Orbits.circularOrbitPeriod(semiMajorAxis) / 3600) * 2 * Math.PI)) % (2 * Math.PI),
                    properties.getTradespaceSearch().getMission().getStart(),Double.toString(LTANs.get(i)));
            this.orbits.add(orbit);
        }
        this.inclination = inc;
        this.sma = semiMajorAxis;
        this.LTANs = LTANs;
    }

    public double getSma() {
        return sma;
    }

    public double getInclination() {
        return inclination;
    }

    public ArrayList<Double> getLTANs() {
        return LTANs;
    }

    public List<Orbit> getOrbits() {
        return orbits;
    }

    @Override
    public File toJSON(int counter) {
        List<Satellite> satellites = new ArrayList<>();
        Satellite satelliteFromTradespaceSearch=this.properties.getTradespaceSearch().getDesignSpace().getSatellites().get(0);
        Constellation constellationFromTradespaceSearch=this.properties.getTradespaceSearch().getDesignSpace().getConstellations().get(0);
        for (Orbit orbit : this.getOrbits()){
            satellites.add(new Satellite(satelliteFromTradespaceSearch.getName(),
                    satelliteFromTradespaceSearch.getAcronym(),
                    satelliteFromTradespaceSearch.getAgency(),
                    satelliteFromTradespaceSearch.getMass(),
                    satelliteFromTradespaceSearch.getVolume(),
                    satelliteFromTradespaceSearch.getPower(),
                    satelliteFromTradespaceSearch.getCommBand(),
                    satelliteFromTradespaceSearch.getPayload(),
                    orbit));
        }
        Constellation constellation=new Constellation(constellationFromTradespaceSearch.getConstellationType(),
                this.getOrbits().size(), null,null,constellationFromTradespaceSearch.getOrbit(),
                constellationFromTradespaceSearch.getSatelliteInterval(),satellites);
        GroundNetwork groundNetwork=this.properties.getTradespaceSearch().getDesignSpace().getGroundNetworks().get(0);
        GroundNetwork groundNetworkWithGroundStations = new GroundNetwork(groundNetwork.getName(),
                groundNetwork.getAcronym(),
                groundNetwork.getAgency(),
                groundNetwork.getNumberStations(),
                this.properties.getTradespaceSearch().getDesignSpace().getGroundStations());
        List<Constellation> constellations = new ArrayList<>();
        constellations.add(constellation);
        List<GroundNetwork> groundNetworksWithGroundStations = new ArrayList<>();
        groundNetworksWithGroundStations.add(groundNetworkWithGroundStations);
        Architecture arch =new Architecture(constellations, groundNetworksWithGroundStations);
        File mainPath = new File(System.getProperty("user.dir"), "problems");
        File file = new File (mainPath,"arch-"+Integer.toString(counter)+".json");
        JSONIO.writeJSON(file,arch);
        try {
            JSONIO.replaceTypeFieldUnderscore(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
