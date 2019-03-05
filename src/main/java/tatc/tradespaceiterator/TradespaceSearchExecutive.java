package tatc.tradespaceiterator;

import java.io.File;
import java.util.Properties;

import tatc.architecture.specifications.TradespaceSearch;
import tatc.util.JSONIO;

/**
 * TradespaceSearchExecutive class which reads TradespaceSearchRequest.json, creates a problem (e.g. Walker or Train),
 * and calls a search strategy (e.g. Full Factorial or Genetic Algorithm), shuts down the search and releases 
 * any resources.
 */
public class TradespaceSearchExecutive {

    public void run() throws IllegalArgumentException {

        this.setDirectories();

        TradespaceSearch tsr = JSONIO.readJSON( new File(System.getProperty("tatc.root"), "TradespaceSearch.json"),
                TradespaceSearch.class);

        Properties properties = new Properties();
        
        ProblemProperties searchProperties = this.createProblemProperties(tsr, properties);

        TradespaceSearchStrategy problem = this.createTradespaceSearchtrategy(tsr, searchProperties);

        problem.start();
    }
    
    public static void evaluateArchitecture(File architectureJSONFile) {
        //make python call: python arch_eval.py arch_file arch_dir
    }

    private TradespaceSearchStrategy createTradespaceSearchtrategy(TradespaceSearch tsr, ProblemProperties searchProperties) throws IllegalArgumentException {
        if (tsr.getSettings().getSearchPreference()==null){
            return new TradespaceSearchStrategyFF(searchProperties);
        }

        switch (tsr.getSettings().getSearchPreference()) {
            case "FF":
                return new TradespaceSearchStrategyFF(searchProperties);
            case "GA":
                return new TradespaceSearchStrategyMOEA(searchProperties);
//            case "AOS":
//                return new TradespaceSearchStrategyAOS(searchProperties);
            case "KDO":
                return new TradespaceSearchStrategyKDO(searchProperties);
            default:
                throw new IllegalArgumentException("Search Preference has to be either FF, MOEA, AOS or KDO.");
        }
    }

    private ProblemProperties createProblemProperties(TradespaceSearch tsr, Properties properties) throws IllegalArgumentException {
        
        switch (tsr.getDesignSpace().getConstellations().get(0).getConstellationType()) {
            case "DELTA_HOMOGENOUS":
                return new ProblemPropertiesWalker(tsr);
            case "TRAIN":
                return new ProblemPropertiesTrain(tsr);
            default:
                throw new IllegalArgumentException("Problem Type has to be either Walker or Train.");
        }
    }

    private void setDirectories() {
        
        File mainPath = new File(System.getProperty("user.dir"), "problems");
        System.setProperty("tatc.root", mainPath.getAbsolutePath());
        System.setProperty("tatc.numThreads", "16");
    }

}
