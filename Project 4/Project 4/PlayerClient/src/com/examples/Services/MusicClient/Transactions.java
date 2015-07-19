package com.examples.Services.MusicClient;

import java.util.List;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Transactions extends ListActivity {
	
	private List<String> tuples;
	ArrayAdapter<String> tuplesToAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transactions);
		Bundle b = this.getIntent().getExtras();
		tuples = b.getStringArrayList("records");
		tuplesToAdapter = new ArrayAdapter <String>(this,R.layout.row_layout,R.id.listText, tuples);
		setListAdapter(tuplesToAdapter);

	}
	

}
