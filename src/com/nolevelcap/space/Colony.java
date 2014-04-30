package com.nolevelcap.space;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nolevelcap.assets.NameGen;

public class Colony {
	ORE[] ores;
	Planet parent;
	
	public boolean unused = false;
	
	public long population;
	
	public int funding;
	
	public float completionPc;
	
	public String name;
	
	public Colony(Planet parent) {
		this.ores = ORE.Argentite.getOres(parent.ores, 3);
		this.parent = parent;
		this.population = 0;
		this.name = new NameGen().genName();
	}
	
	public void simulate() {
		Gdx.app.log("Funding", funding+"");
		if(!unused) {
		Random r = new Random();
		population+=(10000*funding*(1-(parent.getSurvivabiliy()/100.0f)));
		Gdx.app.log("POPULUS", population+"pp");
		if(r.nextFloat()<(1-(parent.getSurvivabiliy()/100.0f))) {
			if(r.nextInt(100)<=40-(funding/10.0f)) {
				population -= (1000-funding);
			} else {
				population *= 0.5;
			}
		}
		
		if(population>=100000) {
			//Gdx.app.log("CONGRATULATIONS ON "+parent.getName(), "You succeded to colonise");
			
		parent.player.eventPool.add(new Event(parent.player, parent.textures.getTexture("colony_event"), new String[] {"Long may it continue!"}, "On "+parent.getName()+" a colony named "+name+" has gathered enough population to make a new colony.", "CONGRATULATIONS ON "+parent.getName()+"!") {
				
				@Override
				public void action(int selectionID) {
					// TODO Auto-generated method stub
					
				}
			});
			
			parent.successColony(this);
			unused = true;
		} else if(population<=100) {
			//Gdx.app.log("DISASTER ON "+parent.getName(), "You failed to colonise");
			
			parent.failureColony(this);
			unused = true;
			
			parent.player.eventPool.add(new Event(parent.player, parent.textures.getTexture("event_filler"), new String[] {"We must leave it alone.", "We must revive the colony. (Recreate the colony with 100 funding)."}, "On "+parent.getName()+" a colony named "+name+" has been ravaged by a giant beast.", "DISASTER ON "+parent.getName()+"!") {
				
				@Override
				public void action(int selectionID) {
					switch(selectionID) {
					case 1:
						if(parent.player.Money-5100>=0) {
						parent.startColony();
						parent.colony.funding = 100;
						parent.player.changeMoney(-5100);
						}
						break;
					}
				}
			});
			
		}
		completionPc = population/1000000;
		}
	}
	
	public void increaseFunding(int i) {
		funding += i;
		Gdx.app.log("Funding ", i+" increased");
	}
}
