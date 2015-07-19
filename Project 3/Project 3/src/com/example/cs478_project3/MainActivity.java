package com.example.cs478_project3;



import com.example.cs478_project3.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
	
	TextView textview1;
	TextView textview2;
	TextView textview3;
	Button button;
	FrameLayout frameLayout;
	GridFragment mGridFragment1 = new GridFragment();
	GridFragment mGridFragment2 = new GridFragment();
	GridFragment mGridFragment3 = new GridFragment();
//	String array definition containing URLs for the images to be displayed on clicking the first section 
	private String[] USA = new String[]{
			"http://travelchannel.sndimg.com/content/dam/images/travel/fullset/2014/12/2/top-10-family-spring-break-vacations-atlantis.jpg.rend.tccom.616.462.jpeg",
			"http://travelchannel.sndimg.com/content/dam/images/travel/fullset/2014/12/2/top-10-family-spring-break-vacations-snowbird-lodge.jpg.rend.tccom.616.462.jpeg",
			"http://travelchannel.sndimg.com/content/dam/images/travel/fullset/2013/02/15/7c/top-10-family-vacation-spots-tanque-verde-ranch-2.rend.tccom.616.462.jpeg",
			"http://travelchannel.sndimg.com/content/dam/images/travel/fullset/2014/12/2/top-10-family-spring-break-vacations-hilton-waikoloa-village.jpg.rend.tccom.616.462.jpeg",
			"http://travelchannel.sndimg.com/content/dam/images/travel/fullset/2014/08/14/ff/back-to-school-travel-kingsmill-resort.rend.tccom.616.462.jpeg",
			"http://travelchannel.sndimg.com/content/dam/images/travel/fullset/2014/12/3/top-10-family-vacations-circus-circus-las-vegas.jpg.rend.tccom.616.462.jpeg",
			"http://travelchannel.sndimg.com/content/dam/images/travel/fullset/2014/12/2/top-10-family-spring-break-vacations-treesort.jpg.rend.tccom.616.462.jpeg",
			"http://travelchannel.sndimg.com/content/dam/images/travel/fullset/2013/02/14/b8/top-10-family-vacation-spots-la-costa.rend.tccom.616.462.jpeg",
			"http://travelchannel.sndimg.com/content/dam/images/travel/fullset/2013/02/14/15/top-10-family-vacation-spots-animal-kingdom.rend.tccom.616.462.jpeg",
			"http://www.wickedgoodtraveltips.com/wp-content/uploads/2013/10/Great-Smoky-Mountains-National-Park-Tennessee.jpg"
			};
	
//	String array definition containing URLs for the images to be displayed on clicking the second section
	private String[] India = new String[]{
			"http://siliconindia.com:81/travelcity/images/special_images/ga69Z4z2rf5mAdn.jpeg",
			"http://www.formertourist.com/wp-content/uploads/2013/09/honeymoon-in-kerala.jpg",
			"http://1p6ep31f32pvjrml246jyq31.wpengine.netdna-cdn.com/wp-content/uploads/2011/03/Top-10-places-to-visit-in-India-img-041.jpg",
			"http://i.dailymail.co.uk/i/pix/2014/02/25/article-2567704-1BD3FD1000000578-330_634x396.jpg",
			"https://s1.yimg.com/bt/api/res/1.2/3oybAH8UCBHnsW_VkR_upA--/YXBwaWQ9eW5ld3M7Zmk9aW5zZXQ7aD0zNjA7aWw9cGxhbmU7cT03NTt3PTYzMA--/http://l.yimg.com/os/624/2013/01/29/4Sweden-RP-JPG_075852.jpg",
			"http://www.wefindyougo.com/wp-content/uploads/2013/10/Top-10-Best-Places-for-tourist-in-India.jpg",
			"http://ordiate.com/wp-content/uploads/2013/03/Tunnel-No.-103-Shimla.jpeg",
			"http://traveljee.com/wp-content/uploads/2014/03/shillong_lake.jpg",
			"http://static.topyaps.com/wp-content/uploads/2012/07/Andaman-and-Nicobar.jpeg",
			"http://www.transindiatravels.com/wp-content/uploads/howrah-bridge.jpg"
			};
	
