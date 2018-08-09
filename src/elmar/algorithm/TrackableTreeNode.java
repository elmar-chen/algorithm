package elmar.algorithm;

import java.util.Iterator;

public abstract class TrackableTreeNode<T> extends TreeNode<T> {

	private boolean visited = false;

	public TrackableTreeNode(T val) {
		super(val);
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public abstract Iterator<? extends TrackableTreeNode<T>> children();
}
