package uk.co.lemberg.motion_gestures.settings;

import android.content.Context;
import android.content.SharedPreferences;

import uk.co.lemberg.motion_gestures.Application;


public class AppSettings extends Settings
{
	public static AppSettings getAppSettings(Context context)
	{
		if (context instanceof Application) return ((Application) context).getSettings();

		Context appContext = context.getApplicationContext();
		if (appContext instanceof Application) return ((Application) appContext).getSettings();

		return null;
	}

	private final SharedPreferences prefs;

	public AppSettings(SharedPreferences prefs)
	{
		this.prefs = prefs;
	}

	public void load()
	{
		load(prefs);
	}

	public void save()
	{
		save(prefs);
	}

	public void saveDeferred()
	{
		saveDeferred(prefs);
	}
}
