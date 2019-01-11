package org.matsim.area.kgw;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.io.MatsimNetworkReader;
import org.matsim.core.population.io.PopulationWriter;
import org.matsim.core.scenario.ScenarioUtils;

import java.util.Random;

public class CreatePopulationUtils {

    public static Coord coordSW;		// south-west coordinate（min x,y）
    public static Coord coordNE;		// north-east coordinate（max x,y）

    private static void setMaxMinCoord(Network network){
        // setup coordinate: coordSW, coordNE
        for (Id<Node> nodeId : network.getNodes().keySet()){
            Coord nodeCoord = network.getNodes().get(nodeId).getCoord();
            if(coordSW == null||coordNE == null){
                coordSW = nodeCoord;
                coordNE = nodeCoord;
                continue;
            }
            coordSW = new Coord(coordSW.getX()<nodeCoord.getX()?coordSW.getX():nodeCoord.getX(),
                    coordSW.getY()<nodeCoord.getY()?coordSW.getY():nodeCoord.getY());
            coordNE = new Coord(coordNE.getX()>nodeCoord.getX()?coordNE.getX():nodeCoord.getX(),
                    coordNE.getY()>nodeCoord.getY()?coordNE.getY():nodeCoord.getY());
        }
    }

    private static Coord getRandomCoord(){
        // get random coordinate
        double x, y;
        x = coordSW.getX()+(coordNE.getX() - coordSW.getX())*Math.random();
        y = coordSW.getY()+(coordNE.getY() - coordSW.getY())*Math.random();
        return new Coord(x, y);

    }

    public static void main(String[] args){

        Config config = ConfigUtils.createConfig();
        Scenario scenario = ScenarioUtils.createScenario(config);

        // read network.xml into memory
        new MatsimNetworkReader(scenario.getNetwork()).readFile("D:/matsim prog/MATSim_Base/test_then_delete/network.xml");

        setMaxMinCoord(scenario.getNetwork());
        fillScenario(scenario);

        // plans.xml directory
        String filePath = "D:/matsim prog/MATSim_Base/test_then_delete/plans.xml";
        new PopulationWriter(scenario.getPopulation()).write(filePath);
        System.out.println("Done writing file to"+filePath);
    }


    private static Population fillScenario(Scenario scenario) {
        Population population = scenario.getPopulation();

        // create agents : in this case 500
        for (int i = 0; i < 1500; i++) {
            Coord coord = getRandomCoord();
            Coord coordWork = getRandomCoord();
            createOnePerson(scenario, population, i, coord, coordWork);
        }

        return population;
    }

    private static void createOnePerson(Scenario scenario, Population population, int i, Coord coord, Coord coordWork) {

        Person person = population.getFactory().createPerson(Id.createPersonId("p_"+i));

        Plan plan = population.getFactory().createPlan();

        Activity home = population.getFactory().createActivityFromCoord("home", coord);
        home.setEndTime(randomTime(8*60*60, 60*60));
        plan.addActivity(home);

        Leg hinweg = population.getFactory().createLeg("car");
        plan.addLeg(hinweg);

        Activity work = population.getFactory().createActivityFromCoord("work", coordWork);
        work.setEndTime(randomTime(19*60*60, 3*60*60));
        plan.addActivity(work);

        Leg rueckweg = population.getFactory().createLeg("car");
        plan.addLeg(rueckweg);

        Activity home2 = population.getFactory().createActivityFromCoord("home", coord);
        plan.addActivity(home2);

        person.addPlan(plan);
        population.addPerson(person);
    }

    private static int randomTime(int normalTime, int variance){
        // generate travel times which follow Gaussian distrbution (normal distribution)
        Random rand = new Random();
        return (int)(variance*rand.nextGaussian()+normalTime);
    }
}
