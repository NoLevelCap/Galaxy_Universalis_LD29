package com.nolevelcap.ld29;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.nolevelcap.space.Event;
import com.nolevelcap.space.ORE;
import com.nolevelcap.space.Planet;

public class PlayerProfile {
	public HashMap<ORE, Integer> miningResources = new HashMap<ORE, Integer>();
	
	public int Money, Science, Ore;
	
	public boolean MsM, MdC, QwM, ST, GE;
	
	public int maxAvailableSolarSystems;
	
	public Planet Home;
	
	public Array<Event> eventPool;
	
	public PlayerProfile() {
		Money = 0;
		Science = 0;
		for(ORE ore: ORE.values()) {
			miningResources.put(ore, 0);
		}
		
		eventPool = new Array<Event>();
		
		maxAvailableSolarSystems = 1;
	}
	
	public void updateResource(ORE ore, int amount) {
		if(miningResources.containsKey(ore)) {
			Gdx.app.log("updating", " resources");
			int tempAmount = miningResources.get(ore) + amount;
			miningResources.remove(ore);
			miningResources.put(ore, tempAmount);
			Gdx.app.log("MR", ore.toString()+"("+ore.rarity+"): "+tempAmount);
		} else { 
		}
		calculateOre();
		
	}
	
	public void PrintTotal() {
		for(ORE ore: ORE.values()) {
		Gdx.app.log("TOTAL",  ore.toString()+"("+ore.rarity+"): "+miningResources.get(ore));
		}
	}
	
	public void calculateOre() {
		int total = 0;
		for(ORE ore: ORE.values()) {
			total+=miningResources.get(ore);
		}
		
		this.Ore = total;
	}
	
	public int getOre(ORE ore) {
		return miningResources.get(ore);
	}
	
	public void changeMoney(int i) {
		this.Money += i;
	}
	
	public void changeScience(int i) {
		this.Science += i;
	}
}
