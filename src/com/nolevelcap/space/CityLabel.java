package com.nolevelcap.space;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.nolevelcap.widgets.Label;

public class CityLabel extends Label{

	public CityLabel(Texture tex, BitmapFont font, City p, int x,
			int y, int height, int width, int scale) {
		super(tex, font, genStringArray(p), x, y, height, width, scale);
	}

	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}
	
	public void setCity(City p) {
		super.setText(genStringArray(p));
	}
	
	public static String[] genStringArray(City p) {
		return new String[]{
				"CITY POPULATION: "+p.population+".             "+" CITY WORKER POPULATION: "+p.workingPop+".             "+" CITY GROWTH RATE: "+p.growthRate,
				"CITY TRADE EFFICIENCY: "+p.tradeEfficincy+".             "+" CITY MINING CHANCE: "+p.miningChance,
				};
		
	}
	
	

}
