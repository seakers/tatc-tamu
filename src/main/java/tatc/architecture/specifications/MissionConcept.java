package tatc.architecture.specifications;

import java.util.List;

/**
 * A top-level functional description of an Earth-observing mission concept independent of the implementing form.
 * Used by: TradespaceSearch
 */

public class MissionConcept {
    private final String name;
    private final String acronym;
    private final Agency agency;
    private final String start;
    private final String end;
    private final Object duration;
    // private final String duration;
    // private final double duration;
    private final Region target;
    private final List<String> objects;
    private final List<MissionObjective> objectives;

    public MissionConcept(String name, String acronym, Agency agency, String start, String end, Object duration, Region target, List<String> objects, List<MissionObjective> objectives) {
        this.name = name;
        this.acronym = acronym;
        this.agency = agency;
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.target = target;
        this.objects = objects;
        this.objectives = objectives;
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

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Object getDuration() throws IllegalArgumentException{
        if (duration instanceof Double){
            return duration;
        }else if (duration instanceof String){
            return duration;
        }else {
            throw new IllegalArgumentException("Duration has to be either a Double or a String in TradespaceSearch.json");
        }
    }

    public Class getDurationType() throws IllegalArgumentException{
        if (duration instanceof String){
            return String.class;
        }else if (duration instanceof Double){
            return Double.class;
        }else {
            throw new IllegalArgumentException("Duration has to be either a Double or a String in TradespaceSearch.json");
        }
    }

    public Region getTarget() {
        return target;
    }

    public List<String> getObjects() {
        return objects;
    }

    public List<MissionObjective> getObjectives() {
        return objectives;
    }
}
