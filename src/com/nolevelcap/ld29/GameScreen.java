package com.nolevelcap.ld29;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.nolevelcap.widgets.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nolevelcap.assets.NameGen;
import com.nolevelcap.assets.TextureHandler;
import com.nolevelcap.space.Background;
import com.nolevelcap.space.Element;
import com.nolevelcap.space.Event;
import com.nolevelcap.space.HomeWorld;
import com.nolevelcap.space.Planet;
import com.nolevelcap.space.PlanetLabel;
import com.nolevelcap.space.RES;
import com.nolevelcap.space.SolarSystem;

public class GameScreen implements Screen{
	
	public enum State {
		SolarSystem,
		Galaxy,
		ResourcesInfo,
		SienceInfo,
		PlanetInfo;
	}
	
	private State currentState;
	
	private NameGen gen;
	
	private Game g;
	
	private Planet Home;
	
	Stage stage;
	
	public TextureHandler textures;
	
	public Planet selectedPlanet;
	
	ArrayList<Planet> planetsOnScreen = new ArrayList<Planet>();
	
	ArrayList<CustomButton> button = new ArrayList<CustomButton>();
	
	ArrayList<CustomButton> ShopButton = new ArrayList<CustomButton>();
	
	ArrayList<TextButton> planetsMenu = new ArrayList<TextButton>();
	
	ArrayList<ResBoard> upDateUi = new ArrayList<ResBoard>();
	
	long timeSince;
	PlanetLabel planetInfo;
	
	PlanetInfo planetPage;
	ResScreen resourcesScreen;
	ScienceScreen scienceScreen;
	GalaxyScreen galaxyScreen;
	
	public int speed;
	
	public int day;
	
	private SmallLabel timeLabel;
	
	long timeSinceLastDay;
	
	public PlayerProfile profile;
	
	FleetButton fleet;
	
	TextButton solarSystemPage;
	
	EventScreen EventDisplay;
	
	SolarSystem currentSystem;

