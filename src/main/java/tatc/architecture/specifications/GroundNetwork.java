package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

/**
 * A network of ground stations providing uplink and downlink connectivity for a mission.
 * Used by: ArchitectureConstraints, ArchitectureMethods
 */
public class GroundNetwork {

    @SerializedName("@type")
    private final String _type="GroundNetwork";
    private final String name;
    private final String acronym;
    private final Agency agency;
    private final Object numberStations;
    // private final int numberStations;
    // private final QuantitativeRange numberStations;
    private final List<GroundStation> groundStations;

    public GroundNetwork(String name, String acronym, Agency agency, Object numberStations, List<GroundStation> groundStations) {
        this.name = name;
        this.acronym = acronym;
        this.agency = agency;
        this.numberStations = numberStations;
        this.groundStations = groundStations;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public Agency getAgency() {
        return agency;
    }

    public Object getNumberStations() throws IllegalArgumentException{
        if (numberStations instanceof Integer) {
            return numberStations;
        }else if (numberStations instanceof Double){
            return ((Double) numberStations).intValue();
        }else if (numberStations instanceof LinkedTreeMap && ((LinkedTreeMap) numberStations).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.createQuantitativeRangeFromLinkedTreeMap((LinkedTreeMap)numberStations);
        }else {
            throw new IllegalArgumentException("NumberStations has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public Class getNumberStationsType() throws IllegalArgumentException{
        if (numberStations instanceof Integer || numberStations instanceof Double){
            return Integer.class;
        }else if (numberStations instanceof LinkedTreeMap && ((LinkedTreeMap) numberStations).get("@type").equals("QuantitativeRange")){
            return QuantitativeRange.class;
        }else {
            throw new IllegalArgumentException("NumberStations has to be either an Integer or a QuantitativeRange in TradespaceSearch.json");
        }
    }

    public List<GroundStation> getGroundStations() {
        return groundStations;
    }
}
