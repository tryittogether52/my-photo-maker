package com.wsc.photomaker.controller;

import android.app.Activity;
import android.content.Intent;

@SuppressWarnings("rawtypes")
public class ScreenManager extends AbstractManager {

	// private List<Intent> previousIntents;
	//
	// public Activity prevActivity = null;

	private static ScreenManager instance;

	public static ScreenManager getInstance() {
		if (instance == null) {
			instance = new ScreenManager();
		}
		return instance;
	}

	public void forward(Activity current, Class nextActivityClass) {
		Intent intent = new Intent(current, nextActivityClass);
		// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		forward(current, intent);
	}

	public void forward(Activity current, Intent intent) {
		// addPrevIntent(current.getIntent());
		current.startActivity(intent);
		// current.finish();
	}

	// public void backward(Activity current, Intent intent) {
	// current.startActivity(intent);
	// prevActivity = current;
	// }

	public void forwardForResult(Activity current, Class nextActivityClass, int requestCode) {
		Intent intent = new Intent(current, nextActivityClass);
		current.startActivityForResult(intent, requestCode);
	}
	
	public void forwardForResult(Activity current, Intent intent, int requestCode) {
		current.startActivityForResult(intent, requestCode);
	}

	// public void cleanPreviousActivity() {
	//
	// if (prevActivity != null) {
	// prevActivity.finish();
	// prevActivity = null;
	// }
	// }
	//
	// public void addPrevIntent(Intent intent) {
	// if (intent != null) {
	// previousIntents.add(intent);
	// }
	// }
	//
	// public Intent popPrevIntent() {
	// Intent intent = null;
	//
	// if (previousIntents.size() > 0) {
	// intent = previousIntents.remove(previousIntents.size() - 1);
	// }
	//
	// return intent;
	// }
	//
	// public void back(final Activity activity) {
	// final Intent backActivity = ScreenManager.getInstance().popPrevIntent();
	// if (backActivity == null) {
	// return;
	// }
	// if (backActivity != null) {
	// activity.startActivity(backActivity);
	// prevActivity = activity;
	// }
	// }
	//
	// public void clearPrevIntentQueue() {
	// previousIntents.clear();
	// }

	@Override
	public void onCreationComplete() {
		// previousIntents = new ArrayList<Intent>();

	}

	@Override
	public void onRefresh() {
	}

	@Override
	public void onDestroy() {
		// previousIntents.clear();
	}
}
