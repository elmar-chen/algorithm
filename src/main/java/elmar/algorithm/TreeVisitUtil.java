package elmar.algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeVisitUtil {
	public static <T> void bfsVisit(TrackableTreeNode<T> root, NodeVisitor<T> visitor) {
		Deque<TrackableTreeNode<T>> queue = new LinkedList<>();
		queue.push(root);
		while (!queue.isEmpty()) {
			TrackableTreeNode<T> node = queue.removeFirst();
			if (!node.isVisited()) {
				node.setVisited(true);
				visitor.visit(node);
				Iterator<? extends TrackableTreeNode<T>> children = node.children();
				while (children.hasNext()) {
					TrackableTreeNode<T> child = children.next();
					queue.addLast(child);
				}
			}
		}
	}

	public static <T> void dfsVisit(TreeNode<T> root, NodeVisitor<T> visitor) {
		dfsVisit(root, visitor, true);
	}

	public static <T> void dfsVisit(TreeNode<T> root, NodeVisitor<T> visitor, boolean parentFirst) {
		DFSNodeVisitor<T> dfsVisitor = DFSNodeVisitor.fromNodeVistor(visitor, parentFirst);
		dfsVisit(root, dfsVisitor);
	}

	public static <T> void dfsVisit(TreeNode<T> root, DFSNodeVisitor<T> visitor) {

		Stack<Iterator<? extends TreeNode<T>>> stackIter = new Stack<>();
		Stack<TreeNode<T>> stackNode = new Stack<>();
		stackNode.push(root);
		visitor.visitFirstRound(root);
		stackIter.push(root.children());
		while (!stackNode.isEmpty()) {
			Iterator<? extends TreeNode<T>> nodeIterator = stackIter.peek();
			if (nodeIterator.hasNext()) {
				TreeNode<T> next = nodeIterator.next();
				stackNode.push(next);
				visitor.visitFirstRound(next);
				stackIter.push(next.children());
			} else {
				TreeNode<T> node = stackNode.pop();
				stackIter.pop();
				visitor.visitSecondRound(node);
			}
		}
	}

	static class NumberTreeNode extends TrackableTreeNode<Integer> {
		List<NumberTreeNode> children = new ArrayList<>();

		public NumberTreeNode(int val, NumberTreeNode parent) {
			super(val);
			if (parent != null)
				parent.children.add(this);
		}

		@Override
		public Iterator<NumberTreeNode> children() {
			System.out.println(sbuff + " " + children);
			return children.iterator();
		}

		@Override
		public String toString() {
			return getValue().toString();
		}
	}

	static StringBuffer sbuff = new StringBuffer();

	static DFSNodeVisitor<Integer> visitor2 = new DFSNodeVisitor<Integer>() {

		@Override
		public void visitFirstRound(TreeNode<Integer> node) {
			Integer val = node.getValue();
			sbuff.append(val);
			System.out.println("+" + val + " -> [" + sbuff + "]");
		}

		@Override
		public void visitSecondRound(TreeNode<Integer> node) {
			Integer val = sbuff.charAt(sbuff.length() - 1) - '0';
			sbuff.setLength(sbuff.length() - 1);
			System.out.println("-" + val + " -> [" + sbuff + "]");
		}

	};

	public static void main(String[] args) {
		NumberTreeNode[] nodes = new NumberTreeNode[10];
		nodes[0] = new NumberTreeNode(0, null);
		nodes[1] = new NumberTreeNode(1, nodes[0]);
		nodes[2] = new NumberTreeNode(2, nodes[0]);
		nodes[3] = new NumberTreeNode(3, nodes[1]);
		nodes[4] = new NumberTreeNode(4, nodes[1]);
		nodes[5] = new NumberTreeNode(5, nodes[1]);
		nodes[6] = new NumberTreeNode(6, nodes[2]);
		nodes[7] = new NumberTreeNode(7, nodes[6]);
		nodes[8] = new NumberTreeNode(8, nodes[6]);
		nodes[9] = new NumberTreeNode(9, nodes[6]);

		NodeVisitor<Integer> visitor = new NodeVisitor<Integer>() {
			@Override
			public void visit(TreeNode<Integer> node) {
				// path.push(node.getValue());
				// System.out.println(path);
				System.out.print(node.getValue());
			}

		};

		dfsVisit(nodes[0], visitor2);
		// bfsVisit(nodes[0], visitor);
		// System.out.println();
		// dfsVisit(nodes[0], visitor);
		// System.out.println();
		// dfsVisit(nodes[0], visitor, false);
	}

}
