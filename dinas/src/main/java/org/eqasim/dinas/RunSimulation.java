package org.eqasim.dinas;

import ch.sbb.matsim.config.SwissRailRaptorConfigGroup;
import ch.sbb.matsim.routing.pt.raptor.SwissRailRaptorModule;
import org.eqasim.core.components.config.EqasimConfigGroup;
import org.eqasim.core.simulation.EqasimConfigurator;
import org.eqasim.core.simulation.analysis.EqasimAnalysisModule;
import org.eqasim.core.simulation.mode_choice.EqasimModeChoiceModule;
import org.eqasim.dinas.mode_choice.IDFModeAvailability;
import org.eqasim.dinas.mode_choice.IDFModeChoiceModule;
import org.eqasim.core.parkingCost.*;
import org.matsim.api.core.v01.Scenario;
import org.matsim.contribs.discrete_mode_choice.modules.DiscreteModeChoiceModule;
import org.matsim.contribs.discrete_mode_choice.modules.config.DiscreteModeChoiceConfigGroup;
import org.matsim.core.config.CommandLine;
import org.matsim.core.config.CommandLine.ConfigurationException;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

import java.util.HashSet;
import java.util.Set;

public class RunSimulation {
	static public void main(String[] args) //throws ConfigurationException
	{
		//CommandLine cmd = new CommandLine.Builder(args) //

		//		.allowPrefixes("mode-choice-parameter", "cost-parameter") //
		//		.build();
// replaced IDFConfigurator with EqasimConfigurator
		// Config config = ConfigUtils.loadConfig("dinas/dinasConfig.xml", EqasimConfigurator.getConfigGroups());
		Config config = ConfigUtils.loadConfig("dinas/bus6or7/bus6or7_config.xml",
		//		new DiscreteModeChoiceConfigGroup(),
				new SwissRailRaptorConfigGroup(),
				new ParkingCostConfigGroup()
		);
		//cmd.applyConfiguration(config);




		//Scenario scenario = ScenarioUtils.createScenario(config);
		// EqasimConfigurator.configureScenario(scenario);
		//IDFConfigurator.configureScenario(scenario);
		Scenario scenario = ScenarioUtils.loadScenario(config);

		Controler controller = new Controler(scenario);
		// EqasimConfigurator.configureController(controller);
		//IDFConfigurator.configureController(controller); //replaced with Eqasim version

		//controller.addOverridingModule(new EqasimAnalysisModule());
		//controller.addOverridingModule(new EqasimModeChoiceModule());
		controller.addOverridingModule(new ParkingCostModule());
		controller.addOverridingModule(new SwissRailRaptorModule());
		//controller.addOverridingModule(new DiscreteModeChoiceModule());
		//controller.addOverridingModule(new IDFModeChoiceModule(cmd));
		controller.run();
	}


}