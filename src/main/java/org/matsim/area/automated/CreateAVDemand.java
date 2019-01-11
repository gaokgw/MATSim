package org.matsim.area.automated;

import com.pb.common.util.ResourceUtil;
import org.matsim.area.configMatsim.planCreation.CentroidsToLocations;
import org.matsim.area.configMatsim.planCreation.Location;
import org.matsim.area.configMatsim.planCreation.ReadSyntheticPopulation;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by carlloga on 17.03.2017.
 */
public class CreateAVDemand {

    private ResourceBundle rb;



    public void createAVDemand( float tripScalingFactor, float avPenetrationRate, String plansFileName){

        File propFile = new File("munich.properties");
        rb = ResourceUtil.getPropertyBundle(propFile);

        CentroidsToLocations centroidsToLocations = new CentroidsToLocations(rb);
        ArrayList<Location> locationList = centroidsToLocations.readCentroidList();

        ReadSyntheticPopulation readSp = new ReadSyntheticPopulation(rb, locationList);
        readSp.demandFromSyntheticPopulation(avPenetrationRate, tripScalingFactor, plansFileName);
        readSp.printSyntheticPlansList(plansFileName + ".csv");


    }

}
