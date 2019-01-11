/* *********************************************************************** *
 * project: org.matsim.*
 * MyControler1.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package org.matsim.area.kgw;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Scenario;
import org.matsim.contrib.otfvis.OTFVis;
import org.matsim.core.config.Config;
import org.matsim.run.Controler;
//import org.matsim.contrib.otfvis.OTFVis;

/**
 * runs iterations and writes events files.  See the config file for configuration details.
 * 
 * @author nagel
 *
 */
public class RunConfigFile {
	private static Logger log = Logger.getLogger(RunConfigFile.class);

	public static void main(final String[] args) {
		String configFile = "test_then_delete/0.config.xml" ;
		//String configFile = "D:/matsim prog/MATSim_Base/test_then_delete/0.config.xml" ;
		Controler controler = new Controler( configFile ) ;
		controler.setOverwriteFiles(true) ;
		controler.run() ;
		
		Scenario sc = controler.getScenario() ;
		Config cf = sc.getConfig() ;
		String dir = cf.controler().getOutputDirectory();
		log.warn("Output is in " + dir + ".") ; 
		
		//String[] outputMvi = {"test_then_delete/0.otfvis.mvi"};
		//OTFVis.main(outputMvi);
		//OTFVis.main(new String[] {"-convert", "./output/111/output_events.xml.gz", "./output/111/output_network.xml.gz", "./output/222/ttt.mvi", "10"});
	}

}
