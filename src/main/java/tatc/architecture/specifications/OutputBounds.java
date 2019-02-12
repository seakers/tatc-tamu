package tatc.architecture.specifications;

import com.google.gson.annotations.SerializedName;

/**
 * Configuration options to filter the output of TAT-C based on ranges of parameters such as time bounds, lat/long regions, and mission performance.
 * Used by: AnalysisSettings
 */
public class OutputBounds {

    @SerializedName("@type")
    private final String _type="OutputBounds";
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

    public OutputBounds(QuantitativeValue outputTimeAfterStart, QuantitativeValue outputTimeToCoverage, QuantitativeValue outputAccessTime, QuantitativeValue outputDownlinkLatency, QuantitativeValue outputRevisitTime, QuantitativeValue outputRepeatTime, QuantitativeValue outputCrossOverlap, QuantitativeValue outputAlongOverlap, QuantitativeValue outputNumPasses, QuantitativeValue outputLunarPhase, QuantitativeValue outputObsZenith, QuantitativeValue outputObsAzimuth, QuantitativeValue outputSunZenith, QuantitativeValue outputSunAzimuth, QuantitativeValue outputSpatialResolution, QuantitativeValue outputCrossSwath, QuantitativeValue outputAlongSwath, QuantitativeValue outputObsLatitude, QuantitativeValue outputObsLongitude, QuantitativeValue outputObsAltitude, QuantitativeValue outputObjZenith, QuantitativeValue outputObjAzimuth, QuantitativeValue outputObjRange) {
        this.outputTimeAfterStart = outputTimeAfterStart;
        this.outputTimeToCoverage = outputTimeToCoverage;
        this.outputAccessTime = outputAccessTime;
        this.outputDownlinkLatency = outputDownlinkLatency;
        this.outputRevisitTime = outputRevisitTime;
        this.outputRepeatTime = outputRepeatTime;
        this.outputCrossOverlap = outputCrossOverlap;
        this.outputAlongOverlap = outputAlongOverlap;
        this.outputNumPasses = outputNumPasses;
        this.outputLunarPhase = outputLunarPhase;
        this.outputObsZenith = outputObsZenith;
        this.outputObsAzimuth = outputObsAzimuth;
        this.outputSunZenith = outputSunZenith;
        this.outputSunAzimuth = outputSunAzimuth;
        this.outputSpatialResolution = outputSpatialResolution;
        this.outputCrossSwath = outputCrossSwath;
        this.outputAlongSwath = outputAlongSwath;
        this.outputObsLatitude = outputObsLatitude;
        this.outputObsLongitude = outputObsLongitude;
        this.outputObsAltitude = outputObsAltitude;
        this.outputObjZenith = outputObjZenith;
        this.outputObjAzimuth = outputObjAzimuth;
        this.outputObjRange = outputObjRange;
    }

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
