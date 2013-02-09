package org.harrynoob.api;

import java.util.ArrayList;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;

import static org.powerbot.game.api.wrappers.Tile.Flag.*;

public class Pathfinding {

	private static final int FLAG = WALL_BLOCK_EAST | WALL_BLOCK_NORTH | WALL_BLOCK_SOUTH | WALL_BLOCK_WEST 
			| WALL_BLOCK_NORTHEAST | WALL_BLOCK_NORTHWEST | WALL_BLOCK_SOUTHEAST | WALL_BLOCK_SOUTHWEST;

	public static boolean isWalkable(Tile t1, Tile t2) {
		final int[][] flags = Walking.getCollisionFlags(Game.getPlane());
		final Tile offset = Walking.getCollisionOffset(Game.getPlane()).derive(Game.getBaseX(), Game.getBaseY());
		Vector v = new Vector(t1, t2);
		if(v.getDistance() > 20) {
			return false;
		}
		for(final Tile t : generateStraightPath(v)) {
			int toCheckFlag = flags[t.getX() - offset.getX()][t.getY() - offset.getY()];
			if((toCheckFlag | FLAG) == toCheckFlag) {
				return false;
			}
		}
		return true;
	}

	private static Tile[] generateStraightPath(final Vector v) {
		ArrayList<Tile> path = new ArrayList<Tile>();
		double currX = v.getTileOne().getX(), currY = v.getTileOne().getY();
		while(currX < v.getTileTwo().getX()) {
			path.add(new Tile((int)(currX += 1), (int)(currY += v.getdYdX()), 0));
		}
		return path.toArray(new Tile[path.size()]);
	}

	private static class Vector {
		private final double x;
		private final double y;
		private final Tile t1;
		private final Tile t2;

		public Vector(final Tile t1, final Tile t2) {
			this.x = Math.abs(t1.getX() - t2.getX());
			this.y = Math.abs(t1.getY() - t2.getY());
			this.t1 = t1;
			this.t2 = t2;
		}

		public Tile getTileOne() {
			return t1;
		}

		public Tile getTileTwo() {
			return t2;
		}

		public double getdX() {
			return x;
		}

		public double getdY() {
			return y;
		}

		public double getdYdX() {
			return getdY() / getdX();
		}

		public double getDistance() {
			return Math.sqrt( Math.pow(x, 2) + Math.pow(y, 2));
		}
	}
}