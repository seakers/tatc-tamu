package tatc.architecture.specifications;

/**
 * Configuration options specific to TAT-C outputs.
 * Used by: TradespaceSearch
 */
public class AnalysisSettings {

    private final int propagationFidelity;
    private final boolean includePropulsion;
    private final Outputs outputs;
    private final OutputBounds outputBounds;
    private final String searchPreference;
    private final SearchParameters searchParameters;

    public int getPropagationFidelity() {
        return propagationFidelity;
    }

    public boolean isIncludePropulsion() {
        return includePropulsion;
    }

    public Outputs getOutputs() {
        return outputs;
    }

    public OutputBounds getOutputBounds() {
        return outputBounds;
    }

    public String getSearchPreference() {
        return searchPreference;
    }

    public SearchParameters getSearchParameters() {
        return searchParameters;
    }
}
