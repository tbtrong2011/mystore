package com.mymoney.note.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mymoney.note.R;
import com.mymoney.note.application.MyApplication;

public class Utils {

	public static void showToast(Context context, int resId, int duration) {
		showToast(context, context.getString(resId), duration);
	}

	public static void showToast(Context context, String resId, int duration) {
		Toast toast = Toast.makeText(context, resId, duration);
		toast.show();
	}

	/*
	 * Common function support show message as dialog format
	 * @param: context: dialog belong to this context title: title of dialog (can empty or null if don't use) imgTitle:
	 * icon for title (can be 0 if don't use) message: message show in dialog positive: text of positive button (can
	 * empty or null if don't use) positiveListener: define function to handle event click positive button navigate:
	 * text of navigate button (can empty or null if don't use) negativeListener: define function to handle event click
	 * navigate button
	 */

	public static void showAlertDialog(Context context, String title, int imgTitle, String message, String positive,
			DialogInterface.OnClickListener positiveListener, String negative,
			DialogInterface.OnClickListener negativeListener) {

		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
		if (title != null && !title.equals("")) {
			if (imgTitle != 0) {
				dialogBuilder.setIcon(imgTitle);
			}
			dialogBuilder.setTitle(title);

		}
		dialogBuilder.setMessage(message);
		if (positiveListener != null) {
			if (positive != null && !positive.equals(""))
				dialogBuilder.setPositiveButton(positive, positiveListener);
			else
				dialogBuilder.setPositiveButton(context.getString(R.string.yes), positiveListener);
		}
		if (negativeListener != null) {
			if (negative != null && !negative.equals(""))
				dialogBuilder.setPositiveButton(negative, negativeListener);
			else
				dialogBuilder.setPositiveButton(context.getString(R.string.no), negativeListener);
		}
		dialogBuilder.create().show();

	}

	public static void showAlertDialog(Context context, int title, int imgTitle, int message, int positive,
			DialogInterface.OnClickListener positiveListener, int negative,
			DialogInterface.OnClickListener negativeListener) {
		showAlertDialog(context, context.getString(title), imgTitle, context.getString(message),
				context.getString(positive), positiveListener, context.getString(negative), negativeListener);
	}

	public static void showAlertDialog(Context context, int title, int imgTitle, int message, int positive,
			DialogInterface.OnClickListener positiveListener) {
		showAlertDialog(context, context.getString(title), imgTitle, context.getString(message),
				context.getString(positive), positiveListener, null, null);
	}

	public static void showAlertDialog(Context context, int title, int imgTitle, int message, int positive) {
		showAlertDialog(context, context.getString(title), imgTitle, context.getString(message),
				context.getString(positive), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}, null, null);

	}

	public static Date getDateObjectFromStringDate(String strDate, String format) {
		Date date = new Date();
		try {
			SimpleDateFormat formater = new SimpleDateFormat(format, Locale.US);
			date = formater.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getStringDateFromDateObject(Date date, String format) {
		String strDate = "";
		try {
			SimpleDateFormat formater = new SimpleDateFormat(format, Locale.US);
			strDate = formater.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}

	public static String wrapperFormatDate(String strDate, String formatInput, String formatOutput) {
		String result = "";
		try {
			SimpleDateFormat formaterIn = new SimpleDateFormat(formatInput, Locale.US);
			SimpleDateFormat formaterOut = new SimpleDateFormat(formatOutput, Locale.US);
			result = getStringDateFromDateObject(getDateObjectFromStringDate(strDate, formatInput), formatOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void overrideFont(View view, String fontName) {
		try {
			if (view instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) view;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					overrideFont(child, fontName);
				}
			} else if (view instanceof TextView) {
				((TextView) view).setTypeface(Typeface.createFromAsset(MyApplication.instance.getAssets(), fontName));
			} else if (view instanceof EditText) {
				((EditText) view).setTypeface(Typeface.createFromAsset(MyApplication.instance.getAssets(), fontName));
			}
		} catch (Exception e) {
		}
	}

	public static void overrideFont(View view, String fontName, String fontBoldName) {
		try {
			if (view instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) view;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					overrideFont(child, fontName, fontBoldName);
				}
			} else if (view instanceof TextView) {
				if (((TextView) view).getTypeface().isBold())
					((TextView) view).setTypeface(Typeface.createFromAsset(MyApplication.instance.getAssets(),
							fontBoldName));
				else
					((TextView) view)
							.setTypeface(Typeface.createFromAsset(MyApplication.instance.getAssets(), fontName));
			} else if (view instanceof EditText) {
				if (((EditText) view).getTypeface().isBold())
					((EditText) view).setTypeface(Typeface.createFromAsset(MyApplication.instance.getAssets(),
							fontBoldName));
				else
					((EditText) view)
							.setTypeface(Typeface.createFromAsset(MyApplication.instance.getAssets(), fontName));
			}
		} catch (Exception e) {
		}
	}

}
