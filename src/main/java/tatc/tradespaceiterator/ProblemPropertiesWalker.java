package tatc.tradespaceiterator;

import tatc.architecture.specifications.TradespaceSearch;
import tatc.architecture.variable.Decision;

import java.util.HashMap;

public class ProblemPropertiesWalker implements ProblemProperties{
    private final TradespaceSearch tradespaceSearch;
    private final HashMap<String,Decision<?>> decisions;

    public ProblemPropertiesWalker(TradespaceSearch tsr) {
        this.tradespaceSearch = tsr;
        decisions = this.tradespaceSearch.TradespaceSearch2Decisions();
    }


    public TradespaceSearch getTradespaceSearch() {
        return tradespaceSearch;
    }

    public HashMap<String, Decision<?>> getDecisions() {
        return decisions;
    }
}
