package tatc.tradespaceiterator;

import org.moeaframework.core.Solution;
import org.moeaframework.problem.AbstractProblem;
import tatc.architecture.StandardFormArchitecture;
import tatc.architecture.variable.IntegerVariable;
import tatc.architecture.variable.RealVariable;

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
        // 2. create the ArchitectureMethods JSON file using the method toJSON() from TATCWalker
        // 3. Call TradespaceSearchExecutive.evaluate(JSONfile)

        incrementCounter();
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
        
        sol.setVariable(0, new IntegerVariable(0, 0, ((ProblemPropertiesWalker)properties).getSmas().size() - 1));
        sol.setVariable(1, new IntegerVariable(0, 0, ((ProblemPropertiesWalker)properties).getInclinations().size() - 1));
        sol.setVariable(2, new IntegerVariable(0, 0, ((ProblemPropertiesWalker)properties).getNumberOfSats().size() - 1));
        sol.setVariable(3, new RealVariable(0, 1)); //planes
        sol.setVariable(4, new RealVariable(0, 1)); //phasing
        
        return sol;
    }
}
