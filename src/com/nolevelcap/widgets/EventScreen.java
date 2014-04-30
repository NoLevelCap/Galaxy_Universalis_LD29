package com.nolevelcap.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nolevelcap.space.Event;

public class EventScreen extends Actor{
	private Event activeEvent;
	
	private Texture window;
	private BitmapFont font;
	private TextureRegion[] Sections;
	private int selectionID = -1;
	private Rectangle[] SelectBounds;
	
	private long lastClick;
	public EventScreen(Texture window, BitmapFont font, Event event) {
		this.setWindow(window);
		String test = " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ut auctor dui. Aliquam purus eros, ornare in dui vitae, commodo bibendum libero. Sed tellus est, ultrices vel magna vel, rutrum rutrum risus. Nam sapien nisl, tincidunt non nunc ac, sollicitudin porta purus. Nullam fermentum pharetra nunc et tempor. Sed libero nibh, lacinia non dictum eu, fringilla et leo. Curabitur aliquet nec nisl eget pharetra. Praesent et nisl eu nibh malesuada suscipit. Aliquam hendrerit sollicitudin nunc, eget molestie sapien lobortis a. Nulla facilisi. Suspendisse non dictum lectus. Praesent iaculis posuere tincidunt. Sed scelerisque nisl vitae diam congue vehicula sit amet et justo. Phasellus vestibulum adipiscing nibh in placerat. Morbi dolor augue, accumsan suscipit elit eget.";
		this.setFont(font);
		this.setBounds(320, 90, 640, 540);
		this.initSections(window);
		if(event!=null) {
		this.activeEvent.setEventText(test);
		this.setActiveEvent(event);
		this.initRectangles();
		} else {
			this.setVisible(false);
		}
		
		this.lastClick = System.currentTimeMillis();
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
	
	private void initRectangles() {
		SelectBounds = new Rectangle[activeEvent.getButtonText().length+1];
		for(int i = 0; i<activeEvent.getButtonText().length; i++) {
			SelectBounds[i] = new Rectangle(getX()+10, getY()+200-font.getWrappedBounds(activeEvent.getEventText(), 620).height-(i*15)-15-i*(5)-10, font.getBounds(activeEvent.getButtonText()[i]).width, font.getBounds(activeEvent.getButtonText()[i]).height);
		}
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.draw(this.Sections[0], getX(), getY()+getHeight()-16, 16, 16);
		batch.draw(this.Sections[1], getX()+16, getY()+getHeight()-16, getWidth()-2*(16), 16);
		batch.draw(this.Sections[2], getX()+getWidth()-16, getY()+getHeight()-16, 16, 16);
		
		
		batch.draw(this.Sections[3], getX(), getY()+16, 16, getHeight()-2*(16));
		batch.draw(this.Sections[4], getX()+16, getY()+16, getWidth()-2*(16), getHeight()-2*(16));
		batch.draw(this.Sections[5], getX()+getWidth()-16, getY()+16, 16, getHeight()-2*(16));
		
		batch.draw(this.Sections[6], getX(), getY(), 16, 16);
		batch.draw(this.Sections[7], getX()+16, getY(), getWidth()-2*(16), 16);
		batch.draw(this.Sections[8], getX()+getWidth()-16, getY(), 16, 16);
		
		font.setScale(2);
		font.draw(batch, activeEvent.getEventTitle(), getX()+20, getY()+520);
		font.setScale(1);
	
		font.drawWrapped(batch, activeEvent.getEventText(), getX()+10, getY()+190, 620);
		for(int i = 0; i<activeEvent.getButtonText().length; i++) {
			if(i == selectionID) {
				font.setColor(Color.GREEN);
			} else {
				font.setColor(Color.CYAN);
			}
			font.draw(batch, activeEvent.getButtonText()[i], getX()+10, getY()+200-font.getWrappedBounds(activeEvent.getEventText(), 620).height-(i*15)-15-i*(5));
			font.setColor(Color.WHITE);
		}
		batch.draw(activeEvent.getEventTex(), getX()+4, getY()+200, 632, 280);
	}
	
	public void logic() {
		checkIfPressed(Gdx.input.getX(), 720 - Gdx.input.getY());
	}
	
	public void checkIfPressed(int x, int y) {
		for(int i = 0; i<activeEvent.getButtonText().length; i++) {
		if(SelectBounds[i].contains(x, y)) {
			selectionID = i;
			if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
				activeEvent.action(selectionID);
				this.setVisible(false);
			}
		} else {
			if(selectionID==i) {
			selectionID = -1;
			}
		}
		}
	}
	

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public TextureRegion[] getSections() {
		return Sections;
	}

	public void setSections(TextureRegion[] sections) {
		Sections = sections;
	}

	public Event getActiveEvent() {
		return activeEvent;
	}

	public void setActiveEvent(Event activeEvent) {
		this.activeEvent = activeEvent;
		this.initRectangles();
	}

	public Texture getWindow() {
		return window;
	}

	public void setWindow(Texture window) {
		this.window = window;
	}
}
