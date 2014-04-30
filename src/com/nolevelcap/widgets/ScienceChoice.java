package com.nolevelcap.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ScienceChoice extends Actor{
	
	private TextureRegion Image;
	private Texture selection;
	private String tiltleString;
	private String descriptionString;
	private int cost;
	private boolean available, hovered, active, bought;
	
	private Rectangle bounds;
	
	long timeSince;
	
	public ScienceChoice(TextureRegion image, Texture selection, String title, String descprition, int cost, boolean available, int x, int y , int width, int height) {
		this.setImage(image);
		this.setSelection(selection);
		this.setTiltleString(title);
		this.setDescriptionString(descprition);
		this.setCost(cost);
		this.setAvailable(available);
		this.setBounds(x, y, width, height);
		this.bounds = new Rectangle(x, y, width, height);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(active && available|| hovered && available) {
			batch.draw(getSelection(), getX(), getY(), getWidth(), getHeight());
		}
		
		if(!available) {
			batch.setColor(Color.GRAY);
		} else if(bought) {
			batch.setColor(new Color(194, 116, 40, 0.5f));
		}
		batch.draw(Image, getX(), getY(), getWidth(), getHeight());
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
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
			 if(this.isAvailable()) {
			 onPress();
			 }
		Gdx.app.log("Pressed", "To right");
		if(!active) {
			active = true;
		} else {
			active = false;
		}
		}

	}
	
	public abstract void action();
	public abstract void onPress();

	public TextureRegion getImage() {
		return Image;
	}

	public void setImage(TextureRegion image) {
		Image = image;
	}

	public String getTiltleString() {
		return tiltleString;
	}

	public void setTiltleString(String tiltleString) {
		this.tiltleString = tiltleString;
	}

	public String getDescriptionString() {
		return descriptionString;
	}

	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Texture getSelection() {
		return selection;
	}

	public void setSelection(Texture selection) {
		this.selection = selection;
	}

	public boolean isBought() {
		return bought;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
}
