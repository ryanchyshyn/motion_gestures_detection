package uk.co.lemberg.motion_gestures.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class TimestampAxisFormatter implements IAxisValueFormatter {
	public TimestampAxisFormatter() {
	}

	@Override
	public String getFormattedValue(float value, AxisBase axis) {
		// "value" represents the position of the label on the axis (x or y)
		return formatValue(value);
	}

	public static String formatValue(float value) {
		return String.format("%.3f", value / 1000f); // convert microseconds to seconds
	}
}