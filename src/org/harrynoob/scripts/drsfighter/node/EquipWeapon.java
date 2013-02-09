package org.harrynoob.scripts.drsfighter.node;

import org.harrynoob.api.Condition;
import org.harrynoob.api.Utilities;
import org.harrynoob.scripts.drsfighter.DRSFighter;
import org.harrynoob.scripts.drsfighter.misc.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;

public class EquipWeapon extends Node {

	@Override
	public boolean activate() {
		return Variables.switchWeapons
				&& Inventory.getItem(Variables.weaponID) != null
				//&& Equipment.containsOneOf(Variables.shieldID)
				&& ((Variables.rejuvTimer != null && Variables.rejuvTimer
						.getRemaining() == 0) || Players.getLocal().getHealthPercent() > Random.nextInt(90, 95));
	}

	@Override
	public void execute() {
		Utilities.ensureInventoryTab();
		DRSFighter.instance.status = "Switching to weapon";
		DRSFighter.getDebugger().logMessage("Switching to weapon");
		Equipment.equip(Variables.weaponID);
		if (Utilities.waitFor(new Condition() {
			public boolean validate() {
				return Equipment.containsOneOf(Variables.weaponID);
			}
		}, 3000)) {
			System.out.println("Succesfully equipped weapon!");
			DRSFighter.getDebugger().logMessage("Succesfully equipped weapon");
			return;
		}
	}
}
