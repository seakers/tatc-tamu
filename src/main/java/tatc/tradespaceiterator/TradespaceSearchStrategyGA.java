package tatc.tradespaceiterator;

import org.moeaframework.core.EpsilonBoxDominanceArchive;
import org.moeaframework.core.Initialization;
import org.moeaframework.core.Population;
import org.moeaframework.core.Problem;
import org.moeaframework.core.comparator.DominanceComparator;
import org.moeaframework.core.comparator.ParetoDominanceComparator;
import org.moeaframework.core.operator.RandomInitialization;
import org.moeaframework.core.operator.TournamentSelection;

import java.util.ArrayList;

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
        this.maxNFE=properties.getTradespaceSearch().getSettings().getSearchParameters().getMaxNFE();
        this.populationSize=properties.getTradespaceSearch().getSettings().getSearchParameters().getPopulationSize();
        this.initialization=new RandomInitialization(this.problem, populationSize);
        this.population=new Population();
        this.comparator=new ParetoDominanceComparator();
        //10k$ cost and 60seconds rev time
        ArrayList<Double> epsilon = properties.getTradespaceSearch().getSettings().getSearchParameters().getEpsilons();
        this.archive=new EpsilonBoxDominanceArchive(new double[]{epsilon.get(0), epsilon.get(1)});
        this.selection=new TournamentSelection(properties.getTradespaceSearch().getSettings().getSearchParameters().getSizeTournament(), comparator);
    }

    protected  Problem createProblem(ProblemProperties properties) throws IllegalArgumentException{
        if (this.properties instanceof ProblemPropertiesWalker){
            return new ProblemGAWalker(properties);
        } else if (this.properties instanceof ProblemPropertiesTrain){
            return new ProblemGATrain(properties);
        } else {
            throw new IllegalArgumentException("No Problem Type found.");
        }
    }

    public abstract void start();
}
