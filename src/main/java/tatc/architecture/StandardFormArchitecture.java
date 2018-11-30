/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tatc.architecture;

import org.moeaframework.core.Solution;

public class StandardFormArchitecture extends Solution {

    private static final long serialVersionUID = -453938510114132596L;

    public StandardFormArchitecture(int numberOfVariables, int numberOfObjectives) {
        super(numberOfVariables, numberOfObjectives);
    }

    /**
     * Private constructor used for copying solution
     * @param solution 
     */
    private StandardFormArchitecture(Solution solution) {
        super(solution);
    }

    @Override
    public Solution copy() {
        return new StandardFormArchitecture(this);
    }
    
}
