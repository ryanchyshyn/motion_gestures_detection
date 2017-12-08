package uk.co.lemberg.motion_gestures.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import uk.co.lemberg.motion_gestures.R;

public class PromptDialog extends DialogFragment {
	public static final String REQUEST_CODE_KEY = "requestCode";
	public static final String TITLE = "title";
	public static final String VALUE = "value";
	public static final String HINT = "hint";
	private int requestCode;

	public PromptDialog() {}

	public static PromptDialog newInstance(Fragment target, int requestCode, String title, String value, String hint) {
		Bundle bundle = new Bundle();
		bundle.putInt(REQUEST_CODE_KEY, requestCode);
		bundle.putString(TITLE, title);
		bundle.putString(VALUE, value);
		bundle.putString(HINT, hint);

		PromptDialog dialog = new PromptDialog();
		dialog.setArguments(bundle);
		if (target != null) dialog.setTargetFragment(target, requestCode);

		return dialog;
	}

	public static PromptDialog newInstance(int requestCode, String title, String value, String hint) {
		Bundle bundle = new Bundle();
		bundle.putInt(REQUEST_CODE_KEY, requestCode);
		bundle.putString(TITLE, title);
		bundle.putString(VALUE, value);
		bundle.putString(HINT, hint);

		PromptDialog dialog = new PromptDialog();
		dialog.setArguments(bundle);

		return dialog;
	}

	private void finishDialog(int result, String value) {
		dismissAllowingStateLoss();

		Fragment fragment = getTargetFragment();
		if (fragment != null) fragment.onActivityResult(requestCode, result, getResultIntent(value));
		else {
			FragmentActivity activity = getActivity();
			if (activity instanceof DialogResultListener)
				((DialogResultListener) activity).onDialogResult(requestCode, result, getResultIntent(value));
		}
	}

	private Intent getResultIntent(String value) {
		Intent ret = new Intent();
		if (value != null) ret.putExtra(VALUE, value);
		return ret;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		requestCode = getArguments().getInt(REQUEST_CODE_KEY);
		String title = getArguments().getString(TITLE);
		String value = getArguments().getString(VALUE);
		String hint = getArguments().getString(HINT);

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View contentView = inflater.inflate(R.layout.dialog_prompt, null);

		final EditText editText = contentView.findViewById(R.id.edit_text);
		editText.setText(value);
		editText.setHint(hint);

		return new AlertDialog.Builder(getActivity())
			//.setIcon(R.drawable.ic_launcher)
			.setTitle(title)
			.setView(contentView)
			.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finishDialog(FragmentActivity.RESULT_OK, editText.getText().toString());
					}
				})
			.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finishDialog(FragmentActivity.RESULT_CANCELED, null);
					}
				}).create();
	}
}
