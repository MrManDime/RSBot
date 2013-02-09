package org.harrynoob.scripts.autoflipper.node;

import org.harrynoob.api.Condition;
import org.harrynoob.api.Priority;
import org.harrynoob.api.PriorityNode;
import org.harrynoob.api.Utilities;
import org.harrynoob.scripts.autoflipper.misc.Constants;
import org.harrynoob.scripts.autoflipper.misc.FlipItem;
import org.harrynoob.scripts.autoflipper.misc.Variables;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.wrappers.interactive.NPC;

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
		return false;
	}
}
