package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Contains the values of all the parameters needed to set up the genetic algorithm.
 * Used by: AnalysisSettings
 */
public class SearchParameters {

    @SerializedName("@type")
    private final String _type="SearchParameters";
    private final Integer maxNFE;
    private final Integer populationSize;
    private final ArrayList<Double> epsilons;
    private final Integer sizeTournament;
    private final Double pCrossover;
    private final Double pMutation;
    private final Double alpha;
    private final Double beta;
    private final Double pmin;
    private final String iOperators;
    private final String dOperators;
    private final Integer NFEtriggerDM;
    private final Integer nOperRepl;

    public SearchParameters(int maxNFE, int populationSize, ArrayList<Double> epsilons, int sizeTournament, double pCrossover, double pMutation, double alpha, double beta, double pmin, String iOperators, String dOperators, int NFEtriggerDM, int nOperRepl) {
        this.maxNFE = maxNFE;
        this.populationSize = populationSize;
        this.epsilons = epsilons;
        this.sizeTournament = sizeTournament;
        this.pCrossover = pCrossover;
        this.pMutation = pMutation;
        this.alpha = alpha;
        this.beta = beta;
        this.pmin = pmin;
        this.iOperators = iOperators;
        this.dOperators = dOperators;
        this.NFEtriggerDM = NFEtriggerDM;
        this.nOperRepl = nOperRepl;
    }

    public int getMaxNFE() {
        return maxNFE;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public ArrayList<Double> getEpsilons() {
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
