package tatc.tradespaceiterator;

import org.orekit.errors.OrekitException;
import org.orekit.time.AbsoluteDate;
import seakers.orekit.constellations.EnumerateConstellations;
import seakers.orekit.constellations.TrainParameters;
import seakers.orekit.constellations.WalkerParameters;
import tatc.architecture.TATCTrain;
import tatc.architecture.TATCWalker;

import java.io.File;
import java.util.ArrayList;

public class TradespaceSearchStrategyFF implements TradespaceSearchStrategy {
    ProblemProperties properties;

    private int counter = 0;
    
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
            // 1. From constellation, create a TATCWalker object
            TATCWalker architecture = new TATCWalker(constellation.getA(),constellation.getI(),constellation.getT(),constellation.getP(),constellation.getF(),this.properties);
            // 2. create the Architecture JSON file
            File architectureJsonFile = architecture.toJSON(this.getCounter());
            // 3. Evaluate architecture
            TradespaceSearchExecutive.evaluateArchitecture(architectureJsonFile);
            // increment the counter at each architecture evaluation
            this.incrementCounter();
        }

    }


    public void startTrain(){
        //The LTANs should be defined somewhere in the tsr and ArrayList<Double> LTANs created in ProblemProperties

        //convert arraylists to array in order to pass into fullFactWalker
        Double[] smaArray = ((ProblemPropertiesTrain)properties).getSmas().toArray(new Double[((ProblemPropertiesTrain)properties).getSmas().size()]);

        ArrayList<TrainParameters> constellationParams=EnumerateConstellations.fullFactTrain(smaArray,((ProblemPropertiesTrain)properties).getLTANs());

        for (TrainParameters constellation : constellationParams) {

            try {
                //TODO: figure out how to create the orekit ABsoluteDate from starDate epoch
                // 1. From constellation, create a TATCTrain object
                TATCTrain architecture = new TATCTrain(constellation.getA(),constellation.getLTANs(),new AbsoluteDate(),this.properties);
                // 2. create the ArchitectureMethods JSON
                File architectureJsonFile = architecture.toJSON(this.getCounter());
                // 3. Evaluate architecture
                TradespaceSearchExecutive.evaluateArchitecture(architectureJsonFile);
                // increment the counter at each architecture evaluation
                this.incrementCounter();
            } catch (OrekitException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCounter() {
        return counter;
    }

    private void incrementCounter(){
        this.counter++;
    }
}
