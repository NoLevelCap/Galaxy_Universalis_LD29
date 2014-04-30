package com.nolevelcap.ld29;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.nolevelcap.assets.NameGen;
import com.nolevelcap.assets.TextureHandler;
import com.nolevelcap.ld29.GameScreen.State;
import com.nolevelcap.space.ORE;
import com.nolevelcap.space.SolarSystem;
import com.nolevelcap.widgets.Backboard;
import com.nolevelcap.widgets.CustomButton;
import com.nolevelcap.widgets.CustomSmallLabel;
import com.nolevelcap.widgets.Image;
import com.nolevelcap.widgets.Label;
import com.nolevelcap.widgets.ScienceChoice;
import com.nolevelcap.widgets.SellButton;
import com.nolevelcap.widgets.SmallLabel;
import com.nolevelcap.widgets.SystemSlot;
import com.nolevelcap.widgets.Text;
import com.nolevelcap.widgets.TextButton;
import com.nolevelcap.widgets.WrappedText;

public class GalaxyScreen implements Screen{
	
	public GameScreen screen;
	
	Stage stage;
	
	PlayerProfile player;
	
	TextureHandler textures;
	
	ArrayList<SystemSlot> slots;
	
	long reUsableTime;
	
	public GalaxyScreen(GameScreen screen, PlayerProfile play, TextureHandler textures) {
		this.player = play;
		this.screen = screen;
		
		this.textures = textures;
		
		stage = new Stage(new FillViewport(1280, 720));
		
		slots = new ArrayList<SystemSlot>();
		
		for(int x = 0; x<10; x++) {
			for(int y = 4; y>=0; y--) {
				SystemSlot slot = new SystemSlot(this, textures.getTexture("solar_systems"), (x+1)+((5-(y+1))*10), x*128, y*128+40);
				slots.add(slot);
				stage.addActor(slot);
			}
		}
		
		slots.get(0).setSystem(new SolarSystem(player, textures, new NameGen().genName(), true));
		
		Gdx.app.log("GEN COMPLETE", slots.get(0).system.planets.get(0).getName());
		
		reUsableTime = System.currentTimeMillis();
	}

	@Override
	public void render(float delta) {
		handleInputs();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	private void handleInputs() {
		if(System.currentTimeMillis()-reUsableTime>500) {
			for(SystemSlot slot:slots) {
				if(slot.isVisible()){
				slot.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY());
				}
			}
		}
		
		for(SystemSlot slot:slots) {
			Gdx.app.log("INFO", slot.ID+" "+player.maxAvailableSolarSystems+"");
			if(slot.ID<=player.maxAvailableSolarSystems+1) {
				
				slot.setVisible(true);
			} else {
				slot.setVisible(false);
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		player = screen.profile;
		reUsableTime = System.currentTimeMillis();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public SolarSystem createSolarSystem() {
		return new SolarSystem(player, textures, new NameGen().genName(), false);
	}

}
