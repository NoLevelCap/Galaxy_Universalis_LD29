package com.nolevelcap.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class WrappedText extends Actor{
	private String text;
	private BitmapFont font;
	
	public WrappedText(String text, BitmapFont font, int x, int y,int width, int scale, Color color) {
		this.text = text;
		this.font = font;
		this.setX(x);
		this.setY(y);
		this.setScale(scale);
		this.setColor(color);
		this.setWidth(width);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.setScale(getScaleX());
		font.setColor(this.getColor());
		font.drawWrapped(batch, text, getX(), getY(), getWidth());
		font.setColor(Color.WHITE);
		font.setScale(1);
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
