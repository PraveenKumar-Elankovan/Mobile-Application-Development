package edu.uic.cs478.Praveen;
import edu.uic.cs478.Praveen.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class GridLayoutActivity extends Activity
{
	//Define the name of the cars for the toast
	String[] carsName={"Aston Martin Vanquish","Audi R8","Mercedes Benz Sl Roadster","BMW Z4","Bugatti Veyron","Buick Cascada","Cadillac CTS V",
						"Chevrolet Camaro","Dodge Charger","Ferrari 488-GTB","Honda CRV","Hyundai SantaFe","Jaguar","Kia","Lamborghini","Porshe","Renault","Maruthi Suzuki Swift"};
	//Define the URL for the selected cars's website
	String[] URL={
			"http://www.astonmartin.com/en-us/cars/the-new-vanquish",
			"http://www.audiusa.com/models/audi-r8",
			"http://www.mbusa.com/mercedes/vehicles/class/class-SL/bodystyle-RDS",
			"http://www.bmwusa.com/standard/content/vehicles/2015/z4/default.aspx",
			"http://www.bugatti.com/en/veyron.html",
			"http://www.buick.com/cascada-luxury-convertible.html",
			"http://www.cadillac.com/v-series/2016-cts-v-sedan.html",
			"http://www.chevrolet.com/camaro-performance-cars.html",
			"http://www.dodge.com/en/charger/",
			"http://488gtb.ferrari.com/us/",
			"http://automobiles.honda.com/cr-v/audio.aspx",
			"https://www.hyundaiusa.com/santa-fe/index.aspx",
			"http://www.jaguarusa.com/index.html",
			"http://www.kia.com/us/en/home?series=&year=0",
			"http://www.lamborghini.com/en/home/",
			"http://www.porsche.com/",
			"http://www.renault.co.uk/",
			"http://www.marutisuzuki.com/swift.aspx"
			
	};
	int clickId=0;
	protected static final String EXTRA_RES_ID = "POS";
	//Define the list of images
	public  Integer[] mThumbIdsCars = {
				
						R.drawable.aston_martin_vanquish,
						R.drawable.audi_r8,
						R.drawable.benz_sl_roadster,
						R.drawable.bmw_z4,
						R.drawable.bugati_veyron,
						R.drawable.buick_cascada,
						R.drawable.cadillac_cts_v,
						R.drawable.camaro,
						R.drawable.dodge_charger,
						R.drawable.ferrari_488_gtb,
						R.drawable.honda_crv,
						R.drawable.hyundai_santafe,
						R.drawable.jaguar,
						R.drawable.kia,
						R.drawable.lamborgini,
						R.drawable.porshe,
						R.drawable.renault,
						R.drawable.swift
	};
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        GridView gridview =(GridView)findViewById(R.id.gridview);
        //Set an image adapter to gridview to display set of cars
        gridview.setAdapter(new ImageAdapter(this, mThumbIdsCars));
        gridview.setOnItemClickListener(new OnItemClickListener()
        {
        	//Called when an item is clicked
        	@Override        
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        	{
        		Intent intent = new Intent(GridLayoutActivity.this,ImageViewActivity.class);
        		intent.putExtra(GridLayoutActivity.EXTRA_RES_ID,(int)id);
//        		System.out.println("===============>"+id);
        		Toast.makeText(getApplicationContext(), "Displaying "+carsName[position],
        				   Toast.LENGTH_LONG).show();//Showing toast in the bottom for the selected car
        		startActivity(intent);
        	}
        	
        });
        //Called when the item is clicked and held
        gridview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int position, long id) {
				android.widget.GridView gridView=(android.widget.GridView) findViewById(R.id.gridview);
				clickId=(int)id;
				 registerForContextMenu( gridView );//Registering for context menu
		          openContextMenu( gridView );  
				return false;
			}
        	
        	
		});
        
        
    }
    
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select An Option:");
        menu.add(0, v.getId(), 0, "View Full Image");
        menu.add(0, v.getId(), 0, "GoTo Official Website"); 
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        int indexPos=info.position;
        if(item.getTitle()=="View Full Image")
        {
     		Intent intent = new Intent(GridLayoutActivity.this, ImageViewActivity.class);
     		intent.putExtra(GridLayoutActivity.EXTRA_RES_ID, clickId);
     		Toast.makeText(getApplicationContext(), "Displaying "+carsName[indexPos],
 				   Toast.LENGTH_LONG).show(); //Showing toast in the bottom for the selected car
     		startActivity(intent);
        }
        else if(item.getTitle()=="GoTo Official Website")
        	{
        	Intent URLintent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL[indexPos])); 
        	URLintent.addCategory(Intent.CATEGORY_BROWSABLE);
        	Toast.makeText(getApplicationContext(), "Fetching the webpage of "+carsName[indexPos],
 				   Toast.LENGTH_LONG).show();//Showing toast in the bottom for the selected car
        	startActivity(URLintent);
        	}
        	else{
        		return false;
        	}
        return true;
    }
    
    
}
