package org.harrynoob.scripts.drsfighter.node;

import org.harrynoob.api.Condition;
import org.harrynoob.api.Utilities;
import org.harrynoob.scripts.drsfighter.misc.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.GroundItem;

public class SpinEffigy extends Node {

	@Override
	public boolean activate() {
		return getSpinEffigies() != null
				&& getSpinEffigies().length > 0
				&& !Inventory.isFull()
				&& !Players.getLocal().isMoving()
				&& Players.getLocal().getInteracting() == null;
	}

	@Override
	public void execute() {
		Utilities.ensureInventoryTab();
		for (GroundItem d : getSpinEffigies()) {
			d.validate();
			final GroundItem g = GroundItems.getNearest(new int[] {
					Variables.EFFIGY_ID, Variables.SPIN_TICKET_ID });
			if (!Utilities.isOnScreen(g))
				Utilities.cameraTurnTo(g);
			final int i = Inventory.getCount(new int[] { Variables.EFFIGY_ID,
					Variables.SPIN_TICKET_ID });
			g.interact("Take", g.getGroundItem().getName());
			if (Utilities.waitFor(new Condition() {

				@Override
				public boolean validate() {
					return !g.validate()
							|| Inventory.getCount(new int[] {
									Variables.EFFIGY_ID,
									Variables.SPIN_TICKET_ID }) > i;
				}

			}, 5000)) {
				if (Inventory.getCount(Variables.SPIN_TICKET_ID) > 0)
					Inventory.getItem(Variables.SPIN_TICKET_ID)
							.getWidgetChild().interact("Claim spin");
			}
		}
	}

	private GroundItem[] getSpinEffigies() {
		GroundItem[] g = GroundItems.getLoaded(new Filter<GroundItem>() {
			public boolean accept(GroundItem g) {
				return (g.getId() == Variables.EFFIGY_ID | g.getId() == Variables.SPIN_TICKET_ID)
						&& g.getLocation().canReach();
			}
		});
		return g;
	}

}
