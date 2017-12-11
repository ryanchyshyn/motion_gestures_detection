package uk.co.lemberg.motiongesturesdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import uk.co.lemberg.motiondetectionlib.MotionDetector;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private ScrollView scrollLogs;
	private TextView textLogs;

	private MotionDetector motionDetector;
	private DateFormat dateFormat;
	private DateFormat timeFormat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		scrollLogs = findViewById(R.id.scrollLogs);
		textLogs = findViewById(R.id.textLogs);

		motionDetector = new MotionDetector(this, gestureListener);
	}

	@Override
	protected void onDestroy() {
		motionDetector.stop();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		MenuItem switchItem = menu.findItem(R.id.actionSwitch);

		SwitchCompat switchActionBar = switchItem.getActionView().findViewById(R.id.switchActionBar);
		switchActionBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					try {
						motionDetector.start();
					}
					catch (Exception e) {
						e.printStackTrace();
						showToast("Failed to start motion detector. Error:" + e);
					}
				}
				else {
					motionDetector.stop();
				}
			}
		});

		return true;
	}

	private final MotionDetector.Listener gestureListener = new MotionDetector.Listener() {
		@Override
		public void onGestureRecognized(MotionDetector.GestureType gestureType) {
			showToast(gestureType.toString());
			addLog("Gesture detected: " + gestureType);
			Log.d(TAG, "Gesture detected: " + gestureType);
		}
	};

	private DateFormat getDateFormat() {
		if (dateFormat == null) {
			dateFormat = android.text.format.DateFormat.getDateFormat(this);
		}
		return dateFormat;
	}

	private DateFormat getTimeFormat() {
		if (timeFormat == null) {
			timeFormat = android.text.format.DateFormat.getTimeFormat(this);
		}
		return timeFormat;
	}

	private void addLog(String str) {
		Date date = new Date();
		String logStr = String.format("[%s %s] %s\n", getDateFormat().format(date), getTimeFormat().format(date), str);
		textLogs.append(logStr);
		scrollLogs.fullScroll(View.FOCUS_DOWN);
	}

	private void showToast(String str) {
		Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
	}
}
