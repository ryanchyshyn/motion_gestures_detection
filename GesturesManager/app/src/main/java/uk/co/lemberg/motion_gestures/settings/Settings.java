package uk.co.lemberg.motion_gestures.settings;

import android.content.SharedPreferences;

public class Settings  {
	private static final String WORKING_DIR_KEY = "working_dir";

	private String workingDir = null;

	public String getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(String workingDir) {
		this.workingDir = workingDir;
	}

	public void load(SharedPreferences prefs) {
		workingDir = prefs.getString(WORKING_DIR_KEY, null);
	}

	public void save(SharedPreferences prefs) {
		SharedPreferences.Editor editor = prefs.edit();
		save(editor);
		editor.commit();
	}

	public void saveDeferred(SharedPreferences prefs) {
		SharedPreferences.Editor editor = prefs.edit();
		save(editor);
		editor.apply();
	}

	public void save(SharedPreferences.Editor editor) {
		editor.putString(WORKING_DIR_KEY, workingDir);
	}
}
