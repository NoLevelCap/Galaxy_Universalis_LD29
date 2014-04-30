package com.nolevelcap.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;

public abstract class TextButton extends Actor{
	private boolean isHovered, isActive;
	private BitmapFont font;
	private String text;
	private Texture tex;
	private TextureRegion[] Sections;
	private TextureRegion[] HoverSections;
	private TextureRegion[] PressSections;
	private TextureRegion[] BothSections;
	private Rectangle bounds;
	
	private long timeSince;
	
	public TextButton(Texture tex, BitmapFont font, String text, int x, int y, int height, int width, int scale) {
		this.tex = tex;
		this.font = font;
		this.text = text;
		this.setBounds(x, y, width, height);
		this.bounds = new Rectangle(x, y, width*scale, height*scale);
		this.setScale(scale);
		this.initSections(tex);
		this.timeSince = System.currentTimeMillis();
	}
	
	private void initSections(Texture tex) {
		this.Sections = new TextureRegion[3];
		this.Sections[0] = new TextureRegion(tex, 0, 16, 16, 16);
		this.Sections[1] = new TextureRegion(tex, 16, 16, 16, 16);
		this.Sections[2] = new TextureRegion(tex, 32, 16, 16, 16);
		
		this.HoverSections = new TextureRegion[3];
		this.HoverSections[0] = new TextureRegion(tex, 0, 48, 16, 16);
		this.HoverSections[1] = new TextureRegion(tex, 16, 48, 16, 16);
		this.HoverSections[2] = new TextureRegion(tex, 32, 48, 16, 16);
		
		this.PressSections = new TextureRegion[3];
		this.PressSections[0] = new TextureRegion(tex, 0, 32, 16, 16);
		this.PressSections[1] = new TextureRegion(tex, 16, 32, 16, 16);
		this.PressSections[2] = new TextureRegion(tex, 32, 32, 16, 16);
		
		this.BothSections = new TextureRegion[3];
		this.BothSections[0] = new TextureRegion(tex, 0, 32, 16, 16);
		this.BothSections[1] = new TextureRegion(tex, 16, 32, 16, 16);
		this.BothSections[2] = new TextureRegion(tex, 32, 32, 16, 16);
	}

	public void draw(Batch batch, float parentAlpha) {
		if(isHovered && isActive) {
			batch.draw(this.BothSections[0], getX(), getY(), 16*getScaleX(), getHeight()*getScaleY());
			batch.draw(this.BothSections[1], getX()+16*getScaleX(), getY(), getWidth()-2*(16*getScaleX()), getHeight()*getScaleY());
			batch.draw(this.BothSections[2], getX()+getWidth()-16*getScaleX(), getY(), 16*getScaleX(), getHeight()*getScaleY());
	
		} else if(isHovered) {
			batch.draw(this.HoverSections[0], getX(), getY(), 16*getScaleX(), getHeight()*getScaleY());
			batch.draw(this.HoverSections[1], getX()+16*getScaleX(), getY(), getWidth()-2*(16*getScaleX()), getHeight()*getScaleY());
			batch.draw(this.HoverSections[2], getX()+getWidth()-16*getScaleX(), getY(), 16*getScaleX(), getHeight()*getScaleY());
		} else if(isActive){
			batch.draw(this.PressSections[0], getX(), getY(), 16*getScaleX(), 16*getScaleY());
			batch.draw(this.PressSections[1], getX()+16*getScaleX(), getY(), getWidth()-2*(16*getScaleX()), getHeight()*getScaleY());
			batch.draw(this.PressSections[2], getX()+getWidth()-16*getScaleX(), getY(), 16*getScaleX(), getHeight()*getScaleY());
		} else {
			batch.draw(this.Sections[0], getX(), getY(), 16*getScaleX(), getHeight()*getScaleY());
			batch.draw(this.Sections[1], getX()+16*getScaleX(), getY(), getWidth()-2*(16*getScaleX()), getHeight()*getScaleY());
			batch.draw(this.Sections[2], getX()+getWidth()-16*getScaleX(), getY(), 16*getScaleX(), getHeight()*getScaleY());
		}
		
		font.draw(batch, text, getX()+4*getScaleX(), getY()+32);
		
	}
	
	public boolean checkIfPressed(int x, int y) {
		if(bounds.contains(x, y)) {
			
			hover();
			if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
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
		Gdx.app.log("Pressed", "To right");
		onPress();
		if(!isActive) {
			isActive = true;
		} else {
			isActive = false;
		}
		}
	}
	
	public void notPressed() {
		isActive = false;
	}
	
	public void hover() {
		isHovered = true;
	}
	
	public void noHover() {
		isHovered = false;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public abstract void onPress();
}
