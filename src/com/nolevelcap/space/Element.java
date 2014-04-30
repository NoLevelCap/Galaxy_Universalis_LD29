package com.nolevelcap.space;

import java.util.Random;

public enum Element {
	ICE("IP"), GAS("GP"), WATER("WP"), FIRE("FP"), TERRA("TP");
	
	public String idTag;
	
	Element(String idTag){
		this.idTag = idTag;
	}
	
	public int genHeat(Element e) {
		Random r = new Random();
		switch(e) {
		case ICE:
			return -r.nextInt(5);
		case GAS:
			return -r.nextInt(200);
		case WATER:
			return r.nextInt(20)-10;
		case FIRE:
			return r.nextInt(75);
		case TERRA:
			return r.nextInt(30);
		default:
			return 0;
		}
		
		
	}
	
	public int genSurvivability(Element e){
		Random r = new Random();
		switch(e) {
		case ICE:
			return r.nextInt(20)+20+genHeat(e);
		case GAS:
			int id = r.nextInt(20)+0+genHeat(e)/10;
			if(id<0) {
				id = 0;
			}
			return id;
		case WATER:
			return r.nextInt(20)+30-genHeat(e);
		case FIRE:
			return r.nextInt(20)+10+genHeat(e);
		case TERRA:
			return r.nextInt(20)+40+genHeat(e);
		default:
			return 0;
		}
		
	}
}
