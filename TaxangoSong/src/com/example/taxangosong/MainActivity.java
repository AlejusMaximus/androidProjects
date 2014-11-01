package com.example.taxangosong;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends ActionBarActivity {
	MediaPlayer somUnRiu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("Pickle", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		somUnRiu = MediaPlayer.create(this, R.raw.som_un_riu);
	}
	@Override
	protected void onResume() {
		Log.e("Pickle", "onResume");
		somUnRiu.start();
		super.onResume();
	}
	
	protected void onPause() {
		Log.e("Pickle", "onPause");
		somUnRiu.stop();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void openTV3(View v){
		String url = "http://www.tv3.cat";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
	public void openUAB(View v){
		String url = "http://www.uab.cat/";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
}
