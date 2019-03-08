package tatc.tradespaceiterator;

import org.moeaframework.algorithm.EpsilonMOEA;
import org.moeaframework.core.Initialization;
import org.moeaframework.core.Population;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;
import org.moeaframework.core.operator.*;
import tatc.ResultIO;
import tatc.tradespaceiterator.search.VariableMutation;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class TradespaceSearchStrategyMOEA extends TradespaceSearchStrategyGA {

    public TradespaceSearchStrategyMOEA(ProblemProperties properties) {
        super(properties);
    }

    public void start() {
        for (int i = 0; i < 30; i++) {
            long startTime = System.nanoTime();

            Variation crossover1 = new OnePointCrossover(1);
            Variation crossover2 = new TwoPointCrossover(1);
            Variation crossover3 = new UniformCrossover(1);
            Variation mutation = new VariableMutation(1);
          
            CompoundVariation operators = new CompoundVariation(crossover1, crossover2, crossover3, mutation);

            Initialization initialization = new RandomInitialization(this.problem, populationSize);
            Population initialPopulation = new Population();
            
            //create MOEA
            EpsilonMOEA emoea = new EpsilonMOEA(problem, initialPopulation, archive,
                    selection, operators, initialization, comparator);

            //for all solutions found
            ArrayList<Solution> allSolutions = new ArrayList<>();

            //for unique solutions found
            HashSet<Solution> uniqueSolutions = new HashSet<>();

            System.out.println(String.format("Initializing population... Size = %d", populationSize));
            emoea.step(); // to make sure that the initial population is evaluated

            //add initial population to the solutions list
            for (int j = 0; j < initialPopulation.size(); j++) {
                Solution s = initialPopulation.get(j);
                s.setAttribute("NFE", 0);
                allSolutions.add(s);
            }

            while (!emoea.isTerminated() && emoea.getNumberOfEvaluations() < maxNFE) {
                emoea.step();
                double currentTime = ((System.nanoTime() - startTime) / Math.pow(10, 9)) / 60.;
                System.out.println(
                        String.format("%d NFE out of %d NFE: Time elapsed = %10f min."
                                        + " Approximate time remaining %10f min.",
                                emoea.getNumberOfEvaluations(), maxNFE, currentTime,
                                currentTime / emoea.getNumberOfEvaluations() * (maxNFE - emoea.getNumberOfEvaluations())));
                for (Solution solution : emoea.getPopulation()) {
                    uniqueSolutions.add(solution);
                }
                for (int j = 1; j < 3; j++) {
                    Solution s = emoea.getPopulation().get(emoea.getPopulation().size() - j);
                    s.setAttribute("NFE", emoea.getNumberOfEvaluations());
                    allSolutions.add(s);
                }
            }
            ResultIO.saveSearchResults(new Population(allSolutions), Paths.get(System.getProperty("tatc.moea"), String.format("HV%d", i)).toString());
            ResultIO.savePopulation(new Population(uniqueSolutions), Paths.get(System.getProperty("tatc.moea"), String.format("population%d", i)).toString());
            ResultIO.saveSearchResults(new Population(uniqueSolutions), Paths.get(System.getProperty("tatc.moea"), String.format("results%d", i)).toString());
            emoea.terminate();
        }
    }
}
