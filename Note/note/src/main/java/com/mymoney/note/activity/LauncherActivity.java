package com.mymoney.note.activity;

import android.content.Intent;
import android.os.Bundle;

public class LauncherActivity extends BaseFragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = new Intent(this, SplashScreenActivity.class);
		startActivity(intent);
		finish();
	}
}
