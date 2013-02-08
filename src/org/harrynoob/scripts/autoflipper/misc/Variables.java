package org.harrynoob.scripts.autoflipper.misc;

import java.util.LinkedList;

public class Variables {
	
	private static LinkedList<FlipItem> flipList = new LinkedList<>();
	
	public static void addToFlipList(final String name, final int amount) {
		flipList.add(new FlipItem(name, amount));
	}
	
	public static FlipItem getNextItem() {
		if(flipList.peek() != null) {
			return flipList.remove();
		}
		return null;
	}
	
}
