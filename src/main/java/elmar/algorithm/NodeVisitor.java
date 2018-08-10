package elmar.algorithm;

public interface NodeVisitor<T> {
	void visit(TreeNode<T> node);
}
