package elmar.algorithm;

import java.util.Iterator;

public abstract class TreeNode<T> {

	private T value;

	public TreeNode(T val) {
		this.value = val;
	}

	public T getValue() {
		return value;
	}

	public abstract Iterator<? extends TreeNode<T>> children();

}
