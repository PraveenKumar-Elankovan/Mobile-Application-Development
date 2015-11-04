package com.example.cs478_project3;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	public String[] mThumbNails;
	public static Status status;

	// Constructor for imageAdapter instance
	public ImageAdapter(Context c, String[] m) {
		mContext = c;
		mThumbNails = m;
	}

	// Default constructor
	public ImageAdapter() {
	}

	// To return the count of images to be displayed in gridview control
	@Override
	public int getCount() {
		return mThumbNails.length;
	}

	// Returns the present object that is clicked
	@Override
	public Object getItem(int position) {
		return mThumbNails[position];
	}

	// returns the objectId of the image displayed
	@Override
	public long getItemId(int position) {
		return 0;
	}

	// Helper method to return the present status of AsyncTask
	public String status() {
		if (ImageAdapter.status == AsyncTask.Status.RUNNING) {
			return "running";
		} else {
			return null;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Creating image adapters to fit all the images in a grid view'
		ImageView imageView;
		if (convertView == null) {
			// if it's not recycled, initialize someattributes
			imageView = new ImageView(mContext);
			// Sets the height and width of the view
			imageView.setLayoutParams(new LayoutParams(500, 500));
			// Declares that images should be cropped towards the center
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			// defines the padding for all sides.
			imageView.setPadding(8, 8, 8, 8);

		} else {
			imageView = (ImageView) convertView;
		}
		status = new DownloadImage(imageView).execute(mThumbNails[position])
				.getStatus();
		return imageView;
	}

	// Class which inherits AsyncTask to perform threading operations in a
	// designated thread
	private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImage(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		// Method to download the images from background
		@Override
		protected Bitmap doInBackground(String... urls) {
			// Status Update
			status = AsyncTask.Status.RUNNING;
			Bitmap mIcon11 = null;
			for (int i = 0; i < urls.length; i++) {
				String urldisplay = urls[i];
				// to download the image after click
				try {
					InputStream in = new java.net.URL(urldisplay).openStream();
					mIcon11 = BitmapFactory.decodeStream(in);
				} catch (Exception e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			// To set the bit map image and update the status
			bmImage.setImageBitmap(result);
			status = AsyncTask.Status.FINISHED;
		}

	}

}
