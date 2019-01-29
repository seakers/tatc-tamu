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

    public void start() throws IllegalArgumentException{
        if (this.properties instanceof ProblemPropertiesWalker){
            startWalker();
        } else if (this.properties instanceof ProblemPropertiesTrain){
            startTrain();
        } else {
            throw new IllegalArgumentException("No Problem Type found.");
        }
    }

    //TODO: catch IllegalArgumentException
    public void startWalker(){
        //TODO: make this readable
        //convert arraylists to array in order to pass into fullFactWalker
        Double[] smaArray = ((ProblemPropertiesWalker)properties).getSmas().toArray(new Double[((ProblemPropertiesWalker)properties).getSmas().size()]);
        Double[] incArray = ((ProblemPropertiesWalker)properties).getInclinations().toArray(new Double[((ProblemPropertiesWalker)properties).getInclinations().size()]);
        Integer[] numSatsArray = ((ProblemPropertiesWalker)properties).getNumberOfSats().toArray(new Integer[((ProblemPropertiesWalker)properties).getNumberOfSats().size()]);

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
        Double[] smaArray = ((ProblemPropertiesTrain)properties).getSmas().toArray(new Double[((ProblemPropertiesTrain)properties).getSmas().size()]);

        ArrayList<TrainParameters> constellationParams=EnumerateConstellations.fullFactTrain(smaArray,((ProblemPropertiesTrain)properties).getLTANs());

        for (TrainParameters constellation : constellationParams) {
            //TODO:
            // 1. From constellation, create a TATCTrain object
            // 2. create the Architecture JSON file using the method toJSON() from TATCTrain
            // 3. Call TradespaceSearchExecutive.evaluate(JSONfile)
        }
    }
}
