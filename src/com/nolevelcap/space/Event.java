package com.nolevelcap.space;

import com.badlogic.gdx.graphics.Texture;
import com.nolevelcap.ld29.PlayerProfile;

public abstract class Event {
	
	private Texture eventTex;
	private String EventText;
	private String EventTitle;
	private PlayerProfile profile;
	private String[] options;
	
	public Event(PlayerProfile profile, Texture eventTexture, String[] options, String eventString, String EventTitle) {
		this.setEventTex(eventTexture);
		this.setButtonText(options);
		this.setEventText(eventString);
		this.setEventTitle(EventTitle);
		this.setProfile(profile);
	}
	
	public abstract void action(int selectionID);

	public Texture getEventTex() {
		return eventTex;
	}

	public void setEventTex(Texture eventTex) {
		this.eventTex = eventTex;
	}

	public String[] getButtonText() {
		return options;
	}

	public void setButtonText(String[] buttonText) {
		this.options = buttonText;
	}

	public String getEventText() {
		return EventText;
	}

	public void setEventText(String eventText) {
		EventText = eventText;
	}

	public PlayerProfile getProfile() {
		return profile;
	}

	public void setProfile(PlayerProfile profile) {
		this.profile = profile;
	}

	public String getEventTitle() {
		return EventTitle;
	}

	public void setEventTitle(String eventTitle) {
		EventTitle = eventTitle;
	}
}
