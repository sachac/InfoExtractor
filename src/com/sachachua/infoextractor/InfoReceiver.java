package com.sachachua.infoextractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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
		 Pattern pattern = Pattern.compile(
				 "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" + 
						 "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" + 
						 "|mil|biz|info|mobi|name|aero|jobs|museum" + 
						 "|travel|[a-z]{2}))(:[\\d]{1,5})?" + 
						 "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" + 
						 "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" + 
						 "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" + 
						 "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" + 
						 "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" + 
				 "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");
		 Matcher matcher = pattern.matcher(sharedText);
		 if (matcher.find()) {
			 Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(matcher.group()));
			 startActivity(newIntent);
		 }
	 }
}
