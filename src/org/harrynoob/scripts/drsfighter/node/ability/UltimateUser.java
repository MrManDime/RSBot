package org.harrynoob.scripts.drsfighter.node.ability;

import org.harrynoob.api.Actionbar;
import org.harrynoob.api.Percentages;
import org.harrynoob.scripts.drsfighter.DRSFighter;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;

public class UltimateUser extends Node {

	@Override
	public boolean activate() {
		return Percentages.getHealthPercent(Players.getLocal().get()) > 50
				&& Players.getLocal().getInteracting() != null
				&& Players.getLocal().getInteracting().validate()
				&& Players.getLocal().getInteracting().getHealthPercent() > 33
				&& Actionbar.getAdrenalinPercent() == 100
				&& !Players.getLocal().isMoving();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Actionbar.Ability[] ultimates = (int) getUltimateCount() > 0 ? new Actionbar.Ability[(int) getUltimateCount()]
				: null;
		if (ultimates == null)
			return;
		byte j = 0x0;
		for (int i = 0; i < 12; i++) {
			if (Actionbar.getAbilityAt(i) != null
					&& Actionbar.getAbilityAt(i).getAbilityType() == Actionbar.AbilityType.ULTIMATE) {
				ultimates[j] = Actionbar.getAbilityAt(i);
				j++;
			}
		}
		for (Actionbar.Ability a : ultimates) {
			if (Actionbar.getSlotWithAbility(a).isAvailable()) {
				DRSFighter.instance.status = "Using ultimates";
				Actionbar.getSlotWithAbility(a).activate(true);
				break;
			}
		}
	}

	private byte getUltimateCount() {
		byte j = 0;
		for (byte i = 0; i < 12; i++) {
			if (Actionbar.getSlotStateAt(i).equals(Actionbar.SlotState.ABILITY)
					&& Actionbar.getAbilityAt(i) != null
					&& Actionbar.getAbilityAt(i).getAbilityType()
							.equals(Actionbar.AbilityType.ULTIMATE))
				j++;
		}
		return j;
	}

}
