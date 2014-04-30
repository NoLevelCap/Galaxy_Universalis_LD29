package com.nolevelcap.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nolevelcap.ld29.PlayerProfile;
import com.nolevelcap.space.ORE;

public class SellButton extends CustomButton {
	
	public PlayerProfile player;
	
	public ORE ore;
	
	public CustomSmallLabel label;

	public SellButton(CustomSmallLabel label, PlayerProfile player, ORE ore, TextureRegion pressedTex, TextureRegion defaultTex,
			int x, int y, int height, int width, int scale, int activateButton) {
		super(pressedTex, defaultTex, x, y, height, width, scale, activateButton);
		this.player = player;
		this.ore = ore;
		this.label = label;
	}
	
	public void pressed() {
		if(System.currentTimeMillis()- timeSince > 100) {
			 timeSince = System.currentTimeMillis();
				onPress();
		Gdx.app.log("Pressed", "To right");
		if(!active) {
			active = true;
		} else {
			active = false;
		}
		}

	}

	@Override
	public void onPress() {
		if(player.getOre(ore)-1000>=0) {
			player.updateResource(ore, -1000);
			player.changeMoney((int) (1000-(ore.rarity*1000.0f)));
			player.changeScience((int) (1000-(ore.rarity*1000.0f))/2);
			readjustLabel();
		} else if(player.getOre(ore)>0){
			
		}
	}
	
	public void readjustLabel() {
		label.setText(label.ore.toString()+": "+player.getOre(label.ore)+"");
	}

}
