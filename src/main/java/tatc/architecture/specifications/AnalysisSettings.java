package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

/**
 * Configuration options specific to TAT-C outputs.
 * Used by: TradespaceSearch
 */
public class AnalysisSettings {

    @SerializedName("@type")
    private final String _type="AnalysisSettings";
    private final int propagationFidelity;
    private final boolean includePropulsion;
    private final Outputs outputs;
    private final OutputBounds outputBounds;
    private final String searchPreference;
    private final SearchParameters searchParameters;

    public AnalysisSettings(int propagationFidelity, boolean includePropulsion, Outputs outputs, OutputBounds outputBounds, String searchPreference, SearchParameters searchParameters) {
        this.propagationFidelity = propagationFidelity;
        this.includePropulsion = includePropulsion;
        this.outputs = outputs;
        this.outputBounds = outputBounds;
        this.searchPreference = searchPreference;
        this.searchParameters = searchParameters;
    }

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
