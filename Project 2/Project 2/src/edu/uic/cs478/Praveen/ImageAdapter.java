package edu.uic.cs478.Praveen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
	private static final int PADDING = 8;
	private static final int WIDTH = 350;
	private static final int HEIGHT = 350;
	private Context mContext;
	private Integer[] mThumbIds;
	public ImageAdapter(Context c, Integer[] mThumbIdsCars) 
	{ 
		mContext = c;
		this.mThumbIds = mThumbIdsCars;
	}
//	public ImageAdapter(GridLayoutActivity gridLayoutActivity) {
//		// TODO Auto-generated constructor stub
//	}
	public int getCount()
	{
		return mThumbIds.length;
	}
	public long getItemId(int position)
	{
		return mThumbIds[position];
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ImageView imageView = (ImageView) convertView;
		
		if (imageView == null) {
			//Design of the image view which contains the set of cars
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT)); 
			imageView.setPadding(PADDING, PADDING, PADDING, PADDING); 
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		}
		//Paints the image view with the image
		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
