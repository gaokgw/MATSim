package org.matsim.area.outputCreation.accessibilityCalculator;

import com.pb.common.util.ResourceUtil;
import org.matsim.area.configMatsim.planCreation.CentroidsToLocations;
import org.matsim.area.configMatsim.planCreation.Location;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by carlloga on 20.03.2017.
 */
public class AcessCalculator {

    private static ResourceBundle rb;

    public static void main (String[] args){

        File propFile = new File( "./accessibility/accessibility.properties");

        rb = ResourceUtil.getPropertyBundle(propFile);

        //read the zones where accessibility is going to be calculated

        CentroidsToLocations centroidsToLocations = new CentroidsToLocations(rb);

        ArrayList<Location> locationList = centroidsToLocations.readCentroidList();

        //calculate and print accessibility

        String skimFileName = rb.getString("skim.file");

        System.out.println(skimFileName);

        Accessibility accessibility = new Accessibility(skimFileName, "mat1", rb);
        accessibility.calculateAccessibility(locationList);
        //distance to marienplatz muenchen
        accessibility.calculateTravelTimesToZone(locationList, Integer.parseInt(rb.getString("dest.zone")));
        accessibility.printAccessibility(locationList);


    }

}
