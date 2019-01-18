package tatc.architecture.specifications;

/**
 * Contains the values of all the parameters needed to set up the genetic algorithm.
 * Used by: AnalysisSettings
 */
public class SearchParameters {

    private final int maxNFE;
    private final int populationSize;
    private final double epsilons;
    private final int sizeTournament;
    private final double pCrossover;
    private final double pMutation;
    private final double alpha;
    private final double beta;
    private final double pmin;
    private final String iOperators;
    private final String dOperators;
    private final int NFEtriggerDM;
    private final int nOperRepl;

    public int getMaxNFE() {
        return maxNFE;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public double getEpsilons() {
        return epsilons;
    }

    public int getSizeTournament() {
        return sizeTournament;
    }

    public double getpCrossover() {
        return pCrossover;
    }

    public double getpMutation() {
        return pMutation;
    }

    public double getAlpha() {
        return alpha;
    }

    public double getBeta() {
        return beta;
    }

    public double getPmin() {
        return pmin;
    }

    public String getiOperators() {
        return iOperators;
    }

    public String getdOperators() {
        return dOperators;
    }

    public int getNFEtriggerDM() {
        return NFEtriggerDM;
    }

    public int getnOperRepl() {
        return nOperRepl;
    }
}
