package elmar.algorithm;

import java.util.List;

public interface Node<T> {
	List<Node<T>> getChildren();
	T getValue();
}
