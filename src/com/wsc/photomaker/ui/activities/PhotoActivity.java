package com.wsc.photomaker.ui.activities;

import java.io.File;
import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wsc.photomaker.R;
import com.wsc.photomaker.controller.FileManagerService;
import com.wsc.photomaker.controller.ResourceManager;
import com.wsc.photomaker.controller.ScreenManager;
import com.wsc.photomaker.exceptions.InternetMissingException;
import com.wsc.photomaker.ui.activities.base.BaseActivity;
import com.wsc.photomaker.utils.ConnectionUtils;
import com.wsc.photomaker.utils.DialogUtils;
import com.wsc.photomaker.utils.IntentAbilitiesUtils;

public class PhotoActivity extends BaseActivity {
	private static final int REQUEST_CODE_PHOTO = 1;
	private static final int REQUEST_CODE_EMAIL = 2;
	private String previewPath;

	private ImageView preview;
	private Button shootPhoto;
	private Button sentPhoto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_photo);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initComponents() {
		preview = (ImageView) findViewById(R.id.preview);
		shootPhoto = (Button) findViewById(R.id.button_shoot);
		sentPhoto = (Button) findViewById(R.id.button_sent);
	}

	@Override
	public void initListeners() {
		shootPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (IntentAbilitiesUtils.isIntentAvailable(PhotoActivity.this, MediaStore.ACTION_IMAGE_CAPTURE)) {
					boolean isExistProblems = false;
					Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					try {
						File file = FileManagerService.getInstance().createImageFile();
						previewPath = file.getAbsolutePath();
						takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
					} catch (IOException e) {
						DialogUtils.showExceptionAsDialog(PhotoActivity.this, e);
						previewPath = null;
						isExistProblems = true;
					}
					if (!isExistProblems)
						ScreenManager.getInstance().forwardForResult(PhotoActivity.this, takePictureIntent, REQUEST_CODE_PHOTO);
				} else {
					DialogUtils.showExceptionAsDialog(PhotoActivity.this, ResourceManager.getStringValue(R.string.exception_missing_camera));
				}
			}
		});

		sentPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if (ConnectionUtils.isInternetConnection()) {

						Intent emailIntent = new Intent(Intent.ACTION_SEND);
						emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "wallkerby@tut.by" });
						emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
						emailIntent.putExtra(Intent.EXTRA_TEXT, "Message body");
						emailIntent.setType("text/plain");

						ScreenManager.getInstance().forwardForResult(PhotoActivity.this, emailIntent, REQUEST_CODE_EMAIL);
					}
				} catch (InternetMissingException e) {
					DialogUtils.showExceptionAsDialog(PhotoActivity.this, e);
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_PHOTO) {
			if (previewPath != null) {
				setPreview();
				FileManagerService.getInstance().updateGallery(PhotoActivity.this, previewPath);
				previewPath = null;
			}
		}

		if (requestCode == REQUEST_CODE_EMAIL) {
			Toast.makeText(this, "Письмо отправлено", Toast.LENGTH_SHORT).show();
		}
	}

	private void setPreview() {
		int targetW = preview.getWidth();
		int targetH = preview.getHeight();

		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(previewPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW / targetW, photoH / targetH);
		}

		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(previewPath, bmOptions);
		preview.setImageBitmap(bitmap);
	}
}