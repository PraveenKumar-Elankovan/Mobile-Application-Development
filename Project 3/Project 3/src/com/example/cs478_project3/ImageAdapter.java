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
	
	
	public ImageAdapter(Context c,String[] m) {
		mContext = c;
		mThumbNails = m;
	}
	
	public ImageAdapter() {
		
	}

	@Override
	public int getCount() {
		return mThumbNails.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumbNails[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	public String status(){
		if(ImageAdapter.status == AsyncTask.Status.RUNNING){
		return "running";
		}else{
			return null;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		'Creating image adapters to fit all the images in a grid view'
		ImageView imageView;
		if (convertView == null){ // if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new LayoutParams(500, 500)); //sets the height and width for the View
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); //declares that images should be cropped toward the center (if necessary).
            imageView.setPadding(8, 8, 8, 8); //defines the padding for all sides. 
		}else{
			imageView = (ImageView) convertView;
		}
		status = new DownloadImage(imageView).execute(mThumbNails[position]).getStatus();
		return imageView;
	}
	
	
	private class DownloadImage extends AsyncTask<String , Void, Bitmap>{
		ImageView bmImage;
		
		public DownloadImage(ImageView bmImage){
			this.bmImage = bmImage;
		}

		@Override
		protected Bitmap doInBackground(String... urls) {
			status = AsyncTask.Status.RUNNING;
			Bitmap mIcon11 = null;
			for(int i =0;i<urls.length;i++){
			String urldisplay = urls[i];
//			to download the image after click
			try{
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			}catch(Exception e){
				Log.e("Error",e.getMessage());
				e.printStackTrace();
			}
			}
			return mIcon11;
		}
		
		protected void onPostExecute(Bitmap result){
			
			bmImage.setImageBitmap(result);
			status = AsyncTask.Status.FINISHED;
		}
		
	}

}
