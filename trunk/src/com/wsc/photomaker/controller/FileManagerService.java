package com.wsc.photomaker.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.wsc.photomaker.R;
import com.wsc.photomaker.app.KApplication;
import com.wsc.photomaker.utils.DialogUtils;

@SuppressLint("SimpleDateFormat")
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
		String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String previewName = JPEG_FILE_PREFIX + date;
		File gallery = getAlbumDir();
		// File result = File.createTempFile(previewName, JPEG_FILE_SUFFIX,
		// gallery);
		File result;
		do {
			result = new File(gallery, previewName + JPEG_FILE_SUFFIX);
		} while (!result.createNewFile());
		return result;
	}

	public void updateGallery(Context context, String photoPath) {
		Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		File f = new File(photoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		context.sendBroadcast(mediaScanIntent);
	}
}
