package apps101.aleix.survey;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
		
		// Simplest way to send some text		
//		Intent i = new Intent(Intent.ACTION_SEND);
//		i.setType("text/plain");
//		i.putExtra(Intent.EXTRA_TEXT, "What a wonderful app!");
		
		// SMS message
//		Intent intent = new Intent(Intent.ACTION_VIEW);
//		intent.setData(Uri.parse("sms:"+phone));
//		// Alternative...
//		// intent.setData(Uri.fromParts("sms", phone, null));
//		intent.putExtra("sms_body", comments);
		
		String message = name + " says.. \n" + comments;
		
		if (phone.length() > 0) {
			message = message + "\nPhone:" + phone;
		}

		if (email.length() > 0) {
			message = message + "\nAlternative Email:" + email;
		}
		
		
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
		emailIntent.setData(Uri.fromParts("mailto",
				"feedback@myapp.somewhere...", null)); // SENDTO: "feedback@myapp.somewhere..." -> hardcoded
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "important news"); // Subject Field on Email
		emailIntent.putExtra(Intent.EXTRA_TEXT, message); // Message email
		
		if (emailIntent.resolveActivity(getPackageManager()) == null) {
			Toast.makeText(getApplicationContext(),
					"Please configure your email client!", Toast.LENGTH_LONG)
					.show();
		} else {
			// Secondly, use a chooser will gracefully handle 0,1,2+ matching
			// activities
			startActivity(Intent.createChooser(emailIntent,
					"Please choose your email app!"));
		}
		
		// We are catching the problem after it happens!
//		try {
//			startActivity(emailIntent);
//			// startActivity can throw ActivityNotFoundException
//			// So to be robust our app will catch the exception ..
//		} catch (Exception ex) {
//			// We could tell the user it didn't work e.g. with a Toast
//			// Also we can print the exception message and stack trace in the
//			// log...
//			Toast.makeText(this.getApplicationContext(), "Cannot send comments!",
//					Toast.LENGTH_LONG).show();
//			Log.e("Main activity", "Could not send an email!", ex);
//		}
	}

	public void processFormOld(View duck) {
		Log.d("MainActivity", "processFormOld");
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

		// We might run into some surprises when we try to convert
		// a string to an integer value
		// Convert a string (a sequence of characters) "123123123" into an
		// integer value

		// Notice we declare these variables OUTSIDE the try-catch block
		// So we can use them after the catch block ends
		int value = -1; // we will change this
		boolean valueOK = false; // will become true if everything works out OK

		try {
			// The next line will throw an exception if it does not like our
			// string!
			// e.g. if phone is an empty string "3000000000" or "askdgahksdg"
			value = Integer.parseInt(phone);

			// We will skip this code if parseInt throws its
			// NumberFormatException
			// If everything goes to plan though we will just continue here...

			valueOK = true; // Change AFTER parseInt has returned
			Log.d("MainActivity", "Phone number:" + value);

		} catch (Exception e) {
			// Uh oh... We caught that nasty exception!!
			// (FYI More experienced programmers might choose to catch
			// NumberFormatException)

			Log.d("MainActivity",
					"Invalid Phone Number!? Could not be turned into an Java integer value"
							+ phone);
		}

		if (valueOK) {
			Log.d("MainActivity", "Phone number as an integer value:" + value);
		}

		String username = email.substring(0, position);
		String thankyou = "Thankyou " + username + "!";

		Toast.makeText(this.getApplicationContext(), thankyou,
				Toast.LENGTH_LONG).show();

		// Move the duck to the right and fade it out
		Animation anim = AnimationUtils.makeOutAnimation(this, true);
		duck.startAnimation(anim);
	}
}
