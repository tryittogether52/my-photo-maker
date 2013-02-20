package com.wsc.photomaker.ui.activities.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

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

//	public void showExceptionAsDialog(final Exception e) {
//		KApplication.handler.post(new Runnable() {
//			public void run() {
//				DialogUtils.createOkDialog(BaseActivity.this, ResourceManager.getStringValue(R.string.dialog_title_error), e.getMessage(), null);
//			}
//		});
//	}
//
//	public void showExceptionAsDialog(final String message) {
//		KApplication.handler.post(new Runnable() {
//			public void run() {
//				DialogUtils.createOkDialog(BaseActivity.this, ResourceManager.getStringValue(R.string.dialog_title_error), message, null);
//			}
//		});
//	}
}
