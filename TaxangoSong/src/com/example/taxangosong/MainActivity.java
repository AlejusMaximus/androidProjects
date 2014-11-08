/*
 Copyright (c) 2013 Aleix Pascual
 
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

package com.example.taxangosong;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends ActionBarActivity {
	MediaPlayer somUnRiu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("Pickle", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	// When this Activity is ready (and the layout has been been created)
	// Android will call
	// our onResume method. The onResume method can be called again if we are
	// paused for some reason.
	@Override
	protected void onResume() {
		Log.e("Pickle", "onResume");
		// "MediaPlayer.create(...)" is the code that actually creates a media
		// player object
		// "dontcallme =" is the code that changes ('assigns') our pointer to
		// point to the new media player object
		// You need to add "import android.media.MediaPlayer;" 
		// import statement at the top of this file
		somUnRiu = MediaPlayer.create(this, R.raw.som_un_riu);
		somUnRiu.start();
		super.onResume();
	}
	
	protected void onPause() {
		Log.e("Pickle", "onPause");
		// We're pausing. For this demo we will just stop the MediaPlayer and
		// then ask it to release
		// all of the valuable resources it's using.
		// If the user comes back to this app then onResume() will be called
		// again
		// (and we'll make a new Media player; see above)
		somUnRiu.stop();
		somUnRiu.release();
		super.onPause();
	}

	public void openFB(View v){
		String url = "http://www.facebook.com/Txarangooficial?fref=ts";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
	public void openWS(View v){
		String url = "http://txarango.com/";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
}
