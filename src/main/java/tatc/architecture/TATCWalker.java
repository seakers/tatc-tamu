/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tatc.architecture;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hipparchus.util.FastMath;
import org.orekit.utils.Constants;
import tatc.architecture.specifications.*;
import tatc.tradespaceiterator.ProblemProperties;
import tatc.util.JSONIO;

/**
 *
 * @author Prachi
 */
public class TATCWalker implements ArchitectureMethods{

    private final ProblemProperties properties;

    private final List<Orbit> orbits;

    private final double semimajoraxis;

    private final double inclination;

    private final int numberSatellites;

    private final int numberPlanes;

    private final int relativeSpacing;

    /**
     * Creates a walker delta-pattern constellation in the specified Walker
     * configuration at the specified semi-major axis. The satellites contained
     * in this constellation are not assigned any instrumentation nor are any
     * steering/attitude laws. Can specify where the reference raan and true
     * anomaly to orient the walker configuration
     */
    public TATCWalker(double semimajoraxis, double inc, int t, int p, int f, ProblemProperties props) {

        this.semimajoraxis=semimajoraxis;
        this.inclination=inc;
        this.numberSatellites=t;
        this.numberPlanes=p;
        this.relativeSpacing=f;
        this.properties=props;

        //checks for valid parameters
        if (t < 0 || p < 0) {
            throw new IllegalArgumentException(String.format("Expected t>0, p>0."
                    + " Found f=%d and p=%d", t, p));
        }
        if ((t % p) != 0) {
            throw new IllegalArgumentException(
                    String.format("Incompatible values for total number of "
                            + "satellites <t=%d> and number of planes <p=%d>. "
                            + "t must be divisible by p.", t, p));
        }
        if (f < 0 && f > p - 1) {
            throw new IllegalArgumentException(
                    String.format("Expected 0 <= f <= p-1. "
                            + "Found f = %d and p = %d.", f, p));
        }

        //Uses Walker delta pa
        final int s = t / p; //number of satellites per plane
        final double pu = 2 * FastMath.PI / t; //pattern unit
        final double delAnom = pu * p; //in plane spacing between satellites
        final double delRaan = pu * s; //node spacing
        final double phasing = pu * f;
        final double refAnom = 0;
        final double refRaan = 0;

        this.orbits = new ArrayList<>();
        for (int planeNum = 0; planeNum < p; planeNum++) {
            for (int satNum = 0; satNum < s; satNum++) {
                Orbit orbit = new Orbit("KEPLERIAN",semimajoraxis - Constants.WGS84_EARTH_EQUATORIAL_RADIUS,
                        semimajoraxis,inclination,0.0,0.0,
                        refRaan + planeNum * delRaan,
                        (refAnom + satNum * delAnom + phasing * planeNum) % (2. * FastMath.PI),
                        properties.getTradespaceSearch().getMission().getStart(),
                        "");
                this.orbits.add(orbit);
            }
        }
    }

    public List<Orbit> getOrbits() {
        return orbits;
    }

    public double getSemimajoraxis() {
        return semimajoraxis;
    }

    public double getInclination() {
        return inclination;
    }

    public int getNumberSatellites() {
        return numberSatellites;
    }

    public int getNumberPlanes() {
        return numberPlanes;
    }

    public int getRelativeSpacing() {
        return relativeSpacing;
    }

    @Override
    public File toJSON(int counter) {
        List<Satellite> satellites = new ArrayList<>();
        Constellation constellationFromTradespaceSearch=this.properties.getTradespaceSearch().getDesignSpace().getConstellations().get(0);
        Satellite satelliteFromTradespaceSearch=this.properties.getTradespaceSearch().getDesignSpace().getSatellites().get(0);
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
                this.getNumberSatellites(),this.getNumberPlanes(),this.getRelativeSpacing(),constellationFromTradespaceSearch.getOrbit(),null,satellites);
        GroundNetwork groundNetwork=this.properties.getTradespaceSearch().getDesignSpace().getGroundNetworks().get(0);
        GroundNetwork groundNetworkWithGroundStations = new GroundNetwork(groundNetwork.getName(),
                                                                        groundNetwork.getAcronym(),
                                                                        groundNetwork.getAgency(),
                                                                        groundNetwork.getNumberStations(),
                                                                        this.properties.getTradespaceSearch().getDesignSpace().getGroundStations());
        Architecture arch =new Architecture(constellation, groundNetworkWithGroundStations);
        File mainPath = new File(System.getProperty("user.dir"), "problems");
        File file = new File (mainPath,"Architecture"+Integer.toString(counter)+".json");
        JSONIO.writeJSON(file,arch);
        try {
            JSONIO.replaceTypeFieldUnderscore(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
