package com.wsc.photomaker.model;


public class KRuntimeModel {

	private static KRuntimeModel instance;

	private KRuntimeModel() {
	}

	public static KRuntimeModel getInstance() {
		if (instance == null) {
			instance = new KRuntimeModel();
		}
		return instance;
	}

	public void cleanUp() {
		instance = null;
	}
}