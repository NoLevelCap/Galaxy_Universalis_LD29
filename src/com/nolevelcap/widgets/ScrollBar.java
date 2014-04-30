package com.nolevelcap.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class ScrollBar extends Actor {
	
	protected boolean topHover, bottomHover;
	private Texture tex;
	private TextureRegion[] Sections;
	private Rectangle Bbounds, Tbounds;
	public int currentSelections;
	public int MxSelections;
	
	private long timeSince;
	
	public ScrollBar(Texture tex, int x, int y, int height, int width, int scale, int MxSelections) {
		this.currentSelections = 0;
		this.MxSelections = MxSelections;
		this.tex = tex;
		this.setBounds(x, y, width*scale, height*scale);
		this.setScale(scale);
		this.initSections(tex);
		this.Tbounds = new Rectangle(x, getY()+getHeight()-this.Sections[0].getRegionHeight()*scale, this.Sections[0].getRegionWidth()*scale, this.Sections[0].getRegionHeight()*scale);
		this.Bbounds = new Rectangle(x, y, this.Sections[0].getRegionWidth()*scale, this.Sections[0].getRegionHeight()*scale);
		this.timeSince = System.currentTimeMillis();
	}
	
	private void initSections(Texture tex) {
		this.Sections = new TextureRegion[5];
		this.Sections[0] = new TextureRegion(tex, 80, 48, 16, 16);
		this.Sections[1] = new TextureRegion(tex, 80, 64, 16, 16);
		this.Sections[2] = new TextureRegion(tex, 80, 80, 16, 16);
		this.Sections[3] = new TextureRegion(tex, 64, 48, 16, 16);
		this.Sections[4] = new TextureRegion(tex, 64, 80, 16, 16);
	}

	public void draw(Batch batch, float parentAlpha) {
		Gdx.app.debug("A", "A");
		if (!topHover) {
			batch.draw(this.Sections[0], getX(), getY()+getHeight()-this.Sections[0].getRegionHeight()*getScaleX(), this.Sections[0].getRegionWidth()*getScaleX(), this.Sections[0].getRegionHeight()*getScaleX());
		} else {
			batch.draw(this.Sections[3], getX(), getY()+getHeight()-this.Sections[0].getRegionHeight()*getScaleX(), this.Sections[0].getRegionWidth()*getScaleX(), this.Sections[0].getRegionHeight()*getScaleX());
		}
		batch.draw(this.Sections[1], getX(), getY()+this.Sections[0].getRegionHeight()*getScaleX(), this.Sections[0].getRegionWidth()*getScaleX(), getHeight()-2*(this.Sections[0].getRegionHeight()*getScaleX()));
		if (!bottomHover) {
			batch.draw(this.Sections[2], getX(), getY(), this.Sections[0].getRegionWidth()*getScaleX(), this.Sections[0].getRegionHeight()*getScaleX());
		} else {
			batch.draw(this.Sections[4], getX(), getY(), this.Sections[0].getRegionWidth()*getScaleX(), this.Sections[0].getRegionHeight()*getScaleX());
		}
	}
	
	public void checkIfPressed(int x, int y) {
		//Gdx.app.log("A", x+"X, Y"+y+", TX"+Tbounds.x+", TY"+Tbounds.y);
		
		if(Tbounds.contains(x, y)) {
			topHover = true;
			if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
				if(!(currentSelections>=MxSelections)) {
					pressed(+1);
				}
			}
		} else {
			topHover = false;
		}
		
		if(Bbounds.contains(x, y)) {
			bottomHover = true;
			if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
				if(!(currentSelections<=0)) {
					pressed(-1);
				}
			}
		} else {
			bottomHover = false;
		}
	}
	
	public void pressed(int update) {
		if(System.currentTimeMillis()- timeSince > 500) {
			 timeSince = System.currentTimeMillis();
			 currentSelections+=update;
			 update();
		}
	}
	
	public abstract void update();
	
	
}