//	String array definition containing URLs for the images to be displayed on clicking the third section
	private String[] China = new String[]{
			"http://www.izi-travel.com/wp-content/uploads/2013/03/China1.jpg",
			"http://www.mawista.com/blog/wp-content/uploads/2013/11/china.jpg",
			"http://greatinspire.com/wp-content/uploads/2013/06/rice-worker-china.jpg",
			"http://i.dailymail.co.uk/i/pix/2014/04/07/article-2599027-1CE91BFD00000578-307_964x645.jpg",
			"http://images.china.cn/attachement/jpg/site1007/20131002/00114320db4113b620fc45.jpg",
			"http://img.gmw.cn/images/attachement/jpg/site2/20131018/eca86bd9e38a13cb3ef91f.jpg",
			"http://usa.chinadaily.com.cn/travel/attachement/png/site1/20130710/30f9edf0b4801347082d23.png",
			"http://easyscienceforkids.com/wp-content/uploads/2014/02/Easy-Science-Kids-Facts-about-the-Top-10-Hottest-Places-on-Earth-the-Flaming-Mountains-in-China.jpg",
			"http://img.amerikanki.com/wp-content/uploads/2014/08/Most_Beautiful_Places_in_China.jpg",
			"http://usa.chinadaily.com.cn/travel/attachement/jpg/site1/20130726/002564bc654b135c1d740c.jpg"
			};

	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Binding from the textviews from the XML file
		textview1 = (TextView)findViewById(R.id.usa);
		textview2 = (TextView)findViewById(R.id.india);
		textview3 = (TextView)findViewById(R.id.china);
		button = (Button)findViewById(R.id.button);
//		binding the fragments through frameLayout
		frameLayout = (FrameLayout)findViewById(R.id.change_frag); 
		textview1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
//				On clicking the elements in the first fragment, secong fragments to be initialised
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				bundle.putStringArray("images", USA);
				if(mGridFragment1.getArguments()!= null){
					mGridFragment1.getArguments().putAll(bundle);
					
				}else{
					mGridFragment1.setArguments(bundle);
				}
				
				
				ft.replace(R.id.change_frag,mGridFragment1,"GRID_VIEW_FRAG");
				ft.commit();
			}
		});
		
	
		
		textview2.setOnClickListener(new View.OnClickListener() {
		
			
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				bundle.putStringArray("images", India);
				if(mGridFragment2.getArguments()!= null){
					mGridFragment2.getArguments().putAll(bundle);
				}else{
					mGridFragment2.setArguments(bundle);
				}
				
				
				ft.replace(R.id.change_frag,mGridFragment2,"GRID_VIEW_FRAG");
				ft.commit();
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Bundle bundle = new Bundle();
				
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				bundle.putStringArray("images", China);
				if(mGridFragment3.getArguments()!= null){
					mGridFragment3.getArguments().putAll(bundle);
					
				}else{
					mGridFragment3.setArguments(bundle);
				}
				
				
				ft.replace(R.id.change_frag,mGridFragment3,"GRID_VIEW_FRAG");
				
				//ft.addToBackStack(null);
				ft.commit();
			}
		});
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean x,y,a,b,p,q;
				ImageAdapter im = new ImageAdapter();
				x = mGridFragment1.isVisible();
				y = mGridFragment1.isVisib();
				a = mGridFragment2.isVisible();
				b = mGridFragment2.isVisib();
				p = mGridFragment3.isVisible();
				q = mGridFragment3.isVisib();
				Context context = getApplicationContext();
				
				CharSequence text = null;
				int duration = Toast.LENGTH_LONG;
				if(im.status() != null && im.status() == "running"){
					text = "downloading";
				}
				else{
					if((x==true || a== true || p == true) && (y==false || b==false || q==false)){
						text = "Showing downloaded thumbnails";
					}else if((x == false || a==false || p==false) && (y == true||b==true||q==true)){
						text = "Showing selected picture";
					}else{
						text = "idle";
					}
				}
				
				
				
				final Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		});
	}

	
}
