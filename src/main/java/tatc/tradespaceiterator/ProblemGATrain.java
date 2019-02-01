package tatc.tradespaceiterator;

import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.BinaryVariable;
import org.moeaframework.problem.AbstractProblem;
import org.orekit.errors.OrekitException;
import org.orekit.time.AbsoluteDate;
import tatc.architecture.StandardFormArchitecture;
import tatc.architecture.TATCTrain;
import tatc.architecture.specifications.Constellation;
import tatc.architecture.specifications.Orbit;
import tatc.architecture.specifications.QuantitativeRange;
import tatc.architecture.variable.IntegerVariable;

import java.io.File;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProblemGATrain extends AbstractProblem {
    
    ProblemProperties properties;

    private int counter = 0;

    public ProblemGATrain(ProblemProperties properties){
        super(2, 2);
        this.properties=properties;
    }


    @Override
    public void evaluate(Solution solution) throws IllegalArgumentException{
       
        StandardFormArchitecture soln = null;
        if (solution instanceof StandardFormArchitecture) {
            soln = (StandardFormArchitecture) solution;
        } else {
            throw new IllegalArgumentException(
                    String.format("Expected a TATCArchitecture."
                            + " Found %s", solution.getClass()));
        }

        // 1. From the gene, create a TATCTrain object
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


        BitSet bits=((BinaryVariable) soln.getVariable(1)).getBitSet();
        ArrayList<Double> LTANsFiltered = IntStream.range(0, ((ProblemPropertiesTrain)properties).getLTANs().size())
                .filter(i -> bits.get(i))
                .mapToObj(((ProblemPropertiesTrain)properties).getLTANs()::get)
                .collect(Collectors.toCollection(ArrayList::new));

        try {
            //TODO: figure out how to create the orekit AbsoluteDate from starDate epoch
            // 1. From constellation, create a TATCTrain object
            TATCTrain architecture = new TATCTrain(sma,LTANsFiltered,new AbsoluteDate(),this.properties);
            // 2. create the ArchitectureMethods JSON
            File architectureJsonFile = architecture.toJSON(this.getCounter());
            // 3. Evaluate architecture
            TradespaceSearchExecutive.evaluateArchitecture(architectureJsonFile);
            // increment the counter at each architecture evaluation
            this.incrementCounter();
        } catch (OrekitException e) {
            e.printStackTrace();
        }
    }

    public int getCounter() {
        return counter;
    }

    private void incrementCounter(){
        this.counter++;
    }

    //structure of solution with walker params
    @Override
    public final Solution newSolution() {
        
        Solution sol = new StandardFormArchitecture(getNumberOfVariables(), getNumberOfObjectives());
        
        sol.setVariable(0, new IntegerVariable(0, 0, ((ProblemPropertiesTrain)properties).getSmas().size() - 1));
        sol.setVariable(1, new BinaryVariable(((ProblemPropertiesTrain)properties).getLTANs().size()));
        
        return sol;
    }
}
