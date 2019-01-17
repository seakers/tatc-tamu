package tatc.tradespaceiterator;

import org.moeaframework.core.EpsilonBoxDominanceArchive;
import org.moeaframework.core.Initialization;
import org.moeaframework.core.Population;
import org.moeaframework.core.Problem;
import org.moeaframework.core.comparator.DominanceComparator;
import org.moeaframework.core.comparator.ParetoDominanceComparator;
import org.moeaframework.core.operator.RandomInitialization;
import org.moeaframework.core.operator.TournamentSelection;

public abstract class TradespaceSearchStrategyGA implements TradespaceSearchStrategy {
    public final ProblemProperties properties;
    public final Problem problem;
    int maxNFE;
    int populationSize;
    Initialization initialization;
    Population population;
    DominanceComparator comparator;
    EpsilonBoxDominanceArchive archive;
    TournamentSelection selection;

    public TradespaceSearchStrategyGA(ProblemProperties properties) {
        this.properties=properties;
        this.problem=createProblem(properties);
        this.maxNFE=10;
        this.populationSize=2;
        this.initialization=new RandomInitialization(this.problem, populationSize);
        this.population=new Population();
        this.comparator=new ParetoDominanceComparator();
        //10k$ cost and 60seconds rev time
        this.archive=new EpsilonBoxDominanceArchive(new double[]{10, 10});
        this.selection=new TournamentSelection(2, comparator);
    }

    protected  Problem createProblem(ProblemProperties properties){
        String problemType=properties.tsr.getMissionConcept().getProblemType();
        switch (problemType){
            case "Walker":
                return new ProblemGAWalker(properties);
            case "Train":
                return new ProblemGATrain(properties);
            default:
                throw new IllegalArgumentException("No Problem Type found.");
        }
    }

    public abstract void start();
}
