package com.nolevelcap.ld29;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.nolevelcap.assets.TextureHandler;
import com.nolevelcap.ld29.GameScreen.State;
import com.nolevelcap.space.Event;
import com.nolevelcap.space.ORE;
import com.nolevelcap.widgets.Backboard;
import com.nolevelcap.widgets.CustomButton;
import com.nolevelcap.widgets.CustomSmallLabel;
import com.nolevelcap.widgets.Image;
import com.nolevelcap.widgets.Label;
import com.nolevelcap.widgets.ScienceChoice;
import com.nolevelcap.widgets.SellButton;
import com.nolevelcap.widgets.SmallLabel;
import com.nolevelcap.widgets.Text;
import com.nolevelcap.widgets.TextButton;
import com.nolevelcap.widgets.WrappedText;

public class ScienceScreen implements Screen{
	
	GameScreen screen;
	
	Stage stage;
	
	PlayerProfile player;
	
	ArrayList<ScienceChoice> choices;
	TextButton back;
	TextButton buy;
	
	Backboard rightback;
	
	Text title, cost;
	WrappedText description;
	Image sample;
	
	long timeSince;
	
	ScienceChoice currentChoice;
	
	TextureHandler textureHandle;
	
	public ScienceScreen(PlayerProfile play, TextureHandler textures) {
		this.player = play;
		
		stage = new Stage(new FillViewport(1280, 720));
		
		
		BitmapFont font = new BitmapFont();
		
		this.textureHandle = textures;
		
		
		choices = new ArrayList<ScienceChoice>();
		
		rightback = new Backboard(textures.getTexture("window"), 1280-250, 0, 720, 250, 1);
		stage.addActor(rightback);
		
		ScienceChoice first = new ScienceChoice(new TextureRegion(textures.getTexture("science"), 0, 0, 64, 64), textures.getTexture("borders"), "Major Scale Mining", "Your science labs have discovered a way to mine to the core of the earth using carbon Nano tubes infused with solid hydrogen, making the drill heat independent. They are close to being able to put this in production but need some more top-secret research.", 5, true, 540-64, 720-128-25, 128, 128) {
			
			@Override
			public void onPress() {
				currentChoice = this;
				
				for(ScienceChoice choice: choices) {
					if(!choice.equals(this)) {
						choice.setActive(false);
					}
				}
				
				
			}
			@Override
			public void action() {
				if(player.Science-this.getCost()>=0) {
				player.changeScience(-this.getCost());
				
				choices.get(1).setAvailable(true);
				
				this.setBought(true);
				this.setActive(false);
				
				player.MsM = true;
				
				player.eventPool.add(new Event(player, textureHandle.getTexture("event_filler"), new String[] {"This can only get better."}, "Your scientists have discovered a new way to mine resources, unlocking a realm of new ores and possiblities! The game will automatically mine but you will have to sell your ores in the ore inventory menu which will get you science and money.", "JOURNEY TO THE CENTER OF THE EARTH") {
					
					@Override
					public void action(int selectionID) {
						// TODO Auto-generated method stub
						
					}
				});
				}
			}
		};
		
		stage.addActor(first);
		choices.add(first);
		
		ScienceChoice second = new ScienceChoice(new TextureRegion(textures.getTexture("science"), 64, 0, 64, 64), textures.getTexture("borders"), "Modern Day Colonies", "With the development of the Terra-Pod we can survive and inhabit any environment. If only we knew how to fly in space", 2000, false, 1080/4-64, 720-256-50, 128, 128) {
			
			@Override
			public void onPress() {
				currentChoice = this;
				
				for(ScienceChoice choice: choices) {
					if(!choice.equals(this)) {
						choice.setActive(false);
					}
				}
			}
			
			@Override
			public void action() {
				if(player.Science-this.getCost()>=0) {
					player.changeScience(-this.getCost());
					
					choices.get(2).setAvailable(true);
					
					this.setBought(true);
					this.setActive(false);
					player.MdC = true;
					
					player.eventPool.add(new Event(player, textureHandle.getTexture("event_filler"), new String[] {"Where will we settle next?"}, "Your scientists have discovered a new way to sustain a colony in almost impossible climates, they are calling their discovery the green pod. While they aren't revealing how the patented system works, a informant has told us that the pod just pumps pot and indie music into the atmosphere. You can increase the funding for the colony buy pressing plus or minus button, it is not visually represented but it does affect the success of the colony.", "A NEW DISCOVERY") {
						
						@Override
						public void action(int selectionID) {
							// TODO Auto-generated method stub
							
						}
					});
					}
			}
		};
		
		stage.addActor(second);
		choices.add(second);
		
		ScienceChoice third = new ScienceChoice(new TextureRegion(textures.getTexture("science"), 128, 0, 64, 64), textures.getTexture("borders"), "Quantum Wave Movement", "This technology will enable you to maneuverer yourself through the stars.", 10000, false, (3*1080)/4-64, 720-256-50, 128, 128) {
			
			@Override
			public void onPress() {
				currentChoice = this;
				
				for(ScienceChoice choice: choices) {
					if(!choice.equals(this)) {
						choice.setActive(false);
					}
				}
			}
			
			@Override
			public void action() {
				if(player.Science-this.getCost()>=0) {
					player.changeScience(-this.getCost());
					
					choices.get(3).setAvailable(true);
					
					this.setBought(true);
					this.setActive(false);
					player.QwM = true;
					
					player.eventPool.add(new Event(player, textureHandle.getTexture("event_filler"), new String[] {"Will we meet the Quagaars?"}, "Your scientists have discovered a way to covert light energy into solid matter. As the photon was slowed and observed your scientists noticed that the particles acted like a hydrogen particle and under close quarters. It could then be used to push a shuttle by releasing high concentration of particle-like photons which generate a reaction force. This technology will allow you to observe and establish colonies on other planets and discover new ores.", "WHAT IS THIS SORCERY?") {
						
						@Override
						public void action(int selectionID) {
							// TODO Auto-generated method stub
							
						}
					});
					
					}
			}
		};
		
		stage.addActor(third);
		choices.add(third);
		
		ScienceChoice forth = new ScienceChoice(new TextureRegion(textures.getTexture("science"), 192, 0, 64, 64), textures.getTexture("borders"), "Space Transport", "The new production facilities allow you to make a fleet to travel to new realms.", 50000, false, (1*1080)/4-64, 720-384-75, 128, 128) {
			
			@Override
			public void onPress() {
				currentChoice = this;
				
				for(ScienceChoice choice: choices) {
					if(!choice.equals(this)) {
						choice.setActive(false);
					}
				}
			}
			
			@Override
			public void action() {
				if(player.Science-this.getCost()>=0) {
					player.changeScience(-this.getCost());
					
					choices.get(4).setAvailable(true);
					
					this.setBought(true);
					this.setActive(false);
					player.ST = true;
					
					player.eventPool.add(new Event(player, textureHandle.getTexture("event_filler"), new String[] {"But where do we go?"}, "Your scientists have designed a way to make spaceship using the Terra-Pod design that can warp to further systems. ", "WE SHALL GO FURTHER.") {
						
						@Override
						public void action(int selectionID) {
							// TODO Auto-generated method stub
							
						}
					});
					
					}
			}
		};
		
		stage.addActor(forth);
		choices.add(forth);
		
		ScienceChoice fifth = new ScienceChoice(new TextureRegion(textures.getTexture("science"), 0, 64, 64, 64), textures.getTexture("borders"), "Galaxy Exploration", "The model-S fuel efficiency has improve you can now venture out to new space.", 20000, false, (3*1080)/4-64, 720-384-75, 128, 128) {
			
			@Override
			public void onPress() {
				currentChoice = this;
				
				for(ScienceChoice choice: choices) {
					if(!choice.equals(this)) {
						choice.setActive(false);
					}
				}
			}
			
			@Override
			public void action() {
				if(player.Science-this.getCost()>=0) {
					player.changeScience(-this.getCost());
					
					choices.get(4).setAvailable(true);
					
					this.setBought(true);
					this.setActive(false);
					player.GE = true;
					
					player.eventPool.add(new Event(player, textureHandle.getTexture("event_filler"), new String[] {"To Infinity and Beyond"}, "Your scientists have developed the sat-nav of the stars, this will complete the ability to venture out to other solar systems.", "WE SHALL GO EVEN FURTHER.") {
						
						@Override
						public void action(int selectionID) {
							// TODO Auto-generated method stub
							
						}
					});
					}
				
			}
		};
		
		stage.addActor(fifth);
		choices.add(fifth);
		
		back = new TextButton(textures.getTexture("controls"), font, "< Back", 1280-192-32, 10, 16, 192, 3) {
			
			@Override
			public void onPress() {
				screen.switchState(State.SolarSystem);
			}
		};
		stage.addActor(back);
		
		title = new Text("Title Test", font, 1280-250+10, 720 - 10, 1, Color.WHITE);
		stage.addActor(title);
		
		sample = new Image(new TextureRegion(textures.getTexture("science"), 0, 0, 64, 64), 1280-60-128, 720 - 60 - 128, 128, 128);
		stage.addActor(sample);
		
		description = new WrappedText("This is a really long bit of text. Can you do longer? aa aa aa aa aa aa aa aa aa aa aa aa aa aa", font, 1280-250+10, 720 - 80 - 128, 230, 1, Color.WHITE);
		stage.addActor(description);
		
		cost = new Text("Cost: 5550", font, 1280-250+20, 720 - 620, 1, Color.WHITE);
		stage.addActor(cost);
		
		buy = new TextButton(textures.getTexture("controls"), font, "BUY", 1280-250+110, 720 - 650, 16, 125, 3) {
			
			@Override
			public void onPress() {
				if(currentChoice !=null) {
					if(currentChoice.isAvailable()) {
					currentChoice.action();
					}
				}
			}
		};
		stage.addActor(buy);
		
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
		for(ScienceChoice cb: choices) {
			if(cb.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY())) {
			}
		}
		
		back.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY());
		
		if(currentChoice !=null) {
			title.setVisible(true);
			title.setText(currentChoice.getTiltleString());
			sample.setVisible(true);
			sample.setImage(currentChoice.getImage());
			description.setVisible(true);
			description.setText(currentChoice.getDescriptionString());
			cost.setVisible(true);
			cost.setText("COST: "+currentChoice.getCost());
			buy.setVisible(true);
			buy.checkIfPressed(Gdx.input.getX(), 720-Gdx.input.getY());
		} else {
			title.setVisible(false);
			sample.setVisible(false);
			description.setVisible(false);
			cost.setVisible(false);
			buy.setVisible(false);
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
		timeSince = System.currentTimeMillis();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
