package org.harrynoob.scripts.autoflipper.misc;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Constants {
	
	public static final int WIDGET_GE_BASE = 105;
	public static final int WIDGET_SLOT_PRICE = 19;
	public static final int WIDGET_SLOT_NAME = 18;
	public static final int WIDGET_SLOT_ID = 17;
	public static final int WIDGET_PROGRESS_BAR = 12;
	public static final int WIDGET_ABORT = 200;
	public static final int WIDGET_REWARD_1 = 206;
	public static final int WIDGET_REWARD_2 = 208;
	public static final int WIDGET_PROGRESS_BAR_LARGE_BASE = 199;
	public static final int WIDGET_PROGRESS_BAR_LARGE_ACTUAL = 2;
	public static final int WIDGET_PROGRESS_BAR_LARGE_MAX = 1;
	public static final int WIDGET_SEARCH_BASE = 389;
	public static final int WIDGET_SEARCH_OPTIONS = 4;
	public static final int WIDGET_SEARCH_TEXT = 9;
	public static final int[] NPC_CLERK = {1419, 2593, 2240, 2241, 2590 };
	
	public enum GESlot {
		
		ONE(1),
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5),
		SIX(6);
		
		private final int widgetSlotBase;
		private final int sellWidgetId;
		private final int buyWidgetId;
		private final int slotNumber;
		
		GESlot(final int slotNum) {
			//this.sellWidgetId = widgetSell;
			//this.buyWidgetId = widgetBuy;
			this.slotNumber = slotNum;
			this.widgetSlotBase = slotNum < 3 ? 3 + 16 * slotNum : 51 + 19 * (slotNum - 3);
			this.sellWidgetId = 29 + (slotNum - 1) * 16;
			this.buyWidgetId = 30 + (slotNum - 1) * 16;
		}
		
		
		
		public int getSellWidgetId() {
			return sellWidgetId;
		}
		
		public int getBuyWidgetId() {
			return buyWidgetId;
		}
		
		public int getSlotNumber() {
			return slotNumber;
		}
		
		public int getCurrentPrice() {
			final WidgetChild wc = Widgets.get(WIDGET_GE_BASE, widgetSlotBase);
			return wc != null && wc.isOnScreen() && wc.getChildStackSize() > 11 ? Integer.parseInt(Widgets.get(WIDGET_GE_BASE, widgetSlotBase).getChild(WIDGET_SLOT_PRICE).getText().replaceAll("\\D", "").trim()) : -1;
		}
		
		public int getCurrentId() {
			final WidgetChild wc = Widgets.get(WIDGET_GE_BASE, widgetSlotBase);
			return wc != null && wc.isOnScreen() && wc.getChildStackSize() > 11 ? wc.getChild(17).getChildId() : -1;
		}
	}
	
	public static GESlot getSlotByNumber(final int num) {
		for(GESlot s : GESlot.values()) {
			if(s.getSlotNumber() == num) {
				return s;
			}
		}
		return null;
	}

}
