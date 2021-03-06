package com.nolevelcap.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Backboard extends Group {
	
	private Texture tex;
	private TextureRegion[] Sections;
	
	public Backboard(Texture tex, int x, int y, int height, int width, int scale) {
		this.tex = tex;
		this.setBounds(x, y, width, height);
		this.setScale(scale);
		this.initSections(tex);
	}
	
	private void initSections(Texture tex) {
		this.Sections = new TextureRegion[9];
		this.Sections[0] = new TextureRegion(tex, 0, 0, 16, 16);
		this.Sections[1] = new TextureRegion(tex, 16, 0, 16, 16);
		this.Sections[2] = new TextureRegion(tex, 32, 0, 16, 16);
		this.Sections[3] = new TextureRegion(tex, 0, 16, 16, 16);
		this.Sections[4] = new TextureRegion(tex, 16, 16, 16, 16);
		this.Sections[5] = new TextureRegion(tex, 32, 16, 16, 16);
		this.Sections[6] = new TextureRegion(tex, 0, 32, 16, 16);
		this.Sections[7] = new TextureRegion(tex, 16, 32, 16, 16);
		this.Sections[8] = new TextureRegion(tex, 32, 32, 16, 16);
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.draw(this.Sections[0], getX(), getY()+getHeight()-16*getScaleY(), 16*getScaleX(), 16*getScaleY());
		batch.draw(this.Sections[1], getX()+16*getScaleX(), getY()+getHeight()-16*getScaleY(), getWidth()-2*(16*getScaleX()), 16*getScaleY());
		batch.draw(this.Sections[2], getX()+getWidth()-16*getScaleX(), getY()+getHeight()-16*getScaleY(), 16*getScaleX(), 16*getScaleY());
		
		
		batch.draw(this.Sections[3], getX(), getY()+16*getScaleY(), 16*getScaleX(), getHeight()-2*(16*getScaleY()));
		batch.draw(this.Sections[4], getX()+16*getScaleX(), getY()+16*getScaleY(), getWidth()-2*(16*getScaleX()), getHeight()-2*(16*getScaleY()));
		batch.draw(this.Sections[5], getX()+getWidth()-16*getScaleX(), getY()+16*getScaleY(), 16*getScaleX(), getHeight()-2*(16*getScaleY()));
		
		batch.draw(this.Sections[6], getX(), getY(), 16*getScaleX(), 16*getScaleY());
		batch.draw(this.Sections[7], getX()+16*getScaleX(), getY(), getWidth()-2*(16*getScaleX()), 16*getScaleY());
		batch.draw(this.Sections[8], getX()+getWidth()-16*getScaleX(), getY(), 16*getScaleX(), 16*getScaleY());
		super.draw(batch, parentAlpha);
		
	}
	
	
}
