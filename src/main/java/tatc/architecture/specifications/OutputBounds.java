package tatc.architecture.specifications;

/**
 * Configuration options to filter the output of TAT-C based on ranges of parameters such as time bounds, lat/long regions, and mission performance.
 * Used by: AnalysisSettings
 */
public class OutputBounds {

    private final QuantitativeValue outputTimeAfterStart;
    private final QuantitativeValue outputTimeToCoverage;
    private final QuantitativeValue outputAccessTime;
    private final QuantitativeValue outputDownlinkLatency;
    private final QuantitativeValue outputRevisitTime;
    private final QuantitativeValue outputRepeatTime;
    private final QuantitativeValue outputCrossOverlap;
    private final QuantitativeValue outputAlongOverlap;
    private final QuantitativeValue outputNumPasses;
    private final QuantitativeValue outputLunarPhase;
    private final QuantitativeValue outputObsZenith;
    private final QuantitativeValue outputObsAzimuth;
    private final QuantitativeValue outputSunZenith;
    private final QuantitativeValue outputSunAzimuth;
    private final QuantitativeValue outputSpatialResolution;
    private final QuantitativeValue outputCrossSwath;
    private final QuantitativeValue outputAlongSwath;
    private final QuantitativeValue outputObsLatitude;
    private final QuantitativeValue outputObsLongitude;
    private final QuantitativeValue outputObsAltitude;
    private final QuantitativeValue outputObjZenith;
    private final QuantitativeValue outputObjAzimuth;
    private final QuantitativeValue outputObjRange;

    public QuantitativeValue getOutputTimeAfterStart() {
        return outputTimeAfterStart;
    }

    public QuantitativeValue getOutputTimeToCoverage() {
        return outputTimeToCoverage;
    }

    public QuantitativeValue getOutputAccessTime() {
        return outputAccessTime;
    }

    public QuantitativeValue getOutputDownlinkLatency() {
        return outputDownlinkLatency;
    }

    public QuantitativeValue getOutputRevisitTime() {
        return outputRevisitTime;
    }

    public QuantitativeValue getOutputRepeatTime() {
        return outputRepeatTime;
    }

    public QuantitativeValue getOutputCrossOverlap() {
        return outputCrossOverlap;
    }

    public QuantitativeValue getOutputAlongOverlap() {
        return outputAlongOverlap;
    }

    public QuantitativeValue getOutputNumPasses() {
        return outputNumPasses;
    }

    public QuantitativeValue getOutputLunarPhase() {
        return outputLunarPhase;
    }

    public QuantitativeValue getOutputObsZenith() {
        return outputObsZenith;
    }

    public QuantitativeValue getOutputObsAzimuth() {
        return outputObsAzimuth;
    }

    public QuantitativeValue getOutputSunZenith() {
        return outputSunZenith;
    }

    public QuantitativeValue getOutputSunAzimuth() {
        return outputSunAzimuth;
    }

    public QuantitativeValue getOutputSpatialResolution() {
        return outputSpatialResolution;
    }

    public QuantitativeValue getOutputCrossSwath() {
        return outputCrossSwath;
    }

    public QuantitativeValue getOutputAlongSwath() {
        return outputAlongSwath;
    }

    public QuantitativeValue getOutputObsLatitude() {
        return outputObsLatitude;
    }

    public QuantitativeValue getOutputObsLongitude() {
        return outputObsLongitude;
    }

    public QuantitativeValue getOutputObsAltitude() {
        return outputObsAltitude;
    }

    public QuantitativeValue getOutputObjZenith() {
        return outputObjZenith;
    }

    public QuantitativeValue getOutputObjAzimuth() {
        return outputObjAzimuth;
    }

    public QuantitativeValue getOutputObjRange() {
        return outputObjRange;
    }
}
