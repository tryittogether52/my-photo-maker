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

import com.wsc.photomaker.R;
import com.wsc.photomaker.controller.FileManagerService;
import com.wsc.photomaker.controller.ResourceManager;
import com.wsc.photomaker.ui.activities.base.BaseActivity;
import com.wsc.photomaker.utils.DialogUtils;
import com.wsc.photomaker.utils.IntentAbilitiesUtils;

public class PhotoActivity extends BaseActivity {
	private static final int REQUEST_CODE = 1;
	private String previewPath;

	private ImageView preview;
	private Button shootPhoto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_photo);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initComponents() {
		preview = (ImageView) findViewById(R.id.preview);
		shootPhoto = (Button) findViewById(R.id.button_shoot);
	}

	@Override
	public void initValues() {

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
						startActivityForResult(takePictureIntent, REQUEST_CODE);
				} else {
					DialogUtils.showExceptionAsDialog(PhotoActivity.this, ResourceManager.getStringValue(R.string.exception_missing_camera));
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			if (previewPath != null) {
				setPreview();
				FileManagerService.getInstance().updateGallery(PhotoActivity.this, previewPath);
				previewPath = null;
			}
		}
	}

	private void setPreview() {

		/* There isn't enough memory to open up more than a couple camera photos */
		/* So pre-scale the target bitmap into which the file is decoded */
		int targetW = 300;
		int targetH = 300;

		/* Get the size of the image */
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(previewPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		/* Figure out which way needs to be reduced less */
		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW / targetW, photoH / targetH);
		}

		/* Set bitmap options to scale the image decode target */
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(previewPath, bmOptions);

		preview.setImageBitmap(bitmap);
	}
}