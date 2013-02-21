package com.wsc.photomaker.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wsc.photomaker.R;
import com.wsc.photomaker.app.KApplication;
import com.wsc.photomaker.controller.ResourceManager;
import com.wsc.photomaker.exceptions.InternetMissingException;

public class ConnectionUtils {

	public static boolean isInternetConnection() throws InternetMissingException {
		ConnectivityManager cm = (ConnectivityManager) KApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}

		throw new InternetMissingException(ResourceManager.getStringValue(R.string.exception_missing_internet));
	}
}
