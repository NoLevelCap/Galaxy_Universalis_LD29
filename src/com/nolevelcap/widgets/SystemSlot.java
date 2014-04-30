package com.nolevelcap.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.nolevelcap.ld29.GalaxyScreen;
import com.nolevelcap.ld29.GameScreen.State;
import com.nolevelcap.space.Planet;
import com.nolevelcap.space.SolarSystem;

public class SystemSlot extends Group {
	public boolean hovered;
	
	public SolarSystem system;
	
	public TextureRegion[] textures;
	
	public GalaxyScreen parent;
	
	private Rectangle bounds;
	
	private long timeSince;
	
	private Text name;
	
	private Image planet;
	
	public int ID;
	
	public SystemSlot(GalaxyScreen parent, Texture tex, int ID, int x, int y) {
		this.ID = ID;
		this.parent = parent;
		this.textures = new TextureRegion[3];
		this.textures[0] = new TextureRegion(tex, 0, 0, 64, 64);
		this.textures[1] = new TextureRegion(tex, 64, 0, 64, 64);
		this.textures[2] = new TextureRegion(tex, 128, 0, 64, 64);
		this.setBounds(x, y, 128, 128);
		this.bounds = new Rectangle(x, y, getWidth(), getHeight());
		
		this.timeSince = System.currentTimeMillis();
		
		BitmapFont font = new BitmapFont();
		
		this.name = new Text("", font, 10, 118, 1, Color.WHITE);
		this.planet = new Image(null, 0, 25, 64, 64);
		this.planet.setVisible(false);
		this.name.setVisible(false);
		
		this.addActor(name);
		this.addActor(planet);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(system != null) {
			if(hovered) {
				batch.setColor(Color.ORANGE);
				batch.draw(textures[0], getX(), getY(), getWidth(), getHeight());
				batch.setColor(Color.WHITE);
			} else {
				batch.draw(textures[0], getX(), getY(), getWidth(), getHeight());
			}
		} else {
			if(hovered) {
				batch.draw(textures[2], getX(), getY(), getWidth(), getHeight());
			} else {
				batch.draw(textures[1], getX(), getY(), getWidth(), getHeight());
			}
		}
		super.draw(batch, parentAlpha);
	}

	public SolarSystem getSystem() {
		return system;
	}

	public void setSystem(SolarSystem system) {
		this.system = system;
		
		if(system!=null) {
			name.setText(system.name);
			name.setX(64-(new BitmapFont().getBounds(system.name).width/2));
			planet.setImage(system.planets.get(0).region);
			planet.setX(64-32);
			this.planet.setVisible(true);
			this.name.setVisible(true);
		}
	}
	
	private void refreshSystem() {
		name.setText(system.name);
		name.setX(64-(new BitmapFont().getBounds(system.name).width/2));
		planet.setImage(system.planets.get(0).region);
		planet.setX(64-32);
		this.planet.setVisible(true);
		this.name.setVisible(true);
	}
	
	public boolean checkIfPressed(int x, int y) {
		if(bounds.contains(x, y)) {
			hovered = true;
			if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
				pressed();
				return true;
			}
		} else {
			hovered = false;
		}
		return false;
	}
	
	public void pressed() {
		if(System.currentTimeMillis()- timeSince > 1000) {
			 timeSince = System.currentTimeMillis();
			 if(system == null) {
				 system = parent.createSolarSystem();
				 refreshSystem();
			 } else {
				 Gdx.app.log("PRESSED", system.name);
				 parent.screen.setSolarSystem(system);
				 parent.screen.switchState(State.SolarSystem);
			 }
		}

	}
	
	
}
