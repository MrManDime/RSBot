package org.powerbot.harrynoob.scripts.drsfighter.misc;

import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.harrynoob.scripts.drsfighter.gui.MainPanel;


public class Variables {
	
	public static String[] bankLocations = {"Varrock East"/*, "Draynor Village", "Edgeville"*/};
	public static String[] food = {"Trout", "Salmon", "Tuna", "Lobster", "Swordfish", "Monkfish", "Shark", "Manta ray", "Rocktail"}; 
	public static final int[] FOOD_IDS = {333, 351, 329, 361, 379, 365, 373, 7946, 385, 697, 391, 15266, 15272};
	public static final int SPIDER_ID = 63;
	public static final int REJUVENATE_ANIMATION_ID = 18082;
	public static final int[] CHARM_IDS = {12158, 12159, 12160, 12161};
	public static final Tile VARROCK_CENTRAL_TILE = new Tile(3179, 9885, 0).randomize(1, 1);
	
	public static boolean rejuvenate;
	public static boolean switchWeapons;
	public static boolean banking;
	public static boolean withdrawFood;
//	public static boolean requiresSwitch;
	public static int weaponID;
	public static int shieldID;
	public static String bankLocation;
	public static String foodWithdrawal;
		
	public enum FightLocation
	{
		VARROCK(new Tile(3144, 9840, -1), "Varrock Sewers", "Varrock East", new Tile(3179, 9886, 0)),
		/*KARAMJA(new Tile(0,0,0), "Karamja Volcano", "Draynor Village")*/;
		
		public Tile areaTile;
		public String locationName;
		public String bankName;
		public Tile centralTile;
		
		FightLocation(Tile tile, String name, String preferredBank, Tile centralTile)
		{
			
			areaTile = tile; 
			locationName = name;
			bankName = preferredBank;
			this.centralTile = centralTile;
		}
		
	}
	
	public static void initOptions(MainPanel mainPanel)
	{
		boolean[] b = mainPanel.booleanOptions();
		rejuvenate = b[0];
		switchWeapons = b[1];
		banking = b[2];
		withdrawFood = b[3];
		String[] s = mainPanel.stringOptions();
		weaponID = getItemByName(s[0]) != null ? getItemByName(s[0]).getId() : 0;
		shieldID = getItemByName(s[1]) != null ? getItemByName(s[1]).getId() : 0;
		bankLocation = s[2];
		foodWithdrawal = s[3];
	}
	
	private static Item getItemByName(final String n)
	{
		return n != null ? Inventory.getItem(new Filter<Item>(){

			@Override
			public boolean accept(Item arg0) {
				return arg0.getName().equals(n);
			}
		}) : null;
	}	
}