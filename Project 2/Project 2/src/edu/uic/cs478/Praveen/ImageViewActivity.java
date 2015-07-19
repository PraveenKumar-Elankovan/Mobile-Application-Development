package edu.uic.cs478.Praveen;





import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import edu.uic.cs478.Praveen.GridLayoutActivity;
//Called when an image is single clicked
public class ImageViewActivity extends Activity 
{
	public void onCreate(Bundle savedInstanceState) 
	{ 
		
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		ImageView imageView = new ImageView(getApplicationContext());
		setContentView(R.layout.full_image);
		imageView = (ImageView) findViewById(R.id.full_image_view);
//		GridLayoutActivity gridObject=new GridLayoutActivity();
//		Fits the image for full screen
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//		Fits the image for a centred view
//		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setImageResource(intent.getIntExtra(GridLayoutActivity.EXTRA_RES_ID, 0));//Gets the ID  of the image from the intent that was passed On
//		imageView.setImageResource(gridObject.mThumbIdsCars[position]);
//	setContentView(imageView);

	}
}
