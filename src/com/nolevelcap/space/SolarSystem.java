package com.nolevelcap.space;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.nolevelcap.assets.NameGen;
import com.nolevelcap.assets.TextureHandler;
import com.nolevelcap.ld29.PlayerProfile;

public class SolarSystem {
	public String name;
	
	public ArrayList<Planet> planets;
	
	public PlayerProfile profile;
	
	public TextureHandler textures;
	
	public NameGen gen;
	
	public SolarSystem(PlayerProfile profile, TextureHandler handler, String name, boolean homeWorld) {
		this.name = name;
		this.planets = new ArrayList<Planet>();
		this.profile = profile;
		this.textures = handler;
		this.gen = new NameGen();
		
		genPlanet(homeWorld);
	}
	
	public void genPlanet(boolean homeWorld) {
		Random r = new Random();
		int PlanetNumber = r.nextInt(5) + 1;
		int lastWidth = 0;
		for(int no = 0; no<=PlanetNumber; no++) {
			int planetSize = (r.nextInt(8) + 11)*2;
			int planetPicX = r.nextInt(6);
			Planet newPlanet;
			if(homeWorld) {
				newPlanet = new HomeWorld(profile, textures, Element.TERRA, planetPicX, 25+lastWidth+no*25, (350-planetSize*3), planetSize, planetSize, 6, gen.genName());
				homeWorld = false;
				
				profile.Home = newPlanet;
			} else {
				newPlanet = new Planet(profile, textures, getRandomElement(), planetPicX, 25+lastWidth+no*25, (350-planetSize*3), planetSize, planetSize, 6, gen.genName());
			}
			planets.add(newPlanet);
			lastWidth += planetSize*6;
		}
		
	}
	
	public Element getRandomElement() {
		int length = Element.values().length;
		Element[] elements  = Element.values();
		Random r = new Random();
		int id  = r.nextInt(length);
		Gdx.app.log("Element", elements[id].name());
		return elements[id];
		
	}
}
