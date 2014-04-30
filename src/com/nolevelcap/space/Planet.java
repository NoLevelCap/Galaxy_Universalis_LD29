package com.nolevelcap.space;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nolevelcap.assets.TextureHandler;
import com.nolevelcap.ld29.PlayerProfile;


public class Planet extends Actor {
	private boolean isHovered, currentColony, isActive;
	public TextureHandler textures;
	public TextureRegion region;
	private Rectangle rect;
	
	private int Heat, Survivabiliy;
	
	private Element element;
	public ArrayList<City> cities = new ArrayList<City>();
	public Colony colony;
	public ORE[] ores;
	protected PlayerProfile player;
	
	public Planet(PlayerProfile player, TextureHandler textures, Element e, int texX, int x, int y, int width, int height, int scale, String name) {
		this.textures = textures;
		this.setName(name);
		this.region = new TextureRegion(textures.getTexture(e.idTag), texX*36, 0, 36, 36);
		this.setScale(scale);
		this.setBounds(x, y, width, height);
		this.setRect(new Rectangle(x, y, width*scale, height*scale));
		this.element = e;
		this.setHeat(e.genHeat(e));
		this.setSurvivabiliy(e.genSurvivability(e));
		this.currentColony = false;
		this.ores = ORE.Argentite.RandomOres(10);
		this.player = player;
		
	}
	
	public Planet(Planet p, int x, int y) {
		this.textures = p.textures;
		this.setName(p.getName());
		this.region = p.region;
		this.setScale(p.getScaleX());
		this.setBounds(x, y, p.getWidth(), p.getHeight());
		this.rect = p.rect;
		this.element = p.element;
		this.setHeat(p.Heat);
		this.setSurvivabiliy(p.Survivabiliy);
		this.currentColony = false;
		this.ores = p.ores;
		this.player = p.player;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(region, getX(), getY(), getWidth()*getScaleX(), getHeight()*getScaleY());
		
		if(isHovered || isActive) {
			batch.draw(textures.getTexture("borders"), getX(), getY(), getWidth()*getScaleX(), getHeight()*getScaleY());
		}
	}
	
	public void simulate() {
		for(City c: cities) {
			c.simulate();
			Gdx.app.log("City", c.printData());
		}
		
		if(colony!=null && player.MdC) {
		colony.simulate();
		}
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public boolean isHovered() {
		return isHovered;
	}

	public void setHovered(boolean isHovered) {
		this.isHovered = isHovered;
	}

	public int getHeat() {
		return Heat;
	}

	public void setHeat(int heat) {
		Heat = heat;
	}

	public int getSurvivabiliy() {
		return Survivabiliy;
	}

	public void setSurvivabiliy(int survivabiliy) {
		Survivabiliy = survivabiliy;
	}

	public Element getElement() {
		return element;
	}
	
	public void addCity(City city) {
		cities.add(city);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void startColony() {
		Gdx.app.log("Colony", "Trying to log");
		
		if(!currentColony && cities.size()<10) {
		colony = new Colony(this);
		currentColony = true;
		}
	}
	
	public void successColony(Colony col) {
		Gdx.app.log("CITIES", cities.size()+"");
		
		City c = new City(player, this, col);
		currentColony = false;
		cities.add(c);
		
		Gdx.app.log("CITIES", cities.size()+"");
		
	}
	
	public void failureColony(Colony col) {
		currentColony = false;
	}
	
	
}
