package aleix.app101.imagen;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	
	private static final String KEY_CLICKED = "clicked";

	private static final String TAG = MainActivity.class.getSimpleName();
	
	private static final String KEY_COUNT = "count";
	private SharedPreferences mPrefs;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG,"onCreate!");
		//Start using preferences
		mPrefs = getPreferences(MODE_PRIVATE);
		// mPrefs represents information stored on the flash disk.
		int count = mPrefs.getInt(KEY_COUNT, 0); // 0 is the initial value
		Log.d(TAG,"Count is "+count);
		
		count = count +1;
		
		// If we want to edit it on flash disk
		Editor editor = mPrefs.edit(); // Source > Organize imports		
		//editor.putInt(KEY_COUNT, count);
		// only when commit() is applied the changes are written on flash disk
		//editor.commit();
		
		// All commands together:
		editor.putInt(KEY_COUNT, count).commit();
		
		Log.d(TAG,"Count is "+count);
		
		// We can make Text view from a Java Object
		mTextView = new TextView(this); // is not displayed yet, is not part of the screen
		mTextView.setTextSize(80);
		mTextView.setText("count is: " + count);
		
		setContentView(mTextView);		
		//setContentView(R.layout.activity_main);
		
		mTextView.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		//Todo: Can you refactor these keys into a constants?
		int clickCount = 20 + mPrefs.getInt(KEY_CLICKED, 0); //initial value is 0
		mPrefs.edit().putInt(KEY_CLICKED, clickCount).putBoolean("user", true).commit();
		// The hexadecimal value 0xff00ff00 is
		// opaque green. Why?
		// [0x(Opacity)(R)(G)(B)]
		
		mTextView.setTextColor(0xff00ff00);
		mTextView.setText("Click!" + clickCount);

	}
}
