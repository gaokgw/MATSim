package org.matsim.area.kgw;

public class RunSimulation {

    public static void main (String[] args){

        // details could be changed case by case in java_SimulationInputDetails
        // which is above in the same folder.
        // this java just calls that one for run.

        SimulationInputDetails rs = new SimulationInputDetails();
        rs.runMatsim();

    }
}
