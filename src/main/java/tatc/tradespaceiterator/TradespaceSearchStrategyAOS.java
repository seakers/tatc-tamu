package tatc.tradespaceiterator;

import org.moeaframework.algorithm.EpsilonMOEA;
import org.moeaframework.core.Initialization;
import org.moeaframework.core.Population;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;
import org.moeaframework.core.operator.*;
import seakers.aos.aos.AOSMOEA;
import seakers.aos.creditassignment.setimprovement.SetImprovementDominance;
import seakers.aos.history.AOSHistoryIO;
import seakers.aos.operator.AOSVariation;
import seakers.aos.operator.AOSVariationSI;
import seakers.aos.operatorselectors.AdaptivePursuit;
import seakers.aos.operatorselectors.OperatorSelector;
import seakers.architecture.operators.IntegerUM;
import tatc.ResultIO;
import tatc.tradespaceiterator.search.VariableMutation;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class TradespaceSearchStrategyAOS extends TradespaceSearchStrategyGA {

    public TradespaceSearchStrategyAOS(ProblemProperties properties) {
        super(properties);
    }

    public void start() {
        for (int i = 0; i < 30; i++) {

            long startTime = System.nanoTime();

            //set up variations
            //example of operators you might use
            ArrayList<Variation> operators = new ArrayList();

            Variation crossover1 = new OnePointCrossover(1);
            Variation crossover2 = new TwoPointCrossover(1);
            Variation crossover3 = new UniformCrossover(1);
            Variation mutation1 = new VariableMutation(1);
            Variation mutation2 = new IntegerUM(0.5);
            Variation compoundOp1 = new CompoundVariation(crossover1, mutation1);
            Variation compoundOp2 = new CompoundVariation(crossover2, mutation1);
            Variation compoundOp3 = new CompoundVariation(crossover3, mutation1);
            Variation compoundOp4 = new CompoundVariation(crossover1, mutation2);
            Variation compoundOp5 = new CompoundVariation(crossover2, mutation2);
            Variation compoundOp6 = new CompoundVariation(crossover3, mutation2);

            operators.add(crossover1);
            operators.add(crossover2);
            operators.add(crossover3);
            operators.add(mutation1);
            operators.add(mutation2);
            operators.add(compoundOp1);
            operators.add(compoundOp2);
            operators.add(compoundOp3);
            operators.add(compoundOp4);
            operators.add(compoundOp5);
            operators.add(compoundOp6);

            Initialization initialization = new RandomInitialization(this.problem, populationSize);
            Population initialPopulation = new Population();

            //create AOS
            //create operator selector
            OperatorSelector operatorSelector = new AdaptivePursuit(operators, 0.8, 0.8, 0.03);

            //create credit assignment
            SetImprovementDominance creditAssignment = new SetImprovementDominance(archive, 1, 0);

            //create AOS
            AOSVariation aosStrategy = new AOSVariationSI(operatorSelector, creditAssignment, populationSize);
            EpsilonMOEA emoea = new EpsilonMOEA(problem, initialPopulation, archive,
                    selection, aosStrategy, initialization, comparator);
            AOSMOEA aos = new AOSMOEA(emoea, aosStrategy, true);

            //for all solutions found
            ArrayList<Solution> allSolutions = new ArrayList<>();

            //for unique solutions found
            HashSet<Solution> uniqueSolutions = new HashSet<>();

            System.out.println(String.format("Initializing population... Size = %d", populationSize));
            aos.step(); // to make sure that the initial population is evaluated

            //add initial population to the solutions list
            for (int j = 0; j < initialPopulation.size(); j++) {
                Solution s = initialPopulation.get(j);
                s.setAttribute("NFE", 0);
                allSolutions.add(s);
            }

            while (!aos.isTerminated() && aos.getNumberOfEvaluations() < maxNFE) {
                aos.step();
                double currentTime = ((System.nanoTime() - startTime) / Math.pow(10, 9)) / 60.;
                System.out.println(
                        String.format("%d NFE out of %d NFE: Time elapsed = %10f min."
                                + " Approximate time remaining %10f min.",
                                aos.getNumberOfEvaluations(), maxNFE, currentTime,
                                currentTime / emoea.getNumberOfEvaluations() * (maxNFE - aos.getNumberOfEvaluations())));
                for (Solution solution : aos.getPopulation()) {
                    uniqueSolutions.add(solution);
                }
                for (int j = 1; j < 3; j++) {
                    Solution s = aos.getPopulation().get(aos.getPopulation().size() - j);
                    s.setAttribute("NFE", aos.getNumberOfEvaluations());
                    allSolutions.add(s);
                }
            }
            ResultIO.saveSearchResults(new Population(allSolutions), Paths.get(System.getProperty("tatc.moea"), String.format("HV%d", i)).toString());
            ResultIO.savePopulation(new Population(allSolutions), Paths.get(System.getProperty("tatc.moea"), "population").toString());
            ResultIO.saveSearchResults(new Population(allSolutions), Paths.get(System.getProperty("tatc.moea"), "results").toString());
            AOSHistoryIO.saveCreditHistory(aos.getCreditHistory(), new File(System.getProperty("tatc.moea"), "res.credit"), ",");
            AOSHistoryIO.saveSelectionHistory(aos.getSelectionHistory(), new File(System.getProperty("tatc.moea"), "res.select"), ",");
            aos.terminate();
        }
    }
}
