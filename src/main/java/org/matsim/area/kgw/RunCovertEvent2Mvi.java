package org.matsim.area.kgw;

import org.matsim.contrib.otfvis.OTFVis;

/**
 * this will take about 20 20 minutes for kagawa, 20190110.
 */

public class RunCovertEvent2Mvi {
    public static void main(String[] args) {

        OTFVisConvertTest();

    }

    public static void OTFVisConvertTest() {

        //takes about 20 minutes for kagawa case

        String eventFile = "output/111/test1_2010.output_events.xml.gz" ;
        String networkFile = "output/111/test1_2010.output_network.xml.gz" ;
        String outputMviFile = "output/111.mvi" ;

        OTFVis.main(new String[] {"-convert", eventFile, networkFile,outputMviFile, "10"});
        //OTFVis.main(new String[] {"-convert", "./output/111/test1_2010.output_events.xml.gz", "./output/111/test1_2010.output_network.xml.gz", "./output/MVI/event.visualization_test1_170919.via", "10"});

    }


    /**https://github.com/matsim-org/playgrounds/blob/master/tthunig/src/main/java/signals/laemmer/run/LaemmerBasicExample.java

    public static void visExample(String configPath) {
        Config config = ConfigUtils.createConfig();
        ConfigUtils.loadConfig(config, configPath);

        config.qsim().setSnapshotStyle(QSimConfigGroup.SnapshotStyle.withHoles);
        config.qsim().setNodeOffset(5.);
        OTFVisConfigGroup otfvisConfig =
                ConfigUtils.addOrGetModule(config, OTFVisConfigGroup.GROUP_NAME, OTFVisConfigGroup.class);
        otfvisConfig.setScaleQuadTreeRect(true);
//            otfvisConfig.setColoringScheme(OTFVisConfigGroup.ColoringScheme.byId);
//            otfvisConfig.setAgentSize(240);
        Scenario scenario = ScenarioUtils.loadScenario(config);
        OTFVis.playScenario(scenario);
    }


*/
}
