package com.nolevelcap.assets;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class TextureHandler {
	
	private HashMap<String, Texture> textures;
	
	public TextureHandler() {
		textures = new HashMap<String, Texture>();
	}
	
	public void addTexture(String key, Texture value) {
		textures.put(key, value);
	}
	
	public void addTexture(String key, FileHandle file) {
		Texture value = new Texture(file);
		textures.put(key, value);
	}
	
	public Texture getTexture(String key) {
		if(textures.containsKey(key)) {
			return textures.get(key);
		} else {
			new Exception();
		}
		return null;
	}
	
	public void clearTextures() {
		textures.clear();
	}
}
