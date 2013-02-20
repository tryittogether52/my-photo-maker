package com.wsc.photomaker.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.wsc.photomaker.R;
import com.wsc.photomaker.app.KApplication;
import com.wsc.photomaker.utils.DialogUtils;

public class FileManagerService extends AbstractManager {

	private static FileManagerService instance;

	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";

	public static FileManagerService getInstance() {
		if (instance == null) {
			instance = new FileManagerService();
		}
		return instance;
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

	public File getAlbumStorageDir(String albumName) {
		return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
	}

	private File getAlbumDir() {
		File storageDir = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			storageDir = getAlbumStorageDir("PhotoMaker");
			if (storageDir != null) {
				if (!storageDir.mkdirs()) {
					if (!storageDir.exists()) {
						Log.d("CameraSample", "failed to create directory");
						return null;
					}
				}
			}
		} else {
			DialogUtils.showExceptionAsDialog(KApplication.getContext(), R.string.exception_cd);
		}

		return storageDir;
	}

	public File createImageFile() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
		File albumF = getAlbumDir();
		File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
		return imageF;
	}

	public void updateGallery(Context context, String photoPath) {
		Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		File f = new File(photoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		context.sendBroadcast(mediaScanIntent);
	}
}
