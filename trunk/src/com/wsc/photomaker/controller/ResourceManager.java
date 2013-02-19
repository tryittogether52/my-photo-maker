package com.wsc.photomaker.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.wsc.photomaker.app.KApplication;

public class ResourceManager extends AbstractManager {
	private static ResourceManager instance;

	public static ResourceManager getInstance() {
		if (instance == null) {
			instance = new ResourceManager();
		}
		return instance;
	}

	public static String getStringValue(int id) {
		return KApplication.getContext().getResources().getString(id);
	}

	public static String[] getStringArrayValue(int id) {
		return KApplication.getContext().getResources().getStringArray(id);
	}

	public static int getColorValue(int id) {
		return KApplication.getContext().getResources().getColor(id);
	}

	public static Drawable getDrawable(int id) {
		return KApplication.getContext().getResources().getDrawable(id);
	}

	public static Bitmap getBitmap(int id) {
		return BitmapFactory.decodeResource(KApplication.getContext().getResources(), id);
	}

	public static float getDimen(int id) {
		return KApplication.getContext().getResources().getDimension(id);
	}

	@Override
	public void onCreationComplete() {

	}

	@Override
	public void onRefresh() {

	}

	@Override
	public void onDestroy() {
	}
}
