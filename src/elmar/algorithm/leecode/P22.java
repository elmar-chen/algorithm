package elmar.algorithm.leecode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import elmar.algorithm.NodeVisitor;
import elmar.algorithm.TreeNode;
import elmar.algorithm.TreeVisitUtil;

public class P22 {
	static int MAX_DEPTH = 10 * 2;

	static class ParenthesisGenerateTreeVisitor implements NodeVisitor<String> {

		@Override
		public void visit(TreeNode<String> node) {
			ParenthesisGenerateTree pgNode = (ParenthesisGenerateTree) node;
			if (pgNode.balance == 0 && pgNode.getValue().length() == MAX_DEPTH)
				System.out.println(pgNode.getValue());

		}

	}

	static class ParenthesisGenerateTree extends TreeNode<String> {

		private int balance;

		public ParenthesisGenerateTree(char val, ParenthesisGenerateTree parent) {
			super((parent == null ? "" : parent.getValue()) + val);
			this.balance = (parent == null ? 0 : parent.balance) + (val == '(' ? 1 : -1);
		}

		@Override
		public Iterator<? extends TreeNode<String>> children() {
			List<ParenthesisGenerateTree> children = new ArrayList<>();
			if (getValue().length() < MAX_DEPTH) {
				children.add(new ParenthesisGenerateTree('(', this));
				if (balance > 0)
					children.add(new ParenthesisGenerateTree(')', this));
			}
			return children.iterator();
		}
	}

	public static void main(String[] args) {
		TreeVisitUtil.dfsVisit(new ParenthesisGenerateTree('(', null), new ParenthesisGenerateTreeVisitor());
	}

}
