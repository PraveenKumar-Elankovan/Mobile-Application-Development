package com.example.cs478_project3;

import java.io.InputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FullImageFragment extends Fragment {

	ImageView imageView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for the full image layout from full_image.xml
		View view = inflater.inflate(R.layout.full_image, container, false);
		imageView = (ImageView) view.findViewById(R.id.full_image_view);
		Bundle bundle = this.getArguments();
		String url = bundle.getString("exact_image");
		new DownloadImage(imageView).execute(url);
		return view;

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set the retain instance as true to maek sure the fragment is retained
		setRetainInstance(true);

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		// Extended AsyncTask for threading handling
		public DownloadImage(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		@Override
		protected Bitmap doInBackground(String... urls) {
			Bitmap mIcon11 = null;
			for (int i = 0; i < urls.length; i++) {
				String urldisplay = urls[i];
				// TO download images from URL string arrays
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
			// Set the bitmap image for all the pictures
			bmImage.setImageBitmap(result);
		}
	}

}
