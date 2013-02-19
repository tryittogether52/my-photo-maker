package com.wsc.photomaker.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.wsc.photomaker.model.KRuntimeModel;

public class KApplication extends Application {

	private static Context context;
	private static KApplication instance;

	public static Handler handler = new Handler();

	@Override
	public void onCreate() {

		super.onCreate();
		KApplication.context = getApplicationContext();
		KApplication.instance = this;

		KFrontManager.getInstance().onCreationComplete();
	}

	@Override
	public void onTerminate() {
		finilizeAll();
		super.onTerminate();
	}

	public static Context getContext() {
		return context;
	}

	public static KApplication getInstance() {
		return instance;
	}

	public void closeApplication() {
		finilizeAll();
	}

	private void finilizeAll() {
		KFrontManager.getInstance().onDestroy();
	}

	public void logout() {
		KRuntimeModel.getInstance().cleanUp();
	}
}
