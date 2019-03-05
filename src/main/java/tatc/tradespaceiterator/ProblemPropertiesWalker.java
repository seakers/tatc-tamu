package tatc.tradespaceiterator;

import org.hipparchus.util.FastMath;
import org.orekit.utils.Constants;
import tatc.architecture.specifications.Constellation;
import tatc.architecture.specifications.Orbit;
import tatc.architecture.specifications.QuantitativeRange;
import tatc.architecture.specifications.TradespaceSearch;
import tatc.architecture.variable.Decision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
