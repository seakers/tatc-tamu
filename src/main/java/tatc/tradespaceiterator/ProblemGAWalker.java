package tatc.tradespaceiterator;

import org.moeaframework.core.Solution;
import org.moeaframework.problem.AbstractProblem;
import tatc.architecture.StandardFormArchitecture;
import tatc.architecture.variable.IntegerVariable;
import tatc.architecture.variable.RealVariable;

public class ProblemGAWalker extends AbstractProblem {
    
    ProblemProperties properties;

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
        // 2. create the Architecture JSON file using the method toJSON() from TATCWalker
        // 3. Call ExecutiveDriver.evaluate(JSONfile)

        
    }

    //structure of solution with walker params
    //TODO:create Architecture.json creator in StandardFormArchitecture class
    @Override
    public final Solution newSolution() {
        
        Solution sol = new StandardFormArchitecture(getNumberOfVariables(), getNumberOfObjectives());
        
        sol.setVariable(0, new IntegerVariable(0, 0, ((ProblemPropertiesWalker)properties).smas.size() - 1));
        sol.setVariable(1, new IntegerVariable(0, 0, ((ProblemPropertiesWalker)properties).inclination.size() - 1));
        sol.setVariable(2, new IntegerVariable(0, 0, ((ProblemPropertiesWalker)properties).numberOfSats.size() - 1));
        sol.setVariable(3, new RealVariable(0, 1)); //planes
        sol.setVariable(4, new RealVariable(0, 1)); //phasing
        
        return sol;
    }
}
