package com.nolevelcap.ld29;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.nolevelcap.assets.TextureHandler;
import com.nolevelcap.ld29.GameScreen.State;
import com.nolevelcap.space.ORE;
import com.nolevelcap.widgets.CustomButton;
import com.nolevelcap.widgets.CustomSmallLabel;
import com.nolevelcap.widgets.Label;
import com.nolevelcap.widgets.SellButton;
import com.nolevelcap.widgets.SmallLabel;
import com.nolevelcap.widgets.TextButton;

public class ResScreen implements Screen{
	
	GameScreen screen;
	
	Stage stage;
	
	PlayerProfile player;
	
	ArrayList<CustomSmallLabel> labels;
	ArrayList<SellButton> buttons;
	
	TextButton back;
	
	long timeSince;
	
	public ResScreen(PlayerProfile player, TextureHandler textures) {
		stage = new Stage(new FillViewport(1280, 720));
		
		this.player = player;
		
		BitmapFont font = new BitmapFont();
		
		labels = new ArrayList<CustomSmallLabel>();
		buttons = new ArrayList<SellButton>();
		
		int i=0;
		int x=0;
		for(ORE ores: ORE.values()){
			CustomSmallLabel label = new CustomSmallLabel(ores, textures.getTexture("controls"), font, ores.toString()+": "+player.getOre(ores)+"",  x*198, i*32, 32, 196, 2);
			SellButton button = new SellButton(label, player, ores, new TextureRegion(textures.getTexture("controls"), 64, 32, 16, 16), new TextureRegion(textures.getTexture("controls"), 48, 32, 16, 16), x*198+174, i*32+13, 16, 16, 1, Buttons.LEFT);
			stage.addActor(label);
			labels.add(label);
			
			stage.addActor(button);
			buttons.add(button);
			i++;
			if(i*32>720-32) {
				i=0;
				x++;
			}
		}
		
		back = new TextButton(textures.getTexture("controls"), font, "< Black", 1280-192, 10, 16, 192, 3) {
			
			@Override
			public void onPress() {
				screen.switchState(State.SolarSystem);
			}
		};
		stage.addActor(back);
		
		timeSince = System.currentTimeMillis();
		
	}

	@Override
	public void render(float delta) {
		handleInputs();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	private void handleInputs() {
		for(CustomSmallLabel label:labels){
			label.setText(label.ore.toString()+": "+player.getOre(label.ore)+"");
		}
		
		for(SellButton cb: buttons) {
			if(cb.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY())) {
				cb.notPressed();
			}
		}
		if(System.currentTimeMillis() - timeSince > 1000) {
		back.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY());
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		player = screen.profile;
		this.player.PrintTotal();
		
		for(CustomSmallLabel label:labels){
			label.setText(label.ore.toString()+": "+player.getOre(label.ore)+"");
		}
		
		timeSince = System.currentTimeMillis();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
