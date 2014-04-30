package com.nolevelcap.space;

import com.nolevelcap.assets.TextureHandler;
import com.nolevelcap.ld29.PlayerProfile;

public class HomeWorld extends Planet {
	
	City Capital;

	public HomeWorld(PlayerProfile player, TextureHandler textures, Element e, int texX, int x,
			int y, int width, int height, int scale, String name) {
		super(player, textures, e, texX, x, y, width, height, scale, name);
		
		Capital = new City(player, this, 2000000, 0.75f);
		super.addCity(Capital);
		
		Capital = new City(player, this, 2000000, 0.75f);
		super.addCity(Capital);
	}

}
