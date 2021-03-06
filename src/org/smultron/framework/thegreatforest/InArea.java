package org.smultron.framework.thegreatforest;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.scene.Players;
import org.smultron.framework.Location;
import org.smultron.framework.content.MoveTo;
import org.smultron.framework.tasks.Task;
import org.smultron.framework.tasks.TaskListener;

/**
 * A {@link BinaryBranch} which moves to a {@link Location} if the player isnt there.
 */
public class InArea extends BinaryBranch {

	/**
	 * @param successNode   The {@link TreeNode} which this branch will return if the player is in the location
	 * @param location      the location
	 * @param randomization randomization of the {@link Location} center position.
	 */
	public InArea(TreeNode successNode, final Location location, final int randomization) {
		super(() -> successNode,
				() -> location.asArea().contains(Players.getLocal()) || Game.isInCutscene(),
				() -> new LeafNode(new MoveTo(location, randomization)));
	}

	/**
	 * @param successTask   the {@link Task} which will get wrapper around a {@link LeafNode}
	 *                      and be returned if the player is in the location
	 * @param location      the location
	 * @param randomization randomization of the {@link Location} center position.
	 */
	public InArea(Task successTask, final Location location, final int randomization) {
		super(() -> new LeafNode(successTask),
				() -> location.asArea().contains(Players.getLocal()) || Game.isInCutscene(),
				() -> new LeafNode(new MoveTo(location, randomization)));
	}

}
