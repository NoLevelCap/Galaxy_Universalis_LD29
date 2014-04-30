package com.nolevelcap.space;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.nolevelcap.widgets.Label;

public class PlanetLabel extends Label{

	public PlanetLabel(Texture tex, BitmapFont font, Planet p, int x,
			int y, int height, int width, int scale) {
		super(tex, font, genStringArray(p), x, y, height, width, scale);
	}

	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}
	
	public void setPlanet(Planet p) {
		super.setText(genStringArray(p));
	}
	
	public static String[] genStringArray(Planet p) {
		return new String[]{
				"PLANET NAME: "+p.getName(),
				"PLANET TYPE: "+p.getElement().toString(),
				"PLANET HEAT: "+p.getHeat()+"C",
				"PLANET SURVIVABILIY: "+p.getSurvivabiliy()+"%",
				};
		
	}
	
	

}
