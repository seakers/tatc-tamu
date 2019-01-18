package tatc.architecture.specifications;

/**
 * A set of constraints and parameters to bound and define a tradespace search.
 * Used by: N/A (top level)
 */
public class TradespaceSearch {
    private final MissionConcept mission;
    private final DesignSpace designSpace;
    private final AnalysisSettings settings;

    public TradespaceSearch(MissionConcept mission, DesignSpace designSpace, AnalysisSettings settings) {
        this.mission = mission;
        this.designSpace = designSpace;
        this.settings = settings;
    }

    public MissionConcept getMission() {
        return mission;
    }

    public DesignSpace getDesignSpace() {
        return designSpace;
    }

    public AnalysisSettings getSettings() {
        return settings;
    }
}