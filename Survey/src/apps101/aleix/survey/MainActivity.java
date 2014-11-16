package apps101.aleix.survey;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private EditText mName;
	private EditText mPhone;
	private EditText mEmail;
	private EditText mComments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		// Look for these views after we've created them !
		
		mName = (EditText) findViewById(R.id.name);
		mPhone = (EditText) findViewById(R.id.phone);
		mEmail = (EditText) findViewById(R.id.email);
		mComments = (EditText) findViewById(R.id.comments);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void processForm(View duck) {
		Log.d("MainActivity", "processForm");
		String comments = mComments.getText().toString();
		String email = mEmail.getText().toString();
		String phone = mPhone.getText().toString();
		String name = mName.getText().toString();

		// it's time to check if email adress has "@" symbol:
		int position = email.indexOf("@");
		if (position == -1) { 
			// if ( ! email.contains("@") ) ... We must do something
			Toast.makeText(this.getApplicationContext(),
					"Invalid email adress!", Toast.LENGTH_LONG).show();
			mEmail.requestFocus();
			return; // get our of - public void processForm();
		}

		int len = comments.length();
		
		
		if (len == 0) { // if(!comments == null)
			Toast.makeText(this.getApplicationContext(), "Give me comments!",
					Toast.LENGTH_LONG).show();
			mComments.requestFocus();
			return; // get our of - public void processForm();
		}

		// To see if two String objects are equal use the "equals" method
		// if( name == null) OK (compare the two memory pointers)
		// if (name == "Fred") Not OK! (because name points to a different
		// object)
		if (name.equals("Fred")) {
			Toast.makeText(this.getApplicationContext(), "Hi Fred!",
					Toast.LENGTH_LONG).show();
		}

		// We might run into two surprises with the following... Can you find
		// them?
		// Convert a string (a sequence of characters) "123123123" into an
		// integer value
		int value = Integer.parseInt(phone);
		Log.d("MainActivity", "Phone number:" + value);

		String username = email.substring(0, position);
		String thkx = "Thank you " + username + "!";

		Toast.makeText(this.getApplicationContext(), thkx, Toast.LENGTH_LONG)
				.show();

		// duck.setVisibility(View.INVISIBLE);
		// Toast.makeText(this.getApplicationContext(), R.string.app_name,
		// Toast.LENGTH_LONG).show();
	}
}
