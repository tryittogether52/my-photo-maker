package com.wsc.photomaker.ui.activities.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.wsc.photomaker.R;
import com.wsc.photomaker.app.KApplication;
import com.wsc.photomaker.app.KFrontManager;
import com.wsc.photomaker.controller.ResourceManager;
import com.wsc.photomaker.utils.DialogUtils;

public abstract class BaseActivity extends Activity {
	public KFrontManager controller = KFrontManager.getInstance();

	private static Handler handler = new Handler();

	protected Handler getHander() {
		return handler;
	}

	public void showExceptionAsDialog(final Exception e) {
		e.printStackTrace();
		KApplication.handler.post(new Runnable() {
			public void run() {
				DialogUtils.createOkDialog(BaseActivity.this, ResourceManager.getStringValue(R.string.dialog_title_error), e.getMessage(), null);
			}
		});
	}

	public void initComponents() {
	}

	public void initValues() {

	}

	public void initListeners() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initComponents();
		initValues();
		initListeners();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// this.menu = menu;
	// // getMenuInflater().inflate(R.menu.main, menu);
	// // menu.findItem(R.id.settings_menu_item).setIntent(new Intent(this,
	// // MainPreferencesActivity.class));
	//
	// return true;
	// }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		// if (item.equals(menu.findItem(R.id.settings_menu_item))) {
		// startActivity(item.getIntent());
		// } else {
		// // HPModel.getInstance().setIsNeeded(false);
		// }

		return true;
	}
}
