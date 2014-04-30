package com.nolevelcap.space;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Background extends Actor {
	
	Texture BackTex;
	
	public Background(Texture BackTex) {
		this.BackTex = BackTex;
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.draw(BackTex, 0, 0, 1280, 720);
	}
	
	
}
