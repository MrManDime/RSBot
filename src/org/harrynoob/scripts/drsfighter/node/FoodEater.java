package org.harrynoob.scripts.drsfighter.node;

import org.harrynoob.api.Utilities;
import org.harrynoob.scripts.drsfighter.DRSFighter;
import org.harrynoob.scripts.drsfighter.misc.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;

public class FoodEater extends Node {

	@Override
	public boolean activate() {
		return Players.getLocal().getHealthPercent() < Random.nextInt(45, 55)
				&& Inventory.getCount(Variables.FOOD_IDS) > 0;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		DRSFighter.instance.status = "Eating food";
		DRSFighter.getDebugger().logMessage("Eating "+Inventory.getItem(Variables.FOOD_IDS).getName());
		Utilities.ensureInventoryTab();
		if (Inventory.getItem(Variables.FOOD_IDS).getWidgetChild() != null
				&& Inventory.getItem(Variables.FOOD_IDS).getWidgetChild()
						.validate()) {
			Inventory.getItem(Variables.FOOD_IDS).getWidgetChild()
					.interact("Eat");
		}
	}

}
