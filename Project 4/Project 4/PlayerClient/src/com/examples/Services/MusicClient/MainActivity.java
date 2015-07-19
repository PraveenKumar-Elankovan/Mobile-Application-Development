package com.examples.Services.MusicClient;

import java.util.ArrayList ; 
import java.util.List;

import com.examples.Services.MusicCommon.MusicPlayer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private MusicPlayer musicPlayerInstance;
	List<String> tuples = new ArrayList<String>();
	IntentFilter iFilter;
	private boolean mIsBound;
	boolean isBind;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Map the view Ids to objects
		final Button song1Button = (Button) findViewById(R.id.song1);
		final Button song2Button = (Button) findViewById(R.id.song2);
		final Button song3Button = (Button) findViewById(R.id.song3);
		final ImageButton pauseButton = (ImageButton) findViewById(R.id.pause);
		final Button logButton = (Button) findViewById(R.id.log);
		final ImageButton stopButton = (ImageButton) findViewById(R.id.stop);
		pauseButton.setTag(0);
		try{
		//Binding mechanism
		Intent intent = new Intent(MusicPlayer.class.getName());
		intent.setClassName("com.examples.Services.MusicService", "com.examples.Services.MusicService.MusicPlayerImpl");
		isBind = bindService(intent,this.mConnection, Context.BIND_AUTO_CREATE);
		}catch(Exception e){
		}
		//To post a toast once the song playback is completed
		iFilter = new IntentFilter("com.examples.Services.Song_Done");
		registerReceiver(recv, iFilter);
		song1Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					if(mIsBound){
						//Set the pause button text to "Pause" when play is clicked
						pauseButton.setTag(0);
						pauseButton.setBackgroundResource(R.drawable.pause);
//						Call the service to play the song 1
						musicPlayerInstance.playMusic(1);
					}
				}catch (Exception e){
				}
			}
		});
		
		song2Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					if(mIsBound){
						//Set the pause button text to "Pause" when play is clicked
						pauseButton.setTag(0);
						pauseButton.setBackgroundResource(R.drawable.pause);
//						//Call the service to play song 2
						musicPlayerInstance.playMusic(2);
						
					}
				}catch (Exception e){
				
				}
				
			}
		});
		
		song3Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					if(mIsBound){
						//Set the pause button text to "Pause" when play is clicked
						pauseButton.setTag(0);
						pauseButton.setBackgroundResource(R.drawable.pause);
//						pauseButton.setText("Pause");
//						//call the service to play the song 3
						musicPlayerInstance.playMusic(3);						
					}
						
				}catch (Exception e){
					
				}
				
			}
		});
		
		pauseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final int status = (Integer) pauseButton.getTag();
				try{
					if(mIsBound){
						if(status == 0 && musicPlayerInstance.isPlaying()){
							//call the service to pause the song
							musicPlayerInstance.pauseMusic();
							//If status is 0 which means pause then change to resume when hit on pause
							pauseButton.setTag(1);
							pauseButton.setBackgroundResource(R.drawable.play);
						}else if(status == 1){
							//call the service to resume the song
							musicPlayerInstance.resumeMusic();
							//If status is 1 which means resume then change to pause when hit on resume
							pauseButton.setTag(0);
							pauseButton.setBackgroundResource(R.drawable.pause);
//							pauseButton.setText("Pause");
						}
								
					}
						
				}catch (Exception e){
					
				}
				
			}
		});
		
		stopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					if(mIsBound){
						//Set the text to pause
						pauseButton.setTag(0);
						pauseButton.setBackgroundResource(R.drawable.pause);
//						//call the service to stop the song
						musicPlayerInstance.stopMusic();
					
					}
						
				}catch (Exception e){
				
				}
				
			}
		});
		
		logButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					if(mIsBound){
						//Retrieve all the SQLite records 
						tuples = musicPlayerInstance.logTransacations();
						//create the bundle
						Bundle bundle = new Bundle();
						//Put the retrieved records in the bundle
						bundle.putStringArrayList("records", (ArrayList<String>)tuples);
						//create an intent for second activity
						Intent i = new Intent(getApplicationContext(),Transactions.class);
						//pass the bundle to the intent
						i.putExtras(bundle);
						//start the activity
						startActivity(i);
					}
						
				}catch (Exception e){
				}
				
			}
		});
		
	}
	
	
	private final BroadcastReceiver recv = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			//Listen for the broadcast which is sent by the service
			if(intent.getAction().equalsIgnoreCase("com.examples.Services.Song_Done"))
            {
				Toast.makeText(getApplicationContext(), "Song playback is completed", Toast.LENGTH_SHORT).show();
				
            }

		}
		
	};
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		if(isFinishing()){
		//unregister the service
		unregisterReceiver(recv);
		if (mIsBound) {
			try{
				//delete all the database records
				musicPlayerInstance.removeLog();
				//stop the music player
				musicPlayerInstance.stopMusicPlayer();
				
			}catch(Exception e){
				
			}
			//unbind the service
			unbindService(this.mConnection);

		}
		}

		
	}
	
	private final ServiceConnection mConnection = new ServiceConnection() {
		
		@Override
		public void onServiceConnected(ComponentName classname, IBinder iservice) {
			//get the stub as interface
			musicPlayerInstance = MusicPlayer.Stub.asInterface(iservice);
			//set is bound to true
			mIsBound = true;
			Log.i("bound", "Service connected");
			
			
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			//on service disconnected, set the service to null and is bound to false
			musicPlayerInstance = null;
			mIsBound = false;
			Log.i("bound", "Service disconnected");
			
		}
	};

}
