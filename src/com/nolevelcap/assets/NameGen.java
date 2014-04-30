package com.nolevelcap.assets;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class NameGen {
	

	public NameGen() {
	}
	
	public String genName() {
		BufferedReader reader = new BufferedReader(Gdx.files.internal("nameGen.txt").reader());
		
		String line = "";
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] names = line.split(" ");
		
		
		Random r = new Random();
		String name = names[r.nextInt(names.length)];
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		
		
		return name;
		
	}
}