	public GameScreen(Game g) {
		stage = new Stage(new FitViewport(1280, 720));
		this.g = g;
		
		profile = new PlayerProfile();
		
		currentState = State.SolarSystem;
		
		textures = new TextureHandler();
		textures.addTexture("planets", Gdx.files.internal("Planets.png"));
		textures.addTexture("borders", Gdx.files.internal("Selection.png"));
		textures.addTexture("WP", Gdx.files.internal("WP.png"));
		textures.addTexture("IP", Gdx.files.internal("IP.png"));
		textures.addTexture("GP", Gdx.files.internal("GP.png"));
		textures.addTexture("FP", Gdx.files.internal("FP.png"));
		textures.addTexture("TP", Gdx.files.internal("TP.png"));
		textures.addTexture("window", Gdx.files.internal("window.png"));
		textures.addTexture("space", Gdx.files.internal("background.png"));
		textures.addTexture("controls", Gdx.files.internal("controlButtons.png"));
		textures.addTexture("icons", Gdx.files.internal("icons.png"));
		textures.addTexture("event_filler", Gdx.files.internal("Event.png"));
		textures.addTexture("colony_event", Gdx.files.internal("Event_1.png"));
		textures.addTexture("shop", Gdx.files.internal("shops.png"));
		textures.addTexture("science", Gdx.files.internal("science.png"));
		textures.addTexture("fleet", Gdx.files.internal("spaceTravel.png"));
		textures.addTexture("solar_systems", Gdx.files.internal("solarSystems.png"));
		
		galaxyScreen = new GalaxyScreen(this, profile, textures);
		
		currentSystem = galaxyScreen.slots.get(0).system;
		
		selectedPlanet = currentSystem.planets.get(0);
		
		
		Gdx.app.log("NAME", galaxyScreen.slots.get(0).system.name);
		
		planetPage = new PlanetInfo(profile, selectedPlanet, textures);
		planetPage.screen = this;
		
		resourcesScreen = new ResScreen(profile, textures);
		resourcesScreen.screen = this;
		
		scienceScreen = new ScienceScreen(profile, textures);
		scienceScreen.screen = this;
		
		gen = new NameGen();
		
		
		BitmapFont font = new BitmapFont();
		
		stage.addActor(new Background(textures.getTexture("space")));
		
		setPlanets(galaxyScreen.slots.get(0).system.planets);
		
		CustomButton pause = new CustomButton(new TextureRegion(textures.getTexture("controls"), 64, 16, 16, 16), new TextureRegion(textures.getTexture("controls"), 48, 16, 16, 16), 0, 500, 16, 16, 3, Buttons.LEFT) {
			public void onPress() {
				speed = 0;
			}
			
		};
		
		pause.active = true;
		
		CustomButton play = new CustomButton(new TextureRegion(textures.getTexture("controls"), 16, 0, 16, 16), new TextureRegion(textures.getTexture("controls"), 0, 0, 16, 16), 16*3, 500, 16, 16, 3, Buttons.LEFT){
			public void onPress() {
				speed = 1;
			}
			
		};
		CustomButton second = new CustomButton(new TextureRegion(textures.getTexture("controls"), 48, 0, 16, 16), new TextureRegion(textures.getTexture("controls"), 32, 0, 16, 16), 32*3, 500, 16, 16, 3, Buttons.LEFT){
			public void onPress() {
				speed = 2;
			}
			
		};
		CustomButton third = new CustomButton(new TextureRegion(textures.getTexture("controls"), 80, 0, 16, 16), new TextureRegion(textures.getTexture("controls"), 64, 0, 16, 16), 48*3, 500, 16, 16, 3, Buttons.LEFT){
			public void onPress() {
				speed = 3;
			}
			
		};
		
		
		stage.addActor(pause);
		button.add(pause);
		
		stage.addActor(play);
		button.add(play);
		
		stage.addActor(second);
		button.add(second);
		
		stage.addActor(third);
		button.add(third);
		
		CustomButton OreInventory = new CustomButton(new TextureRegion(textures.getTexture("shop"), 64, 0, 64, 64), new TextureRegion(textures.getTexture("shop"), 0, 0, 64, 64), 1280-64*3, 0, 64, 64, 3, Buttons.LEFT){
			public void onPress() {
				switchState(State.ResourcesInfo);
			}
			
		};
		
		Text Ores = new Text("Ores Inv", font, 1138, 128, 2, Color.BLACK);
		
		stage.addActor(OreInventory);
		stage.addActor(Ores);
		ShopButton.add(OreInventory);
		
		CustomButton ScienceShop = new CustomButton(new TextureRegion(textures.getTexture("shop"), 192, 0, 64, 64), new TextureRegion(textures.getTexture("shop"), 128, 0, 64, 64), 0 , 0, 64, 64, 3, Buttons.LEFT){
			public void onPress() {
				switchState(State.SienceInfo);
			}
			
		};
		
		Text Research = new Text("Research", font, 25, 128, 2, Color.BLACK);
		
		solarSystemPage = new TextButton(textures.getTexture("controls"), font, "Venture Out", 1080-400, 720-128, 16, 192, 3) {
			
			@Override
			public void onPress() {
				switchState(State.Galaxy);
			}
		};
		
		stage.addActor(solarSystemPage);
		
		stage.addActor(ScienceShop);
		stage.addActor(Research);
		ShopButton.add(ScienceShop);
		

		timeLabel = new SmallLabel(textures.getTexture("controls"), font, "Day "+day, 0, 545, 16, 192, 3);
		stage.addActor(timeLabel);
	
		
		
		planetInfo = new PlanetLabel(textures.getTexture("window"), font, selectedPlanet, 1280-400, 720-250, 250, 400, 3);
		stage.addActor(planetInfo);
		
		ResBoard Money = new ResBoard(textures.getTexture("icons"), font, profile.Money+"", RES.Money, 0, 720-32*4, 32, 32, 2);
		upDateUi.add(Money);
		stage.addActor(Money);
		
		ResBoard Science = new ResBoard(textures.getTexture("icons"), font, profile.Science+"", RES.Science, 64, 720-32*4, 32, 32, 2);
		upDateUi.add(Science);
		stage.addActor(Science);
		
		ResBoard Ore = new ResBoard(textures.getTexture("icons"), font, profile.Ore+"", RES.Ores, 128, 720-32*4, 32, 32, 2);
		upDateUi.add(Ore);
		stage.addActor(Ore);
		
		timeSince = System.currentTimeMillis();
		
		day = 1;
		timeSinceLastDay = System.currentTimeMillis();
		
		
		fleet = new FleetButton(this, textures.getTexture("fleet"), textures.getTexture("controls"), 640-64, 0);
		stage.addActor(fleet);
		
		Event Welcome = new Event(profile, textures.getTexture("event_filler"), new String[] {"I will enjoy this."}, "I made this game for Ludum Dare 29, If you have not got this game from their definatly go check them out. It was made in 48 hours and the theme was 'Beneath the Surface' so I made a mining exploration game with random solar systems. I wanted to make the game a space europa universalis, but that was overly ambitious, so I was left with this. Please enjoy, I will continue development of this on my site www.nolevelcap.net .", "WELCOME TO GALAXY UNIVERSALIS") {
			
			@Override
			public void action(int selectionINFO) {
				profile.changeScience(5);
			}
		};
		
		
		EventDisplay = new EventScreen(textures.getTexture("window"), font, null);
		EventDisplay.setVisible(false);
		
		profile.eventPool.add(Welcome);
		//eventPool.add(Welcome2);
		stage.addActor(EventDisplay);
	
		
	}

