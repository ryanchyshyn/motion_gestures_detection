package uk.co.lemberg.motion_gestures.utils;

public enum Label {
	Red(0xFFFF0000),
	Green(0xFF00FF00),
	Blue(0xFF0000FF),
	Teal(0xFF008080),
	Purple(0xFF800080),
	Olive(0xFF808000);

	public final int color;

	Label(int color) {
		this.color = color;
	}
}
