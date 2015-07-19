package edu.uic.cs478.praveen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView editText;
	private Button search;
	private TextView status;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		search=(Button)findViewById(R.id.dialButton);
		editText=(TextView)findViewById(R.id.editText1);
		status=(TextView)findViewById(R.id.textView1);
		search.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			
				if(numberMatcher(editText.getText().toString())==null)
				{
					status.setText("Number Match Not Found.");
				}
				else
				{
					status.setText("");
				Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+numberMatcher(editText.getText().toString())));
				startActivity(intent);
				}
			}
		
	});
	}

	private String numberMatcher(String data){

		//Extracting (xxx) yyy-zzzz number
		Pattern pattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}");
		Matcher matcher = pattern.matcher(data);
		if (matcher.find()) {
			return matcher.group(0);
		}
		else
			return null;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
