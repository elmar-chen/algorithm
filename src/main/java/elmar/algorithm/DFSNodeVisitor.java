package elmar.algorithm;

public interface DFSNodeVisitor<T> {
	public abstract void visitFirstRound(TreeNode<T> node);;

	public abstract void visitSecondRound(TreeNode<T> node);;

	public static <T> DFSNodeVisitor<T> fromNodeVistor(NodeVisitor<T> visitor, boolean parentFirst) {
		return new DFSNodeVisitor<T>() {
			@Override
			public void visitFirstRound(TreeNode<T> node) {
				if (parentFirst)
					visitor.visit(node);
			}

			@Override
			public void visitSecondRound(TreeNode<T> node) {
				if (!parentFirst)
					visitor.visit(node);
			}
		};
	}
}
