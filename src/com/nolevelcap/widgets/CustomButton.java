package com.nolevelcap.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class CustomButton extends Actor {
	protected TextureRegion pressedTex, defaultTex;
	public boolean active, hover;
	protected Rectangle bounds;
	protected int button;
	long timeSince;
	
	public CustomButton(TextureRegion pressedTex, TextureRegion defaultTex, int x, int y, int height , int width, int scale, int activateButton){
		this.button = activateButton;
		this.pressedTex = pressedTex;
		this.defaultTex = defaultTex;
		this.setBounds(x, y, width, height);
		this.setScale(scale);
		this.bounds = new Rectangle(x, y, width*getScaleX(), height*getScaleY());
		this.active = false;
		this.timeSince = System.currentTimeMillis();
	}

	public void draw(Batch batch, float parentAlpha) {
		if(hover || active) {
			batch.draw(pressedTex, getX(), getY(), getWidth()*getScaleX(), getHeight()*getScaleY());
		} else {
			batch.draw(defaultTex, getX(), getY(), getWidth()*getScaleX(), getHeight()*getScaleY());
		}
	}
	
	public boolean checkIfPressed(int x, int y) {
		if(bounds.contains(x, y)) {
			hover();
			if(Gdx.input.isButtonPressed(button)) {
				pressed();
				return true;
			}
		} else {
			noHover();
		}
		return false;
	}
	
	public void pressed() {
		if(System.currentTimeMillis()- timeSince > 1000) {
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
	
	public boolean checkIfPressed(int x, int y, int i) {
		if(bounds.contains(x, y)) {
			hover();
			if(Gdx.input.isButtonPressed(button)) {
				pressed(i);
				return true;
			}
		} else {
			noHover();
		}
		return false;
	}
	
	public void pressed(int i) {
		if(System.currentTimeMillis()- timeSince > i) {
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
	
	public void notPressed() {
		active = false;
	}
	
	public void hover() {
		hover = true;
	}
	
	public void noHover() {
		hover = false;
	}
	
	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public abstract void onPress();
	
	
}
