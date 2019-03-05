package tatc.architecture.variable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Decision<T> {
    private final String type;
    private final List<T> allowedValues;

    public Decision(String type, List<T> allowedValues) {
        this.type = type;
        this.allowedValues = allowedValues;
    }

//    /*
//        Iterates over values of decisions
//         */
//    public T nextValue(){
//
//    }

//    /*
//    Performs crossover (eg uniform) for this decision so that crossover at chromosome level can simply call crossover at decision level
//     */
//    public <S, T> T crossoverDecision(Decision<S> other){
//
//    }
//
//    /*
//    Performs mutation
//     */
//    public T mutateDecision(){
//
//    }

    /*
    Returns random allowed value
     */
    public T getRandomValue(){
        int random=ThreadLocalRandom.current().nextInt(0, this.allowedValues.size());
        return this.allowedValues.get(random);
    }

    public String getType() {
        return type;
    }

    public List<T> getAllowedValues() {
        return allowedValues;
    }
}