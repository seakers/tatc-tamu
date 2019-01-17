package tatc.tradespaceiterator;

import seakers.orekit.constellations.EnumerateConstellations;
import seakers.orekit.constellations.TrainParameters;
import seakers.orekit.constellations.WalkerParameters;

import java.util.ArrayList;

public class TradespaceSearchStrategyFF implements TradespaceSearchStrategy {
    ProblemProperties properties;
    
    public TradespaceSearchStrategyFF(ProblemProperties properties){
        this.properties=properties;
    }

    public void start(){
        String problem = properties.tsr.getMissionConcept().getProblemType();
        switch (problem){
            case "Walker":
                startWalker();
                break;
            case "Train":
                startTrain();
                break;
            default:
                throw new IllegalArgumentException("No Problem Type found.");
        }
    }

    //TODO: catch IllegalArgumentException
    public void startWalker(){
        //TODO: make this readable
        //convert arraylists to array in order to pass into fullFactWalker
        Double[] smaArray = ((ProblemPropertiesWalker)properties).smas.toArray(new Double[((ProblemPropertiesWalker)properties).smas.size()]);
        Double[] incArray = ((ProblemPropertiesWalker)properties).inclination.toArray(new Double[((ProblemPropertiesWalker)properties).inclination.size()]);
        Integer[] numSatsArray = ((ProblemPropertiesWalker)properties).numberOfSats.toArray(new Integer[((ProblemPropertiesWalker)properties).numberOfSats.size()]);

        //TODO: full factorial enumeration: use SystemArchitectureProblems
        ArrayList<WalkerParameters> constellationParams = EnumerateConstellations.fullFactWalker(smaArray, incArray, numSatsArray);

        //TODO: change params to constellation
        for (WalkerParameters constellation : constellationParams) {
            //TODO:
            // 1. From constellation, create a TATCWalker object
            // 2. create the Architecture JSON file using the method toJSON() from TATCWalker
            // 3. Call TradespaceSearchExecutive.evaluate(JSONfile)
        }

    }


    public void startTrain(){
        //The LTANs should be defined somewhere in the tsr and ArrayList<Double> LTANs created in ProblemProperties

        //convert arraylists to array in order to pass into fullFactWalker
        Double[] smaArray = ((ProblemPropertiesTrain)properties).smas.toArray(new Double[((ProblemPropertiesTrain)properties).smas.size()]);

        ArrayList<TrainParameters> constellationParams=EnumerateConstellations.fullFactTrain(smaArray,((ProblemPropertiesTrain)properties).LTANs);

        for (TrainParameters constellation : constellationParams) {
            //TODO:
            // 1. From constellation, create a TATCTrain object
            // 2. create the Architecture JSON file using the method toJSON() from TATCTrain
            // 3. Call TradespaceSearchExecutive.evaluate(JSONfile)
        }
    }
}
