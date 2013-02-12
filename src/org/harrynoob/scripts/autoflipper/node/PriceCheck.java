package org.harrynoob.scripts.autoflipper.node;

import java.awt.Point;
import java.awt.Rectangle;

import org.harrynoob.api.Condition;
import org.harrynoob.api.Priority;
import org.harrynoob.api.PriorityNode;
import org.harrynoob.api.Utilities;
import org.harrynoob.scripts.autoflipper.misc.Constants;
import org.harrynoob.scripts.autoflipper.misc.Constants.GESlot;
import org.harrynoob.scripts.autoflipper.misc.FlipItem;
import org.harrynoob.scripts.autoflipper.misc.Variables;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class PriceCheck extends PriorityNode {

	private FlipItem fi;
	
	public PriceCheck() {
		super(Priority.VERY_HIGH);
	}

	@Override
	public boolean activate() {
		return (fi = Variables.getNextItem()) != null;
	}

	@Override
	public void execute() {
	}
	
	private final boolean openGe() {
		NPC clerk = NPCs.getNearest(Constants.NPC_CLERK);
		if(isGeOpen()) return true;
		if(clerk != null && clerk.validate()) {
			if(!clerk.isOnScreen()) {
				Utilities.cameraTurnTo(clerk);
			}
			if(clerk.interact("Exchange", clerk.getName())) {
				return Utilities.waitFor(new Condition() {
					public boolean validate() {
						return isGeOpen();
					}
				}, 3000);
			}
		}
		return false;
	}
	
	private final boolean isGeOpen() {
		return Widgets.get(Constants.WIDGET_GE_BASE) != null
				&& Widgets.get(Constants.WIDGET_GE_BASE).validate();
	}
	
	private final boolean searchItem() {
		final WidgetChild w1 = Widgets.get(Constants.WIDGET_SEARCH_BASE, 0);
		Widgets.get(Constants.WIDGET_SEARCH_BASE, Constants.WIDGET_SEARCH_BUTTON).interact("Choose Item");
		if(Utilities.waitFor(new Condition() {
			public boolean validate() {
				return w1.getChild(Constants.WIDGET_SEARCH_TEXT).isOnScreen();
			}
		}, 3000)) {
			Keyboard.sendText(fi.getName(), false);
			final WidgetChild opts = Widgets.get(Constants.WIDGET_SEARCH_BASE, Constants.WIDGET_SEARCH_OPTIONS);
			for(final WidgetChild w : opts.getChildren()) {
				if(w.getText() != null && w.getText().equalsIgnoreCase(fi.getName())) {
					final WidgetChild scroll = Widgets.get(Constants.WIDGET_SEARCH_BASE, 8).getChild(5);
					while(!opts.contains(getCentralPoint(w.getBoundingRectangle()))) {
						scroll.click(true);
					}
					w.click(true);
					return true;
				}
			}
		}
		return false;
	}
	
	private final Point getCentralPoint(Rectangle r) {
		return new Point((int)(r.getMaxX() + r.getMinX()) / 2, (int)(r.getMaxY() + r.getMinY()) / 2);
	}
	
	private final GESlot getFirstOpenSlot() {
		for(GESlot g : GESlot.values()) {
			if(Widgets.get(Constants.WIDGET_GE_BASE, g.getBuyWidgetId()) != null 
					&& Widgets.get(Constants.WIDGET_GE_BASE, g.getSellWidgetId()) != null
					&& Widgets.get(Constants.WIDGET_GE_BASE, g.getBuyWidgetId()).isOnScreen() 
					&& Widgets.get(Constants.WIDGET_GE_BASE, g.getSellWidgetId()).isOnScreen()) {
				return g;
			}
		}
		return null;
	}
	
	
}
