package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

/**
 * A variable to be maximized or minimized to achieve mission objectives.
 * Used by: MissionConcept
 */
public class MissionObjective {

    @SerializedName("@type")
    private final String _type="MissionObjective";
    private final String name;
    private final String parent;
    private final double weight;
    private final String type;
    private final double target;

    public MissionObjective(String name, String parent, double weight, String type, double target) {
        this.name = name;
        this.parent = parent;
        this.weight = weight;
        this.type = type;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public double getTarget() {
        return target;
    }
}
