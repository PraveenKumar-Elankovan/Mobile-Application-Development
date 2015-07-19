package com.examples.Services.MusicService;
import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.examples.Services.MusicCommon.MusicPlayer;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class MusicPlayerImpl extends Service {
	//Declarations
	MediaPlayer mediaPlayerInstance = new MediaPlayer();
	int resumeIndex;
	public int songId;
	Transactions transactionInstance;
	
	private final MusicPlayer.Stub mBinder = new MusicPlayer.Stub(){
		
			@Override
			public void playMusic(int songIndex) throws RemoteException {
			//Function implementation for playing the music. The user click is passed as an paramaeter and the music is played as per choice 
				switch(songIndex)
				{
				case 1:
					if(mediaPlayerInstance.isPlaying()){
							//If the song is playing, that is stopped prior to the next song's start().
							mediaPlayerInstance.stop();
					}
					//Creating media plater instance
					mediaPlayerInstance = MediaPlayer.create(getApplicationContext(), R.raw.song1);
					songId =1;
					insertDataTransaction("Play");
					break;
				
				case 2:
					if(mediaPlayerInstance.isPlaying()){
						//If the song is playing, that is stopped prior to the next song's start().
						mediaPlayerInstance.stop();
					}
					//Creating media plater instance
					mediaPlayerInstance = MediaPlayer.create(getApplicationContext(), R.raw.song2);	
					songId =2;
					insertDataTransaction("Play");
					break;
					
				case 3:
					if(mediaPlayerInstance.isPlaying()){
						//If the song is playing, that is stopped prior to the next song's start().
						mediaPlayerInstance.stop();
					}
					//Creating media plater instance
					mediaPlayerInstance = MediaPlayer.create(getApplicationContext(), R.raw.song3);
					songId =3;
					insertDataTransaction("Play");
					break;
				}
				
				//Starting the song
				mediaPlayerInstance.start();
				//On completion a broadcast is done and the receiver displayes the toast of the status
				mediaPlayerInstance.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer mPlayer) {
						Intent intent = new Intent();
						intent.setAction("com.examples.Services.Song_Done");
						sendBroadcast(intent);
						
					}
				});
				
			}

			@Override
			public void pauseMusic() throws RemoteException {
				mediaPlayerInstance.pause();
				//current position is stored for enabling resume of the song
				resumeIndex = mediaPlayerInstance.getCurrentPosition();
				insertDataTransaction("Pause");
			}

			@Override
			public void resumeMusic() throws RemoteException {
				mediaPlayerInstance.seekTo(resumeIndex);
				mediaPlayerInstance.start();
				insertDataTransaction("Resume");
			}

			@Override
			public void stopMusic() throws RemoteException {
				mediaPlayerInstance.stop();
				insertDataTransaction("Stop");
			}

			@Override
			public void stopMusicPlayer() throws RemoteException {
				//Called from onDestroy() of the client app
				mediaPlayerInstance.release();
			}
			
			// Method to get the present date
			public String getDate(){
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH)+1;
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				String date = Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year);
				return date;
			}
			
			// Method to get the present time
			@SuppressLint("SimpleDateFormat")
			public String getTime(){
				Calendar c = Calendar.getInstance();
				String time = new SimpleDateFormat("HH:mm:ss").format(c.getTime());
				return time;
			}
		
			public void insertDataTransaction(String operation)
			{
//				Method to insert data into the SQLite database
				transactionInstance = new Transactions(getBaseContext());
				transactionInstance.open();
				transactionInstance.insertData(getDate(), getTime(),operation,Integer.toString(songId), "");
				transactionInstance.close();
			}
			@Override
			public List<String> logTransacations() throws RemoteException {
				List<String> tuples = new ArrayList<String>();
				transactionInstance = new Transactions(getBaseContext());
				transactionInstance.open();
				//get the data through the cursor until there is a next row
				Cursor c = transactionInstance.returnData();
				if(c.moveToFirst()){
					do{
//						Iterates through cursor to get the data fields
						tuples.add("Date : "+c.getString(0)+"\nTime : "+c.getString(1)+" "+"\nRequested Operation : "+c.getString(2)+" "+"\nAudio ID : "+c.getString(3));
					}while(c.moveToNext());
					
				}
				transactionInstance.close();
				//return the records in a List<String>
				return tuples;
			}

			@Override
			public void removeLog() throws RemoteException {
				
				transactionInstance = new Transactions(getBaseContext());
				transactionInstance.open();
				//delete all the data
				transactionInstance.deleteData();
				transactionInstance.close();
				
			}

			@Override
			public boolean isPlaying() throws RemoteException {
				return mediaPlayerInstance.isPlaying();
			}


			
		};

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
}
