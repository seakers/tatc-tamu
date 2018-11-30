/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tatc.tradespaceiterator;

import tatc.architecture.specifications.MissionConcept;

public class TradespaceSearchRequest {

    private final MissionConcept MissionConcepts;
    private final SatelliteOrbits SatelliteOrbits;

    /**
     * @param missionConcepts
     * @param satelliteOrbits
     */
    public TradespaceSearchRequest(MissionConcept missionConcepts, SatelliteOrbits satelliteOrbits) {
        this.MissionConcepts = missionConcepts;
        this.SatelliteOrbits = satelliteOrbits;

    }

    public MissionConcept getMissionConcept() {
        return MissionConcepts; //TODO: this.MissionConcepts
    }

    public SatelliteOrbits getSatelliteOrbits() {
        return SatelliteOrbits;
    }
}
