package com.wsc.photomaker.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.wsc.photomaker.R;
import com.wsc.photomaker.app.KApplication;
import com.wsc.photomaker.controller.ResourceManager;

public class DialogUtils {

	public static void createOkDialog(Context context, String title, String message, final Runnable ok) {
		final Dialog dialog = new Dialog(context);
		dialog.setCancelable(false);
		dialog.setContentView(R.layout.dialog_ok);
		dialog.setTitle(title);

		((TextView) dialog.findViewById(R.id.message)).setText(message);

		dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (ok != null) {
					ok.run();
				}
				dialog.cancel();
			}
		});

		dialog.show();
	}

	public static void showExceptionAsDialog(final Context context, final Exception e) {
		KApplication.handler.post(new Runnable() {
			public void run() {
				DialogUtils.createOkDialog(context, ResourceManager.getStringValue(R.string.dialog_title_error), e.getMessage(), null);
			}
		});
	}

	public static void showExceptionAsDialog(final Context context, final String message) {
		KApplication.handler.post(new Runnable() {
			public void run() {
				DialogUtils.createOkDialog(context, ResourceManager.getStringValue(R.string.dialog_title_error), message, null);
			}
		});
	}

	public static void showExceptionAsDialog(final Context context, final int message) {
		KApplication.handler.post(new Runnable() {
			public void run() {
				DialogUtils.createOkDialog(context, ResourceManager.getStringValue(R.string.dialog_title_error), ResourceManager.getStringValue(message), null);
			}
		});
	}
}
