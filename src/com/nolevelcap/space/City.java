package com.nolevelcap.space;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.nolevelcap.assets.NameGen;
import com.nolevelcap.ld29.PlayerProfile;

public class City {
	Planet masterPlanet;
	
	public long population, workingPop;
	public float miningChance;
	public float tradeEfficincy;
	public float growthRate;
	
	public ORE[] ores;
	
	public PlayerProfile profile;
	
	public String name;
	
	
	public City(PlayerProfile owner, Planet parent, Colony colony){
		this.masterPlanet = parent;
			Random r = new Random();
			population = colony.population;
			workingPop = (long) (population * r.nextDouble());
			tradeEfficincy = (workingPop*100.0f)/population;
			growthRate =  r.nextInt(50)/100.0f;
			this.profile = owner;
			this.ores = colony.ores;
			this.name = colony.name;
	}
	
	public City(PlayerProfile owner,Planet p, long population, float workerpop) {
		this.masterPlanet = p;
		
		Random r = new Random();
		this.population = population;
		workingPop = (long) (population * r.nextDouble());
		tradeEfficincy = (workingPop*100.0f)/population;
		float next = r.nextFloat();

		this.name = new NameGen().genName();
		
		growthRate =  r.nextInt(50)/100.0f;
		this.profile = owner;
		ores = ORE.Argentite.getOres(p.ores, 3);
	}
	
	public String printData() {
		return "Population: "+population+", Workmen: "+workingPop+"%, Trade Efficiency: "+tradeEfficincy+", Mining Chance: "+miningChance+", Growth: "+growthRate;
	}
	
	public void simulate() {
		Random r = new Random();
		
			population += (long) (population*growthRate)/365.0f;
			workingPop = (long) (population * r.nextDouble());
			tradeEfficincy = (workingPop*100.0f)/population;
			growthRate =  r.nextInt(50)/100.0f;
			
			if(profile.MsM) {
			calculateOres();
			}
	}
	
	public void calculateOres() {
		Gdx.app.log("AMOUNT BEFORE", profile.Ore+" "+workingPop* 0.25f);
		
		long availableWorkers = (long) (workingPop * 0.25f);
		float yearlyOre = availableWorkers * ores[0].rarity;
		profile.updateResource(ores[0], (int) (yearlyOre/365.0f));
		availableWorkers -= yearlyOre;
		
		Gdx.app.log("AMOUNT BEFORE", yearlyOre/365.0f+"");
		
		yearlyOre = availableWorkers * ores[1].rarity;
		profile.updateResource(ores[1], (int) (yearlyOre/365.0f));
		availableWorkers -= yearlyOre;
		
		yearlyOre = availableWorkers * ores[2].rarity;
		profile.updateResource(ores[2], (int) (yearlyOre/365.0f));
		availableWorkers -= yearlyOre;
		
		Gdx.app.log("AMOUNT AFTER", profile.Ore+" "+availableWorkers);
	}
}
