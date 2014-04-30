package com.nolevelcap.space;

import java.util.Random;

import com.badlogic.gdx.Gdx;

public enum ORE {
	Iron(0.8f), Lead(0.9f), 
	Silver(0.3f), Gold(0.2f), 
	Platinum(0.1f), Zinc(0.6f),
	Argentite(0.4f), Barite(0.4f),
	Bauxite(0.7f), Beryl(0.3f),
	Cassiterite(0.5f), Chalcoite(0.8f),
	Chalcopyrite(0.3f), Chromite(0.4f),
	Cinnabar(0.7f), Cobalite(0.9f),
	Mercury(0.1f), Coltan(0.7f),
	Dolomite(0.5f), Galena(0.3f),
	Quartz(0.7f), Hermantite(0.3f),
	Ilmenite(0.5f), Magnetite(0.5f),
	Malachite(0.5f), Pentatandite(0.6f),
	Pyrolite(0.4f), Scweelite(0.2f),
	Sperrylite(0.8f), Sphalerite(0.2f),
	Uranite(0.6f), Wolframite(0.5f),
	Uranuim(0.1f), FairyDust(0.01f),
	Scanduim(0.5f), Yitruim(0.6f),
	Lantanum(0.7f), Pasedym(0.8f),
	Europuim(0.1f), Holmien(0.1f),
	Erbuim(0.3f), Lituim(0.5f),
	Nitrolite(0.2f), Goldite(0.9f);
	
	public float rarity;
	
	ORE(float rarity){
		this.rarity = rarity;
	}
	
	public ORE[] RandomOres(int amount) {
		ORE[] ores = new ORE[amount];
		Random r = new Random();
		ORE[] oreList = ORE.values();
		for(int i=0; i<amount;i++) {
			boolean accepted = false;
			while(!accepted) {
				int selection = r.nextInt(ORE.values().length);
				float rarityBonus = r.nextFloat();
				if(rarityBonus <= oreList[selection].rarity) {
					ores[i] = oreList[selection];
					accepted = true;
				}
			}
		}
		String bigString = "";
		for(int a=0; a<amount; a++) {
			bigString+=ores[a].toString()+"("+ores[a].rarity+"), ";}
		Gdx.app.log("ID", bigString);
		return ores;
	}
	
	public ORE[] getOres(ORE[] oreList, int amount) {
		ORE[] ores = new ORE[amount];
		Random r = new Random();
		for(int i=0; i<amount;i++) {
			boolean accepted = false;
			while(!accepted) {
				int selection = r.nextInt(oreList.length);
				float rarityBonus = r.nextFloat();
				if(rarityBonus <= oreList[selection].rarity) {
					ores[i] = oreList[selection];
					accepted = true;
				}
			}
		}
		String bigString = "";
		for(int a=0; a<amount; a++) {
			bigString+=ores[a].toString()+"("+ores[a].rarity+"), ";}
		Gdx.app.log("ID", bigString);
		return ores;
	}
}
