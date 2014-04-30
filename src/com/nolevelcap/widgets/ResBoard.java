package com.nolevelcap.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nolevelcap.ld29.GameScreen.State;
import com.nolevelcap.space.RES;

public class ResBoard extends Actor {
	
	private BitmapFont font;
	private String text;
	private Texture tex;
	private TextureRegion[] Sections;
	public RES res;
	
	public ResBoard(Texture tex, BitmapFont font, String text, RES state,  int x, int y, int height, int width, int scale) {
		this.tex = tex;
		this.font = font;
		this.text = text;
		this.setBounds(x, y, width, height);
		this.setScale(scale);
		this.initSections(tex, state);
		this.res = state;
	}
	
	private void initSections(Texture tex, RES res) {
		this.Sections = new TextureRegion[2];
		switch(res) {
		case Money:
			this.Sections[0] = new TextureRegion(tex, 128, 0, 32, 32);
			this.Sections[1] = new TextureRegion(tex, 160, 0, 32, 32);
			break;
		case Ores:
			this.Sections[0] = new TextureRegion(tex, 0, 0, 32, 32);
			this.Sections[1] = new TextureRegion(tex, 32, 0, 32, 32);
			break;
		case Science:
			this.Sections[0] = new TextureRegion(tex, 64, 0, 32, 32);
			this.Sections[1] = new TextureRegion(tex, 96, 0, 32, 32);
			break;
		default:
			break;
		}
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.draw(this.Sections[0], getX(), getY()+getHeight()*getScaleY(), getWidth()*getScaleX(), getHeight()*getScaleY());
		batch.draw(this.Sections[1], getX(), getY(), getWidth()*getScaleX(), getHeight()*getScaleY());
		
		font.draw(batch, text, getX()+((getWidth()*getScaleX())/2)-font.getBounds(text).width/2, getY()+18*getScaleY());
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	
}

