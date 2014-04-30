package com.nolevelcap.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SmallLabel extends Actor {
	
	private BitmapFont font;
	private String text;
	private Texture tex;
	private TextureRegion[] Sections;
	
	public SmallLabel(Texture tex, BitmapFont font, String text, int x, int y, int height, int width, int scale) {
		this.tex = tex;
		this.font = font;
		this.text = text;
		this.setBounds(x, y, width, height);
		this.setScale(scale);
		this.initSections(tex);
	}
	
	private void initSections(Texture tex) {
		this.Sections = new TextureRegion[3];
		this.Sections[0] = new TextureRegion(tex, 0, 16, 16, 16);
		this.Sections[1] = new TextureRegion(tex, 16, 16, 16, 16);
		this.Sections[2] = new TextureRegion(tex, 32, 16, 16, 16);
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.draw(this.Sections[0], getX(), getY(), 16*getScaleX(), 16*getScaleY());
		batch.draw(this.Sections[1], getX()+16*getScaleX(), getY(), getWidth()-2*(16*getScaleX()), 16*getScaleY());
		batch.draw(this.Sections[2], getX()+getWidth()-16*getScaleX(), getY(), 16*getScaleX(), 16*getScaleY());
		
		font.draw(batch, text, getX()+4*getScaleX(), getY()+32);
		
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	
}
