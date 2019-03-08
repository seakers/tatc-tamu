package tatc.tradespaceiterator;

import org.moeaframework.core.Solution;
import org.moeaframework.problem.AbstractProblem;
import seakers.conmop.util.Factor;
import tatc.architecture.StandardFormArchitecture;
import tatc.architecture.specifications.Constellation;
import tatc.architecture.specifications.Orbit;
import tatc.architecture.specifications.QuantitativeRange;
import tatc.architecture.variable.IntegerVariable;
import tatc.architecture.variable.RealVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ProblemGAWalker extends AbstractProblem {
    
    ProblemProperties properties;

    private int counter = 0;

    public ProblemGAWalker(ProblemProperties properties){
        //TODO: variables for objectives
        super(5, 2);
        this.properties=properties;
    }

    @Override
    public void evaluate(Solution solution) {
        StandardFormArchitecture soln = null;
        if (solution instanceof StandardFormArchitecture) {
            soln = (StandardFormArchitecture) solution;
        } else {
            throw new IllegalArgumentException(
                    String.format("Expected a TATCArchitecture."
                            + " Found %s", solution.getClass()));
        }
        
        //TODO:
        // 1. From the gene, create a TATCWalker object
        Constellation constellation=this.properties.getTradespaceSearch().getDesignSpace().getConstellations().get(0);
        Orbit orbit = constellation.getOrbit().get(0);

        double sma=-1;
        if (orbit.getAltitudeType() == QuantitativeRange.class){
            sma = ((QuantitativeRange)orbit.getAltitude()).discretize().get(((IntegerVariable) soln.getVariable(0)).getValue());
        } else if (orbit.getAltitudeType() == Double.class){
            //TODO: think about it
            sma = 0;
        } else if (orbit.getAltitudeType() == List.class) {
            //TODO: think about it
            sma = 0;
        }

        double incl=-1;
        if (orbit.getInclinationType() == QuantitativeRange.class){
            incl = ((QuantitativeRange)orbit.getInclination()).discretize().get(((IntegerVariable) soln.getVariable(1)).getValue());
        } else if (orbit.getAltitudeType() == Double.class){
            //TODO: think about it
            incl = 0;
        } else if (orbit.getAltitudeType() == String.class) {
            //TODO: think about it
            incl = 0;
        }

        double nsat=-1;
        if (orbit.getInclinationType() == QuantitativeRange.class){
            nsat = ((QuantitativeRange)constellation.getNumberSatellites()).discretize().get(((IntegerVariable) soln.getVariable(2)).getValue());
        } else if (orbit.getAltitudeType() == Integer.class){
            //TODO: think about it
            nsat = 0;
        } else if (orbit.getAltitudeType() == List.class) {
            //TODO: think about it
            nsat = 0;
        }

        //need to convert the real value that's between [0,1] to the number of planes.
        //The available number of planes is listed in the line below
        List<Integer> possiblePlanes = Factor.divisors((int)nsat); //planes would be divisors

        HashMap<Double, Integer> mappedPlanes = new HashMap<>();

        int counterPlanes = 0; //counter for possible planes index

        for (double i = 0; i <= 1; i += 1. / (possiblePlanes.size() - 1)) {
            mappedPlanes.put(i, possiblePlanes.get(counterPlanes));
            counterPlanes = counterPlanes + 1;
        }

        //read in the real value of planes from the solution
        double planes = ((RealVariable) (soln.getVariable(3))).getValue();

        int p = -1;
        double minDistancePlanes = Double.POSITIVE_INFINITY;
        Iterator<Double> iter1 = mappedPlanes.keySet().iterator();
        while (iter1.hasNext()) {
            double val = iter1.next();
            if (Math.abs(planes - val) < minDistancePlanes) {
                minDistancePlanes = Math.abs(planes - val);
                p = mappedPlanes.get(val);
            }
        }

        if (p == -1) {
            throw new IllegalStateException("Error in number of planes p = -1");
        }

        //The available number of phases is listed below
        //copying available number of planes to phases array
        //then we subtract -1 from each value to get the number of phases
        List<Integer> possiblePhases = new ArrayList<>();

        for (int i = 0; i < possiblePlanes.size(); i++) {
            possiblePhases.add(possiblePlanes.get(i) - 1);
        }

        HashMap<Double, Integer> mappedPhases = new HashMap<>();

        int counterPhases = 0; //counter for possible planes index

        for (double i = 0; i <= 1; i += 1. / (possiblePhases.size() - 1)) {
            mappedPhases.put(i, possiblePhases.get(counterPhases));
            counterPhases = counterPhases + 1;
        }

        //read in the real value of phases from the solution
        double phases = ((RealVariable) (soln.getVariable(4))).getValue();

        int f = -1;
        double minDistancePhases = Double.POSITIVE_INFINITY;
        Iterator<Double> iter = mappedPhases.keySet().iterator();
        while (iter.hasNext()) {
            double val = iter.next();
            if (Math.abs(phases - val) < minDistancePhases) {
                minDistancePhases = Math.abs(phases - val);
                f = mappedPhases.get(val);
            }
        }

        if (f == -1) {
            throw new IllegalStateException("Error in number of phases q = -1");
        }
//        TATCWalker architecture = new TATCWalker(sma,incl,(int)nsat,p,f,this.properties);
//
//        // 2. create the Architecture JSON file
//        File architectureJsonFile = architecture.toJSON(this.getCounter());
//        // 3. Evaluate architecture
//        TradespaceSearchExecutive.evaluateArchitecture(architectureJsonFile);
//        // increment the counter at each architecture evaluation
//        this.incrementCounter();


    }

    public int getCounter() {
        return counter;
    }

    private void incrementCounter(){
        this.counter++;
    }

    //structure of solution with walker params
    //TODO:create ArchitectureMethods.json creator in StandardFormArchitecture class
    @Override
    public final Solution newSolution() {
        
        Solution sol = new StandardFormArchitecture(getNumberOfVariables(), getNumberOfObjectives());
        
//        sol.setVariable(0, new IntegerVariable(0, 0, ((ProblemPropertiesWalker)properties).getSmas().size() - 1));
//        sol.setVariable(1, new IntegerVariable(0, 0, ((ProblemPropertiesWalker)properties).getInclinations().size() - 1));
//        sol.setVariable(2, new IntegerVariable(0, 0, ((ProblemPropertiesWalker)properties).getNumberOfSats().size() - 1));
//        sol.setVariable(3, new RealVariable(0, 1)); //planes
//        sol.setVariable(4, new RealVariable(0, 1)); //phasing
        
        return sol;
    }
}