	@Override
	public void render(float delta) {
			handleInputs();
			
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			stage.draw();
			
			timeLabel.setText("Day "+day);
			if(!(speed==0)) {
				//Gdx.app.log("TIME", System.currentTimeMillis()- timeSinceLastDay + "> "+ 1000/speed);
				if(System.currentTimeMillis()- timeSinceLastDay > 1000/speed) {
					timeSinceLastDay = System.currentTimeMillis();
					day++;
					
					for(SystemSlot SyS: galaxyScreen.slots) {
						if(SyS.system!=null) {
						for(Planet p: SyS.system.planets) {
							p.simulate();
						}
						}
					}
				}
			}	
		
	}
	
	public void setPlanets(ArrayList<Planet> planets) {
		for(Planet p: planetsOnScreen) {
			p.remove();
		}
		this.planetsOnScreen = planets;
		for(Planet p: planets) {
			stage.addActor(p);
		}
	}
	
	public void handleInputs() {
		
		for(Planet p: planetsOnScreen) {
			//Gdx.app.log("APP", p.getName());
		}
		
		//Event Logic
		if(profile.eventPool.size!=0 && !EventDisplay.isVisible()) {
			button.get(speed).active = false;
			button.get(speed).hover = false;
			speed=0;
			button.get(0).active = true;
			Gdx.app.log("SIZE", profile.eventPool.size+"");
			EventDisplay.setActiveEvent(profile.eventPool.pop());
			EventDisplay.setVisible(true);
		}
		
		if(EventDisplay.isVisible()) {
			EventDisplay.logic();
		}
		
		
		if(!EventDisplay.isVisible()) {
			if(Gdx.input.isKeyPressed(Keys.P)) {
				if(System.currentTimeMillis()- timeSince > 100) {
					Gdx.app.log("Doing down ", "suck it");
					timeSince = System.currentTimeMillis();
					profile.PrintTotal();
				}
			}	
		
		for(Planet p: planetsOnScreen) {
			if(p.getRect().contains(Gdx.input.getX(), 720-Gdx.input.getY())) {
				if(!p.isHovered()) {
					Gdx.app.log("Planet", "Planet "+p.getName()+" is a "+ p.getElement().toString()+" planet, with a heat of "+p.getHeat()+"°C and a survivabiliy of "+p.getSurvivabiliy()+"%");
					p.setHovered(true);
					selectedPlanet = p;
				}
				
				if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
					selectedPlanet = p;
					if(profile.QwM || p.equals(profile.Home)) {
						this.switchState(State.PlanetInfo);
					}
				}
			} else {
				p.setHovered(false);
			}
		}
		
		for(CustomButton cb: button) {
			if(cb.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY())) {
				for(CustomButton cb2: button) {
					if(!cb.equals(cb2)) {
						cb2.notPressed();
					}
				}
			}
		}
		
		for(CustomButton cb: ShopButton) {
			if(System.currentTimeMillis()- timeSince > 1000) {
			if(cb.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY())) {
				cb.notPressed();
			}
			}
		}
		
		if(profile.GE) {
			solarSystemPage.setVisible(true);
			solarSystemPage.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY());
		} else {
			solarSystemPage.setVisible(false);
		}
		
		if(profile.ST) {
			fleet.setVisible(true);
			fleet.logic();
		} else {
			fleet.setVisible(false);
		}
		}
		

		
		for(ResBoard cb: upDateUi) {
			switch(cb.res) {
			case Money:
				cb.setText(profile.Money+"");
				break;
			case Ores:
				cb.setText(profile.Ore+"");
				break;
			case Science:
				cb.setText(profile.Science+"");
				break;
			default:
				break;
			}
		}
		
		planetInfo.setPlanet(selectedPlanet);
		planetPage.updatePlanet(selectedPlanet);
		
		
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
		timeSince = System.currentTimeMillis();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void switchState(State state) {
		this.currentState = state;
		
		switch(state) {
		case PlanetInfo:
			g.setScreen(planetPage);
			planetPage.resume();
			break;
		case SolarSystem:
			g.setScreen(this);
			this.resume();
			break;
			
		case SienceInfo:
			g.setScreen(scienceScreen);
			scienceScreen.resume();
			break;
			
		case Galaxy:
			g.setScreen(galaxyScreen);
			galaxyScreen.resume();
			break;
			
		case ResourcesInfo:
			g.setScreen(resourcesScreen);
			resourcesScreen.resume();
			break;
		default:
			break;
		
		}
	}
	
	public void setSolarSystem(SolarSystem system) {
		currentSystem = system;
		selectedPlanet = system.planets.get(0);
		
		setPlanets(system.planets);
	}

}
