package org.matsim.area.kgw;;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.algorithms.NetworkCleaner;
import org.matsim.core.network.io.NetworkWriter;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.core.utils.io.OsmNetworkReader;

import java.io.UncheckedIOException;

public class OsmToNetworkUtil {
    public static void main(String[] args){
        Config config = ConfigUtils.createConfig();
        Scenario scenario = ScenarioUtils.loadScenario(config);
		CoordinateTransformation trans = TransformationFactory.getCoordinateTransformation(TransformationFactory.WGS84, "EPSG:2446");
        OsmNetworkReader osmReader = new OsmNetworkReader(scenario.getNetwork(), trans);
        String dir = "D:/matsim prog/MATSim_Base/test_then_delete/";			// your work directory where map.osm data are put into
        String osmFile = "Kagawa_BB05km.osm";				// import .osm data
        String networkFile = "Kagawa_BB05km_network.xml";		// export
        try{
            osmReader.parse(dir+osmFile);
            new NetworkCleaner().run(scenario.getNetwork());
            new NetworkWriter(scenario.getNetwork()).write(dir+networkFile);
            System.out.println("Done writing!");
            System.out.println("Please find network file in "+dir+networkFile);
        }
        catch(UncheckedIOException e){
            e.toString();
        }
    }
}
