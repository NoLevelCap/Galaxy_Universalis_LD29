package com.nolevelcap.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Image extends Actor{
	private TextureRegion image;
	
	public Image(TextureRegion image, int x, int y, int width, int height) {
		this.image = image;
		this.setBounds(x, y, width, height);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(image, getX(), getY(), getWidth(), getHeight());
		super.draw(batch, parentAlpha);
	}

	public TextureRegion getImage() {
		return image;
	}

	public void setImage(TextureRegion image) {
		this.image = image;
	}
	
	
	
	
}
