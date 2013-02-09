package org.harrynoob.scripts.autoflipper.misc;

public class FlipItem {

	private final String name;
	private final int amount;
	
	public FlipItem(final String name, final int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public boolean equals(FlipItem fi) {
		return fi.getName().equalsIgnoreCase(name)
			&& fi.getAmount() == amount;
	}
}
