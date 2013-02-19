package com.wsc.photomaker.model;

public class KModel {

	private static KModel instance;

	private KModel() {
	}

	public static KModel getInstance() {
		if (instance == null) {
			instance = new KModel();
		}
		return instance;
	}

	public void cleanUp() {
		instance = null;
	}

}