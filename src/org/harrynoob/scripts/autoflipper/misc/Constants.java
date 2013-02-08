package org.harrynoob.scripts.autoflipper.misc;

public class Constants {
	
	public static final int WIDGET_GE_BASE = 105;
	
	public static final int WIDGET_PROGRESS_BAR = 12;
	
	public enum GESlot {
		
		ONE(29, 30, 1),
		TWO(45, 46, 2);
		
		private final int sellWidgetId;
		private final int buyWidgetId;
		private final int slotNumber;
		
		GESlot(final int widgetSell, final int widgetBuy, final int slotNum) {
			//this.sellWidgetId = widgetSell;
			//this.buyWidgetId = widgetBuy;
			this.slotNumber = slotNum;
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
