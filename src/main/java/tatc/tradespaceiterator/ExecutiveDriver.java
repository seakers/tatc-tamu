package tatc.tradespaceiterator;

import java.io.File;
import java.util.Properties;
import tatc.util.JSONIO;

/**
 * ExecutiveDriver class which reads TradespaceSearchRequest.json, creates a problem (e.g. Walker or Train),
 * and calls a search strategy (e.g. Full Factorial or Genetic Algorithm), shuts down the search and releases 
 * any resources.
 */
public class ExecutiveDriver {

    public void run() throws IllegalArgumentException {

        this.setDirectories();

        TradespaceSearchRequest tsr = JSONIO.readJSON( new File(System.getProperty("tatc.root"), "TradespaceSearchRequest.json"),
                TradespaceSearchRequest.class);

        Properties properties = new Properties();
        
        ProblemProperties searchProperties = this.createProblemProperties(tsr, properties);

        TradespaceSearchIterator problem = this.createTradespaceSearchIterator(tsr, searchProperties);

        problem.start();

        searchProperties.rm.shutdown();

    }
    
    public outputs evaluateArchitecture(File architectureJSONFile) {
        return outputs;
    }

    private TradespaceSearchIterator createTradespaceSearchIterator(TradespaceSearchRequest tsr, ProblemProperties searchProperties) throws IllegalArgumentException {
        
        switch (tsr.getMissionConcept().getSearchPreferences()) {
            case "FF":
                return new TradespaceSearchIteratorFF(searchProperties);
            case "MOEA":
                return new TradespaceSearchIteratorMOEA(searchProperties);
            case "AOS":
                return new TradespaceSearchIteratorAOS(searchProperties);
            case "KDO":
                return new TradespaceSearchIteratorKDO(searchProperties);
            default:
                throw new IllegalArgumentException("Search Preference has to be either FF, MOEA, AOS or KDO.");
        }
    }

    private ProblemProperties createProblemProperties(TradespaceSearchRequest tsr, Properties properties) throws IllegalArgumentException {
        
        switch (tsr.getMissionConcept().getProblemType()) {
            case "Walker":
                return new ProblemPropertiesWalker(tsr, properties);
            case "Train":
                return new ProblemPropertiesTrain(tsr, properties);
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
