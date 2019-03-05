package tatc.util;

import seakers.orekit.constellations.TrainParameters;
import seakers.orekit.constellations.WalkerParameters;
import seakers.orekit.util.Orbits;
import tatc.architecture.variable.Decision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Enumeration {
    public Enumeration() {
    }

    public static ArrayList<WalkerParameters> fullFactWalker(Decision<Double> alts, Decision<Object> incs, Decision<Integer> ts, Decision<Integer> ps, Decision<Integer> fs) throws IllegalArgumentException{
        if (ps.getAllowedValues()==null){
            ArrayList<Integer> allowedPlanes = new ArrayList<>();
            for (int i=1; i<= Collections.max(ts.getAllowedValues()); i++){
                allowedPlanes.add(i);
            }
            ps=new Decision<>("Integer",allowedPlanes);
        }

        if (fs.getAllowedValues() == null){
            ArrayList<Integer> allowedRelativeSpacing = new ArrayList<>();
            for (int f=0; f<= Collections.max(ps.getAllowedValues())-1; f++){
                allowedRelativeSpacing.add(f);
            }
            fs=new Decision<>("Integer",allowedRelativeSpacing);
        }

        // Start the actual enumeration
        ArrayList<WalkerParameters> constels = new ArrayList();

        for(Double altitude : alts.getAllowedValues()) {
            for(Object inclination : incs.getAllowedValues()) {
                double inclinationModified = -1;
                if (inclination == "SSO"){
                    inclinationModified = Orbits.incSSO(altitude*1000);
                } else if (inclination instanceof Double){
                    inclinationModified = (Double) inclination;
                } else {
                    throw new IllegalArgumentException("Inclination not identified");
                }
                for(Integer t : ts.getAllowedValues()) {

                    ArrayList<Integer> planes = new ArrayList();
                    if (t != 1 && ps.getAllowedValues().contains(1)) {
                        planes.add(1);
                    }
                    int i_p;
                    for(i_p = 2; i_p <= t / 2; ++i_p) {
                        if (t % i_p == 0 && ps.getAllowedValues().contains(i_p)) {
                            planes.add(i_p);
                        }
                    }
                    if ( ps.getAllowedValues().contains(t)){
                        planes.add(t);
                    }

                    for(Integer plane : planes) {
                        for(int i_f = 0; i_f <= plane - 1; ++i_f) {
                            if (fs.getAllowedValues().contains(i_f)){
                                constels.add(new WalkerParameters(altitude, inclinationModified, t, plane, i_f));
                            }
                        }
                    }
                }
            }
        }

        return constels;
    }

    public static ArrayList<TrainParameters> fullFactTrain(Double[] alts, ArrayList<Double> LTANs) {
        ArrayList<TrainParameters> constellations = new ArrayList();
        ArrayList<ArrayList<Double>> combs = Combinatorics.powerSet(LTANs);

        for(int i_a = 0; i_a < alts.length; ++i_a) {
            Iterator iterator = combs.iterator();

            while(iterator.hasNext()) {
                ArrayList<Double> LTANi = (ArrayList)iterator.next();
                if (!LTANi.isEmpty()) {
                    constellations.add(new TrainParameters(alts[i_a], LTANi));
                }
            }
        }

        return constellations;
    }
}