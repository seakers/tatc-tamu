package tatc.tradespaceiterator;

import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.BinaryVariable;
import org.moeaframework.problem.AbstractProblem;
import tatc.architecture.StandardFormArchitecture;
import tatc.architecture.variable.IntegerVariable;

public class ProblemGATrain extends AbstractProblem {
    
    ProblemProperties properties;

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

        //TODO:
        // 1. From the gene, create a TATCTrain object
        // 2. create the Architecture JSON file using the method toJSON() from TATCTrain
        // 3. Call TradespaceSearchExecutive.evaluate(JSONfile)
        

    }

    //structure of solution with walker params
    @Override
    public final Solution newSolution() {
        
        Solution sol = new StandardFormArchitecture(getNumberOfVariables(), getNumberOfObjectives());
        
        sol.setVariable(0, new IntegerVariable(0, 0, ((ProblemPropertiesTrain)properties).smas.size() - 1));
        sol.setVariable(1, new BinaryVariable(((ProblemPropertiesTrain)properties).LTANs.size()));
        
        return sol;
    }
}