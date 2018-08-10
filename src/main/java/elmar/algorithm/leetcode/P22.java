package elmar.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import elmar.algorithm.DFSNodeVisitor;
import elmar.algorithm.TreeNode;
import elmar.algorithm.TreeVisitUtil;

public class P22 {

	private int maxDepth;
	ParenthesisGenerateTreeVisitor visitor = new ParenthesisGenerateTreeVisitor();

	public List<String> generateParenthesis(int n) {
		maxDepth = n * 2;

		TreeVisitUtil.dfsVisit(new ParenthesisGenerateTree('('), visitor);
		return visitor.results;
	}

	class ParenthesisGenerateTreeVisitor implements DFSNodeVisitor<Character> {
		List<String> results = new ArrayList<>();
		int balance = 0;
		StringBuffer sbuff = new StringBuffer();

		@Override
		public void visitFirstRound(TreeNode<Character> node) {
			char val = node.getValue();
			sbuff.append(val);
			balance += val == '(' ? 1 : -1;
			if (sbuff.length() == maxDepth && balance == 0)
				results.add(sbuff.toString());

		}

		@Override
		public void visitSecondRound(TreeNode<Character> node) {
			char val = sbuff.charAt(sbuff.length() - 1);
			sbuff.setLength(sbuff.length() - 1);
			balance -= val == '(' ? 1 : -1;
		}

	}

	class ParenthesisGenerateTree extends TreeNode<Character> {

		public ParenthesisGenerateTree(Character val) {
			super(val);
		}

		@Override
		public Iterator<? extends TreeNode<Character>> children() {
			List<ParenthesisGenerateTree> children = new ArrayList<>();
			if (visitor.sbuff.length() < maxDepth) {
				children.add(new ParenthesisGenerateTree('('));
				if (visitor.balance > 0) {
					children.add(new ParenthesisGenerateTree(')'));
				}
			}
			return children.iterator();
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			long t = System.currentTimeMillis();
			List<String> results = new P22().generateParenthesis(i);

			System.out.println(i + "(" + (System.currentTimeMillis() - t) + ")" + results.size());
		}
	}

}
