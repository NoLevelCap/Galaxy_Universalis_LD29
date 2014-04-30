package com.nolevelcap.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.nolevelcap.ld29.GameScreen;

public class FleetButton extends Group{
	
	GameScreen screen;
	
	TextureRegion[] Sections;
	
	CustomButton plus, minus;
	
	Text info, MoneyInfo;
	
	int fleet;
	
	int fleetCost;
	
	public FleetButton(GameScreen parent, Texture background, Texture buttons, int x, int y) {
		
		this.screen = parent;
		this.Sections(background);
		this.setPosition(x, y);
		
		fleet = 0;
		
		plus = new CustomButton(new TextureRegion(buttons, 80, 32, 16, 16), new TextureRegion(buttons, 80, 16, 16, 16), 6, 16, 16, 16, 2, Buttons.LEFT) {
			
			@Override
			public void onPress() {
				if(fleet<50) {
				
				if(screen.profile.Money-fleetCost>=0) {
					fleet++;
					screen.profile.changeMoney(-fleetCost);
				}
				}
			}
		};
		this.addActor(plus);
		
		minus = new CustomButton(new TextureRegion(buttons, 64, 32, 16, 16), new TextureRegion(buttons, 48, 32, 16, 16), 128-32-6, 16, 16, 16, 2, Buttons.LEFT) {
			
			@Override
			public void onPress() {
				if(fleet>0) {
				fleet--;
				}
			}
		};
		this.addActor(minus);
		
		BitmapFont font = new BitmapFont();
		
		info = new Text(fleet+"", font, (int) (64-font.getBounds(fleet+"").width/2), 40, 1, Color.WHITE);
		MoneyInfo = new Text(fleetCost+"", font, (int) (64-font.getBounds(fleetCost+"").width/2), 60, 1, Color.WHITE);
		this.addActor(info);
		this.addActor(MoneyInfo);
	}
	
	public void Sections(Texture tex) {
		Sections = new TextureRegion[4];
		
		Sections[0] = new TextureRegion(tex, 0, 0, 64, 64);
		Sections[1] = new TextureRegion(tex, 64, 0, 64, 64);
		Sections[2] = new TextureRegion(tex, 128, 0, 64, 64);
		Sections[3] = new TextureRegion(tex, 192, 0, 64, 64);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(Sections[0], getX(), getY()+64, 64, 64);
		batch.draw(Sections[1], getX()+64, getY()+64, 64, 64);
		batch.draw(Sections[2], getX(), 0, getY()+64, 64);
		batch.draw(Sections[3], getX()+64, getY()+0, 64, 64);
		super.draw(batch, parentAlpha);
	}
	
	public void logic() {
		plus.checkIfPressed((int) (Gdx.input.getX()-getX()), (int) ((720-Gdx.input.getY())-getY()), 50);
		minus.checkIfPressed((int) (Gdx.input.getX()-getX()), (int) ((720-Gdx.input.getY())-getY()), 50);
		
		info.setText(fleet+"");
		info.setX((int) (64-info.getFont().getBounds(fleet+"").width/2));
		
		plus.setActive(false);
		minus.setActive(false);
		
		fleetCost = (int) Math.round(Math.pow(1.25, fleet));
		screen.profile.maxAvailableSolarSystems = fleet;
		
		MoneyInfo.setText(fleetCost+"");
		MoneyInfo.setX((int) (64-info.getFont().getBounds(fleetCost+"").width/2));
	}

}
