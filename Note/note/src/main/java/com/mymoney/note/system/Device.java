package com.mymoney.note.system;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.DisplayMetrics;
import android.util.Log;

public class Device {
	protected static String DEVICE_NAME;
	protected static String DEVICE_MODEL;
	protected static int DEVICE_WIDTH;
	protected static int DEVICE_HEIGHT;
	protected static float DEVICE_DENSITY;
	protected static String DEVICE_OS;
	protected static double LATITUDE;
	protected static double LONGITUDE;

	private static LocationManager lm;
	final static String TAG = "system.Device";

	public Device(Activity act) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		act.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		DEVICE_WIDTH = displaymetrics.widthPixels;
		DEVICE_HEIGHT = displaymetrics.heightPixels;
		DEVICE_DENSITY = displaymetrics.density;
		DEVICE_NAME = android.os.Build.DEVICE;
		DEVICE_MODEL = android.os.Build.MODEL;
		DEVICE_OS = System.getProperty("os.version") + "(" + android.os.Build.VERSION.INCREMENTAL + ")";

		try {
			getCoordinates(act);
		} catch (Exception e) {
			Log.e(TAG, "Location Services Has Been Turned Off!");
		}

	}

	private void getCoordinates(Activity act) {
		lm = (LocationManager) act.getSystemService(Context.LOCATION_SERVICE);
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		LONGITUDE = location.getLongitude();
		LATITUDE = location.getLatitude();
		Log.d(TAG, "Latitude = " + LATITUDE);
		Log.d(TAG, "Longitude = " + LONGITUDE);

	}

	public static String getDeviceName() {
		return DEVICE_NAME;
	}

	public static int getDeviceWidth() {
		return DEVICE_WIDTH;
	}

	public static int getDeviceHeight() {
		return DEVICE_HEIGHT;
	}

	public static float getDeviceDensity() {
		return DEVICE_DENSITY;
	}
}
