package com.nolevelcap.ld29;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nolevelcap.assets.TextureHandler;
import com.nolevelcap.ld29.GameScreen.State;
import com.nolevelcap.space.CityLabel;
import com.nolevelcap.space.Colony;
import com.nolevelcap.space.Planet;
import com.nolevelcap.space.PlanetLabel;
import com.nolevelcap.widgets.Backboard;
import com.nolevelcap.widgets.Label;
import com.nolevelcap.widgets.ScrollBar;
import com.nolevelcap.widgets.Text;
import com.nolevelcap.widgets.TextButton;

public class PlanetInfo implements Screen {
	
	GameScreen screen;
	
	Stage stage;
	
	Planet p;
	
	TextureHandler textures;
	
	TextButton back;
	TextButton select;
	Label cityHeading;
	PlanetLabel stats;
	CityLabel cityInfo;
	Text colonyPc;
	Text colonyPop;
	TextButton makeColony;
	Planet np;
	
	ScrollBar bar;
	PlayerProfile player;
	
	private long timeSinceIncrease;
	
	public PlanetInfo(PlayerProfile pl, Planet planet, TextureHandler textures) {
		this.p = planet;
		this.player = pl;
		this.textures = textures;
		stage = new Stage(new FitViewport(1280, 720));
		
		BitmapFont font = new BitmapFont();
		Label backToPlanet = new Label(textures.getTexture("window"), font, new String[] {}, 1280-300, 720-300, 300, 300, 3);
		np = new Planet(p, (int) (1280-150-p.getWidth()*3) , (int) (720-150-p.getHeight()*3));
		stage.addActor(backToPlanet);
		stage.addActor(np);
		
		stats = new PlanetLabel(textures.getTexture("window"), font, np, 1280-300, 0, 415, 300, 3);
		stage.addActor(stats);
		
		
		Backboard backToCities = new Backboard(textures.getTexture("window"), 0, 720-300, 300, 975, 3);
		cityHeading = new Label(textures.getTexture("window"), font, new String[] {}, 20, 720-70, 50, 800, 1);
		cityInfo = new CityLabel(textures.getTexture("window"), font, p.cities.get(0), 30, 720-140, 50, 800, 0);
		if (p.cities.size()==0) cityInfo.setVisible(false); else cityInfo.setVisible(true);
		bar = new ScrollBar(textures.getTexture("controls"), 975-66, 435, 90, 16, 3, planet.cities.size()-1) {

			@Override
			public void update() {
				cityHeading.setText(new String[] {(p.cities.size()==0) ? "No Cities" : p.cities.get(bar.currentSelections).name + ""});
				cityInfo.setCity(p.cities.get(bar.currentSelections));
			}
			
		};

		
		select = new TextButton(textures.getTexture("controls"), font, "Adjust City", 20, 720-280, 16, 192, 3) {
			
			@Override
			public void onPress() {
				//screen.switchState(State.SolarSystem);
			}
		};
		stage.addActor(backToCities);
		stage.addActor(cityHeading);
		stage.addActor(cityInfo);
		stage.addActor(select);
		
		Backboard backToTerraform = new Backboard(textures.getTexture("window"), 0, 65, 350, 975, 3);
		colonyPc = new Text((p.colony!=null && !p.colony.unused) ? p.colony.completionPc*100.0f+"%" : "No Colony", font, 40, 370, 8, Color.WHITE);
		colonyPop = new Text((p.colony!=null && !p.colony.unused) ? p.colony.population + "pp" : "", font, 40, 230, 8, Color.WHITE);
		stage.addActor(backToTerraform);
		stage.addActor(colonyPc);
		stage.addActor(colonyPop);
		
		back = new TextButton(textures.getTexture("controls"), font, "< Back", 0, 10, 16, 192, 3) {
			
			@Override
			public void onPress() {
				screen.switchState(State.SolarSystem);
			}
		};
		stage.addActor(back);
		stage.addActor(bar);
		
		makeColony = new TextButton(textures.getTexture("controls"), font, "Make Colony (5000c)", 760, 10, 16, 192, 3) {
			
			@Override
			public void onPress() {
				if(player.Money-5000>=0) {
					p.startColony();
					player.changeMoney(-5000);
				}
			}
		};
		
		stage.addActor(makeColony);
		
		timeSinceIncrease = System.currentTimeMillis();
	}

	@Override
	public void render(float delta) {
		handleInputs();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	private void handleInputs() {
		back.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY());
		select.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY());
		bar.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY());
		
		if(player.MdC) {
		makeColony.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY());
		
		if(Gdx.input.isKeyPressed(Keys.PLUS) || Gdx.input.isKeyPressed(Keys.EQUALS)) {
			if(System.currentTimeMillis()- timeSinceIncrease > 100) {
				Gdx.app.log("Doing", "suck it");
				timeSinceIncrease = System.currentTimeMillis();
				if(player.Money-10 >=0) {
				player.changeMoney(-10);
				p.colony.increaseFunding(+10);
				}
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.MINUS)) {
			if(System.currentTimeMillis()- timeSinceIncrease > 100) {
				Gdx.app.log("Doing down ", "suck it");
				timeSinceIncrease = System.currentTimeMillis();
				if(p.colony.funding-10 >=0) {
				player.changeMoney(+10);
				p.colony.increaseFunding(-10);
				}
			}
		}
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
		Gdx.app.log("SIZE", p.cities.size()+"");
		
		cityHeading.setText(new String[] {(p.cities.size()==0) ? "No Cities" : p.cities.get(bar.currentSelections).name + ""});
		if (!(p.cities.size()==0)) cityInfo.setCity(p.cities.get(bar.currentSelections));
		if (p.cities.size()==0) cityInfo.setVisible(false); else cityInfo.setVisible(true);
		colonyPc.setText((p.colony!=null && !p.colony.unused) ? p.colony.completionPc*100.0f+"%" : "No Colony");
		colonyPop.setText((p.colony!=null && !p.colony.unused) ? p.colony.population + "pp" : "");
		
		bar.MxSelections =  p.cities.size()-1;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	public void updatePlanet(Planet planet) {
		this.p = planet;
		np.remove();
		np = new Planet(planet, (int) (1280-150-p.getWidth()*3) , (int) (720-150-p.getHeight()*3));
		stage.addActor(np);
		stats.setPlanet(np);
		colonyPc.setText((p.colony!=null && !p.colony.unused) ? p.colony.completionPc*100.0f+"%" : "No Colony");
		colonyPop.setText((p.colony!=null && !p.colony.unused) ? p.colony.population + "pp" : "");
	}

}
