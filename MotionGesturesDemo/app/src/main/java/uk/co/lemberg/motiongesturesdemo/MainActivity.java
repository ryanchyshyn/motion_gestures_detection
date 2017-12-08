package uk.co.lemberg.motiongesturesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import uk.co.lemberg.motiondetectionlib.MotionDetector;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private MotionDetector motionDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		motionDetector = new MotionDetector(this, gestureListener);

		ToggleButton toggleRec = findViewById(R.id.toggleRec);
		toggleRec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
	}

	private final MotionDetector.Listener gestureListener = new MotionDetector.Listener() {
		@Override
		public void onGestureRecognized(MotionDetector.GestureType gestureType) {
			showToast(gestureType.toString());
			Log.d(TAG, "Gesture detected: " + gestureType);
		}
	};

	private void showToast(String str) {
		Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
	}
}
