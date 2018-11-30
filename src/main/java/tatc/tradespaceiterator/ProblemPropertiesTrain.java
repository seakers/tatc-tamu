package tatc.tradespaceiterator;

import java.util.ArrayList;
import java.util.Properties;

public class ProblemPropertiesTrain extends ProblemProperties{
    public final ArrayList<Double> smas;
    public final ArrayList<Double> LTANs;

    public ProblemPropertiesTrain(TradespaceSearchRequest tsr, Properties properties) {
        super(tsr, properties);
        smas = discretizeSemiMajorAxes(tsr.getSatelliteOrbits().getSemiMajorAxisRange());
        LTANs=new ArrayList<>();
        LTANs.add(10.5);
        LTANs.add(11.0);
        LTANs.add(11.5);
    }
}
