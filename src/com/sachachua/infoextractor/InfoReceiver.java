package com.sachachua.infoextractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;

public class InfoReceiver extends Activity {
	 @Override 
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 // Get intent, action and MIME type
		 Intent intent = getIntent();
		 String action = intent.getAction();
		 String type = intent.getType();
		 if (Intent.ACTION_SEND.equals(action) && type != null) {
			 handleSendText(intent); // Handle text being sent
		 }	
		 finish();
	 }

	 private void handleSendText(Intent intent) {
		 String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		 // Find the matching URL
		 Pattern pattern = Patterns.WEB_URL;
		 Matcher matcher = pattern.matcher(sharedText);
		 if (matcher.find()) {
			 Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(matcher.group()));
			 startActivity(newIntent);
		 }
	 }
}
