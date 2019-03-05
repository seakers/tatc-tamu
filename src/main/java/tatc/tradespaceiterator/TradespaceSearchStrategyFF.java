package tatc.tradespaceiterator;

import org.orekit.errors.OrekitException;
import org.orekit.time.AbsoluteDate;
import seakers.orekit.constellations.TrainParameters;
import seakers.orekit.constellations.WalkerParameters;
import tatc.architecture.TATCTrain;
import tatc.architecture.TATCWalker;
import tatc.architecture.specifications.GroundNetwork;
import tatc.architecture.specifications.Satellite;
import tatc.architecture.variable.Decision;
import tatc.util.Enumeration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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
        HashMap<String,Decision<?>> decisions = ((ProblemPropertiesWalker)properties).getDecisions();

        //TODO: full factorial enumeration: use SystemArchitectureProblems
        ArrayList<WalkerParameters> constellationParamsTotal = new ArrayList<>();
        for (int constellationCount=1 ; constellationCount<=properties.getTradespaceSearch().getDesignSpace().getConstellations().size(); constellationCount++){
            ArrayList<WalkerParameters> constellationParams = Enumeration.fullFactWalker((Decision<Double>) decisions.get(String.format("altitude%d",constellationCount)),
                    (Decision<Object>)decisions.get(String.format("inclination%d",constellationCount)),
                    (Decision<Integer>)decisions.get(String.format("numberSatellites%d",constellationCount)),
                    (Decision<Integer>)decisions.get(String.format("numberPlanes%d",constellationCount)),
                    (Decision<Integer>)decisions.get(String.format("relativeSpacing%d",constellationCount)));
            constellationParamsTotal.addAll(constellationParams);
        }


        //TODO: change params to constellation
        Decision<Satellite> decisionSatellite = (Decision<Satellite>)decisions.get("satellite");
        for (WalkerParameters constellation : constellationParamsTotal) {
            for (int groundNetworkCount=1; groundNetworkCount<=properties.getTradespaceSearch().getDesignSpace().getGroundNetworks().size(); groundNetworkCount++){
                Decision<GroundNetwork> decisionGroundNetwork = (Decision<GroundNetwork>)decisions.get(String.format("groundNetwork%d",groundNetworkCount));
                for (GroundNetwork gn : decisionGroundNetwork.getAllowedValues()){
                    for (Satellite sat : decisionSatellite.getAllowedValues()){
                        // 1. From constellation, create a TATCWalker object
                        TATCWalker architecture = new TATCWalker(constellation.getA(),constellation.getI(),constellation.getT(),
                                constellation.getP(),constellation.getF(),sat, gn,this.properties);
                        // 2. create the Architecture JSON file
                        File architectureJsonFile = architecture.toJSON(this.getCounter());
                        // 3. Evaluate architecture
                        TradespaceSearchExecutive.evaluateArchitecture(architectureJsonFile);
                        // increment the counter at each architecture evaluation
                        this.incrementCounter();
                    }
                }
            }

        }

    }


    public void startTrain(){
        //The LTANs should be defined somewhere in the tsr and ArrayList<Double> LTANs created in ProblemProperties

        //convert arraylists to array in order to pass into fullFactWalker
        Double[] smaArray = ((ProblemPropertiesTrain)properties).getSmas().toArray(new Double[((ProblemPropertiesTrain)properties).getSmas().size()]);

        ArrayList<TrainParameters> constellationParams=Enumeration.fullFactTrain(smaArray,((ProblemPropertiesTrain)properties).getLTANs());

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
