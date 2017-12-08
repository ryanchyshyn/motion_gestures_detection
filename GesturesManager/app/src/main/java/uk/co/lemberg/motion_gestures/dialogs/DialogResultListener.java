package uk.co.lemberg.motion_gestures.dialogs;

import android.content.Intent;

public interface DialogResultListener {
	void onDialogResult(int requestCode, int resultCode, Intent data);
}
